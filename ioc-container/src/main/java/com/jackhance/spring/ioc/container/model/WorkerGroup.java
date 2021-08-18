package com.jackhance.spring.ioc.container.model;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * 工人组
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class WorkerGroup {

    private Worker[] workers;

    private Environment environment;

    private ObjectFactory<BeanFactory> beanFactoryObjectFactory;

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

    @Override
    public String toString() {
        return "WorkerGroup{" +
                "workers=" + Arrays.toString(workers) +
                ", environment=" + environment +
                ", beanFactoryObjectFactory=" + beanFactoryObjectFactory +
                '}';
    }
}
