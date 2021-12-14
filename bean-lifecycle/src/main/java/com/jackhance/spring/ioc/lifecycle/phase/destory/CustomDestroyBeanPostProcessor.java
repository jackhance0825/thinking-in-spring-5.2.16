package com.jackhance.spring.ioc.lifecycle.phase.destory;

import com.jackhance.spring.ioc.lifecycle.model.Creature;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * 自定义 {@link DestructionAwareBeanPostProcessor}
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class CustomDestroyBeanPostProcessor implements DestructionAwareBeanPostProcessor {

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("creature", beanName) && Creature.class.equals(bean.getClass())) {
            Creature creature = (Creature) bean;
            creature.setDescription("creature postProcessBeforeDestruction");
        }
    }

    @Override
    public boolean requiresDestruction(Object bean) {
        return true;
    }
}
