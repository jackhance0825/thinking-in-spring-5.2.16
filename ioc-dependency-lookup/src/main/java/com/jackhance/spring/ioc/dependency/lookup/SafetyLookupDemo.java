package com.jackhance.spring.ioc.dependency.lookup;

import com.jackhance.spring.ioc.dependency.lookup.model.Worker;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 依赖查找安全方式示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class SafetyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(SafetyLookupDemo.class);

        applicationContext.refresh();

        // BeanFactory # getBean 安全性
        getBean(applicationContext);

        // ObjectProvider # getObject 安全性
        getObjectByObjectProvider(applicationContext);

        // ObjectProvider # getIfAvailable 安全性
        getIfAvailableByObjectProvider(applicationContext);

        // ObjectProvider # forEach 安全性
        forEachByObjectProvider(applicationContext);

        // ListableBeanFactory # getBeansOfType 安全性
        getBeansOfType(applicationContext);

        applicationContext.close();
    }

    private static void getBeansOfType(ListableBeanFactory beanFactory) {
        print("getBeansOfType", () -> beanFactory.getBeansOfType(Worker.class));
    }

    private static void forEachByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<Worker> beanProvider = applicationContext.getBeanProvider(Worker.class);
        print("ObjectProvider # forEach", () -> beanProvider.forEach(System.out::println));
    }

    private static void getIfAvailableByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<Worker> beanProvider = applicationContext.getBeanProvider(Worker.class);
        print("ObjectProvider # getIfAvailable", () -> beanProvider.getIfAvailable());
    }

    private static void getObjectByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<Worker> beanProvider = applicationContext.getBeanProvider(Worker.class);
        print("ObjectProvider # getObject", () -> beanProvider.getObject());
    }

    private static void getBean(AnnotationConfigApplicationContext applicationContext) {
        print("BeanFactory # getBean", () -> applicationContext.getBean(Worker.class));
    }

    private static void print(String source, Runnable runnable) {
        System.err.println("==========================================");
        System.err.println("Source from :" + source);
        try {
            runnable.run();
        } catch (BeansException exception) {
            exception.printStackTrace();
        }
    }

}
