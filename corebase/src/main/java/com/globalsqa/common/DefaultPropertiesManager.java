package com.globalsqa.common;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.globalsqa.common.annotation.QAComponent;

/**
 * Implements the test automation functionality regarding Properties handling.
 *
 * @author Aliaksei Pershyts
 */
@QAComponent("propertiesManager")
public class DefaultPropertiesManager implements PropertiesManager
{
	@Autowired private ResourceBundleMessageSource messageSource;

	@PostConstruct
	public void init()
	{
		messageSource.setDefaultEncoding("UTF-8");
	}

	@Override
	public String get(String key, String... values)
	{
		Object o = messageSource.getMessage(key, values, Locale.getDefault());

		return o.toString();
	}
}
