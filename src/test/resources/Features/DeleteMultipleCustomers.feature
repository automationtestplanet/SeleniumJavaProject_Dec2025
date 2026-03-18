@DeleteMultipleCustomersOnly
Feature: Delete Multiple Customers

  @DeleteMultipleCustomers @SanityTest @RegressionTest
  Scenario Outline: Delete Customers
    Given user is on the "Delete Customer" page
    When the user enters customer Id "<customerId>"
    And clicks on the delete submit button
    Then a customer should be deleted successfully

    Examples:
      | customerId   |
      | P Pavan |
      | T Surabhi |
      | M Kishore |