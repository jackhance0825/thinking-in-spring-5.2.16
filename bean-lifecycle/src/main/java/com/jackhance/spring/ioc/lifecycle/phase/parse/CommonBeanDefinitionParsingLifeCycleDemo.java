package com.jackhance.spring.ioc.lifecycle.phase.parse;

import com.jackhance.spring.ioc.lifecycle.model.Worker;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * 基于常规资源 {@link BeanDefinition} 解析生命周期示例
 * <p>
 * 常规资源：groovy、xml、properties
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 * @see AbstractBeanDefinitionReader
 */
public class CommonBeanDefinitionParsingLifeCycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于 properties 资源的 BeanDefinitionReader
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

        String resourceLocation = "META-INF/lifecycle-parse-metadata.properties";

        Resource resource = new ClassPathResource(resourceLocation);
        // 资源指定编码 UTF-8
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

        // 从 properties 资源读取元信息
        int loadBeanDefinitionCount = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("加载 BeanDefinition 数量：" + loadBeanDefinitionCount);

        Worker myWorker = beanFactory.getBean("myWorker", Worker.class);
        System.out.println("myWorker=" + myWorker);
    }
}
