package com.globalsqa.common.annotation;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Condition for QAComponent annotation
 *
 * @author Aliaksei Pershyts
 */
public class BeanNameDoesNotExistCondition implements Condition
{
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata)
	{
		Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(
				"com.globalsqa.common.annotation.QAComponent");

		String componentName = null;
		if (annotationAttributes != null)
		{
			componentName = (String) annotationAttributes.get("value");
		}
		return !StringUtils.isNotBlank(componentName) || !context.getRegistry().containsBeanDefinition(componentName);
	}
}
