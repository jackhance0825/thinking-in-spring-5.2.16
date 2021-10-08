package com.jackhance.spring.ioc.bean.model;

import com.jackhance.spring.ioc.container.model.Worker;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link Worker} bean 的 {@link FactoryBean} 实现
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class WorkerFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return Worker.generateWorker();
    }

    @Override
    public Class<?> getObjectType() {
        return Worker.class;
    }
}
