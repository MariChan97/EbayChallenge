Feature: Try to buy on Ebay
  @Ebay
  Scenario: As a customer I want to buy shoes on Ebay
    Given I navigate to "www.ebay.com"
    When I search for shoes brand puma number ten
    And I print the number of results
    Then I print the name and price from the first five results from minor to major
    And Print the products name in ascendent form
    And Print the products price in descendent form
