package com.chenxin.mybatisplus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Created by chenxin on 2023/09/02.
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        /**
         * 乐观锁插件
         * 在进行数据更新时，会自动对version字段值进行填充
         * 乐观锁原理
         * 使用一个额外的字段version，取出记录时，获取当前version值
         * 更新时，进行比对（如果比对成功才可以修改），然后再把version值+1
         */
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}