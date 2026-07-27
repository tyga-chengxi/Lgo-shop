package com.lgoshop.common.annotation;

import com.lgoshop.common.enums.BusinessType;

import java.lang.annotation.*;

/**
 * LGO-Shop 自研操作日志注解
 * <p>
 * 标注于 Controller 方法上，由 {@code LgoOperateLogAspect} 拦截，
 * 方法执行成功后将操作记录写入 Redis List。
 * </p>
 *
 * @author lgo-shop
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LgoOperateLog {

    /**
     * 模块标题（如 "用户管理"、"订单管理"）
     */
    String title() default "";

    /**
     * 业务操作类型
     */
    BusinessType businessType();
}
