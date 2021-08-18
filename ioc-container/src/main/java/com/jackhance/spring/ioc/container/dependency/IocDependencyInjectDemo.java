package com.jackhance.spring.ioc.container.dependency;

import com.jackhance.spring.ioc.container.model.WorkerGroup;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * IOC 容器依赖注入
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
@Configuration
public class IocDependencyInjectDemo {

    public static void main(String[] args) {
        // 通过 xml 配置，启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/ioc-container-dependency-injection.xml");

        // 依赖来源

        // 1. 自定义 Bean
        WorkerGroup workerGroup = beanFactory.getBean("workerGroup", WorkerGroup.class);
        System.out.println("[依赖来源]自定义 Bean : " + workerGroup);

        // 2. 内建依赖 Bean
        ObjectFactory<BeanFactory> beanFactoryObjectFactory = workerGroup.getBeanFactoryObjectFactory();
        BeanFactory beanFactory0 = beanFactoryObjectFactory.getObject();
        System.out.println("[依赖来源]内建依赖 Bean : " + beanFactoryObjectFactory);

        // 3. 容器內建 Bean
        Environment env = workerGroup.getEnvironment();
        System.out.println("[依赖来源]容器內建 Bean : " + env);

        // false ， BeanFactory 非同一个对象？ why？？
        System.out.println("BeanFactory equals  : " + (beanFactory0 == beanFactory));

        // 依赖查找 BeanFactory 失败 org.springframework.beans.factory.NoSuchBeanDefinitionException  ，why？
        System.out.println(beanFactory.getBean(BeanFactory.class));
    }
}
