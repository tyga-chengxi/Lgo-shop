package com.lgoshop.common.annotation;

import java.lang.annotation.*;

/**
 * LGO-Shop 自研 Web 请求日志注解
 * <p>
 * 标注于 Controller 类或方法上，由 {@code LgoWebLogAspect} 拦截并自动记录：
 * <ul>
 *   <li>请求 URL、HTTP Method、客户端 IP</li>
 *   <li>类方法名、入参</li>
 *   <li>执行耗时（超过 3 秒单独 WARN 标记）</li>
 *   <li>响应成功 / 失败状态</li>
 * </ul>
 * </p>
 *
 * @author lgo-shop
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LgoWebLog {
}
