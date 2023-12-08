package com.globalsqa.page;

import java.util.Arrays;

import com.globalsqa.common.element.PageElementFactory;

/**
 * Default implementation of the {@link PageElementFactory} class.
 *
 * @author Aliaksei Pershyts
 */
public class BaseBy
{
	public static final int BY_CLASS_NAME = 0;
	public static final int BY_CSS_SELECTOR = 1;
	public static final int BY_ID = 2;
	public static final int BY_LINK_TEXT = 3;
	public static final int BY_NAME = 4;
	public static final int BY_PARTIAL_LINK_TEXT = 5;
	public static final int BY_XPATH = 6;

	private final int type;
	private final String key;
	private String[] arguments;

	public BaseBy(int type, String key)
	{
		this.type = type;
		this.key = key;
	}

	public BaseBy(int type, String key, String[] arguments)
	{
		this.type = type;
		this.key = key;
		this.arguments = arguments;
	}

	public int getType()
	{
		return type;
	}

	public String getKey()
	{
		return key;
	}

	public String[] getArguments()
	{
		return arguments;
	}
}
