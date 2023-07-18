package com.chenxin.thymeleaf.controller;

import com.chenxin.thymeleaf.entity.Student;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Created by chenxin on 2023/07/18.
 */
@Controller
@Slf4j
public class ThymeleafController {

    /**
     * 官方网站：https://www.thymeleaf.org/
     * 简介
     * SpringBoot并不推荐使用JSP
     * Thymeleaf是一个跟 Velocity、FreeMarker 类似的模板引擎，它可以完全替代JSP
     * Thymeleaf特点：
     * 1、动静结合
     * Thymeleaf 在有网络和无网络的环境下皆可运行
     * 它可以让美工在浏览器查看页面的静态效果，也可以让程序员在服务器查看带数据的动态页面效果
     * 这是由于它支持 html 原型，然后在 html 标签里增加额外的属性来达到 模板+数据 的展示方式
     * 浏览器解释 html 时会忽略未定义的标签属性，所以 thymeleaf 的模板可以静态地运行
     * 当有数据返回到页面时，Thymeleaf 标签会动态地替换掉静态内容，使页面动态显示
     * 2、开箱即用
     * 它提供标准和Spring标准两种方言，可以直接套用模板实现JSTL、OGNL表达式效果
     * 避免每天套模板、改jstl、改标签的困扰，同时开发人员也可以扩展和创建自定义的方言
     * 3、多方言支持
     * Thymeleaf 提供 Spring 标准方言和一个与 SpringMVC 完美集成的可选模块
     * 可以快速的实现表单绑定、属性编辑器、国际化等功能
     * 4、与SpringBoot完美整合
     * Thymeleaf与 SpringBoot 完美整合，SpringBoot提供了Thymeleaf的默认配置
     * 并为Thymeleaf设置了视图解析器，我们可以像以前操作JSP一样来操作Thymeleaf
     */

    @RequestMapping("base")
    public String base() {
        // http://localhost:8080/base
        return "base";
    }

    @RequestMapping("home")
    public String home() {
        // http://localhost:8080/home
        return "home";
    }

    @RequestMapping("thymeleaf")
    public String thymeleaf(Model model, HttpServletRequest request) {

        model.addAttribute("name", "动态文字");
        request.setAttribute("requestAttribute", "requestValue");

        Student student01 = new Student();
        student01.setName("chenxin");
        student01.setAge(18);
        student01.setAddress("广东深圳");
        // 在模型中存储对象
        model.addAttribute("student", student01);

        Student student02 = new Student();
        student02.setName("learn thymeleaf");
        student02.setAge(20);
        student02.setAddress("广东广州");

        List<Student> allStudent = new ArrayList<>();
        allStudent.add(student01);
        allStudent.add(student02);
        model.addAttribute("allStudent", allStudent);

        model.addAttribute("message", "<h1 style=\"color: red\">颜色</h1>\n");

        /**
         * SpringBoot会自动为 Thymeleaf 注册一个视图解析器ThymeleafViewResolver
         * 并配置了模板文件（html）的位置，与JSP类似的前缀+ 视图名 + 后缀风格
         * 与解析JSP的InternalViewResolver类似，Thymeleaf也会根据前缀和后缀来确定模板文件的位置
         * 默认前缀：classpath:/templates/
         * 默认后缀：.html
         */
        // 如果想要页面返回视图，就直接返回视图文件名即可（不需要文件后缀名，因为SpringBoot已配好视图解析器）
        return "thymeleaf";
    }


}