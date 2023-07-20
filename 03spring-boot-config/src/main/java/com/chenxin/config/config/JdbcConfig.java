package com.chenxin.config.config;

import com.chenxin.config.properties.JdbcProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Created by chenxin on 2023/07/21.
 */
@Configuration
public class JdbcConfig {

    @Autowired
    private JdbcProperties jdbcProperties;

    public String getUserName() {
        return jdbcProperties.getUsername();
    }

}