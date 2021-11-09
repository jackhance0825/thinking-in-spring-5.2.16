package com.jackhance.spring.ioc.dependency.injection.constructor;

import com.jackhance.spring.ioc.dependency.injection.model.Worker;
import com.jackhance.spring.ioc.dependency.injection.model.WorkerHolder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 通过 Java 注解配置元信息，实现 constructor 方法依赖注入示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class AnnotationDependencyConstructorInjectDemo {

    public static void main(String[] args) {
        // 创建应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册注册类
        applicationContext.register(AnnotationDependencyConstructorInjectDemo.class);

        // 读取 xml 配置到 Spring 容器，加载、解析、生成 BeanDefinition
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String location = "classpath:/META-INF/ioc-dependency-injection-constructor.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(location);

        // 启动应用上下文
        applicationContext.refresh();

        WorkerHolder holder = applicationContext.getBean("annotatedWorkerHolder", WorkerHolder.class);
        System.out.println(holder);

        // 关闭应用上下文
        applicationContext.close();
    }

    /**
     * 依赖注入 {@link Worker} 对象
     */
    @Bean
    public WorkerHolder annotatedWorkerHolder(Worker worker) {
        return new WorkerHolder(worker);
    }


}
