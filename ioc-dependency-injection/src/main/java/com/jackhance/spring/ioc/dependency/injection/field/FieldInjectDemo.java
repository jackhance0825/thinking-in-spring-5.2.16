package com.jackhance.spring.ioc.dependency.injection.field;

import com.jackhance.spring.ioc.dependency.injection.model.Worker;
import com.jackhance.spring.ioc.dependency.injection.model.WorkerHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * 字段注入示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
@Configuration
public class FieldInjectDemo {

    /**
     * {@link Autowired} 方式依赖注入
     */
    @Autowired
    private WorkerHolder workerHolder1;

    /**
     * {@link Resource} 方式依赖注入
     */
    @Resource
    private WorkerHolder workerHolder2;

    /**
     * {@link Inject} 方式依赖注入
     */
    @Inject
    private WorkerHolder workerHolder3;


    public static void main(String[] args) {
        // 创建应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册注册类
        applicationContext.register(FieldInjectDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        FieldInjectDemo demo = applicationContext.getBean(FieldInjectDemo.class);

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

    @Bean
    public WorkerHolder workerHolder(Worker worker) {
        return new WorkerHolder(worker);
    }

}
