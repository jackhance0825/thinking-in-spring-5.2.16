package com.jackhance.spring.ioc.dependency.injection.lazy;

import com.jackhance.spring.ioc.dependency.injection.model.Worker;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * 延迟依赖注入示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class LazyInjectDemo {

    /**
     * 实时注入
     */
    @Autowired
    private Worker worker;

    /**
     * {@link Autowired} {@link Lazy}延迟注入
     */
    @Autowired
    @Lazy
    private Worker lazyWorker1;

    /**
     * {@link Inject} {@link Lazy}延迟注入
     */
    @Inject
    @Lazy
    private Worker lazyWorker2;

    /**
     * {@link Resource} {@link Lazy}延迟注入
     */
    @Resource
    @Lazy
    private Worker lazyWorker3;

    /**
     * {@link ObjectProvider} 延迟注入
     */
    @Autowired
    private ObjectProvider<Worker> objectProvider;

    /**
     * {@link ObjectFactory} 延迟注入
     */
    @Autowired
    private ObjectFactory<Worker> objectFactory;


    public static void main(String[] args) {
        // 创建应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册注册类
        applicationContext.register(LazyInjectDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        LazyInjectDemo demo = applicationContext.getBean(LazyInjectDemo.class);

        System.out.printf("worker = %s, class=%s %n", demo.worker, demo.worker.getClass());

        // 延迟注入代理类
        System.out.printf("lazyWorker1 = %s, class=%s %n", demo.lazyWorker1, demo.lazyWorker1.getClass());
        System.out.printf("lazyWorker2 = %s, class=%s %n", demo.lazyWorker2, demo.lazyWorker2.getClass());
        System.out.printf("lazyWorker3 = %s, class=%s %n", demo.lazyWorker3, demo.lazyWorker3.getClass());

        System.out.printf("objectProvider = %s%n", demo.objectProvider.getIfAvailable());
        // 不安全，丢失匹配时，会抛异常 NoSuchBeanDefinitionException
        System.out.printf("objectFactory = %s%n", demo.objectFactory.getObject());

        // 关闭应用上下文
        applicationContext.close();
    }

    @Bean
    public Worker worker() {
        Worker worker = new Worker();
        worker.setId("1");
        worker.setName("jackhance");
        worker.setAge(30);
        return worker;
    }
}
