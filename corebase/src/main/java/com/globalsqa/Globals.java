package com.globalsqa;

import com.globalsqa.common.annotation.QAComponent;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class to track global objects for a scenario that can be accessed across its different steps.
 */
@QAComponent("globals")
public class Globals
{
	private ThreadLocal<Map<String, Object>> temporaryValues = new ThreadLocal<>();
	private boolean isReadOnly = false;

	/**
	 * Resets the {@link ThreadLocal} storage mechanism.
	 */
	public void clear()
	{
		temporaryValues.set(null);
	}

	/**
	 * Adds an object to the map.
	 *
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value)
	{
		if (!isReadOnly)
		{
			getMap().put(key, value);
		}
	}

	/**
	 * Remove an object from the map.
	 *
	 * @param key
	 */
	public void remove(String key)
	{
		if (!isReadOnly)
		{
			getMap().remove(key);
			getMap().remove(key + "List");
		}
	}

	/**
	 * Adds an object to the map twice.
	 *
	 * @param key
	 * @param value
	 */
	public void putWithList(String key, Object value)
	{
		if (!isReadOnly)
		{
			getMap().put(key, value);
			addToList(key + "List", value);
		}
	}

	/**
	 * Adds an object to a list of objects represented by this key.
	 *
	 * @param key
	 * @param value
	 */
	public void addToList(String key, Object value)
	{
		if (!isReadOnly)
		{
			Map<String, Object> map = getMap();
			List values = (List) map.get(key);
			if (values == null)
			{
				values = new ArrayList();
				map.put(key, values);
			}

			values.add(value);
		}
	}

	/**
	 * Remove the object from a list of objects represented by this key.
	 *
	 * @param key
	 * @param value
	 */
	public void removeFromList(String key, Object value)
	{
		if (!isReadOnly)
		{
			Map<String, Object> map = getMap();
			List values = (List) map.get(key);
			if (values != null)
			{
				values.remove(value);
			}
		}
	}

	/**
	 * Returns the object matching the specified key.
	 *
	 * @param key
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String key)
	{
		return (T) getMap().get(key);
	}

	/**
	 * Internal method used to access the thread-local map and initialize it (if not yet initialized).
	 *
	 * @return
	 */
	private Map<String, Object> getMap()
	{
		Map<String, Object> map = temporaryValues.get();
		if (map == null)
		{
			map = new HashMap<>();
			temporaryValues.set(map);
		}

		return map;
	}

	/**
	 * Returns a list of keys of the specified value.
	 *
	 * @param value
	 * @return
	 */
	public List<String> getKeyByValue(String value)
	{
		return getMap().entrySet().stream()
				.filter(entry -> Objects.equals(entry.getValue(), value))
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}
}
