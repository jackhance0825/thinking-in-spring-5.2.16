package com.jackhance.spring.ioc.lifecycle.phase.init;

import com.jackhance.spring.ioc.lifecycle.model.Creature;
import com.jackhance.spring.ioc.lifecycle.model.WorkerHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * 自定义 {@link InstantiationAwareBeanPostProcessor}
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class CustomInitBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("creature", beanName) && Creature.class.equals(bean.getClass())) {
            Creature creature = (Creature) bean;
            creature.setDescription("creature postProcessBeforeInitialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("creature", beanName) && Creature.class.equals(bean.getClass())) {
            Creature creature = (Creature) bean;
            creature.setDescription("creature postProcessAfterInitialization");
        }
        return bean;
    }
}
