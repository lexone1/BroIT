package com.globalsqa.page;

import static com.globalsqa.page.BaseBy.BY_CLASS_NAME;
import static com.globalsqa.page.BaseBy.BY_CSS_SELECTOR;
import static com.globalsqa.page.BaseBy.BY_ID;
import static com.globalsqa.page.BaseBy.BY_LINK_TEXT;
import static com.globalsqa.page.BaseBy.BY_NAME;
import static com.globalsqa.page.BaseBy.BY_PARTIAL_LINK_TEXT;
import static com.globalsqa.page.BaseBy.BY_XPATH;

import com.globalsqa.common.annotation.QAComponent;

/**
 * Implements the test automation functionality regarding By handling
 *
 * @author Aliaksei Pershyts
 */
@QAComponent("baseByFactory")
public class DefaultBaseByFactory implements BaseByFactory
{
	@Override
	public BaseBy className(String argument)
	{
		return new BaseBy(BY_CLASS_NAME, argument);
	}

	@Override
	public BaseBy className(String argument, String... arguments)
	{
		return new BaseBy(BY_CLASS_NAME, argument, arguments);
	}
	@Override
	public BaseBy cssSelector(String argument)
	{
		return new BaseBy(BY_CSS_SELECTOR, argument);
	}

	@Override
	public BaseBy cssSelector(String argument, String... arguments)
	{
		return new BaseBy(BY_CSS_SELECTOR, argument, arguments);
	}

	@Override
	public BaseBy ID(String argument)
	{
		return new BaseBy(BY_ID, argument);
	}

	@Override
	public BaseBy ID(String argument, String... arguments)
	{
		return new BaseBy(BY_ID, argument, arguments);
	}

	@Override
	public BaseBy linkText(String argument)
	{
		return new BaseBy(BY_LINK_TEXT, argument);
	}

	@Override
	public BaseBy linkText(String argument, String... arguments)
	{
		return new BaseBy(BY_LINK_TEXT, argument, arguments);
	}

	@Override
	public BaseBy name(String argument)
	{
		return new BaseBy(BY_NAME, argument);
	}

	@Override
	public BaseBy name(String argument, String... arguments)
	{
		return new BaseBy(BY_NAME, argument, arguments);
	}

	@Override
	public BaseBy partialLinkText(String argument)
	{
		return new BaseBy(BY_PARTIAL_LINK_TEXT, argument);
	}

	@Override
	public BaseBy partialLinkText(String argument, String... arguments)
	{
		return new BaseBy(BY_PARTIAL_LINK_TEXT, argument, arguments);
	}

	@Override
	public BaseBy xpath(String argument)
	{
		return new BaseBy(BY_XPATH, argument);
	}

	@Override
	public BaseBy xpath(String argument, String... arguments)
	{
		return new BaseBy(BY_XPATH, argument, arguments);
	}
}
