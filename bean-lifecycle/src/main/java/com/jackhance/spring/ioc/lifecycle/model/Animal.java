package com.jackhance.spring.ioc.lifecycle.model;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 生物
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class Animal implements BeanNameAware, DisposableBean {

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

    @PreDestroy
    private void doPreDestroy() {
        System.out.println(beanName + " @PreDestroy ...");
    }

    @Override
    public void destroy() {
        System.out.println(beanName + " DisposableBean#destroy ...");
    }

    private void doDestroy() {
        System.out.println(beanName + " doDestroy ...");
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
