package com.jackhance.spring.ioc.dependency.injection.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver;

import java.lang.annotation.*;

/**
 * 组注解，基于 {@link Group} -> {@link Qualifier}
 * 依赖注入失败，因为 {@link Qualifier} 匹配只支持一层元标注
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 * @see {@link QualifierAnnotationAutowireCandidateResolver}
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Group
public @interface SubGroup {
}
