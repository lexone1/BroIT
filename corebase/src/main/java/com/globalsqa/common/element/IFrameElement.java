package com.globalsqa.common.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.globalsqa.common.BaseWebDriver;

/**
 * An implementation of {@link PageElement} for interacting with HTML iFrame
 */
public class IFrameElement extends PageElement
{
	/**
	 * Package level constructor for use by {@link PageElementFactory}.
	 *
	 * @param driver - the webdriver object used in the page
	 * @param by - the locator to find the element
	 * @param webElement - the native web element object
	 * @param factory - element factory
	 */
	IFrameElement(BaseWebDriver driver, By by, WebElement webElement, PageElementFactory factory)
	{
		super(driver, by, webElement, factory);
	}

	/**
	 * Switches to frame
	 */
	public void switchToFrame()
	{
		driver.switchToFrame(this.webElement);
	}
}
