Feature: As a user I want to fill shipping information

  Background: Preconditions
    Given I have successfully landed in Home Page, list of items has been showed
    When I tap on a product card named: 'Sauce Lab Back Packs'
    Then I have successfully landed in Product Detail page with the desired product name
    And I set the product quantity to: 2
    And I set the product color to: 'Blue'
    And I click Add to Cart button
    Then I should see the cart icon has a quantity balloon
    And I click the Cart Icon
    Then I have successfully landed in Cart page with the desired product already in there
    And I have successfully landed in Cart page with the desired product already in there
    When I see that the product information is already correct
    And I click Proceed to Checkout button
    Then I have redirected to Login page
    And I have redirected to Login page
    When I filled the username and password form with stored credentials data
    And I clicked on Login button
    Then I have redirected to Shipping Address form page to complete my purchase

  Scenario: Filling Shipping Information
    Given I have redirected to Shipping Address form page to complete my purchase
    When I entered my personal data into the shipping form
    And I click Proceed to Checkout button
    Then I have redirected to Payment page