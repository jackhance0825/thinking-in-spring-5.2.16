package com.jackhance.spring.ioc.dependency.lookup.annotation;

import java.lang.annotation.*;

/**
 * 标注特殊 Bean
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IBean {
}
