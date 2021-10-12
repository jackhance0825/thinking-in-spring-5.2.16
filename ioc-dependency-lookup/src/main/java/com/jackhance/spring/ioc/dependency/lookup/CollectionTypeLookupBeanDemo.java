package com.jackhance.spring.ioc.dependency.lookup;

import com.jackhance.spring.ioc.dependency.lookup.annotation.IBean;
import com.jackhance.spring.ioc.dependency.lookup.model.Worker;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ResolvableType;

import java.util.Arrays;
import java.util.Map;

/**
 * 集合类型依赖查找
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class CollectionTypeLookupBeanDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(CollectionTypeLookupBeanDemo.class);

        applicationContext.refresh();

        lookupByType(applicationContext);

        lookupByAnnotation(applicationContext);

        applicationContext.close();
    }

    /**
     * 通过注解查找
     */
    private static void lookupByAnnotation(ListableBeanFactory beanFactory) {
        // 获取标注类型 Bean 名称列表
        String[] beanNamesForAnnotation = beanFactory.getBeanNamesForAnnotation(IBean.class);
        System.out.println("getBeanNamesForAnnotation : " + Arrays.toString(beanNamesForAnnotation));

        // 获取标注类型 Bean 实例列表
        Map<String, Object> beansWithAnnotation = beanFactory.getBeansWithAnnotation(IBean.class);
        System.out.println("getBeansWithAnnotation : " + beansWithAnnotation);

        // 获取指定名称 + 标注类型 Bean 实例
        IBean annotationOnBean = beanFactory.findAnnotationOnBean("worker3", IBean.class);
        System.out.println("findAnnotationOnBean : " + annotationOnBean);
    }

    /**
     * 通过类型查找
     */
    private static void lookupByType(ListableBeanFactory beanFactory) {
        String[] beanNamesForType = beanFactory.getBeanNamesForType(Worker.class);
        System.out.println("getBeanNamesForType : " + Arrays.toString(beanNamesForType));

        ResolvableType resolvableType = ResolvableType.forClass(Worker.class);
        beanNamesForType = beanFactory.getBeanNamesForType(resolvableType);
        System.out.println("getBeanNamesForType : " + Arrays.toString(beanNamesForType));

        Map<String, Worker> beansOfType = beanFactory.getBeansOfType(Worker.class);
        System.out.println("getBeansOfType : " + beansOfType);
    }

    @Bean
    @Primary
    public Worker worker1() {
        return new Worker(1, "jackhance");
    }

    @Bean
    @IBean
    public Worker worker2() {
        return new Worker(2, "joe");
    }

    @Bean
    @IBean
    public Worker worker3() {
        return new Worker(3, "jay");
    }
}
