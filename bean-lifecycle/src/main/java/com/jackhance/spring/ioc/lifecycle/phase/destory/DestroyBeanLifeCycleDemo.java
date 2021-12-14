package com.jackhance.spring.ioc.lifecycle.phase.destory;

import com.jackhance.spring.ioc.lifecycle.model.Animal;
import com.jackhance.spring.ioc.lifecycle.model.Creature;
import com.jackhance.spring.ioc.lifecycle.phase.init.CustomInitBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * Bean 销毁生命周期示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class DestroyBeanLifeCycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 添加 CommonAnnotationBeanPostProcessor 解决 @PostConstruct
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

        // 添加 BeanPostProcessor 实现 CustomInitBeanPostProcessor
        beanFactory.addBeanPostProcessor(new CustomInitBeanPostProcessor());

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/lifecycle-destroy.xml"};
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("已加载 BeanDefinition 数量：" + beanNumbers);

        // 显式调用 SmartInitializingSingleton#preInstantiateSingletons()
        // 通常由 AbstractApplicationContext#finishBeanFactoryInitialization 启动完成时调用
        // 将已注册的 non-lazy 的 BeanDefinition 初始化成 Spring Bean
        beanFactory.preInstantiateSingletons();

        Animal animal = beanFactory.getBean("animal", Animal.class);
        System.out.println("animal : " + animal);

        // 销毁 BeanFactory 中的单例 Bean
        beanFactory.destroySingletons();
    }
}
