package com.jackhance.spring.ioc.dependency.injection.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * 组注解，基于 {@link Qualifier}, 多匹配值
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier
public @interface MultiValueGroup {

    MultiGroupType type() default MultiGroupType.COMMON;

    int groupId() default 0;
}
