package com.chenxin.mybatis.controller;

import com.chenxin.mybatis.entity.UserAdmin;
import com.chenxin.mybatis.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Created by chenxin on 2023/07/23.
 */
@RestController
public class UserAdminController {
    @Autowired
    private UserAdminService userAdminService;

    @GetMapping(value = "/getUserAdminById/{id}")
    public UserAdmin getUserAdminById(@PathVariable Long id) {
        // http://localhost:8080/api/getUserAdminById/1
        return userAdminService.getUserAdminById(id);
    }

    @GetMapping(value = "/getUserAdminByName")
    public List<UserAdmin> getUserAdminByName(String name) {
        // http://localhost:8080/api/getUserAdminByName?name=test
        return userAdminService.getUserAdminByName(name);
    }

}