package com.jackhance.spring.ioc.dependency.injection.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * 组注解，基于 {@link Qualifier}
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier
public @interface GroupX {
}
