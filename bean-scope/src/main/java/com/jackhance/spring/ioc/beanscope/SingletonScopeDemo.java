package com.jackhance.spring.ioc.beanscope;

import com.jackhance.spring.ioc.beanscope.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Collection;

/**
 * singleton 作用域示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class SingletonScopeDemo {

    /**
     * 默认 singleton,
     * {@code @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)} 可不指定
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    private static Worker singletonWorker() {
        return new Worker(System.nanoTime(), "jackhance");
    }

    @Autowired
    @Qualifier("singletonWorker")
    private Worker singletonWorker;

    @Autowired
    @Qualifier("worker1")
    private Worker worker1;

    @Autowired
    @Qualifier("worker2")
    private Worker worker2;

    @Autowired
    private Collection<Worker> workers;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(SingletonScopeDemo.class);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String configLocation = "classpath:/META-INF/spring-bean-scope-singleton.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);

        // 启动应用上下文
        applicationContext.refresh();

        SingletonScopeDemo demo = applicationContext.getBean(SingletonScopeDemo.class);

        for (int i = 0; i < 4; i++) {
            // singletonWorker 是共享 Bean 对象
            Worker singletonUser = applicationContext.getBean("singletonWorker", Worker.class);
            System.out.printf("singletonWorker = %s, 是否与依赖注入是同一对象：%s %n", singletonUser, (singletonUser== demo.singletonWorker));

            System.out.println("worker1 = " + demo.worker1);
            System.out.println("worker2 = " + demo.worker2);
            System.out.println("workers = " + demo.workers);

            System.out.println("==================================");
        }

        // 关闭应用上下文
        applicationContext.close();

        /**
         * 结论：
         * 1. Singleton Bean 依赖查找与依赖注入，均属同一 Bean
         * 2. 依赖注入到集合类型对象中，Singleton Bean 只会存在一个
         * 3. Spring 管理 Singleton Bean 完整的生命周期
         */
    }
}
