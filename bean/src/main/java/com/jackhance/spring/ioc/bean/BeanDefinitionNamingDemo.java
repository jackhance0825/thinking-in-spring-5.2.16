package com.jackhance.spring.ioc.bean;

import com.jackhance.spring.ioc.container.model.Worker;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 命名示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 * @date 2021/9/27 22:20
 */
public class BeanDefinitionNamingDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        // 读取 xml 加载生成 BeanDefinition 到 BeanFactory 容器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(ctx);
        beanDefinitionReader.loadBeanDefinitions("classpath:META-INF/ioc-bean-definition-naming.xml");

        // 注册配置类 Configuration
        ctx.register(BeanDefinitionNamingDemo.class);

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Worker.class)
                .addPropertyValue("id", "worker-01")
                .addPropertyValue("name", "jackhance")
                .addPropertyValue("age", 30);

        // 命名 Bean 的注册方式
        ctx.registerBeanDefinition("naming-worker", beanDefinitionBuilder.getBeanDefinition());

        // 非命名 Bean 的注册方法
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), ctx);

        ctx.refresh();

        // 按照类型依赖查找
        System.out.println("Config 类型的所有 Beans" + ctx.getBeansOfType(Config.class));
        System.out.println("Worker 类型的所有 Beans" + ctx.getBeansOfType(Worker.class));

        System.out.println("systemA-worker : " + ctx.getBean("systemA-worker", Worker.class));
        System.out.println("systemB-worker : " + ctx.getBean("systemB-worker", Worker.class));

        ctx.close();
    }

    @Component
    public static class Config {

        @Bean(name = {"worker", "jackhance-worker"})
        public Worker worker() {
            Worker worker = new Worker();
            worker.setId("worker-02");
            worker.setName("jackhance");
            worker.setAge(30);
            return worker;
        }
    }
}
