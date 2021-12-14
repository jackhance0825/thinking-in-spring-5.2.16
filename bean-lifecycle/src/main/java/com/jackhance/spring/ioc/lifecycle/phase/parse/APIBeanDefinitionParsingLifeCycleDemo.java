package com.jackhance.spring.ioc.lifecycle.phase.parse;

import com.jackhance.spring.ioc.lifecycle.model.Location;
import com.jackhance.spring.ioc.lifecycle.model.Worker;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 基于 API 的{@link BeanDefinition} 解析生命周期示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class APIBeanDefinitionParsingLifeCycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        int loadBeanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();

        // 构建 BeanDefinition
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Worker.class)
                .addPropertyValue("id", 111)
                .addPropertyValue("name", "jackhance")
                .addPropertyValue("location", Location.FOSHAN)
                .addPropertyValue("locations", new Location[]{Location.FOSHAN, Location.GUANGZHOU})
                .getBeanDefinition();

        // 注册 BeanDefinition
        beanFactory.registerBeanDefinition("myWorker", beanDefinition);

        int loadBeanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();

        System.out.println("加载 BeanDefinition 数量：" + (loadBeanDefinitionCountAfter - loadBeanDefinitionCountBefore));

        Worker myWorker = beanFactory.getBean("myWorker", Worker.class);
        System.out.println("myWorker=" + myWorker);
    }
}
