package com.chenxin.mybatis.mapper;

import com.chenxin.mybatis.entity.UserAdmin;
import com.chenxin.mybatis.entity.UserAdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Created by chenxin on 2023/07/23.
 */
public interface UserAdminMapper {
    long countByExample(UserAdminExample example);

    int deleteByExample(UserAdminExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserAdmin row);

    int insertSelective(UserAdmin row);

    List<UserAdmin> selectByExample(UserAdminExample example);

    UserAdmin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UserAdmin row, @Param("example") UserAdminExample example);

    int updateByExample(@Param("row") UserAdmin row, @Param("example") UserAdminExample example);

    int updateByPrimaryKeySelective(UserAdmin row);

    int updateByPrimaryKey(UserAdmin row);
}