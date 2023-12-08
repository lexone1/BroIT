package com.globalsqa.common.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.globalsqa.common.BaseWebDriver;

/**
 * It allows us to expose public Element objects in a page class to reduce small methods to do simple WebDriver
 * interactions.
 *
 * @author Aliaksei Pershyts
 */
public class PageElement
{
	protected By by;
	protected BaseWebDriver driver;
	protected WebElement webElement;
	protected PageElementFactory factory;

	/**
	 * Constructs the Element object.
	 *
	 * @param driver     the webdriver object used in the page
	 * @param by         the locator to find the element
	 * @param webElement the native web element object
	 */
	public PageElement(BaseWebDriver driver, By by, WebElement webElement, PageElementFactory factory)
	{
		this.by = by;
		this.driver = driver;
		this.webElement = webElement;
		this.factory = factory;
	}

	/**
	 * Returns the visible text of this element and any of its sub-elements.
	 *
	 * @return {@link String} visible ext of this element and any of its sub-elements
	 */
	public String getText()
	{
		return webElement.getText();
	}

	/**
	 * Returns if this element is visible or not.
	 *
	 * @return {@link Boolean} if this element is visible or not
	 */
	public boolean isVisible()
	{
		return webElement.isDisplayed();
	}

	/**
	 * Returns if this element is enabled or not.
	 *
	 * @return {@link Boolean} if this element is enabled or not
	 */
	public boolean isEnabled()
	{
		return webElement.isEnabled();
	}

	/**
	 * Clicks this element
	 */
	public void click()
	{
		webElement.click();
	}
}
