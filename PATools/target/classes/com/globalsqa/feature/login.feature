@PATools
Feature: Login
  As a default User I can login
  to the application

  Background:
    Given the login page of the PATools website

  Scenario: Login verification
    When I enter the following user credentials:
      | Username                 | Password                 |
      | PROPERTY_defaultUsername | PROPERTY_defaultPassword |
    And I click 'Sign In' button
    Then I should see the PATools brand icon