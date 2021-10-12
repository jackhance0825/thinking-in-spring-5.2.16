package com.jackhance.spring.ioc.dependency.lookup;

import com.jackhance.spring.ioc.dependency.lookup.model.Worker;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.function.Supplier;

/**
 * {@link ObjectProvider} 延迟查找
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ObjectProviderDemo.class);

        applicationContext.refresh();

        // 通过 {@link ObjectProvider} 延迟查找
        lookupByObjectProvider(applicationContext);

        // 通过 {@link ObjectProvider#getIfAvailable(Supplier)} 延迟查找
        lookupByObjectProviderIfAvailable(applicationContext);

        lookupByObjectProviderToStream(applicationContext);

        applicationContext.close();
    }

    private static void lookupByObjectProviderToStream(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider objectProvider = applicationContext.getBeanProvider(String.class);
        objectProvider.stream().forEach(System.out::println);
    }

    /**
     * 通过 {@link ObjectProvider#getIfAvailable(Supplier)} 延迟查找
     */
    private static void lookupByObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider objectProvider = applicationContext.getBeanProvider(Worker.class);
        System.out.println(objectProvider.getIfAvailable(() -> new Worker(1, "jackhance")));

        objectProvider.ifAvailable(System.out::println);
    }

    /**
     * 通过 {@link ObjectProvider} 延迟查找
     */
    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }

    @Bean
    public String message() {
        return "message";
    }

    @Bean
    @Primary
    public String mail() {
        return "jackhance0825@163.com";
    }
}
