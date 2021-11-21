package com.jackhance.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * 外部化配置依赖来源示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
@Configuration
@PropertySource(value = "META-INF/spring-ioc-source-external.properties", encoding = "UTF-8")
public class ExternalSourceDemo {

    @Value("${usr.name}")
    String name;

    @Value("${usr.age:1}")
    private int age;

    @Value("${usr.resource}")
    private Resource resource;

    /**
     * 没有配置，默认:广州
     */
    @Value("${usr.location:广州}")
    private String location;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册配置类
        applicationContext.register(ExternalSourceDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        ExternalSourceDemo demo = applicationContext.getBean(ExternalSourceDemo.class);

        System.out.println("name : " + demo.name);
        System.out.println("age : " + demo.age);
        System.out.println("resource : " + demo.resource);
        System.out.println("location : " + demo.location);

        // 关闭应用上下文
        applicationContext.close();
    }
}
