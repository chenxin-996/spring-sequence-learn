package com.chenxin.mybatis.service.impl;

import com.chenxin.mybatis.entity.UserAdmin;
import com.chenxin.mybatis.entity.UserAdminExample;
import com.chenxin.mybatis.mapper.UserAdminMapper;
import com.chenxin.mybatis.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Created by chenxin on 2023/07/29.
 */
@Service
public class UserAdminServiceImpl implements UserAdminService {

    @Autowired
    private UserAdminMapper userAdminMapper;

    @Override
    public UserAdmin getUserAdminById(Long id) {
        return userAdminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserAdmin> getUserAdminByName(String name) {
        // Mybatis中Example的用法
        UserAdminExample userAdminExample = new UserAdminExample();
        UserAdminExample.Criteria criteria = userAdminExample.createCriteria();
        criteria.andUsernameLike('%' + name + '%');
        return userAdminMapper.selectByExample(userAdminExample);
    }
}