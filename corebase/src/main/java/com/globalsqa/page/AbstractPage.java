package com.globalsqa.page;

import org.springframework.beans.factory.annotation.Autowired;

import com.globalsqa.common.BaseNavigation;
import com.globalsqa.common.BaseWebDriver;
import com.globalsqa.common.PropertiesManager;
import com.globalsqa.common.TestConfiguration;
import com.globalsqa.common.element.PageElementFactory;
import com.globalsqa.common.element.PageElementFactoryCore;

/**
 * Implements the test automation functionality regarding Page Object.
 *
 * @author Aliaksei Pershyts
 */
public class AbstractPage
{
	@Autowired protected PropertiesManager properties;

	@Autowired protected BaseWebDriver webDriver;

	@Autowired protected BaseByFactory by;

	@Autowired protected BaseNavigation navigation;

	@Autowired protected PageElementFactoryCore factory;

	public BaseNavigation getNavigation()
	{
		return navigation;
	}

	/**
	 * Returns the unique portion of this page's title
	 *
	 * @return page title
	 */
	public String getPageTitle()
	{
		String text = webDriver.getPageTitle().trim();
		return text;
	}

	/**
	 * Sleep method.
	 */
	public void sleep(int sleepMilliseconds)
	{
		webDriver.sleep(sleepMilliseconds);
	}
}
