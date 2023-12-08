package com.globalsqa.page;

import com.globalsqa.common.annotation.QAComponent;
import com.globalsqa.common.element.LinkElement;

@QAComponent("accessoriesPage")
public class AccessoriesPage extends AbstractPage
{
	/**
	 * Returns the Product Name link on the 'Accessories' page
	 *
	 * @return {@link LinkElement}
	 */
	public LinkElement getProductNameLink(String productName)
	{
		return factory.getElement(LinkElement.class, by.xpath("accessoriesPage.productNameLink", productName));
	}
}
