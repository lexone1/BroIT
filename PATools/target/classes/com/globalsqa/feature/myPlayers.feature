@PATools
Feature: My Players
  As a default User I can manage
  adding players

  Background:
    Given I login with the following credentials:
      | Username                 | Password                 |
      | PROPERTY_defaultUsername | PROPERTY_defaultPassword |

  Scenario: Add new Player verification
    Given I add new Player with the following values:
      | Tag                | ID | Account Name               |
      | autoTag_+RANDOM_5+ | 1  | autoAccountName_+RANDOM_3+ |
    When I navigate to 'My Players' page
    Then I should see 'My Players' table with the following values:
      | Tag              | ID              | Account Name             |
      | GLOBAL_playerTag | GLOBAL_playerID | GLOBAL_playerAccountName |