package com.jackhance.spring.ioc.dependency.injection.setter;

import com.jackhance.spring.ioc.dependency.injection.model.WorkerHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 自动注入，实现 setter 方法依赖注入示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class AutowiringDependencySetterInjectDemo {

    public static void main(String[] args) {
        // 创建 IoC 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 读取 xml 配置到 Spring 容器，加载、解析、生成 BeanDefinition
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String location = "classpath:/META-INF/ioc-dependency-injection-setter.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(location);

        // 依赖查找 WorkerHolder
        WorkerHolder workerHolderByName = beanFactory.getBean("workerHolderByName", WorkerHolder.class);
        System.out.println("workerHolderByName=" + workerHolderByName);

        WorkerHolder workerHolderByType = beanFactory.getBean("workerHolderByType", WorkerHolder.class);
        System.out.println("workerHolderByType=" + workerHolderByType);

    }
}
