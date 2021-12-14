package com.jackhance.spring.ioc.lifecycle.model;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;

import javax.annotation.PostConstruct;

/**
 * 生物
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class Creature implements InitializingBean, BeanNameAware, SmartInitializingSingleton {

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * bean 的 id 名称
     */
    private String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @PostConstruct
    private void doInit() {
        System.out.println(beanName + " @PostConstruct ...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(beanName + " InitializingBean#afterPropertiesSet ...");
    }

    private void init() {
        System.out.println(beanName + " init ...");
    }

    @Override
    public void afterSingletonsInstantiated() {
        System.out.println(beanName + " SmartInitializingSingleton#afterSingletonsInstantiated ...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

}
