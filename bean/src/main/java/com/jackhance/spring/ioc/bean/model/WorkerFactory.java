package com.jackhance.spring.ioc.bean.model;

import com.jackhance.spring.ioc.container.model.Worker;

/**
 * {@link Worker} 工厂
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 * @date 2021/9/28 23:46
 */
public interface WorkerFactory {

    /**
     * 工厂方法实例化
     */
    default Worker createWorker() {
        return Worker.generateWorker();
    }

}
