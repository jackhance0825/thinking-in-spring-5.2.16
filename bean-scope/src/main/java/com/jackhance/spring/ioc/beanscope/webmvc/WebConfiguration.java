package com.jackhance.spring.ioc.beanscope.webmvc;

import com.jackhance.spring.ioc.beanscope.model.Worker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Web MVC 配置类
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
@Configuration
public class WebConfiguration {

    @Bean
//    @RequestScope
//    @SessionScope
    @ApplicationScope
    public Worker worker() {
        return new Worker(1, "jackhance");
    }

}
