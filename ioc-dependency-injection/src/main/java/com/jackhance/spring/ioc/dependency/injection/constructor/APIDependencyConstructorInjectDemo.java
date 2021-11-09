package com.jackhance.spring.ioc.dependency.injection.constructor;

import com.jackhance.spring.ioc.dependency.injection.model.WorkerHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 通过 API 配置元信息，实现 constructor 方法依赖注入示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class APIDependencyConstructorInjectDemo {

    public static void main(String[] args) {
        // 创建 IoC 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 读取 xml 配置到 Spring 容器，加载、解析、生成 BeanDefinition
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String location = "classpath:/META-INF/ioc-dependency-injection-constructor.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(location);

        // 通过 API 创建 BeanDefinition
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(WorkerHolder.class)
                // 按照构造器参数顺序添加参数
                .addConstructorArgReference("primaryWorker")
                .getBeanDefinition();

        // 注册 BeanDefinition 到 IoC 容器
        beanFactory.registerBeanDefinition("workerHolderFromAPI", beanDefinition);

        // 依赖查找
        WorkerHolder holder = beanFactory.getBean("workerHolderFromAPI", WorkerHolder.class);
        System.out.println(holder);
    }

}
