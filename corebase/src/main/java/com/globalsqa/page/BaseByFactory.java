package com.globalsqa.page;

/**
 * Implements the test automation functionality regarding Locators handling
 *
 * @author Aliaksei Pershyts
 */
public interface BaseByFactory
{
	BaseBy className(String stringArgument);

	BaseBy className(String stringArgument, String... args);

	BaseBy cssSelector(String stringArgument);

	BaseBy cssSelector(String stringArgument, String... args);

	BaseBy ID(String stringArgument);

	BaseBy ID(String stringArgument, String... args);

	BaseBy linkText(String stringArgument);

	BaseBy linkText(String stringArgument, String... args);

	BaseBy name(String stringArgument);

	BaseBy name(String stringArgument, String... args);

	BaseBy partialLinkText(String stringArgument);

	BaseBy partialLinkText(String stringArgument, String... args);

	BaseBy xpath(String stringArgument);

	BaseBy xpath(String stringArgument, String... args);
}
