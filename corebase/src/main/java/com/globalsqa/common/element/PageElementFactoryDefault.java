package com.globalsqa.common.element;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import com.globalsqa.common.BaseWebDriver;
import com.globalsqa.common.PropertiesManager;
import com.globalsqa.common.TestConfiguration;
import com.globalsqa.common.exception.BaseException;
import com.globalsqa.page.BaseBy;

/**
 * Implements the test automation functionality PageElementFactoryDefault handling
 *
 * @author Aliaksei Pershyts
 */
public abstract class PageElementFactoryDefault implements PageElementFactory
{
	@Autowired
	PropertiesManager properties;
	@Autowired
	TestConfiguration testConfiguration;
	@Autowired
	BaseWebDriver webDriver;

	@Override
	public <T extends PageElement> T getElement(Class<T> pageElementType, BaseBy baseBy)
	{
		return getElement(pageElementType, baseBy, testConfiguration.getElementsLoadTimeOut());
	}

	@Override
	public <T extends PageElement> T getElement(Class<T> pageElementType, BaseBy baseBy, int timeout)
	{
		By by = translateBy(baseBy);
		List<WebElement> webElements = webDriver.waitForElements(by, timeout);
		WebElement webElement = webElements.get(0);
		return castElement(pageElementType, by, webElement);
	}

	@Override
	public <T extends PageElement> T getElementIfPresent(Class<T> pageElementType, BaseBy baseBy)
	{
		return getElementIfPresent(pageElementType, baseBy, 0);
	}

	@Override
	public <T extends PageElement> T getElementIfPresent(Class<T> pageElementType, BaseBy baseBy, int timeout)
	{
		T result = null;
		By by = translateBy(baseBy);
		WebElement webElement = null;
		try
		{
			webElement = webDriver.waitForElement(by, timeout);
		}
		catch (TimeoutException te)
		{
		}

		if (webElement != null)
		{
			result = castElement(pageElementType, by, webElement);
		}

		return result;
	}

	@Override
	public <T extends PageElement> List<T> getElements(Class<T> pageElementType, BaseBy baseBy)
	{
		return getElements(pageElementType, baseBy, testConfiguration.getElementsLoadTimeOut());
	}

	@Override
	public <T extends PageElement> List<T> getElements(Class<T> pageElementType, BaseBy baseBy, int timeout)
	{
		By by = translateBy(baseBy);
		List<T> pageElements = new ArrayList<>();
		try
		{
			List<WebElement> webElements = webDriver.waitForElements(by, timeout);
			for (WebElement webElement : webElements)
			{
				pageElements.add(castElement(pageElementType, by, webElement));
			}
		}
		finally
		{
			return pageElements;
		}
	}

	@Override
	public <T extends PageElement> List<T> getElementsIfPresent(Class<T> pageElementType, BaseBy baseBy)
	{
		return getElements(pageElementType, baseBy, 0);
	}

	/**
	 * Translates BaseBy object into a By object and resolves string property resources.
	 *
	 * @param oldBy
	 * @return
	 */
	public By translateBy(BaseBy oldBy)
	{
		By result;
		switch (oldBy.getType())
		{
			case BaseBy.BY_CLASS_NAME:
				result = By.className(properties.get(oldBy.getKey(), oldBy.getArguments()));
				break;
			case BaseBy.BY_CSS_SELECTOR:
				result = By.cssSelector(properties.get(oldBy.getKey(), oldBy.getArguments()));
				break;
			case BaseBy.BY_ID:
				result = By.id(properties.get(oldBy.getKey(), oldBy.getArguments()));
				break;
			case BaseBy.BY_LINK_TEXT:
				result = By.linkText(properties.get(oldBy.getKey(), oldBy.getArguments()));
				break;
			case BaseBy.BY_NAME:
				String byName = properties.get(oldBy.getKey(), oldBy.getArguments());
				byName = byName.replace("'", "\\'");
				result = By.name(byName);
				break;
			case BaseBy.BY_PARTIAL_LINK_TEXT:
				result = By.partialLinkText(properties.get(oldBy.getKey(), oldBy.getArguments()));
				break;
			case BaseBy.BY_XPATH:
				result = By.xpath(properties.get(oldBy.getKey(), oldBy.getArguments()));
				break;
			default:
				throw new BaseException("By type with id '" + oldBy.getType() + "' could not be found.");
		}
		return result;
	}

	/**
	 * Retrieves the requested web element and casts it to the requested class type.
	 *
	 * @param pageElementType
	 * @param by
	 * @param webElement
	 * @param <T>
	 * @return
	 */
	public abstract <T extends PageElement> T castElement(Class<T> pageElementType, By by, WebElement webElement);
}
