package com.lgoshop.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis-Plus 全局配置
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.lgoshop.mapper")
public class MyBatisPlusConfig {

    /**
     * MP 拦截器
     * 包含分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        paginationInterceptor.setOverflow(true);
        paginationInterceptor.setMaxLimit(500L);
        interceptor.addInnerInterceptor(paginationInterceptor);
        return interceptor;
    }

    /**
     * 全局配置
     */
    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig config = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        // 主键策略：自增
        dbConfig.setIdType(com.baomidou.mybatisplus.annotation.IdType.AUTO);
        // 表前缀
        // dbConfig.setTablePrefix("");
        // 逻辑删除字段
        dbConfig.setLogicDeleteField("delete_status");
        dbConfig.setLogicDeleteValue("1");
        dbConfig.setLogicNotDeleteValue("0");
        config.setDbConfig(dbConfig);
        return config;
    }
}
