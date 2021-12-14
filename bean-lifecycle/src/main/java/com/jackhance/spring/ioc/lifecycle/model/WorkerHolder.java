package com.jackhance.spring.ioc.lifecycle.model;

/**
 * {@link Worker} 持有者
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class WorkerHolder {
    private Worker worker;
    private String message;

    public WorkerHolder(Worker worker) {
        this.worker = worker;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WorkerHolder{" +
                "worker=" + worker +
                ", message='" + message + '\'' +
                '}';
    }
}
