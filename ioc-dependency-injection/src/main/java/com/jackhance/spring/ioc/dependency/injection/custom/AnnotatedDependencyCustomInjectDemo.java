package com.jackhance.spring.ioc.dependency.injection.custom;

import com.jackhance.spring.ioc.dependency.injection.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 自定义注解驱动的依赖注入
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
@Configuration
public class AnnotatedDependencyCustomInjectDemo {

    /**
     * 依赖注入
     */
    @Autowired
    private Worker worker;

    /**
     * 依赖注入 + 延迟
     */
    @Autowired
    @Lazy
    private Worker lazyWorker;

    /**
     * 集合类型依赖注入
     */
    @CustomAutowired
    private Map<String, Worker> workerMap;

    @Inject
    private Worker injectWorker;

    @CustomInject
    private Worker customInjectWorker;


    /**
     * 自定义方式一：
     * 覆盖 {@link AutowiredAnnotationBeanPostProcessor} 注册
     *
     * @see AnnotationConfigUtils#registerAnnotationConfigProcessors
     */
//    @Bean(name = AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//
//        // @Autowired + @Inject +  新注解 @CustomInject
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes =
//                new LinkedHashSet<>(Arrays.asList(Autowired.class, Inject.class, CustomInject.class));
//
//        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//
//        return beanPostProcessor;
//    }

    /**
     * 自定义方式二：
     *
     * 自定义注解驱动
     */
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)// 默认 Ordered.LOWEST_PRECEDENCE - 2
    @Scope
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(CustomInject.class);
        return beanPostProcessor;
    }

    @Bean
    public Worker worker1() {
        return buildWorker("worker1", "jackhance", 30);
    }

    @Bean
    @Primary
    public Worker primaryWorker() {
        return buildWorker("primaryWorker", "jackhance", 30);
    }

    public Worker buildWorker(String id, String name, int age) {
        Worker worker = new Worker();
        worker.setId(id);
        worker.setName(name);
        worker.setAge(age);
        return worker;
    }

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类） -> Spring Bean
        applicationContext.register(AnnotatedDependencyCustomInjectDemo.class);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找 AnnotatedDependencyCustomInjectDemo Bean
        AnnotatedDependencyCustomInjectDemo demo = applicationContext.getBean(AnnotatedDependencyCustomInjectDemo.class);

        System.out.println("demo.worker = " + demo.worker);
        System.out.println("demo.lazyWorker = " + demo.lazyWorker);
        System.out.println("demo.workerMap = " + demo.workerMap);
        System.out.println("demo.injectWorker = " + demo.injectWorker);

        System.out.println("demo.customInjectWorker = " + demo.customInjectWorker);

        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

}
