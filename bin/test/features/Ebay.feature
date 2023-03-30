Feature: Buy shoes on Ebay page.
  @Ebay
  Scenario: As a user I want to buy shoes on Ebay
    Given I navigate to "www.ebay.com"
    When I search for shoes, brand Puma, number 10
    And I print the number of results
    And I order and print the prices from minor to major
    And I print the first five product with their price
    Then I print the product names in ascending order
    And I print the prices in descendant order