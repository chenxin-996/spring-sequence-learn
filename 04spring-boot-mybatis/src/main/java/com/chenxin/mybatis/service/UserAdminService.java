package com.chenxin.mybatis.service;

import com.chenxin.mybatis.entity.UserAdmin;

import java.util.List;

/**
 * @Author Created by chenxin on 2023/07/23.
 */
public interface UserAdminService {
    /**
     * 根据用户ID获取用户信息
     *
     * @param id 用户ID
     * @return 用户实体
     */
    UserAdmin getUserAdminById(Long id);

    /**
     * 根据用户名模糊查询用户信息
     *
     * @param name 用户名
     * @return 用户实体集合
     */
    List<UserAdmin> getUserAdminByName(String name);
}