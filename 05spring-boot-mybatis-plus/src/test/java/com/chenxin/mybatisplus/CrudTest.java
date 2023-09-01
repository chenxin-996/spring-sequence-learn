package com.chenxin.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenxin.mybatisplus.entity.UserCustomer;
import com.chenxin.mybatisplus.mapper.UserCustomerMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * mybatis-plus 增删改查基础用法
 *
 * @Author Created by chenxin on 2023/09/02.
 */
@SpringBootTest
public class CrudTest {
    @Autowired
    private UserCustomerMapper mapper;

    /**
     * 新增
     */
    @Test
    public void insert() {
        UserCustomer userCustomer = new UserCustomer();
        userCustomer.setAge(22);
        userCustomer.setName("chenxin");
        userCustomer.setEmail("188888888@qq.com");
        assertThat(mapper.insert(userCustomer)).isGreaterThan(0);
        // 添加成功，直接拿到新增的 ID
        assertThat(userCustomer.getId()).isNotNull();
    }

    /**
     * 删除
     */
    @Test
    public void delete() {
        assertThat(mapper.deleteById(3L)).isGreaterThan(0);
        assertThat(mapper.delete(new QueryWrapper<UserCustomer>()
                .lambda().eq(UserCustomer::getName, "chenxin996"))).isGreaterThan(0);
    }

    /**
     * 更新
     */
    @Test
    public void update() {
        UserCustomer userCustomer = new UserCustomer();
        userCustomer.setId(1L);
        userCustomer.setName("chenxin-996");
        userCustomer.setEmail("188888666@qq.com");
        assertThat(mapper.updateById(userCustomer)).isGreaterThan(0);
        assertThat(mapper.selectById(1L).getEmail()).isEqualTo("188888666@qq.com");

        UserCustomer customer = new UserCustomer();
        customer.setName("mybatis-plus");
        assertThat(
                mapper.update(
                        customer,
                        Wrappers.<UserCustomer>lambdaUpdate()
                                .set(UserCustomer::getAge, 18)
                                .eq(UserCustomer::getId, 5)
                )
        ).isGreaterThan(0);
        UserCustomer user = mapper.selectById(5);
        assertThat(user.getAge()).isEqualTo(18);
        assertThat(user.getName()).isEqualTo("mybatis-plus");

        mapper.update(
                null,
                Wrappers.<UserCustomer>lambdaUpdate().set(UserCustomer::getEmail, null).eq(UserCustomer::getId, 1)
        );
        user = mapper.selectById(1);
        assertThat(user.getEmail()).isNull();
        assertThat(user.getName()).isEqualTo("chenxin-996");
    }

    /**
     * 乐观锁测试
     */
    @Test
    public void updateOptimisticLock() {
        /**
         * 持的数据类型只有  int,Integer,long,Long,Date,Timestamp,LocalDateTime
         * 整数类型下  newVersion = oldVersion + 1
         * newVersion 会回写到 entity 中
         * 仅支持 updateById(id) 与 update(entity, wrapper) 方法
         * 在 update(entity, wrapper) 方法下, wrapper 不能复用!!!
         */
        UserCustomer userCustomer = mapper.selectById(1);
        userCustomer.setName("chenxin");
        // 执行更新时， set version = newVersion where version = oldVersion
        assertThat(mapper.updateById(userCustomer)).isGreaterThan(0);
    }

    /**
     * 查询
     */
    @Test
    public void select() {
        assertThat(mapper.selectById(2L).getEmail()).isEqualTo("188888888@qq.com");
        UserCustomer user = mapper.selectOne(new QueryWrapper<UserCustomer>().lambda().eq(UserCustomer::getId, 2L));
        assertThat(user.getName()).isEqualTo("chenxin");
        assertThat(user.getAge()).isEqualTo(22);

        LambdaQueryWrapper<UserCustomer> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.select(UserCustomer::getId);
        mapper.selectList(queryWrapper)
                .forEach(x -> {
                    assertThat(x.getId()).isNotNull();
                    assertThat(x.getEmail()).isNull();
                    assertThat(x.getName()).isNull();
                    assertThat(x.getAge()).isNull();
                });

        mapper.selectList(new QueryWrapper<UserCustomer>().select("id", "name"))
                .forEach(x -> {
                    assertThat(x.getId()).isNotNull();
                    assertThat(x.getEmail()).isNull();
                    assertThat(x.getName()).isNotNull();
                    assertThat(x.getAge()).isNull();
                });
    }

