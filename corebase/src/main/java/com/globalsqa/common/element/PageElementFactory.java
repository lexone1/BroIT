package com.globalsqa.common.element;

import java.util.List;

import com.globalsqa.page.BaseBy;

/**
 * Defines a factory interface for accessing {@link PageElement} objects within a web page.
 *
 * @author Aliaksei Pershyts
 */
public interface PageElementFactory
{
	/**
	 * Returns a {@link PageElement} instance of the specified type. If the element is already in the DOM it is returned
	 * immediately. Otherwise, this method will wait a few seconds for the element to be added to the DOM. If it is
	 * still not accessible after waiting an exception is thrown.
	 *
	 * @param pageElementType
	 * @param baseBy
	 * @param <T>
	 * @return
	 */
	<T extends PageElement> T getElement(Class<T> pageElementType, BaseBy baseBy);

	/**
	 * Returns a {@link PageElement} instance of the specified type. If the element is already in the DOM it is returned
	 * immediately. Otherwise, this method will wait a few seconds for the element to be added to the DOM. If it is
	 * still not accessible after waiting an exception is thrown.
	 *
	 * @param pageElementType
	 * @param baseBy
	 * @param timeout
	 * @param <T>
	 * @return
	 */
	<T extends PageElement> T getElement(Class<T> pageElementType, BaseBy baseBy, int timeout);

	/**
	 * Returns a {@link PageElement} instance of the specified type, only if it is currently present in the DOM. If the
	 * element is not present, this method immediately returns null (it does not wait).
	 *
	 * @param pageElementType
	 * @param baseBy
	 * @param <T>
	 * @return
	 */
	<T extends PageElement> T getElementIfPresent(Class<T> pageElementType, BaseBy baseBy);

	/**
	 * Returns a {@link PageElement} instance of the specified type. If the element is already in the DOM it is returned
	 * immediately. Otherwise, this method will wait a few seconds for the element to be added to the DOM. If it is
	 * still not accessible after waiting, this method returns null.
	 *
	 * @param pageElementType
	 * @param baseBy
	 * @param timeout
	 * @param <T>
	 * @return
	 */
	<T extends PageElement> T getElementIfPresent(Class<T> pageElementType, BaseBy baseBy, int timeout);

	/**
	 * Returns a list of {@link PageElement} instances of the specified type. If the elements are already in the DOM
	 * they are returned immediately. Otherwise, this method will wait a few seconds for the elements to be added to the
	 * DOM. If they are still not accessible after waiting an exception is thrown.
	 *
	 * @param pageElementType
	 * @param baseBy
	 * @param <T>
	 * @return
	 */
	<T extends PageElement> List<T> getElements(Class<T> pageElementType, BaseBy baseBy);

	/**
	 * Returns a list of {@link PageElement} instances of the specified type. If the elements are already in the DOM
	 * they are returned immediately. Otherwise, this method will wait a few seconds for the elements to be added to the
	 * DOM.
	 *
	 * @param pageElementType
	 * @param baseBy
	 * @param timeout
	 * @param <T>
	 * @return
	 */
	<T extends PageElement> List<T> getElements(Class<T> pageElementType, BaseBy baseBy, int timeout);

	/**
	 * Returns a list of {@link PageElement} instances of the specified type currently present in the DOM. If the
	 * elements are not present, this method immediately returns empty list (it does not wait).
	 * DOM.
	 *
	 * @param pageElementType
	 * @param baseBy
	 * @param <T>
	 * @return
	 */
	<T extends PageElement> List<T> getElementsIfPresent(Class<T> pageElementType, BaseBy baseBy);
}
