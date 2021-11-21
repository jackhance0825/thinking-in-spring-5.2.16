package com.jackhance.spring.ioc.dependency.source;

import com.jackhance.spring.ioc.dependency.source.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("worker3")
    private Worker worker;

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

        BeanDefinitionSourceDemo demo = applicationContext.getBean(BeanDefinitionSourceDemo.class);
        System.out.println("demo.worker = " + demo.worker);

        // 重新注册 worker3(Spring 不建议)
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Worker.class)
                .addPropertyValue("id", 4)
                .addPropertyValue("name", "jackhance")
                .getBeanDefinition();
        applicationContext.registerBeanDefinition("worker3", beanDefinition);

        // 无法改变已 populate 的 bean 属性
        System.out.println("demo.worker = " + demo.worker);
        System.out.println("demo.worker = " + applicationContext.getBean("worker3"));


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
