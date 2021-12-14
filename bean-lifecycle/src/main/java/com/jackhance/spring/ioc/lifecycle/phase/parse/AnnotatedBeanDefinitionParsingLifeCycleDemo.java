package com.jackhance.spring.ioc.lifecycle.phase.parse;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;

/**
 * 基于 java 注解 {@link BeanDefinition} 解析生命周期示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 * @see AnnotatedBeanDefinitionReader
 * @see ConfigurationClassPostProcessor
 */
@Configuration
public class AnnotatedBeanDefinitionParsingLifeCycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 基于 java 注解的 BeanDefinitionReader
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);

        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();

        beanDefinitionReader.register(AnnotatedBeanDefinitionParsingLifeCycleDemo.class);

        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();

        System.out.println("加载 BeanDefinition 数量：" + (beanDefinitionCountAfter - beanDefinitionCountBefore));

        AnnotatedBeanDefinitionParsingLifeCycleDemo demo = beanFactory.getBean(AnnotatedBeanDefinitionParsingLifeCycleDemo.class);
        System.out.println("demo=" + demo);
    }

}
