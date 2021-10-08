package com.jackhance.spring.ioc.bean;

import com.jackhance.spring.ioc.bean.model.GenericWorkerFactory;
import com.jackhance.spring.ioc.bean.model.WorkerFactory;
import com.jackhance.spring.ioc.bean.model.WorkerFactoryBean;
import com.jackhance.spring.ioc.container.model.Worker;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Iterator;
import java.util.ServiceLoader;

import static org.springframework.beans.factory.config.AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE;

/**
 * Bean 实例化示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class BeanDefinitionInstantiationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(BeanDefinitionInstantiationDemo.class);

        // 读取 xml 加载生成 BeanDefinition 到 BeanFactory 容器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/ioc-bean-instantiation.xml");

        applicationContext.refresh();

        // 通过 ServiceLoaderFactoryBean 实例化 Bean
        instantiationByServiceLoaderFactoryBean(applicationContext);

        // 通过 ServiceLoader # load 实例化 Bean
        instantiationByServiceLoader();

        // 通过 AutowireCapableBeanFactory # createBean 实例化 Bean
        instantionByAutowireCapableBeanFactory(applicationContext);

        System.out.println("===============================================================");

        for (String name : applicationContext.getBeanNamesForType(Worker.class)) {
            System.out.println(name);
        }

        applicationContext.close();
    }

    /**
     * 6. 通过 AutowireCapableBeanFactory # createBean 实例化 Bean
     */
    private static void instantionByAutowireCapableBeanFactory(AnnotationConfigApplicationContext applicationContext) {
        System.out.println("============================ AutowireCapableBeanFactory # createBean ===================================");

        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        WorkerFactory workerFactory = (WorkerFactory) beanFactory.createBean(GenericWorkerFactory.class, AUTOWIRE_BY_TYPE, true);

        System.out.println(workerFactory);
    }

    /**
     * 通过 ServiceLoader # load 实例化 Bean
     */
    private static void instantiationByServiceLoader() {
        System.out.println("====================== ServiceLoader # load =========================================");

        ServiceLoader serviceLoader = ServiceLoader.load(WorkerFactory.class);

        for (Iterator<WorkerFactory> it = serviceLoader.iterator(); it.hasNext(); ) {
            WorkerFactory workerFactory = it.next();
            System.out.println("ServiceLoader : " + workerFactory.createWorker());
        }
    }

    /**
     * 5. 通过 ServiceLoaderFactoryBean 实例化 Bean
     */
    private static void instantiationByServiceLoaderFactoryBean(AnnotationConfigApplicationContext applicationContext) {
        System.out.println("================================= ServiceLoaderFactoryBean ==============================");

        ServiceLoader serviceLoader = applicationContext.getBean("workerFactoryServiceLoader", ServiceLoader.class);

        for (Iterator<WorkerFactory> it = serviceLoader.iterator(); it.hasNext(); ) {
            WorkerFactory workerFactory = it.next();
            System.out.println("ServiceLoader : " + workerFactory.createWorker());
        }
    }

    @Bean(name = "worker-create-by-annotated-constructor")
    public Worker workerCreateByAnnotatedConstructor() {
        Worker worker = new Worker();
        worker.setId("pc9528");
        worker.setName("jackhance");
        worker.setAge(30);
        return worker;
    }

    @Bean(name = "worker-create-by-annotated-factory-bean")
    public WorkerFactoryBean workerCreateByAnnotatedFactoryBean() {
        return new WorkerFactoryBean();
    }

}
