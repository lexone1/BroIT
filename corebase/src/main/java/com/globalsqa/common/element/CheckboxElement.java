package com.globalsqa.common.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.globalsqa.common.BaseWebDriver;
import com.globalsqa.common.annotation.Getter;

/**
 * An implementation of {@link PageElement} for interacting with HTML checkboxes.
 *
 * @author Aliaksei Pershyts
 */
public class CheckboxElement extends PageElement
{
	/**
	 * Package level constructor for use by {@link PageElementFactory}.
	 *
	 * @param driver
	 * @param by
	 * @param webElement
	 * @param factory
	 */
	CheckboxElement(BaseWebDriver driver, By by, WebElement webElement, PageElementFactory factory)
	{
		super(driver, by, webElement, factory);
	}

	/**
	 * Deselects this checkbox.
	 */
	public void unCheck()
	{
		if (this.isChecked())
		{
			this.click();
		}
	}

	/**
	 * Selects this checkbox.
	 */
	public void check()
	{
		if (!this.isChecked())
		{
			this.click();
		}
	}

	/**
	 * Change checkbox state in dependence on needed state.
	 *
	 * @param state - true, if we need to check, false, if we need to uncheck
	 */
	public void check(boolean state)
	{
		if (state)
		{
			check();
		}
		else
		{
			unCheck();
		}
	}

	/**
	 * Returns whether or not this checkbox is selected.
	 *
	 * @return
	 */
	@Getter
	public Boolean isChecked()
	{
		return webElement.isSelected();
	}
}
