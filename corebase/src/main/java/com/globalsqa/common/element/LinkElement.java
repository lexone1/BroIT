package com.globalsqa.common.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.globalsqa.common.BaseWebDriver;
import com.globalsqa.common.annotation.Getter;

/**
 * An implementation of {@link PageElement} for interacting with HTML link elements.
 *
 * @author Aliaksei Pershyts
 */
public class LinkElement extends PageElement
{
	/**
	 * Package level constructor for use by {@link PageElementFactory}.
	 *
	 * @param driver
	 * @param by
	 * @param webElement
	 * @param factory
	 */
	LinkElement(BaseWebDriver driver, By by, WebElement webElement, PageElementFactory factory)
	{
		super(driver, by, webElement, factory);
	}

	/**
	 * Returns Text of the Link
	 *
	 * @return Link Text
	 */
	@Getter
	public String getLinkText()
	{
		return webElement.getText();
	}
}