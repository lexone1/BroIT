package com.globalsqa.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.globalsqa.common.annotation.QAComponent;
import com.globalsqa.common.element.ButtonElement;
import com.globalsqa.common.element.LinkElement;
import com.globalsqa.common.element.PageElementFactoryCore;
import com.globalsqa.common.exception.BaseException;
import com.globalsqa.page.BaseByFactory;

/**
 * Utility class which exposes some commonly performed navigation tasks.
 *
 * @author Aliaksei Pershyts
 */
@QAComponent("navigation")
public class BaseNavigation
{
	@Autowired
	protected BaseWebDriver webDriver;
	@Autowired
	TestConfiguration testConfiguration;
	@Autowired
	BaseByFactory by;
	@Autowired
	protected PageElementFactoryCore factory;

	/**
	 * Redirects the browser back to the home page.
	 */
	public void goHome()
	{
		openURL(testConfiguration.getApplicationURL());
	}

	/**
	 * Click on button with text provided in param
	 *
	 * @param text - button text
	 */
	public void clickButtonByText(String text)
	{
		getButtonByText(text).click();
	}

	/**
	 * Gets button with text provided in param
	 *
	 * @param text - button text
	 */
	public ButtonElement getButtonByText(String text)
	{
		ButtonElement button = factory.getElementIfPresent(ButtonElement.class,
				by.xpath("navigation.actionButton", text));
		if (button == null)
		{
			throw new BaseException("Button with text '" + text + "' is not found.");
		}
		return button;
	}

	/**
	 * Opens the specified URL
	 *
	 * @param url URL to open
	 */
	public void openURL(String url)
	{
		webDriver.goToURL(url);
	}

	/**
	 * Click on link with text provided in param
	 *
	 * @param text - link text
	 */
	public void clickLinkByText(String text)
	{
		LinkElement link = factory.getElement(LinkElement.class, by.xpath("navigation.generalLink", text));
		List<LinkElement> links = factory.getElements(LinkElement.class,
				by.xpath("navigation.linksWithSimilarText", text));
		int linksQuantity = links.size();
		if (linksQuantity > 1)
		{
			link = factory.getElement(LinkElement.class, by.xpath("navigation.exactLinkText", text));
		}
		if (link == null)
		{
			throw new BaseException("Link with text '" + text + "' is not found.");
		}
		link.click();
	}
}
