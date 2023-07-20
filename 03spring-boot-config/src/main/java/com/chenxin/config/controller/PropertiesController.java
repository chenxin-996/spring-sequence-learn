package com.chenxin.config.controller;

import com.chenxin.config.config.ChenXinConfig;
import com.chenxin.config.config.JdbcConfig;
import com.chenxin.config.properties.ChenXinProperties;
import com.chenxin.config.properties.JdbcProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController RestController表示该类下的所有请求都返回json数据，可以省略一个 @Controller 和多个 @ResponseBody 注解
 */
@RestController
/**
 * @Author Created by chenxin on 2023/07/20.
 */
public class PropertiesController {

    @Autowired
    private JdbcProperties jdbcProperties;

    @Autowired
    private JdbcConfig jdbcConfig;

    @Autowired
    private ChenXinProperties chenXinProperties;

    @Autowired
    private ChenXinConfig chenXinConfig;

    @RequestMapping("jdbcProperties")
    public String jdbcProperties() {
        return jdbcProperties.toString();
    }

    @RequestMapping("jdbcConfig")
    public String jdbcConfig() {
        return jdbcConfig.getUserName();
    }


    @RequestMapping("chenXinProperties")
    public String chenXinProperties() {
        return chenXinProperties.toString();
    }

    @RequestMapping("chenXinConfig")
    public String chenXinConfig() {
        return chenXinConfig.getGithubAddress();
    }
    /**
     * 启动浏览器访问以下地址，查看是否正确获取到配置文件的属性值
     * http://localhost:8082/jdbcProperties
     * http://localhost:8082/jdbcConfig
     * http://localhost:8082/chenXinProperties
     * http://localhost:8082/chenXinConfig
     */

}