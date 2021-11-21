package com.jackhance.spring.ioc.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * Resolvable Dependency 依赖来源示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    private static <T> T getResolvableDependencyBean(BeanFactory beanFactory, Class<T> clazz) {
        try {
            return beanFactory.getBean(clazz);
        } catch (Throwable e) {
            System.out.println("无法依赖查找" + clazz);
        } finally {
            return null;
        }
    }

    @Autowired
    private String mark;

    @PostConstruct
    private void init() {
        System.out.println("@PostConstruct init : " + this.mark);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册配置类
        applicationContext.register(ResolvableDependencySourceDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> beanFactory.registerResolvableDependency(String.class, "jackhance"));

        // 启动应用上下文
        applicationContext.refresh();

        ResolvableDependencySourceDemo demo = applicationContext.getBean(ResolvableDependencySourceDemo.class);

        BeanFactory bf = getResolvableDependencyBean(applicationContext, BeanFactory.class);
        System.out.println("BeanFactory equals : " + (bf == demo.beanFactory));

        ResourceLoader rl = getResolvableDependencyBean(applicationContext, ResourceLoader.class);
        System.out.println("ResourceLoader equals : " + (rl == demo.resourceLoader));

        ApplicationEventPublisher aep = getResolvableDependencyBean(applicationContext, ApplicationEventPublisher.class);
        System.out.println("ApplicationEventPublisher equals : " + (aep == demo.applicationEventPublisher));

        ApplicationContext c = getResolvableDependencyBean(applicationContext, ApplicationContext.class);
        System.out.println("ApplicationContext equals : " + (c == demo.applicationContext));

        // 关闭应用上下文
        applicationContext.close();
    }
}
