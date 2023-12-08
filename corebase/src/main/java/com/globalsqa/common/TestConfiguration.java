package com.globalsqa.common;

import com.globalsqa.BaseConstants;
import com.globalsqa.Globals;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.globalsqa.common.annotation.QAComponent;

/**
 * Class to hold test suite execution parameters
 *
 * @author Aliaksei Pershyts
 */
@QAComponent("testConfiguration")
public class TestConfiguration
{
	@Autowired
	Globals globals;
	@Value("${driver.scripts.timeOut:10}")
	private int driverScriptsTimeout;
	@Value("${pageLoad.timeOut:15}")
	private int pageLoadTimeOut;
	@Value("${driver.pageLoad.timeOut:180}")
	private int driverPageLoadTimeout;
	@Value("${driver.implicit.timeOut:0}")
	private int driverImplicitTimeout;
	@Value("${capitalizeRandom:false}")
	private boolean capitalizeRandom;
	@Value("${elementsLoad.timeOut:0}")
	private int elementsLoadTimeOut;
	private String applicationName;
	private String applicationURL;

	/**
	 * @return scripts timeout
	 */
	public int getDriverScriptsTimeout()
	{
		return driverScriptsTimeout;
	}

	/**
	 * Setter for driver scripts timeout
	 *
	 * @param driverScriptsTimeout scripts timeout
	 */
	public void setDriverScriptsTimeout(int driverScriptsTimeout)
	{
		this.driverScriptsTimeout = driverScriptsTimeout;
	}

	/**
	 * @return pageload timeout
	 */
	public int getDriverPageLoadTimeout()
	{
		return driverPageLoadTimeout;
	}

	/**
	 * Setter for driver scripts pageload timeout
	 *
	 * @param driverPageLoadTimeout pageload timeout
	 */
	public void setDriverPageLoadTimeout(int driverPageLoadTimeout)
	{
		this.driverPageLoadTimeout = driverPageLoadTimeout;
	}

	/**
	 * @return implicit timeout
	 */
	public int getDriverImplicitTimeout()
	{
		return driverImplicitTimeout;
	}

	/**
	 * @return capitalizeRandom defines if random string should be capitalized
	 */
	public boolean getCapitalizeRandom()
	{
		return capitalizeRandom;
	}

	/**
	 * Use elementsLoad.timeOut value from properties, or page load timeout value in case elementsLoad.timeOut
	 * is not set.
	 *
	 * @return elements load timeout
	 */
	public int getElementsLoadTimeOut()
	{
		return elementsLoadTimeOut != 0 ? elementsLoadTimeOut : getPageLoadTimeOut();
	}

	/**
	 * Setter for elements load timeout
	 *
	 * @param elementsLoadTimeOut elements load timeout
	 */
	public void setElementsLoadTimeOut(int elementsLoadTimeOut)
	{
		this.elementsLoadTimeOut = elementsLoadTimeOut;
	}

	/**
	 * Setter for capitalizeRandom
	 *
	 * @param capitalizeRandom new capitalizeRandom value
	 */
	public void setCapitalizeRandom(boolean capitalizeRandom)
	{
		this.capitalizeRandom = capitalizeRandom;
	}

	/**
	 * Setter for driver scripts implicit timeout
	 *
	 * @param driverImplicitTimeout implicit timeout
	 */
	public void setDriverImplicitTimeout(int driverImplicitTimeout)
	{
		this.driverImplicitTimeout = driverImplicitTimeout;
	}

	/**
	 * Method to get the application name for a specific test
	 *
	 * @return The application name
	 */
	public String getApplicationName()
	{
		return applicationName;
	}

	/**
	 * @return page load timeout in seconds
	 */
	public int getPageLoadTimeOut()
	{
		int currentTimeout = getCurrentScenarioTimeOut();
		return currentTimeout != 0 ? currentTimeout : pageLoadTimeOut;
	}

	/**
	 * Method to set the application name for a specific test
	 */
	@Value("${applicationName}")
	public void setApplicationName(String name)
	{
		this.applicationName = name;
	}

	/**
	 * Method to get the application base URL for a specific test
	 *
	 * @return The application name
	 */
	public String getApplicationURL()
	{
		return applicationURL;
	}

	/**
	 * @return timeout for the current scenario
	 */
	public int getCurrentScenarioTimeOut()
	{
		Integer timeout = globals.get(BaseConstants.SCENARIO_TIMEOUT_GLOBAL_NAME);
		return timeout != null ? timeout : 0;
	}

	/**
	 * Sets current scenario timeout
	 *
	 * @param timeout - scenario timeout
	 */
	public void setCurrentScenarioTimeout(int timeout)
	{
		globals.put(BaseConstants.SCENARIO_TIMEOUT_GLOBAL_NAME, timeout);
	}

	/**
	 * Method to set the application base URL for a specific test
	 */
	@Value("${applicationURL}")
	public void setApplicationURL(String url)
	{
		this.applicationURL = StringUtils.removeEnd(url, "/");
	}
}
