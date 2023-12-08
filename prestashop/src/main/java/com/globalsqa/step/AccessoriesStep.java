package com.globalsqa.step;

import org.springframework.beans.factory.annotation.Autowired;

import com.globalsqa.page.AccessoriesPage;
import com.globalsqa.page.BaseDataTable;
import com.globalsqa.page.HomePage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;

public class AccessoriesStep extends AbstractStepDefinition
{
	@Autowired
	HomePage homePage;
	@Autowired
	AccessoriesPage accessoriesPage;
	@Autowired
	CartStep cartStep;

	/////////////////////// Action methods //////////////////////////////

	/**
	 * Navigates to 'Accessories' page by clicking on menu item
	 */
	@When("^I (?:navigate to|open) the 'Accessories' page$")
	public void navigateToAccessoriesPage()
	{
		homePage.getAccessoriesMenuItemLink().click();
	}

	/**
	 * Clicks on Product name according to data table
	 * <p>
	 * <b>Date Table Headers:</b> <ul> <li>Product Name</ul>
	 */
	@When("^I choose (?:a|another) Product with the following values:$")
	public void chooseProduct(DataTable table)
	{
		BaseDataTable t = new BaseDataTable(table);
		String productName = t.getValue("Product Name");
		accessoriesPage.getProductNameLink(productName).click();
	}

	/**
	 * Adda Product to Cart according to data table
	 * <p>
	 * <b>Date Table Headers:</b> <ul> <li>Product Name</ul>
	 */
	@When("^I add (?:a|another) Product to Cart with the following values:$")
	public void addProductToCart(DataTable table)
	{
		chooseProduct(table);
		cartStep.addProductToCart();
	}
}
