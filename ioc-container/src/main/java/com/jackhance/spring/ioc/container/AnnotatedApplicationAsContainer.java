package com.jackhance.spring.ioc.container;

import com.jackhance.spring.ioc.container.model.Worker;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注解配置 IOC 容器
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
@Configuration
public class AnnotatedApplicationAsContainer {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 使用当前类作为配置类
        context.register(AnnotatedApplicationAsContainer.class);

        // 启动应用上下文
        context.refresh();

        // 查找 Bean
        Worker worker = context.getBean("worker", Worker.class);
        System.out.println(worker);

        // 关闭应用上下文
        context.close();
    }

    /**
     * 通过注解 @Bean 方式，定义Bean
     */
    @Bean
    Worker worker() {
        Worker worker = new Worker();
        worker.setId("1");
        worker.setName("jackhance");
        worker.setAge(30);
        return worker;
    }
}
