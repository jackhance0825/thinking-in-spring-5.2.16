package com.jackhance.spring.ioc.lifecycle.phase.populate;

import com.jackhance.spring.ioc.lifecycle.model.Location;
import com.jackhance.spring.ioc.lifecycle.model.Worker;
import com.jackhance.spring.ioc.lifecycle.model.WorkerHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.util.Objects;

/**
 * 自定义 {@link InstantiationAwareBeanPostProcessor}
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class CustomPopulateBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        // 替换 Populate 属性填充的属性值
        if (Objects.equals("workerHolder", beanName) && Objects.equals(WorkerHolder.class, bean.getClass())) {

            MutablePropertyValues mpv = pvs instanceof  MutablePropertyValues ? (MutablePropertyValues) pvs
                    : new MutablePropertyValues();

            // 相当于 <property name="message" value="custom message!!!" />
            mpv.addPropertyValue("message", "custom message!!!");

            return mpv;
        }

        return null;
    }
}
