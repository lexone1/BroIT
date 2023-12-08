package com.globalsqa.step;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;

import com.globalsqa.common.element.PageElement;
import com.globalsqa.page.BaseDataTable;
import com.globalsqa.page.CartPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartStep extends AbstractStepDefinition
{
	@Autowired
	CartPage cartPage;

	/////////////////////// Action methods //////////////////////////////

	/**
	 * Clicks 'Add to Cart' button
	 */
	@When("^I add Product to Cart$")
	public void addProductToCart()
	{
		cartPage.getAddToCartButton().click();
	}

	/**
	 * Clicks 'Continue Shopping' button
	 */
	@When("^I continue shopping$")
	public void continueShopping()
	{
		cartPage.getContinueShoppingButton().click();
	}

	/**
	 * Clicks on 'Cart' icon to open the 'Shopping Cart'
	 */
	@When("^I open the Shopping Cart$")
	public void openShoppingCart()
	{
		cartPage.getCartIcon().click();
	}

	/**
	 * Clicks on 'Proceed to Checkout' button
	 */
	@When("^I initiate Proceed to Checkout$")
	public void initiateProceedToCheckout()
	{
		cartPage.getProceedToCheckoutButton().click();
	}

	/**
	 * Enters Personal information according to data table
	 * <p>
	 * <b>Date Table Headers:</b> <ul> <li>First name<li> Last name <li>Email <li></ul>
	 *
	 * @param table cucumber data table
	 */
	@When("^I enter Personal information according to data table:$")
	public void enterPersonalInformation(DataTable table)
	{
		BaseDataTable t = new BaseDataTable(table);

		String firstName = t.getValue("First Name");
		String lastName = t.getValue("Last Name");
		String email = t.getValue("Email");
		boolean agreeToTheTerms = Boolean.parseBoolean(t.getValue("Agree to the terms"));
		boolean customerDataPrivacy = Boolean.parseBoolean(t.getValue("Customer data privacy"));

		globals.putWithList("firstName", firstName);
		globals.putWithList("lastName", lastName);
		globals.putWithList("email", email);

		cartPage.getFirstNameTextField().enterText(firstName);
		cartPage.getLastNameTextField().enterText(lastName);
		cartPage.getEmailTextField().enterText(email);
		cartPage.getAgreeToTheTermsCheckbox().check(agreeToTheTerms);
		cartPage.getCustomerDataPrivacyCheckbox().check(customerDataPrivacy);
		cartPage.getContinueRegisterNewCustomerButton().click();
	}

	/**
	 * Enters Address information according to data table
	 * <p>
	 * <b>Date Table Headers:</b> <ul> <li>First name<li> Last name <li>Email <li></ul>
	 *
	 * @param table cucumber data table
	 */
	@When("^I enter Address information according to data table:$")
	public void enterAddressInformation(DataTable table)
	{
		BaseDataTable t = new BaseDataTable(table);

		String address = t.getValue("Address");
		String zipPostalCode = t.getValue("Zip/Postal Code");
		String city = t.getValue("City");

		cartPage.getAddressTextField().enterText(address);
		cartPage.getZipPostalCodeTextField().enterText(zipPostalCode);
		cartPage.getCityTextField().enterText(city);
		cartPage.getContinueConfirmAddressesButton().click();
	}

	/**
	 * Clicks on 'Continue' button on Shipping method
	 */
	@When("^I confirm shipping method$")
	public void confirmShippingMethod()
	{
		cartPage.getContinueConfirmDeliveryOptionButton().click();
	}

	/**
	 * Chooses default Payment method and clicks Place Order button
	 */
	@When("^I choose default Payment and Place Order$")
	public void chooseDefaultPaymentMethodAndPlaceOrder()
	{
		cartPage.getPayByCheckRadioButton().click();
		cartPage.getAgreeToTheTermsOfServiceCheckbox().check();
		cartPage.getPlaceOrderButton().click();
	}

	/////////////////////// Verification methods //////////////////////////////

	/**
	 * Chooses default Payment method and clicks Place Order button
	 *
	 * @param message message text
	 */
	@Then("^I should see \"([^\"]*)\" success message on Product PopUp$")
	public void verifyAddProductSuccessMessage(String message) throws InterruptedException
	{
		cartPage.getProductPopUp().isVisible();
		String productPopupMessageLabel = cartPage.getProductPopUpMessageLabel().getText();
		assertTrue("Message:" + message + " was not displayed.", productPopupMessageLabel.contains(message));
	}

	/**
	 * Verifies 'Order confirmation' Cart contains text according to data table
	 * <p>
	 * <b>Date Table Headers:</b> <ul>Text </ul>
	 *
	 * @param table cucumber data table
	 */
	@Then("^I should see Order confirmation Cart with the following text:$")
	public void verifyOrderConfirmationCart(DataTable table)
	{
		BaseDataTable t = new BaseDataTable(table);
		String orderConfirmationCartText = cartPage.getOrderConfirmationCart().getText();
		for (int i = 0; i < t.getNumberOfRows(); i++)
		{
			String expectedText = t.getValue(i, "Text");
			softAssert.assertTrue("Text:" + expectedText + " was not displayed.", orderConfirmationCartText.contains(expectedText));
		}
		softAssert.process();
	}
}
