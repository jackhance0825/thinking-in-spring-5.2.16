package com.jackhance.spring.ioc.lifecycle.phase.instantiation;

import com.jackhance.spring.ioc.lifecycle.model.Location;
import com.jackhance.spring.ioc.lifecycle.model.Worker;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.util.Objects;

/**
 * 自定义 {@link InstantiationAwareBeanPostProcessor}
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class CustomInstantiationBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (Objects.equals("superWorker", beanName) && Objects.equals(Worker.class, beanClass)) {
            // 替换 superWorker ，跳过 Aware 回调、populate 属性填充、初始化
            return new Worker();
        }

        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (Objects.equals("worker", beanName) && Objects.equals(Worker.class, bean.getClass())) {
            Worker worker = (Worker) bean;
            worker.setId(4);
            worker.setName("jackhance-04");
            worker.setLocation(Location.GUANGZHOU);

            // 跳过 populate 属性填充
            return false;
        }

        return true;
    }
}
