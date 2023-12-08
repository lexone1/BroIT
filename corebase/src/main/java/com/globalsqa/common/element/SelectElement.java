package com.globalsqa.common.element;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.globalsqa.common.BaseWebDriver;
import com.globalsqa.common.annotation.Getter;
import com.globalsqa.common.annotation.Setter;

/**
 * An implementation of {@link PageElement} for interacting with HTML select elements.
 *
 * @author Aliaksei Pershyts
 */
public class SelectElement extends PageElement
{
	/**
	 * Package level constructor for use by {@link PageElementFactory}.
	 *
	 * @param driver
	 * @param by
	 * @param webElement
	 * @param factory
	 */
	SelectElement(BaseWebDriver driver, By by, WebElement webElement, PageElementFactory factory)
	{
		super(driver, by, webElement, factory);
	}

	/**
	 * Selects an element in a select list using the text of the option. If null is supplied it will be ignored.
	 *
	 * @param text text of the item to select in the list
	 */
	@Setter
	public void selectByVisibleText(String text)
	{
		if (text != null)
		{
			String xPath = ".//option[contains(text(),\"" + text + "\")]";
			Select select = new Select(webElement);
			if (text.isEmpty() && doesOptionExist(""))
			{
				select.selectByIndex(0);
			}
		}
	}

	/**
	 * Gets the selected element in list.
	 *
	 * @return the selected element in list
	 */
	@Getter
	public String getSelectedOptionText()
	{
		Select selectList = new Select(webElement);
		WebElement selectedOption = selectList.getFirstSelectedOption();
		return selectedOption.getText().trim();
	}

	/**
	 * Returns whether or not the specified option exists in this dropdown.
	 *
	 * @param option option text
	 * @return exist/ not exist
	 */
	public boolean doesOptionExist(String option)
	{
		boolean exists = false;
		if (option != null)
		{
			Select selectList = new Select(webElement);
			for (WebElement optionElement : selectList.getOptions())
			{
				if (StringUtils.equals(optionElement.getText().trim(), option))
				{
					exists = true;
					break;
				}
			}
		}
		return exists;
	}

	/**
	 * Number of options in select element
	 *
	 * @return int count of options in SelectElement
	 */
	public int getOptionsCount()
	{
		return new Select(webElement).getOptions().size();
	}

	/**
	 * Selects an element in a select list using the value of the option
	 *
	 * @param value the value of the item to select in the list
	 */
	public void selectByValue(String value)
	{
		new Select(webElement).selectByValue(value);
	}
}