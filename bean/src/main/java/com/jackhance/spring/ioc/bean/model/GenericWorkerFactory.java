package com.jackhance.spring.ioc.bean.model;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 通用 {@link com.jackhance.spring.ioc.container.model.Worker} 工厂
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 * @date 2021/9/28 23:47
 */
public class GenericWorkerFactory implements WorkerFactory, InitializingBean, DisposableBean, BeanNameAware {

    private String name;

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct : " + this.name + " 初始化中...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet() : " + this.name + " 初始化中...");
    }

    public void doInit() {
        System.out.println("自定义初始化方法 doInit() : " + this.name + " 初始化中...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy : " + this.name + " 销毁中...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy() : " + this.name + " 销毁中...");
    }

    public void doDestroy() {
        System.out.println("自定义销毁方法 doDestroy() : " + this.name + " 销毁中...");
    }

    @Override
    public void finalize() throws Throwable {
        System.out.println("当前 GenericWorkerFactory 对象 name = " + this.name + " 正在被垃圾回收...");
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}
