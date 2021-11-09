package com.jackhance.spring.ioc.dependency.injection.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

/**
 * 基于 {@link Aware} 接口回调的依赖注入示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
@Configuration
public class AwareInterfaceInjectDemo implements BeanFactoryAware, ApplicationContextAware, ResourceLoaderAware {

    private BeanFactory beanFactory;

    private ApplicationContext applicationContext;

    private ResourceLoader resourceLoader;

    public static void main(String[] args) {
        // 创建应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册注册类
        applicationContext.register(AwareInterfaceInjectDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        AwareInterfaceInjectDemo demo = applicationContext.getBean(AwareInterfaceInjectDemo.class);

        System.out.printf("beanFactory equals : %s, beanFactory = %s%n", (demo.beanFactory == applicationContext.getBeanFactory()), demo.beanFactory);

        System.out.printf("applicationContext equals : %s, applicationContext = %s%n", (demo.applicationContext == applicationContext), demo.applicationContext);

        System.out.println("resourceLoader = " + demo.resourceLoader);

        // 关闭应用上下文
        applicationContext.close();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
