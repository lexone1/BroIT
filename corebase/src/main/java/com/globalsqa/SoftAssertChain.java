package com.globalsqa;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple bean to collect failures that occurred during the processing of soft assertion tests.
 */
public class SoftAssertChain
{
	private List<Throwable> failures;
	private boolean isAssertPresent;

	/**
	 * Protected constructor - no need to make instances of this outside of the {@link SoftAssert} singleton.
	 */
	protected SoftAssertChain()
	{
		failures = new ArrayList<>();
		isAssertPresent = false;
	}

	/**
	 * Adds a failure to this bean.
	 *
	 * @param failure
	 */
	public void addFailure(Throwable failure)
	{
		failures.add(failure);
	}

	/**
	 * Sets isAssertPresent flag to "true".
	 */
	public void setAssertPresentTrue()
	{
		isAssertPresent = true;
	}

	/**
	 * Sets isAssertPresent flag to "false".
	 */
	public void setAssertPresentFalse()
	{
		isAssertPresent = false;
	}

	/**
	 * @return boolean - flag that specifies whether any assert method has been called.
	 */
	public boolean isAssertPresent()
	{
		return isAssertPresent;
	}

	/**
	 * Resets the list of failures in this bean. Sets isAssertPresent flag to "false".
	 */
	public void reset()
	{
		failures.clear();
		setAssertPresentFalse();
	}

	/**
	 * Returns all recorded failures.
	 *
	 * @return
	 */
	protected List<Throwable> getFailures()
	{
		return failures;
	}
}
