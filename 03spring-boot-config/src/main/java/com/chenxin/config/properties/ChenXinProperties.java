
package com.chenxin.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 当添加了 @ConfigurationProperties 注解时，该类上的属性就不需要通过 @Value 属性注入
 * 在application.properties文件中进行自动匹配注入，该方式内部是通过 set 方法进行设置，因此需要提供 set 方法
 * prefix表示 application.properties 文件中的 key 前缀
 */
@ConfigurationProperties(prefix = "chenxin")
@Component
@Data
/**
 * @Author Created by chenxin on 2023/07/21.
 */
public class ChenXinProperties {

    private String name;

    private String age;

    private String sex;

    private String githubAddress;
}