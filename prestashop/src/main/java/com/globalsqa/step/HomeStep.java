package com.globalsqa.step;

import org.springframework.beans.factory.annotation.Autowired;

import com.globalsqa.page.HomePage;

import io.cucumber.java.en.When;

public class HomeStep extends AbstractStepDefinition
{
	@Autowired
	HomePage homePage;

	/////////////////////// Action methods //////////////////////////////

	/**
	 * Navigates to application Home page using URL as an Unauthorized User
	 */
	@When("^I open the Application as an Unauthorized User$")
	public void openApplicationAsUnauthorizedUser()
	{
		homePage.getNavigation().goHome();
		homePage.getMainPrestashopFrame().switchToFrame();
	}

}
