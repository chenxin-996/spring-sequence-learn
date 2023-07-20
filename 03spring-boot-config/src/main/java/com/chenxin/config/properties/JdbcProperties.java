package com.chenxin.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * 加载指定外部属性文件
 */
@PropertySource("classpath:jdbc.properties")
@Data
@Configuration
/**
 * @Author Created by chenxin on 2023/07/20.
 */
public class JdbcProperties {
    /**
     * @Value 属性注入
     */
    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    /**
     * @Bean 用法：声明在方法上，将方法的返回值加入Bean（springIOC）容器，代替<bean>标签，方法名为bean的key值
     * 方法的返回值就是一个 bean 对象
     * 就可以使用 @Autowired 进行注入
     * 如果没有填写 @Bean 注解，那么该配置就不会交由 springboot 管理
     */
    @Bean
    public DataSource dataSourceTest() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}