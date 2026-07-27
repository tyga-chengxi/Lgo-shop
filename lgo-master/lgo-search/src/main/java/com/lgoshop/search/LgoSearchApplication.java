package com.lgoshop.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * LGO-Shop 搜索系统启动类
 */
@SpringBootApplication(scanBasePackages = "com.lgoshop")
public class LgoSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(LgoSearchApplication.class, args);
    }
}
