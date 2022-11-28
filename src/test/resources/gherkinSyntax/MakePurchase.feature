Feature: As a user I want to buy something from the app and provide delivery and payment information

  Scenario: End-to-End Purchase
    Given I have successfully landed in Home Page, list of items has been showed
    When I tap on a product card named: 'Sauce Lab Back Packs'
    And I have successfully landed in Product Detail page with the desired product name
    And I set the product quantity to: 2
    And I set the product color to: 'Blue'
    And I click Add to Cart button
    Then I should see the cart icon has a quantity balloon
    And I click the Cart Icon
    And I have successfully landed in Cart page with the desired product already in there
    When I see that the product information is already correct
    And I click Proceed to Checkout button
    And I have redirected to Login page
    When I filled the username and password form with stored credentials data
    And I clicked on Login button
    And I have redirected to Shipping Address form page to complete my purchase
    When I entered my personal data into the shipping form
    And I clicked to Payment button to proceed to payment page
    Then I have redirected to Payment page
    When I filled the payment information with stored credentials data
    And I clicked on Review Order button
    And I have redirected to Review Order page
    When I have checked my delivery address information is correct
    And I have checked my billing information is correct
    And I have checked my total qty of shopping is correct
    And I clicked on Place Order button
    Then I should see the Checkout Complete message as the sign of purchase successfully made