    /**
     * 排序
     */
    @Test
    public void orderBy() {
        List<UserCustomer> users1 = mapper.selectList(Wrappers.<UserCustomer>query().orderByAsc("age"));
        assertThat(users1).isNotEmpty();
        // 多字段排序
        List<UserCustomer> users2 = mapper.selectList(Wrappers.<UserCustomer>query().orderByAsc("age", "name"));
        assertThat(users2).isNotEmpty();
        // 先按age升序排列，age相同再按name降序排列
        List<UserCustomer> users3 = mapper.selectList(Wrappers.<UserCustomer>query().orderByAsc("age").orderByDesc("name"));
        assertThat(users3).isNotEmpty();
    }

    /**
     * 查询键值对集合
     */
    @Test
    public void selectMaps() {
        List<Map<String, Object>> mapList = mapper.selectMaps(Wrappers.<UserCustomer>query().orderByAsc("age"));
        assertThat(mapList).isNotEmpty();
        assertThat(mapList.get(0)).isNotEmpty();
        System.out.println(mapList.get(0));
    }

    /**
     * 分页查询键值对集合
     */
    @Test
    public void selectMapsPage() {
        IPage<Map<String, Object>> page = mapper.selectMapsPage(new Page<>(1, 5), Wrappers.<UserCustomer>query().orderByAsc("age"));
        assertThat(page).isNotNull();
        assertThat(page.getRecords()).isNotEmpty();
        assertThat(page.getRecords().get(0)).isNotEmpty();
        System.out.println(page.getRecords().get(0));
    }

    /**
     * 函数使用
     */
    @Test
    public void selectMaxId() {
        QueryWrapper<UserCustomer> wrapper = new QueryWrapper<>();
        wrapper.select("max(id) as id");
        UserCustomer user = mapper.selectOne(wrapper);
        System.out.println("maxId=" + user.getId());
        List<UserCustomer> users = mapper.selectList(Wrappers.<UserCustomer>lambdaQuery().orderByDesc(UserCustomer::getId));
        Assertions.assertEquals(user.getId().longValue(), users.get(0).getId().longValue());
    }

    /**
     * 分组
     */
    @Test
    public void group() {
        QueryWrapper<UserCustomer> wrapper = new QueryWrapper<>();
        wrapper.select("age, count(*)").groupBy("age");
        List<Map<String, Object>> mapList = mapper.selectMaps(wrapper);
        for (Map<String, Object> map : mapList) {
            System.out.println(map);
        }

        LambdaQueryWrapper<UserCustomer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(UserCustomer::getAge)
                .groupBy(UserCustomer::getAge)
                .orderByAsc(UserCustomer::getAge);
        for (UserCustomer user : mapper.selectList(lambdaQueryWrapper)) {
            System.out.println(user);
        }
    }

    /**
     * 字段别名，赋值实体类中非数据库字段
     */
    @Test
    public void tableFieldExist() {
        QueryWrapper<UserCustomer> wrapper = new QueryWrapper<>();
        wrapper.select("age, count(age) as count")
                .groupBy("age");
        List<UserCustomer> list = mapper.selectList(wrapper);
        list.forEach(System.out::println);
        list.forEach(x -> {
            Assertions.assertNull(x.getId());
            Assertions.assertNotNull(x.getAge());
            Assertions.assertNotNull(x.getCount());
        });
        UserCustomer userCustomer = mapper.selectById(1L);
        Assertions.assertNotNull(userCustomer);
    }
}