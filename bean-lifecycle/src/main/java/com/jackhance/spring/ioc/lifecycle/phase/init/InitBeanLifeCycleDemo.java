package com.jackhance.spring.ioc.lifecycle.phase.init;

import com.jackhance.spring.ioc.lifecycle.model.Creature;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * Bean 初始化生命周期示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class InitBeanLifeCycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 添加 CommonAnnotationBeanPostProcessor 解决 @PostConstruct
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

        // 添加 BeanPostProcessor 实现 CustomInitBeanPostProcessor
        beanFactory.addBeanPostProcessor(new CustomInitBeanPostProcessor());

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/lifecycle-init.xml"};
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("已加载 BeanDefinition 数量：" + beanNumbers);

        // 显式调用 SmartInitializingSingleton#preInstantiateSingletons()
        // 通常由 AbstractApplicationContext#finishBeanFactoryInitialization 启动完成时调用
        // 将已注册的 non-lazy 的 BeanDefinition 初始化成 Spring Bean
        beanFactory.preInstantiateSingletons();

        Creature creature = beanFactory.getBean("creature", Creature.class);
        System.out.println("creature : " + creature);
    }
}
