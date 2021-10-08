package com.jackhance.spring.ioc.bean;

import com.jackhance.spring.ioc.bean.model.GenericWorkerFactory;
import com.jackhance.spring.ioc.bean.model.WorkerFactory;
import com.jackhance.spring.ioc.container.model.Worker;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 注册示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
@Import(BeanDefinitionRegisterDemo.Config.class) // 3. 通过 @Import 来进行导入
public class BeanDefinitionRegisterDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        // 6. AnnotatedBeanDefinitionReader 注册配置类 Configuration
        ctx.register(BeanDefinitionRegisterDemo.class);

        // 7. 读取 xml 加载生成 BeanDefinition 到 BeanFactory 容器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(ctx);
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/ioc-bean-definition-register.xml");

        // 8. 外部单例对象注册
        SingletonBeanRegistry beanRegistry = ctx.getBeanFactory();

        WorkerFactory workerFactory = new GenericWorkerFactory();
        beanRegistry.registerSingleton("workerFactory", workerFactory);

        ctx.refresh();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Worker.class)
                .addPropertyValue("id", "worker-01")
                .addPropertyValue("name", "jackhance")
                .addPropertyValue("age", 30);

        // 4.命名 Bean 的注册方式
        ctx.registerBeanDefinition("naming-worker", beanDefinitionBuilder.getBeanDefinition());

        // 5. 非命名 Bean 的注册方法
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), ctx);

        // 按照类型依赖查找
        System.out.println("Config 类型的所有 Beans" + ctx.getBeansOfType(Config.class));
        System.out.println("Worker 类型的所有 Beans" + ctx.getBeansOfType(Worker.class));
        System.out.println("workerFactory " + ctx.getBean("workerFactory", WorkerFactory.class));

        ctx.close();
    }

    /**
     * 2. 通过 @Component 方式
     */
    @Component
    public static class Config {

        /**
         * 1. 通过 @Bean 方式定义
         */
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
