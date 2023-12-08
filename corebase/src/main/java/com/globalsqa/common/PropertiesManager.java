package com.globalsqa.common;

/**
 * Implements the test automation functionality regarding Properties Manager.
 *
 * @author Aliaksei Pershyts
 */
public interface PropertiesManager
{
	String get(String key, String... values);
}
