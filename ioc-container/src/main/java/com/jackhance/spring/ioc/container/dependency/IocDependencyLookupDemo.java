package com.jackhance.spring.ioc.container.dependency;

import com.jackhance.spring.ioc.container.annotation.Advanced;
import com.jackhance.spring.ioc.container.model.Worker;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * IOC 容器依赖查找
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class IocDependencyLookupDemo {

    public static void main(String[] args) {
        // 根据 xml 配置文件启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/ioc-container-dependency-lookup.xml");

        // 实时依赖查找
        dependencyLookupAtRealTime(beanFactory);

        // 延时依赖查找
        dependencyLookupAtLazyTime(beanFactory);

        // 根据类型查找单个Bean对象
        dependencyLookupSingleByType(beanFactory);

        // 根据类型查找多个Bean对象
        dependencyLookupCollectionByType(beanFactory);

        // 根据注解查找多个Bean对象
        dependencyLookupCollectionByAnnotation(beanFactory);

    }

    /**
     * 根据注解查找多个Bean对象
     */
    private static void dependencyLookupCollectionByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Worker> workers = (Map) listableBeanFactory.getBeansWithAnnotation(Advanced.class);
            System.out.println("根据注解查找多个Bean对象：" + workers);
        }
    }

    /**
     * 根据类型查找多个Bean对象
     */
    private static void dependencyLookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Worker> workers = listableBeanFactory.getBeansOfType(Worker.class);
            System.out.println("根据类型查找多个Bean对象：" + workers);
        }
    }

    /**
     * 根据类型查找单个Bean对象
     */
    private static void dependencyLookupSingleByType(BeanFactory beanFactory) {
        Worker worker = beanFactory.getBean(Worker.class);
        System.out.println("根据类型查找单个：" + worker);
    }

    /**
     * 延时依赖查找
     */
    private static void dependencyLookupAtLazyTime(BeanFactory beanFactory) {
        ObjectProvider<Worker> workerProvider = beanFactory.getBeanProvider(Worker.class);
        Worker worker = workerProvider.getIfAvailable();
        System.out.println("ObjectProvider 延时依赖查找：" + worker);

        ObjectFactory<Worker> myServiceFactory = (ObjectFactory<Worker>) beanFactory.getBean("myServiceFactory");
        worker = myServiceFactory.getObject();
        System.out.println("ObjectFactory 延时依赖查找：" + worker);
    }

    /**
     * 实时依赖查找
     */
    private static void dependencyLookupAtRealTime(BeanFactory beanFactory) {
        Worker worker = (Worker) beanFactory.getBean("worker");
        System.out.println("实时依赖查找：" + worker);
    }
}
