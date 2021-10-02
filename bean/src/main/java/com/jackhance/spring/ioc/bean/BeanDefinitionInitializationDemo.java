package com.jackhance.spring.ioc.bean;

import com.jackhance.spring.ioc.bean.model.GenericWorkerFactory;
import com.jackhance.spring.ioc.bean.model.WorkerFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.Map;

/**
 * Bean 初始化示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 * @date 2021/10/2 16:54
 */
public class BeanDefinitionInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(BeanDefinitionInitializationDemo.class);

        // 读取 xml 加载生成 BeanDefinition 到 BeanFactory 容器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/ioc-bean-initialization.xml");

        applicationContext.refresh();

        // 非延迟初始化在 Spring 应用上下文启动完成后，被初始化
        System.out.println("Spring 应用上下文已启动...");

        // 依赖查找 WorkerFactory
        Map<String, WorkerFactory> workerFactoryMap = applicationContext.getBeansOfType(WorkerFactory.class);
        System.out.println(workerFactoryMap);

        System.out.println("Spring 应用上下文准备关闭...");

        // 关闭 Spring 应用上下文
        applicationContext.close();

        System.out.println("Spring 应用上下文已关闭...");
    }


    @Bean(name = "worker-factory-create-by-annotated-non-lazy", initMethod = "doInit", destroyMethod = "doDestroy")
    public WorkerFactory annotatedWorkerFactory() {
        return new GenericWorkerFactory();
    }

    @Bean(name = "worker-factory-create-by-annotated-lazy", initMethod = "doInit", destroyMethod = "doDestroy")
    @Lazy
    public WorkerFactory annotatedLazyWorkerFactory() {
        return new GenericWorkerFactory();
    }

}
