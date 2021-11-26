package com.jackhance.spring.ioc.beanscope;

import com.jackhance.spring.ioc.beanscope.model.Worker;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Map;

/**
 * Prototype 作用域示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class PrototypeScopeDemo {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    private static Worker prototypeWorker() {
        return new Worker(System.nanoTime(), "jackhance");
    }

    @Autowired
    @Qualifier("prototypeWorker")
    private Worker prototypeWorker;

    @Autowired
    @Qualifier("worker1")
    private Worker worker1;

    @Autowired
    private Map<String, Worker> workers;

    @Resource
    private ConfigurableListableBeanFactory beanFactory;

    /**
     * 初始化后回调
     */
    @Bean
    private static BeanPostProcessor beanPostProcessor() {
        return new BeanPostProcessor() {

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                System.out.printf("%s Bean [%s] 初始化后回调... %n", bean.getClass().getName(), beanName);
                return bean;
            }
        };
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(PrototypeScopeDemo.class);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String configLocation = "classpath:/META-INF/spring-bean-scope-prototype.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);

        // 启动应用上下文
        applicationContext.refresh();

        PrototypeScopeDemo demo = applicationContext.getBean(PrototypeScopeDemo.class);

        for (int i = 0; i < 4; i++) {
            // 每次都会产生新的对象
            Worker prototypeWorker = applicationContext.getBean("prototypeWorker", Worker.class);
            System.out.printf("prototypeWorker = %s, 是否与依赖注入是同一对象：%s %n", prototypeWorker, (prototypeWorker == demo.prototypeWorker));

            System.out.println("worker1 = " + demo.worker1);
            System.out.println("workers = " + demo.workers);

            System.out.println("==================================");
        }

        // 关闭应用上下文
        applicationContext.close();

        /**
         * 结论：
         * 1. Prototype Bean 依赖查找与依赖注入，均生成新对象
         * 2. 每个依赖注入的 Prototype Bean 都不一样，是新的对象
         * 3. Spring 管理 Prototype Bean 初始化生命周期
         */
    }

    @PreDestroy
    public void destroy() throws Exception {

        System.out.println("PrototypeScopeDemo Bean 销毁中...");

        this.prototypeWorker.destroy();
        this.worker1.destroy();

        for (Map.Entry<String, Worker> entry : this.workers.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition bd = beanFactory.getBeanDefinition(beanName);

            if (bd.isPrototype()) {
                Worker worker = entry.getValue();
                worker.destroy();
            }
        }
    }
}
