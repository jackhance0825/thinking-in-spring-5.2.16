package com.jackhance.spring.ioc.dependency.source;

import com.jackhance.spring.ioc.dependency.source.model.Worker;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * {@link BeanDefinition} 依赖来源示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class BeanDefinitionSourceDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(BeanDefinitionSourceDemo.class);

        // 方式1： xml
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String configLocation = "classpath:/META-INF/spring-ioc-source-bean-definition.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);

        // 方式3：{@link BeanDefinitionRegistry#registerBeanDefinition}
        registerBeanDefinition(applicationContext);

        // 启动应用上下文
        applicationContext.refresh();

        Map<String, Worker> workers = applicationContext.getBeansOfType(Worker.class);
        System.out.println("workers = " + workers);

        // 关闭应用上下文
        applicationContext.close();
    }

    /**
     * 方式2：注解
     */
    @Bean
    private Worker worker2() {
        return new Worker(2, "jackhance");
    }


    private static void registerBeanDefinition(BeanDefinitionRegistry registry) {
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Worker.class)
                .addPropertyValue("id", 3)
                .addPropertyValue("name", "jackhance")
                .getBeanDefinition();

        registry.registerBeanDefinition("worker3", beanDefinition);
    }

}
