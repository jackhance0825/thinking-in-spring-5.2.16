package com.jackhance.spring.ioc.dependency.injection.model;

/**
 * {@link Worker} Holder 类
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class WorkerHolder {

    private Worker worker;

    public WorkerHolder() {
    }

    public WorkerHolder(Worker worker) {
        this.worker = worker;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }


    @Override
    public String toString() {
        return "WorkerHolder{" +
                "worker=" + worker +
                '}';
    }
}
