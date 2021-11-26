package com.jackhance.spring.ioc.beanscope.model;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 工人
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class Worker implements InitializingBean, DisposableBean, BeanNameAware {

    private long id;

    private String name;

    private String beanName;

    public Worker() {
    }

    public Worker(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hashCode='" + hashCode() + '\'' +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Worker Bean [" + beanName + "] hashCode = " + hashCode() + " 初始化中...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Worker Bean [" + beanName + "] hashCode = " + hashCode() + " 销毁中...");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
