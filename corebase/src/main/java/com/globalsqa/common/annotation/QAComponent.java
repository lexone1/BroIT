package com.globalsqa.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Public @interface QAComponent for Spring bean Autowired
 *
 * @author Aliaksei Pershyts
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Lazy
@Component
@Conditional(BeanNameDoesNotExistCondition.class)
public @interface QAComponent
{
	String value() default "core";
}
