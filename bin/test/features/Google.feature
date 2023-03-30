Feature: Test Google search functionality

  @google
    Scenario: As a user I enter a search criteria in Goggle
      Given I am on the Google search page
      When I enter a search criteria
      And click on the search button
      Then results match the criteria