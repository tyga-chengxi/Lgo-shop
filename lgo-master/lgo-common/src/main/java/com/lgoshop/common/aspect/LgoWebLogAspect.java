package com.lgoshop.common.aspect;

import com.lgoshop.common.annotation.LgoWebLog;
import com.lgoshop.common.exception.LgoBusinessException;
import com.lgoshop.common.util.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * LGO-Shop 自研 Web 请求日志切面
 * <p>
 * 拦截所有标注 {@link LgoWebLog @LgoWebLog} 的类或方法，自动完成：
 * <ul>
 *   <li><b>请求日志记录</b> — URL、Method、IP、类方法、入参、耗时、状态</li>
 *   <li><b>参数预检</b> — {@code @RequestBody} 空值 / ID 类 {@code @PathVariable} 空值和非正数校验</li>
 *   <li><b>慢请求标记</b> — 超过 3 秒的请求单独输出 WARN 日志</li>
 * </ul>
 * </p>
 *
 * @author lgo-shop
 */
@Aspect
@Component
public class LgoWebLogAspect {

    private static final Logger log = LoggerFactory.getLogger(LgoWebLogAspect.class);

    /**
     * 慢请求阈值（毫秒）
     */
    private static final long SLOW_THRESHOLD_MS = 3000L;

    // ==================== 切点定义 ====================

    /**
     * 匹配类级别或方法级别的 @LgoWebLog 注解
     */
    @Pointcut("@within(com.lgoshop.common.annotation.LgoWebLog) || @annotation(com.lgoshop.common.annotation.LgoWebLog)")
    public void webLogPointcut() {
    }

    // ==================== 环绕通知 ====================

    @Around("webLogPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return joinPoint.proceed();
        }
        HttpServletRequest request = attributes.getRequest();

        // ---------- 1. 参数预检（轻量级，短路上报） ----------
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();
        Object[] args = joinPoint.getArgs();

        String preCheckMsg = preCheckParams(parameters, args);
        if (preCheckMsg != null) {
            log.warn("【参数预检失败】{} - URL: {}", preCheckMsg, request.getRequestURL());
            throw new LgoBusinessException(400, preCheckMsg);
        }

        // ---------- 2. 收集请求元数据 ----------
        String url = request.getRequestURL().toString();
        String httpMethod = request.getMethod();
        String ip = RequestUtil.getRequestIp(request);
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()
                + "." + method.getName();

        // ---------- 3. 执行目标方法并计时 ----------
        long startTime = System.currentTimeMillis();
        Object result;
        boolean success = true;
        try {
            result = joinPoint.proceed();
        } catch (Throwable t) {
            success = false;
            throw t;
        } finally {
            long duration = System.currentTimeMillis() - startTime;

            // ---------- 4. 构建并输出日志 ----------
            StringBuilder sb = new StringBuilder(256);
            sb.append("\n================== LGO Web Log ==================");
            sb.append("\n请求URL    : ").append(url);
            sb.append("\nHTTP方法   : ").append(httpMethod);
            sb.append("\n客户端IP   : ").append(ip);
            sb.append("\n类方法     : ").append(classMethod);
            // 仅在日志可输出时序列化入参，避免 toString() 副作用
            if (log.isInfoEnabled() || log.isWarnEnabled()) {
                sb.append("\n入参       : ").append(argsToString(args));
            }
            sb.append("\n状态       : ").append(success ? "SUCCESS" : "FAILED");
            sb.append("\n耗时       : ").append(duration).append("ms");
            sb.append("\n=================================================");

            if (duration > SLOW_THRESHOLD_MS) {
                log.warn("【慢请求】{}ms | {} {}", duration, httpMethod, url);
                log.warn(sb.toString());
            } else {
                log.info(sb.toString());
            }
        }
        return result;
    }

    // ==================== 参数预检 ====================

    /**
     * 轻量级参数预检，在 {@code @Valid} 校验之前短路上报明显错误的参数。
     * <ul>
     *   <li>{@code @RequestBody} 参数为 {@code null} → 请求体缺失</li>
     *   <li>ID 类 {@code @PathVariable} 参数为空或非正数 → 参数值非法</li>
     * </ul>
     *
     * @param parameters 方法参数声明
     * @param args       实际入参
     * @return 校验失败时的提示消息；{@code null} 表示通过
     */
    private String preCheckParams(Parameter[] parameters, Object[] args) {
        if (args == null) {
            return null;
        }
        for (int i = 0; i < parameters.length && i < args.length; i++) {
            // @RequestBody 空值检测
            if (parameters[i].isAnnotationPresent(RequestBody.class) && args[i] == null) {
                return "请求体不能为空";
            }

            // ID 类 @PathVariable 空值 / 非正数检测
            PathVariable pv = parameters[i].getAnnotation(PathVariable.class);
            if (pv != null && args[i] != null) {
                String paramName = resolvePathVariableName(pv, parameters[i]);
                if (paramName.toLowerCase().contains("id")) {
                    String val = args[i].toString().trim();
                    if (val.isEmpty()) {
                        return "参数 " + paramName + " 不能为空";
                    }
                    try {
                        long id = Long.parseLong(val);
                        if (id <= 0) {
                            return "参数 " + paramName + " 必须为正数";
                        }
                    } catch (NumberFormatException e) {
                        // 非数字 ID（如字符串主键），跳过预检
                    }
                }
            }
        }
        return null;
    }

    /**
     * 解析 {@code @PathVariable} 的参数名
     * <p>value 与 name 互为 {@code @AliasFor}，取其一即可，空则回退到编译器参数名</p>
     */
    private static String resolvePathVariableName(PathVariable pv, Parameter param) {
        String name = pv.value();
        if (name.isEmpty()) {
            name = param.getName();
        }
        return name;
    }

    // ==================== 辅助方法 ====================

    /**
     * 将参数数组转为可读字符串，单个参数内容超过 500 字符时截断。
     */
    private static String argsToString(Object[] args) {
        if (args == null || args.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null) {
                sb.append("null");
            } else {
                String str = args[i].toString();
                if (str.length() > 500) {
                    str = str.substring(0, 500) + "...(truncated)";
                }
                sb.append(args[i].getClass().getSimpleName()).append(":").append(str);
            }
            if (i < args.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
