package com.globalsqa.common.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.globalsqa.common.annotation.QAComponent;

/**
 * Implementation of the {@link PageElementFactory}
 *
 * @author Aliaksei Pershyts
 */
@QAComponent("pageElementFactoryCore")
public class PageElementFactoryCore extends PageElementFactoryDefault
{
	@Override
	public <T extends PageElement> T castElement(Class<T> pageElementType, By by, WebElement webElement)
	{
		if (TextElement.class.isAssignableFrom(pageElementType))
		{
			return pageElementType.cast(getTextElement(by, webElement, this));
		}
		else if (ButtonElement.class.isAssignableFrom(pageElementType))
		{
			return pageElementType.cast(getButtonElement(by, webElement, this));
		}
		else if (CheckboxElement.class.isAssignableFrom(pageElementType))
		{
			return pageElementType.cast(getCheckboxElement(by, webElement, this));
		}
		else if (SelectElement.class.isAssignableFrom(pageElementType))
		{
			return pageElementType.cast(getSelectElement(by, webElement, this));
		}
		else if (LinkElement.class.isAssignableFrom(pageElementType))
		{
			return pageElementType.cast(getLinkElement(by, webElement, this));
		}
		else if (IFrameElement.class.isAssignableFrom(pageElementType))
		{
			return pageElementType.cast(getIFrameElement(by, webElement, this));
		}
		else
		{
			return pageElementType.cast(new PageElement(webDriver, by, webElement, this));
		}
	}

	// ---------------------------------------------------------------------

	protected TextElement getTextElement(By by, WebElement webElement, PageElementFactory factory)
	{
		return new TextElement(webDriver, by, webElement, factory);
	}

	protected CheckboxElement getCheckboxElement(By by, WebElement webElement, PageElementFactory factory)
	{
		return new CheckboxElement(webDriver, by, webElement, factory);
	}

	protected SelectElement getSelectElement(By by, WebElement webElement, PageElementFactory factory)
	{
		return new SelectElement(webDriver, by, webElement, factory);
	}

	protected ButtonElement getButtonElement(By by, WebElement webElement, PageElementFactory factory)
	{
		return new ButtonElement(webDriver, by, webElement, factory);
	}

	protected LinkElement getLinkElement(By by, WebElement webElement, PageElementFactory factory)
	{
		return new LinkElement(webDriver, by, webElement, factory);
	}

	protected IFrameElement getIFrameElement(By by, WebElement webElement, PageElementFactory factory)
	{
		return new IFrameElement(webDriver, by, webElement, factory);
	}
}
