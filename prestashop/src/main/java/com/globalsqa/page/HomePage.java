package com.globalsqa.page;

import com.globalsqa.common.annotation.QAComponent;
import com.globalsqa.common.element.IFrameElement;
import com.globalsqa.common.element.LinkElement;
import com.globalsqa.common.element.PageElement;

@QAComponent("homePage")
public class HomePage extends AbstractPage
{
	/**
	 * Returns the Main Prestashop IFrame
	 *
	 * @return {@link IFrameElement}
	 */
	public IFrameElement getMainPrestashopFrame()
	{
		return factory.getElementIfPresent(IFrameElement.class, by.ID("homePage.mainPrestashopFrame"));
	}

	/**
	 * Returns the 'Accessories' menu item link
	 *
	 * @return {@link LinkElement}
	 */
	public LinkElement getAccessoriesMenuItemLink()
	{
		return factory.getElement(LinkElement.class, by.xpath("homePage.accessoriesMenuItemLink"));
	}
}
