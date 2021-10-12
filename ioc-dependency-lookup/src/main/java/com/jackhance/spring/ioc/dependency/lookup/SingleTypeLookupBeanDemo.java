package com.jackhance.spring.ioc.dependency.lookup;

import com.jackhance.spring.ioc.dependency.lookup.model.Worker;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.ResolvableType;

/**
 * 单一类型依赖查找示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
@Configuration
public class SingleTypeLookupBeanDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(SingleTypeLookupBeanDemo.class);

        applicationContext.refresh();

        lookupByName(applicationContext);

        lookupByType(applicationContext);

        lookupByNameAndType(applicationContext);

        applicationContext.close();
    }

    /**
     * 根据 Bean 名称 + 类型查找:
     */
    private static void lookupByNameAndType(BeanFactory beanFactory) {
        System.out.println("根据 Bean 名称 + 类型查找:");

        Worker worker1 = beanFactory.getBean("worker1", Worker.class);
        System.out.println("worker1 : " + worker1);

        System.out.println("=================================================");
    }

    /**
     * 根据 Bean 类型查找
     */
    private static void lookupByType(BeanFactory beanFactory) {
        // 根据 Bean 类型查找
        System.out.println("根据 Bean 类型查找:");

        // 实时查找
        Worker workerByType = beanFactory.getBean(Worker.class);
        System.out.println("<实时查找> worker : " + workerByType);

        Worker workerArgsByType = (Worker) beanFactory.getBean(Worker.class, 3, "jay");
        System.out.println("<实时查找> worker from args : " + workerArgsByType);

        // 延迟查找
        ObjectProvider<Worker> beanProvider = beanFactory.getBeanProvider(Worker.class);
        System.out.println("<延迟查找> beanProvider form class : " + beanProvider.getIfAvailable());

        ResolvableType resolvableType = ResolvableType.forClass(Worker.class);
        beanProvider = beanFactory.getBeanProvider(resolvableType);
        System.out.println("<延迟查找> beanProvider form ResolvableType : " + beanProvider.getIfAvailable());

        System.out.println("=================================================");
    }

    /**
     * 根据 Bean 名称查找
     */
    private static void lookupByName(BeanFactory beanFactory) {
        System.out.println("根据 Bean 名称查找:");

        Worker worker1 = (Worker) beanFactory.getBean("worker1");
        System.out.println("worker1 : " + worker1);

        Worker worker2 = (Worker) beanFactory.getBean("worker2", 3, "jay");
        System.out.println("worker2 : " + worker2);

        System.out.println("=================================================");
    }

    @Bean
    public Worker worker1() {
        return new Worker(1, "jackhance");
    }

    @Bean
    @Primary
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Worker worker2(int id, String name) {
        return new Worker(id, name);
    }

    @Bean
    public int id(){
        return 2;
    }

    @Bean
    public String name() {
        return "kobe";
    }
}
