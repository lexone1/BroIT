package com.globalsqa.page;

import com.globalsqa.common.annotation.QAComponent;
import com.globalsqa.common.element.ButtonElement;
import com.globalsqa.common.element.CheckboxElement;
import com.globalsqa.common.element.LinkElement;
import com.globalsqa.common.element.PageElement;
import com.globalsqa.common.element.TextElement;

@QAComponent("cartPage")
public class CartPage extends AbstractPage
{
	/**
	 * Returns the 'Add To Cart' button
	 *
	 * @return {@link ButtonElement}
	 */
	public ButtonElement getAddToCartButton()
	{
		return factory.getElement(ButtonElement.class, by.xpath("cartPage.addToCartButton"));
	}

	/**
	 * Returns the 'Continue Shopping' button
	 *
	 * @return {@link ButtonElement}
	 */
	public ButtonElement getContinueShoppingButton()
	{
		return factory.getElement(ButtonElement.class, by.xpath("cartPage.continueShoppingButton"));
	}

	/**
	 * Returns the 'Continue Shopping' button
	 *
	 * @return {@link ButtonElement}
	 */
	public ButtonElement getProceedToCheckoutButton()
	{
		return factory.getElement(ButtonElement.class, by.xpath("cartPage.proceedToCheckoutButton"));
	}

	/**
	 * Returns the 'Cart' icon
	 *
	 * @return {@link PageElement}
	 */
	public PageElement getCartIcon()
	{
		return factory.getElement(PageElement.class, by.xpath("cartPage.cartIcon"));
	}

	/**
	 * Returns the 'First Name' text field
	 *
	 * @return {@link TextElement}
	 */
	public TextElement getFirstNameTextField()
	{
		return factory.getElement(TextElement.class, by.ID("cartPage.firstNameTextField"));
	}

	/**
	 * Returns the 'Last Name' text field
	 *
	 * @return {@link TextElement}
	 */
	public TextElement getLastNameTextField()
	{
		return factory.getElement(TextElement.class, by.ID("cartPage.lastNameTextField"));
	}

	/**
	 * Returns the 'Email' text field
	 *
	 * @return {@link TextElement}
	 */
	public TextElement getEmailTextField()
	{
		return factory.getElement(TextElement.class, by.ID("cartPage.emailTextField"));
	}

	/**
	 * Returns the 'Agree to the terms' checkbox
	 *
	 * @return {@link CheckboxElement}
	 */
	public CheckboxElement getAgreeToTheTermsCheckbox()
	{
		return factory.getElement(CheckboxElement.class, by.xpath("cartPage.agreeToTheTermsCheckbox"));
	}

	/**
	 * Returns the 'Customer data privacy' checkbox
	 *
	 * @return {@link CheckboxElement}
	 */
	public CheckboxElement getCustomerDataPrivacyCheckbox()
	{
		return factory.getElement(CheckboxElement.class, by.xpath("cartPage.customerDataPrivacyCheckbox"));
	}

	/**
	 * Returns the 'I agree to the terms of service' checkbox
	 *
	 * @return {@link CheckboxElement}
	 */
	public CheckboxElement getAgreeToTheTermsOfServiceCheckbox()
	{
		return factory.getElement(CheckboxElement.class, by.xpath("cartPage.agreeToTheTermsOfServiceCheckbox"));
	}

	/**
	 * Returns the 'Continue' button
	 *
	 * @return {@link ButtonElement}
	 */
	public ButtonElement getContinueRegisterNewCustomerButton()
	{
		return factory.getElement(ButtonElement.class, by.xpath("cartPage.continueRegisterNewCustomerButton"));
	}

	/**
	 * Returns the 'Address' text field
	 *
	 * @return {@link TextElement}
	 */
	public TextElement getAddressTextField()
	{
		return factory.getElement(TextElement.class, by.ID("cartPage.addressTextField"));
	}

	/**
	 * Returns the 'Zip/Postal Code' text field
	 *
	 * @return {@link TextElement}
	 */
	public TextElement getZipPostalCodeTextField()
	{
		return factory.getElement(TextElement.class, by.ID("cartPage.zipPostalCodeTextField"));
	}

	/**
	 * Returns the 'City' text field
	 *
	 * @return {@link TextElement}
	 */
	public TextElement getCityTextField()
	{
		return factory.getElement(TextElement.class, by.ID("cartPage.cityTextField"));
	}

	/**
	 * Returns the 'Continue' button
	 *
	 * @return {@link ButtonElement}
	 */
	public ButtonElement getContinueConfirmAddressesButton()
	{
		return factory.getElement(ButtonElement.class, by.name("cartPage.continueConfirmAddressesButton"));
	}

	/**
	 * Returns the text message on Adding product PopUp
	 *
	 * @return {@link PageElement}
	 */
	public PageElement getProductPopUpMessageLabel()
	{
		return factory.getElement(PageElement.class, by.ID("cartPage.productPopUpMessageLabel"));
	}

	/**
	 * Returns the product PopUp
	 *
	 * @return {@link PageElement}
	 */
	public PageElement getProductPopUp()
	{
		return factory.getElement(PageElement.class, by.xpath("cartPage.productPopUp"));
	}

	/**
	 * Returns the Order Confirmation Cart
	 *
	 * @return {@link PageElement}
	 */
	public PageElement getOrderConfirmationCart()
	{
		return factory.getElement(PageElement.class, by.ID("cartPage.orderConfirmationCart"));
	}

	/**
	 * Returns the 'Continue' button
	 *
	 * @return {@link ButtonElement}
	 */
	public ButtonElement getContinueConfirmDeliveryOptionButton()
	{
		return factory.getElement(ButtonElement.class, by.name("cartPage.continueConfirmDeliveryOptionButton"));
	}

	/**
	 * Returns the 'Continue' button
	 *
	 * @return {@link PageElement}
	 */
	public PageElement getPayByCheckRadioButton()
	{
		return factory.getElement(PageElement.class, by.xpath("cartPage.payByCheckRadioButton"));
	}

	/**
	 * Returns the 'Place Order' button
	 *
	 * @return {@link ButtonElement}
	 */
	public ButtonElement getPlaceOrderButton()
	{
		return factory.getElement(ButtonElement.class, by.xpath("cartPage.placeOrderButton"));
	}
}
