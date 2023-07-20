package com.chenxin.config.config;

import com.chenxin.config.properties.ChenXinProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Created by chenxin on 2023/07/21.
 */
@Configuration
public class ChenXinConfig {
    @Autowired
    private ChenXinProperties chenXinProperties;

    public String getGithubAddress() {
        return chenXinProperties.getGithubAddress();
    }
}