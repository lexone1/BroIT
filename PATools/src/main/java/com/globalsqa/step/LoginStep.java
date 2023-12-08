package com.globalsqa.step;

import com.globalsqa.page.BaseDataTable;
import com.globalsqa.page.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class LoginStep extends AbstractStepDefinition
{
    @Autowired
    LoginPage loginPage;

    /////////////////////// Action methods //////////////////////////////

    /**
     * Login with user credentials according to data table
     * <p>
     * <b>Date Table Headers:</b> <ul> <li>username <li>password </ul>
     */
    @When("^I login with the following credentials:$")
    public void loginWithCredentials(DataTable table)
    {
        openLoginPage();
        enterLoginCredentials(table);
        clickSignInButton();
    }

    /**
     * Navigates to application Home page using URL
     */
    @Given("^the login page of the PATools website$")
    public void openLoginPage()
    {
        loginPage.getNavigation().goHome();
    }

    /**
     * Enters user credentials according to data table
     * <p>
     * <b>Date Table Headers:</b> <ul> <li>username <li>password </ul>
     */
    @When("^I enter the following user credentials:$")
    public void enterLoginCredentials(DataTable table)
    {
        BaseDataTable t = new BaseDataTable(table);
        String username = t.getValue("Username");
        String password = t.getValue("Password");
        loginPage.getUsernameTextField().enterText(username);
        loginPage.getPasswordTextField().enterText(password);
    }

    /**
     * Clicks Sign In button on the Login page
     */
    @When("^I click 'Sign In' button$")
    public void clickSignInButton()
    {
        loginPage.getSignInButton().click();
    }

    /////////////////////// Verification methods //////////////////////////////

    /**
     * Clicks Sign In button on the Login page
     */
    @When("^I should see the PATools brand icon$")
    public void verifyPAToolsBrandIconDisplayed()
    {
        assertTrue("PATools brand icon is not displayed. ", loginPage.getPAToolsBrandIcon().isVisible());
    }
}
