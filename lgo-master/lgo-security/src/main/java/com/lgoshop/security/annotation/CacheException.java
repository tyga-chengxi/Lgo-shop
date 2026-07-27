package com.lgoshop.security.annotation;

import java.lang.annotation.*;

/**
 * 自定义缓存异常注解，有该注解的缓存方法会抛出异常
 * Created by lgo-shop.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}
