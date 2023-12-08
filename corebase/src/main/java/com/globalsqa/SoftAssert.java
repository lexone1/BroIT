package com.globalsqa;

import com.globalsqa.common.annotation.QAComponent;
import com.globalsqa.common.exception.BaseException;

import org.junit.Assert;
import org.junit.runners.model.MultipleFailureException;

import java.util.List;

/**
 * Provides soft assertion functionality for the automation framework. Assertions are run immediately though their
 * failures are only recorded, not thrown. Once all asserts have been performed the {@link SoftAssert#process()} method
 * can be used to throw all recorded failures on this bean and to reset its state for the next set of assertions.
 * <p>
 * Created by Aliaksei Pershyts on 8/4/2021.
 */
@QAComponent("softAssert")
public class SoftAssert
{
	/**
	 * Internal {@link ThreadLocal} to track the assertion failures.
	 */
	private ThreadLocal<SoftAssertChain> softAssertChain = new ThreadLocal<SoftAssertChain>()
	{
		@Override
		protected SoftAssertChain initialValue()
		{
			return new SoftAssertChain();
		}
	};

	/**
	 * Assert the condition is true.
	 *
	 * @param condition
	 */
	public void assertTrue(boolean condition)
	{
		assertTrue(null, condition);
	}

	/**
	 * Assert the condition is true.
	 *
	 * @param message
	 * @param condition
	 */
	public void assertTrue(String message, boolean condition)
	{
		setAssertsPresentTrue();
		try
		{
			Assert.assertTrue(message, condition);
		}
		catch (Throwable t)
		{
			softAssertChain.get().addFailure(t);
		}
	}

	/**
	 * Assert the condition is false.
	 *
	 * @param condition
	 */
	public void assertFalse(boolean condition)
	{
		assertFalse(null, condition);
	}

	/**
	 * Assert the condition is false.
	 *
	 * @param message
	 * @param condition
	 */
	public void assertFalse(String message, boolean condition)
	{
		setAssertsPresentTrue();
		try
		{
			Assert.assertFalse(message, condition);
		}
		catch (Throwable t)
		{
			softAssertChain.get().addFailure(t);
		}
	}

	/**
	 * Assert the objects are equal.
	 *
	 * @param expected
	 * @param actual
	 */
	public void assertEquals(Object expected, Object actual)
	{
		assertEquals(null, expected, actual);
	}

	/**
	 * Assert the objects are equal.
	 *
	 * @param message
	 * @param expected
	 * @param actual
	 */
	public void assertEquals(String message, Object expected, Object actual)
	{
		setAssertsPresentTrue();
		try
		{
			Assert.assertEquals(message, expected, actual);
		}
		catch (Throwable t)
		{
			softAssertChain.get().addFailure(t);
		}
	}

	/**
	 * Assert the objects are not equal.
	 *
	 * @param first
	 * @param second
	 */
	public void assertNotEquals(Object first, Object second)
	{
		assertNotEquals(null, first, second);
	}

	/**
	 * Assert the objects are not equal.
	 *
	 * @param message
	 * @param first
	 * @param second
	 */
	public void assertNotEquals(String message, Object first, Object second)
	{
		setAssertsPresentTrue();
		try
		{
			Assert.assertNotEquals(message, first, second);
		}
		catch (Throwable t)
		{
			softAssertChain.get().addFailure(t);
		}
	}

	/**
	 * Assert the object is null.
	 *
	 * @param object
	 */
	public void assertNull(Object object)
	{
		assertNull(null, object);
	}

	/**
	 * Assert the object is null.
	 *
	 * @param message
	 * @param object
	 */
	public void assertNull(String message, Object object)
	{
		setAssertsPresentTrue();
		try
		{
			Assert.assertNull(message, object);
		}
		catch (Throwable t)
		{
			softAssertChain.get().addFailure(t);
		}
	}

	/**
	 * Assert the object is not null.
	 *
	 * @param object
	 */
	public void assertNotNull(Object object)
	{
		assertNotNull(null, object);
	}

	/**
	 * Assert the object is not null.
	 *
	 * @param message
	 * @param object
	 */
	public void assertNotNull(String message, Object object)
	{
		setAssertsPresentTrue();
		try
		{
			Assert.assertNotNull(message, object);
		}
		catch (Throwable t)
		{
			softAssertChain.get().addFailure(t);
		}
	}

	/**
	 * Examines this bean for any assertion errors it may have collected and, if any are detected, collects them into a
	 * large exception and throws it. Also resets the state of the bean for the next step/scenario that makes soft
	 * assertions. If no assert method has been called before, than exception will be thrown after the scenario
	 * execution.
	 */
	public void process()
	{
		Globals globals = (Globals) ApplicationContextProvider.getApplicationContext().getBean("globals");
		if (!softAssertChain.get().isAssertPresent())
		{
			globals.put(BaseConstants.EMPTY_SOFT_ASSERT_PROCESS_GLOBAL_NAME, "true");
			//			logger.error("SoftAssert has been processed without any assert method calling");
		}
		processIndependentlyFromAssertsCount();
	}

	/**
	 * Examines this bean for any assertion errors it may have collected and, if any are detected, collects them into a
	 * large exception and throws it. Also resets the state of the bean for the next step/scenario that makes soft
	 * assertions.
	 */
	public void processIndependentlyFromAssertsCount()
	{
		try
		{
			MultipleFailureException.assertEmpty(getFailures());
		}
		catch (Throwable throwable)
		{
			throw new BaseException(throwable);
		}
		finally
		{
			softAssertChain.get().reset();
		}
	}

	/**
	 * Returns List of exceptions
	 */
	public List<Throwable> getFailures()
	{
		return softAssertChain.get().getFailures();
	}

	/**
	 * Sets isAssertPresent flag to "true".
	 */
	public void setAssertsPresentTrue()
	{
		softAssertChain.get().setAssertPresentTrue();
	}
}
