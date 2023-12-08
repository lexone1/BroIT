package com.globalsqa.common.element;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.globalsqa.common.BaseWebDriver;
import com.globalsqa.common.annotation.Setter;

/**
 * An implementation of {@link PageElement} for interacting with HTML text elements.
 *
 * @author Aliaksei Pershyts
 */
public class TextElement extends PageElement

{
	/**
	 * Package level constructor for use by {@link PageElementFactory}.
	 *
	 * @param driver
	 * @param by
	 * @param webElement
	 * @param factory
	 */
	TextElement(BaseWebDriver driver, By by, WebElement webElement, PageElementFactory factory)
	{
		super(driver, by, webElement, factory);
	}

	/**
	 * Sets the non-null text value for this element. If null is supplied it will be ignored.
	 *
	 * @param text text to enter in the element
	 */
	@Setter
	public void enterText(String text)
	{
		if (text != null)
		{
			this.webElement.sendKeys(text);
		}
	}

	/**
	 * Clears value from element
	 */
	public void clearText()
	{
		this.webElement.clear();
	}
}
