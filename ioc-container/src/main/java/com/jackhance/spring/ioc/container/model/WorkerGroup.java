package com.jackhance.spring.ioc.container.model;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * 劳动者组
 *
 * @author jackhance
 * @date 2021/8/17 0:36
 */
public class WorkerGroup {

    private Worker[] workers;

    private Environment environment;

    private ObjectFactory<BeanFactory> beanFactoryObjectFactory;

    private ObjectProvider<ApplicationContext> applicationContextObjectProvider;

    public Worker[] getWorkers() {
        return workers;
    }

    public void setWorkers(Worker[] workers) {
        this.workers = workers;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public ObjectFactory<BeanFactory> getBeanFactoryObjectFactory() {
        return beanFactoryObjectFactory;
    }

    public void setBeanFactoryObjectFactory(ObjectFactory<BeanFactory> beanFactoryObjectFactory) {
        this.beanFactoryObjectFactory = beanFactoryObjectFactory;
    }

    public ObjectProvider<ApplicationContext> getApplicationContextObjectProvider() {
        return applicationContextObjectProvider;
    }

    public void setApplicationContextObjectProvider(ObjectProvider<ApplicationContext> applicationContextObjectProvider) {
        this.applicationContextObjectProvider = applicationContextObjectProvider;
    }
}
