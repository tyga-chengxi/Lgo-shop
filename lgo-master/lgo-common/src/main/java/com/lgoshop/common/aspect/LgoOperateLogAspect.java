package com.lgoshop.common.aspect;

import com.lgoshop.common.annotation.LgoOperateLog;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.common.log.LgoOperateLogRecord;
import com.lgoshop.common.service.LgoOperateLogService;
import com.lgoshop.common.util.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * LGO-Shop 自研操作日志切面
 * <p>
 * 拦截 {@link LgoOperateLog @LgoOperateLog} 注解，方法执行后
 * 自动构建操作日志记录并写入 Redis List。
 * </p>
 *
 * @author lgo-shop
 */
@Aspect
@Component
public class LgoOperateLogAspect {

    private static final Logger log = LoggerFactory.getLogger(LgoOperateLogAspect.class);

    @Autowired
    private LgoOperateLogService operateLogService;

    @Pointcut("@annotation(com.lgoshop.common.annotation.LgoOperateLog)")
    public void operateLogPointcut() {
    }

    @Around("operateLogPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 构建日志记录（执行前收集元数据）
        LgoOperateLogRecord record = buildRecord(joinPoint);
        if (record == null) {
            return joinPoint.proceed();
        }

        try {
            Object result = joinPoint.proceed();
            // 成功：提取状态码
            record.setCostTime(System.currentTimeMillis() - record.getOperTime());
            if (result instanceof LgoResult) {
                record.setStatus(((LgoResult<?>) result).getCode());
            } else {
                record.setStatus(200);
            }
            operateLogService.record(record);
            return result;
        } catch (Throwable t) {
            // 异常：标记 500
            record.setCostTime(System.currentTimeMillis() - record.getOperTime());
            record.setStatus(500);
            operateLogService.record(record);
            throw t;
        }
    }

    /**
     * 构建日志记录（执行前元数据）
     */
    private LgoOperateLogRecord buildRecord(ProceedingJoinPoint joinPoint) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                return null;
            }
            HttpServletRequest request = attributes.getRequest();

            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            LgoOperateLog annotation = method.getAnnotation(LgoOperateLog.class);

            LgoOperateLogRecord record = new LgoOperateLogRecord();
            record.setOperId(UUID.randomUUID().toString().replace("-", ""));
            record.setTitle(annotation.title());
            record.setBusinessType(annotation.businessType().name());
            record.setMethod(joinPoint.getTarget().getClass().getName() + "." + method.getName() + "()");
            record.setRequestMethod(request.getMethod());
            record.setOperUrl(request.getRequestURL().toString());
            record.setOperIp(RequestUtil.getRequestIp(request));
            record.setOperName(getCurrentUsername());
            record.setOperTime(System.currentTimeMillis());
            return record;
        } catch (Exception e) {
            log.error("【操作日志】构建记录异常", e);
            return null;
        }
    }

    /**
     * 获取当前登录用户名，未登录时返回 "anonymous"
     */
    private static String getCurrentUsername() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return "anonymous";
            }
            Object principal = authentication.getPrincipal();
            if (principal instanceof org.springframework.security.core.userdetails.UserDetails) {
                return ((org.springframework.security.core.userdetails.UserDetails) principal).getUsername();
            }
            return principal.toString();
        } catch (Exception e) {
            return "anonymous";
        }
    }
}
