package com.jackhance.spring.ioc.dependency.lookup;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * 依赖查找的常规异常示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class LookupExceptionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(LookupExceptionDemo.class);

        // BeanInstantiationException: CharSequence 是一个接口，Bean 定义无法实例化
        /*
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
        applicationContext.registerBeanDefinition("errorCharSequenceBean", builder.getBeanDefinition());
        */

        // BeanCreationException: 初始化方法抛错，Bean 无法实例化
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Model.class);
        applicationContext.registerBeanDefinition("errorModelBean", builder.getBeanDefinition());

        applicationContext.refresh();

        invokeNoUniqueBeanDefinitionException(applicationContext);

        // 关闭应用上下文
        applicationContext.close();
    }

    /**
     * {@link NoUniqueBeanDefinitionException}
     */
    private static void invokeNoUniqueBeanDefinitionException(AnnotationConfigApplicationContext applicationContext) {
        // 由于 Spring 应用上下文存在两个 String 类型的 Bean，通过单一类型查找会抛出异常
        apply("应用上下文存在两个 String 类型的 Bean, 单一查找无法进行", () -> applicationContext.getBean(String.class));
    }

    private static void apply(String op, Runnable r) {
        try {
            r.run();
        } catch (BeansException e) {
            System.err.printf(" 操作：%s，具体原因：%s%n", op, e.getMessage());
        }
    }

    @Bean
    public String bean1() {
        return "1";
    }

    @Bean
    public String bean2() {
        return "2";
    }

    @Bean
    public String bean3() {
        return "3";
    }

    static class Model implements InitializingBean {

        @PostConstruct // JSR-250 CommonAnnotationBeanPostProcessor
        public void init() throws Throwable {
            throw new Throwable("init() : For purposes...");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet() : For purposes...");
        }
    }


}
