@prestashop
@cartFunctionality
Feature: Cart Functionality
  As a default User of the system
  I can add any product to the cart
  and proceed to checkout

  Background:
    Given I open the Application as an Unauthorized User

  @highImportance
  Scenario: Multiple shopping Cart verification
    Given I open the 'Accessories' page
    And I add a Product to Cart with the following values:
      | Product Name                 |
      | PROPERTY_defaultProductName1 |
    Then I should see "Product successfully added to your shopping cart" success message on Product PopUp
    When I continue shopping
    And I open the 'Accessories' page
    And I add another Product to Cart with the following values:
      | Product Name                 |
      | PROPERTY_defaultProductName1 |
    Then I should see "Product successfully added to your shopping cart" success message on Product PopUp
    When I continue shopping
    And I open the Shopping Cart
    And I initiate Proceed to Checkout
    And I enter Personal information according to data table:
      | First Name          | Last Name           | Email               | Agree to the terms | Customer data privacy |
      | RANDOM_ALPHABETIC_6 | RANDOM_ALPHABETIC_6 | +RANDOM_6+@test.com | true               | true                  |
    And I enter Address information according to data table:
      | Address  | Zip/Postal Code  | City     |
      | RANDOM_6 | RANDOM_NUMERIC_5 | RANDOM_6 |
    And I confirm shipping method
    When I choose default Payment and Place Order
    Then I should see Order confirmation Cart with the following text:
      | Text                                                  |
      | YOUR ORDER IS CONFIRMED                               |
      | An email has been sent to the +GLOBAL_email+ address. |