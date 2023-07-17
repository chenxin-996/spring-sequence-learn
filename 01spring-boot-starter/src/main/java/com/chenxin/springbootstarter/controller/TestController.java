package com.chenxin.springbootstarter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Created by chenxin on 2023/07/17.
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    String index() {
        return "hello spring boot";
    }
    // http://localhost:8080/test

    /**
     * 引入依赖，运行启动类，即完成构建，告别SSM框架带来的繁琐配置
     */

}