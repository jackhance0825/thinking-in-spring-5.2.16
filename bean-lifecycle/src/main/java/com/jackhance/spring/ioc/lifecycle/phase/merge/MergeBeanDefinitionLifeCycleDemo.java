package com.jackhance.spring.ioc.lifecycle.phase.merge;

import com.jackhance.spring.ioc.lifecycle.model.Worker;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * 合并 {@link BeanDefinition} 生命周期示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 * @see AbstractBeanFactory#getMergedBeanDefinition
 */
public class MergeBeanDefinitionLifeCycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String configLocation = "META-INF/lifecycle-merge.xml";

        Resource resource = new ClassPathResource(configLocation);

        EncodedResource encodedResource = new EncodedResource(resource);

        int beanDefinitionCount = xmlBeanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("加载 BeanDefinition 数量：" + beanDefinitionCount);

        Worker superWorker = beanFactory.getBean("superWorker", Worker.class);
        System.out.println("superWorker : " + superWorker);

        // 依赖查找 worker , worker 覆盖了 superWorker 的属性
        Worker worker = beanFactory.getBean("worker", Worker.class);
        System.out.println("worker : " + worker);
    }
}
