package com.jackhance.spring.ioc.dependency.injection.method;

import com.jackhance.spring.ioc.dependency.injection.field.FieldInjectDemo;
import com.jackhance.spring.ioc.dependency.injection.model.Worker;
import com.jackhance.spring.ioc.dependency.injection.model.WorkerHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.Arrays;

/**
 * 方法注入示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
@Configuration
public class MethodInjectDemo {

    private WorkerHolder workerHolder1;

    private WorkerHolder workerHolder2;

    private WorkerHolder workerHolder3;

    /**
     * {@link Autowired} 方式依赖注入
     */
    @Autowired
    public void init1(Worker worker) {
        this.workerHolder1 = new WorkerHolder(worker);
    }

    /**
     * {@link Resource} 方式依赖注入
     */
    @Resource
    public void init2(Worker worker) {
        this.workerHolder2 = new WorkerHolder(worker);
    }

    /**
     * {@link Inject} 方式依赖注入
     */
    @Inject
    public void init3(Worker worker) {
        this.workerHolder3 = new WorkerHolder(worker);
    }

    /**
     * {@link Bean} 方式依赖注入，并注册 workerHolder 实例到 Spring 容器
     */
    @Bean
    public WorkerHolder workerHolder(Worker worker) {
        return new WorkerHolder(worker);
    }

    public static void main(String[] args) {
        // 创建应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册注册类
        applicationContext.register(MethodInjectDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        String[] beanNames = applicationContext.getBeanNamesForType(WorkerHolder.class);
        System.out.println("存在类型为 WorkerHolder 的 Bean：" +Arrays.toString(beanNames));

        MethodInjectDemo demo = applicationContext.getBean(MethodInjectDemo.class);
        System.out.printf("workerHolder1 = %s%n", demo.workerHolder1);
        System.out.printf("workerHolder2 = %s%n", demo.workerHolder2);
        System.out.printf("workerHolder3 = %s%n", demo.workerHolder3);

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
