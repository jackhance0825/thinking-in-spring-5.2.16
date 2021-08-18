package com.jackhance.spring.ioc.container;

import com.jackhance.spring.ioc.container.model.Worker;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * BeanFactory 作为 IOC 容器
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class BeanFactoryAsContainer {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        // 读取 xml 配置，加载 Bean 到  BeanFactory
        String location = "classpath:/META-INF/ioc-container-dependency-lookup.xml";
        int beanDefinitionCount = reader.loadBeanDefinitions(location);

        System.out.println("Bean 加载数量：" + beanDefinitionCount);

        Map<String, Worker> workers = beanFactory.getBeansOfType(Worker.class);
        System.out.println("查找到的所有 Worker : " + workers);
    }
}
