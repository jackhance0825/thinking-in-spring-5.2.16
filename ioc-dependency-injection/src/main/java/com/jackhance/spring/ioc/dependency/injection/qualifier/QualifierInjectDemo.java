package com.jackhance.spring.ioc.dependency.injection.qualifier;

import com.jackhance.spring.ioc.dependency.injection.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Collection;

/**
 * 基于 {@link Qualifier} 注解依赖注入
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 * @see {@link QualifierAnnotationAutowireCandidateResolver#isAutowireCandidate}
 */
public class QualifierInjectDemo {

    @Autowired
    public Worker worker;

    @Autowired
    @Qualifier("worker4")
    public Worker qualifierWorker;

    @Autowired
    public Collection<Worker> workers;

    @Autowired
    @Qualifier
    public Collection<Worker> qualifierWorkers;

    @Autowired
    @Group
    public Collection<Worker> groupWorkers;

    @Autowired
    @SubGroup
    public Collection<Worker> subGroupWorkers;

    @Autowired
    @GroupX
    public Collection<Worker> groupXWorkers;

    /**
     * worker1、worker2
     * 逻辑分组 @Qualifier ： worker3、worker4
     * 自定义逻辑分组 @Group ： worker5 、 worker6
     * 自定义逻辑分组 @SubGroup ： worker7 、 worker8
     * 自定义逻辑分组 @GroupX ： worker9 、 worker10
     */

    @Bean
    public Worker worker1() {
        return buildWorker("1", "jackhance", 30);
    }

    @Bean
    @Primary
    public Worker worker2() {
        return buildWorker("2", "jackhance", 30);
    }

    /**
     * 基于 {@link Qualifier} 逻辑分组
     */
    @Bean
    @Qualifier
    public Worker worker3() {
        return buildWorker("3", "jackhance", 30);
    }

    /**
     * 基于 {@link Qualifier} 逻辑分组
     */
    @Bean
    @Qualifier
    public Worker worker4() {
        return buildWorker("4", "jackhance", 30);
    }

    /**
     * 自定义逻辑分组
     */
    @Bean
    @Group
    public Worker worker5() {
        return buildWorker("5", "jackhance", 30);
    }

    /**
     * 自定义逻辑分组
     */
    @Bean
    @Group
    public Worker worker6() {
        return buildWorker("6", "jackhance", 30);
    }

    /**
     * 自定义逻辑分组
     */
    @Bean
    @SubGroup
    public Worker worker7() {
        return buildWorker("7", "jackhance", 30);
    }

    /**
     * 自定义逻辑分组
     */
    @Bean
    @SubGroup
    public Worker worker8() {
        return buildWorker("8", "jackhance", 30);
    }

    /**
     * 自定义逻辑分组
     */
    @Bean
    @GroupX
    public Worker worker9() {
        return buildWorker("9", "jackhance", 30);
    }

    /**
     * 自定义逻辑分组
     */
    @Bean
    @GroupX
    public Worker worker10() {
        return buildWorker("10", "jackhance", 30);
    }

    public Worker buildWorker(String id, String name, int age) {
        Worker worker = new Worker();
        worker.setId(id);
        worker.setName(name);
        worker.setAge(age);
        return worker;
    }

    public static void main(String[] args) {
        // 创建应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册注册类
        applicationContext.register(QualifierInjectDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        QualifierInjectDemo demo = applicationContext.getBean(QualifierInjectDemo.class);

        // 输出 worker2 Bean
        System.out.println("demo.worker = " + demo.worker);
        // 输出 worker4 Bean
        System.out.println("demo.qualifierWorker = " + demo.qualifierWorker);
        // 输出 worker1-6
        System.out.println("demo.workers = " + demo.workers);
        // 输出 worker3 worker4 worker5 worker6
        System.out.println("demo.qualifiedUsers = " + demo.qualifierWorkers);
        // 输出 worker5 worker6 worker7 worker8
        System.out.println("demo.groupWorkers = " + demo.groupWorkers);
        // 输出 worker5 worker6 worker7 worker8
        System.out.println("demo.subGroupWorkers = " + demo.subGroupWorkers);
        // 输出 worker9 worker10
        System.out.println("demo.groupXWorkers = " + demo.groupXWorkers);

        // 关闭应用上下文
        applicationContext.close();
    }

}
