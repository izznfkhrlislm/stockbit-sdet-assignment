Feature: As a user I want to go to a product detail page


  Scenario: Go to product detail page
    Given I have successfully landed in Home Page, list of items has been showed
    When I tap on a product card named: 'Sauce Lab Back Packs'
    Then I have successfully landed in Product Detail page with the desired product name