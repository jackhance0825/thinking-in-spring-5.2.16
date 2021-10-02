package com.jackhance.spring.ioc.bean;

import com.jackhance.spring.ioc.container.model.Worker;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link BeanDefinition} 构造实例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 * @date 2021/9/25 15:44
 */
public class BeanDefinitionDemo {

    public static void main(String[] args) {

        // 1.通过 BeanDefinitionBuilder 构建
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Worker.class)
                // 属性设置
                .addPropertyValue("id", "worker-1")
                .addPropertyValue("name", "jackhance")
                .addPropertyValue("age", 30)
                // 获取 BeanDefinition 实例
                .getBeanDefinition();



        // 2. 通过 AbstractBeanDefinition 以及派生类构建
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(Worker.class);

        // 属性设置
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues()
                .add("id", "worker-1")
                .add("name", "jackhance")
                .add("age", 30);
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);

    }
}
