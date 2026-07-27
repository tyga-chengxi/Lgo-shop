package com.lgoshop.security.config;

import com.lgoshop.common.config.BaseRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Redis相关配置
 * Created by lgo-shop.
 */
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {

}
