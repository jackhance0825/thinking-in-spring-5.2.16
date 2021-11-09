package com.jackhance.spring.ioc.dependency.injection.constructor;

import com.jackhance.spring.ioc.dependency.injection.model.WorkerHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过 xml 资源配置元信息，实现 constructor 方法依赖注入示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class XmlDependencyConstructorInjectDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/ioc-dependency-injection-constructor.xml");

        WorkerHolder workerHolder = applicationContext.getBean("workerHolder", WorkerHolder.class);

        System.out.println(workerHolder);
    }
}
