package com.jackhance.spring.ioc.beanscope.webmvc.controller;

import com.jackhance.spring.ioc.beanscope.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页 Spring Web MVC Controller
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
@Controller
public class IndexController {

    /**
     * CGLIB 代理后对象（不变的）
     */
    @Autowired
    private Worker worker;

    @GetMapping("/index")
    public String index(Model model) {
        // JSP EL 变量搜索路径 page -> request -> session -> application(ServletContext)
        // workerObject -> 渲染上下文
        // worker 对象存在 ServletContext，上下文名称：scopedTarget.worker == 新生成 Bean 名称
        model.addAttribute("workerObject", worker);
        return "index";
    }
}
