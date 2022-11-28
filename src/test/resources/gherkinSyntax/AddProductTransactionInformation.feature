Feature: As a user I want to add product transaction information

  Background: Preconditions
    Given I have successfully landed in Home Page, list of items has been showed
    When I tap on a product card named: 'Sauce Lab Back Packs'
    Then I have successfully landed in Product Detail page with the desired product name

  Scenario: Add Product Transaction Information and Add to Cart
    Given I have successfully landed in Product Detail page with the desired product name
    When I set the product quantity to: 2
    And I set the product color to: 'Blue'
    And I click Add to Cart button
    Then I should see the cart icon has a quantity balloon
    And I click the Cart Icon
    Then I have successfully landed in Cart page with the desired product already in there