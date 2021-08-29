package com.jackhance.spring.ioc.container.model;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.Collection;

/**
 * 工人组
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class WorkerGroup {

    private Worker worker;

    private Worker[] workers;

    private Collection<Worker> workerCollection;

    private Environment environment;

    private ObjectFactory<BeanFactory> beanFactoryObjectFactory;

    private BeanFactory beanFactory;

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Worker[] getWorkers() {
        return workers;
    }

    public void setWorkers(Worker[] workers) {
        this.workers = workers;
    }

    public Collection<Worker> getWorkerCollection() {
        return workerCollection;
    }

    public void setWorkerCollection(Collection<Worker> workerCollection) {
        this.workerCollection = workerCollection;
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

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public String toString() {
        return "WorkerGroup{" +
                "worker=" + worker +
                ", workers=" + Arrays.toString(workers) +
                ", workerCollection=" + workerCollection +
                ", environment=" + environment +
                ", beanFactoryObjectFactory=" + beanFactoryObjectFactory +
                ", beanFactory=" + beanFactory +
                '}';
    }
}
