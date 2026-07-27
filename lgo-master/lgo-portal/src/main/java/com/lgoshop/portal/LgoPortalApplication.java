package com.lgoshop.portal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * LGO-Shop 前台商城系统启动类
 */
@SpringBootApplication(scanBasePackages = "com.lgoshop")
@MapperScan("com.lgoshop.portal.dao")
public class LgoPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(LgoPortalApplication.class, args);
    }

}
