package com.globalsqa.common.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.globalsqa.common.BaseWebDriver;

/**
 * An implementation of {@link PageElement} for interacting with HTML buttons.
 *
 * @author Aliaksei Pershyts
 */
public class ButtonElement extends PageElement
{
	/**
	 * Package level constructor for use by {@link PageElementFactory}.
	 *
	 * @param driver
	 * @param by
	 * @param webElement
	 * @param factory
	 */
	ButtonElement(BaseWebDriver driver, By by, WebElement webElement, PageElementFactory factory)
	{
		super(driver, by, webElement, factory);
	}
}
