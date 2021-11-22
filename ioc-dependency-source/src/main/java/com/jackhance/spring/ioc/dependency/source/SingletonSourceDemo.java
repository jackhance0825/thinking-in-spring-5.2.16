package com.jackhance.spring.ioc.dependency.source;

import com.jackhance.spring.ioc.dependency.source.model.Worker;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Singleton 依赖来源示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class SingletonSourceDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册配置类
        applicationContext.register(SingletonSourceDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        // 注册单例对象
        SingletonBeanRegistry singletonBeanRegistry = applicationContext.getBeanFactory();
        String id = "SingletonWorker";
        singletonBeanRegistry.registerSingleton(id, new Worker(10, "jackhance"));

        System.out.println("SingletonWorker is : " + applicationContext.getBean(id));

        // 关闭应用上下文
        applicationContext.close();
    }
}
