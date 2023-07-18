package com.chenxin.springbootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
public class SpringBootStarterApplication {

    // 官网文档地址：https://spring.io/projects/spring-boot
    public static void main(String[] args) {
        // Spring Boot 的嵌入式 Apache Tomcat 服务器充当 Web 服务器，默认端口号 8080
        SpringApplication.run(SpringBootStarterApplication.class, args);
    }

}