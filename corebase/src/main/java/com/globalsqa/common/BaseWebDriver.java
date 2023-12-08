package com.globalsqa.common;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import com.globalsqa.common.annotation.QAComponent;
import com.globalsqa.common.exception.BaseException;

/**
 * Utility methods to interact with WebDriver
 *
 * @author Aliaksei Pershyts
 */
@QAComponent("webDriver")
public class BaseWebDriver
{
	private RemoteWebDriver remoteDriver;
	@Autowired
	TestConfiguration testConfiguration;

	@PostConstruct
	public void init()
	{
		startDriver();
	}

	/**
	 * Setup remote Chrome driver.
	 */
	void startDriver()
	{
		System.setProperty("webdriver.chrome.driver","D:\\TestBroIT\\chrome\\chromedriver.exe");
		remoteDriver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized"); // open Browser in maximized mode
		options.addArguments("disable-infobars"); // disabling infobars
		options.addArguments("--disable-extensions"); // disabling extensions
		options.addArguments("--disable-gpu"); // applicable to windows os only
		options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		options.addArguments("--no-sandbox"); // Bypass OS security model
		maximizeWindow();
		remoteDriver.manage().timeouts().setScriptTimeout(testConfiguration.getDriverScriptsTimeout(),
				TimeUnit.SECONDS);
		remoteDriver.manage().timeouts().pageLoadTimeout(testConfiguration.getDriverPageLoadTimeout(),
				TimeUnit.SECONDS);
		remoteDriver.manage().timeouts().implicitlyWait(testConfiguration.getDriverImplicitTimeout(),
				TimeUnit.SECONDS);
	}

	/**
	 * Maximizes browser window.
	 */
	public void maximizeWindow()
	{
		remoteDriver.manage().window().maximize();
	}

	/**
	 * Returns current page URL
	 *
	 * @return current URL
	 */
	public String getCurrentURL()
	{
		return remoteDriver.getCurrentUrl();
	}

	public void goToURL(String url)
	{
		try
		{
			remoteDriver.navigate().to(url);
		}
		catch (TimeoutException e)
		{
			remoteDriver.navigate().refresh();
			throw new BaseException("Unexpected Browser Freeze!", e);
		}
	}

	@PreDestroy
	public void close()
	{
		remoteDriver.quit();
	}

	public String getPageTitle()
	{
		return remoteDriver.getTitle();
	}

	/**
	 * Waits for an element to appear on a page.
	 *
	 * @param by               an element identifier for which to wait
	 * @param maxWaitInSeconds the number seconds for which to wait
	 * @return web element
	 */
	public WebElement waitForElement(By by, Integer maxWaitInSeconds)
	{
		final int waitInSeconds = (maxWaitInSeconds != null) ?
				maxWaitInSeconds : testConfiguration.getElementsLoadTimeOut();
		return (new WebDriverWait(remoteDriver, waitInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
	}

	/**
	 * Waits default waiting time for an element to appear on a page.
	 *
	 * @param by an element identifier for which to wait
	 * @return a web element
	 */
	public WebElement waitForElement(By by)
	{
		return waitForElement(by, null);
	}

	/**
	 * Get elements without waiting and exceptions
	 *
	 * @param by an element identifier
	 */
	public List<WebElement> findElements(By by)
	{
		return remoteDriver.findElements(by);
	}

	/**
	 * Waits for an elements to appear on a page.
	 *
	 * @param by               an element identifier for which to wait
	 * @param maxWaitInSeconds the number seconds for which to wait
	 * @return list of web elements
	 */
	public List<WebElement> waitForElements(By by, Integer maxWaitInSeconds)
	{
		final int waitInSeconds = (maxWaitInSeconds != null) ?
				maxWaitInSeconds : testConfiguration.getElementsLoadTimeOut();
		return (new WebDriverWait(remoteDriver, waitInSeconds)).until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(by));
	}

	/**
	 * Select a frame using its previously located {@link WebElement}.
	 *
	 * @param frame - The frame element to switch to
	 */
	public void switchToFrame(WebElement frame)
	{
		remoteDriver.switchTo().frame(frame);
	}

	/**
	 * Simple sleep wrapper.
	 *
	 * @param sleepMilliseconds
	 */
	public void sleep(int sleepMilliseconds)
	{
		try
		{
			Thread.sleep(sleepMilliseconds);
		}
		catch (Exception e)
		{
		}
	}
}
