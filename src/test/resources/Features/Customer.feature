@CreateAndDeleteCustomer
Feature: As a user I want to create a Customer so that I can manage my customer account information.

  @CreateCustomer @SmokeTest @RegressionTest
  Scenario: Create a new Customer
    Given user is on the Create "New Customer" page
    When the user enters Name as "M Sriraj", Gender as "Male", DateOfBirth as "2000-03-06", Address as "HNO 25 S R Nagar", City as "Hyderabad", State as "Telangana", PinCode as "500043", Mobile Number as "9987654324", Email as "customer789@gmail.com" and Password as "TestCustomer@456"
    And clicks on the submit button
    Then a new customer account should be created successfully
    And Customer Name "M Sriraj" and PhoneNumber "9987654324" should be displayed
    And the user saves customer Id as "M Sriraj" in test properties file for future use

  @DeleteCustomer @SmokeTest @RegressionTest
  Scenario: Delete Customer
    Given user is on the "Delete Customer" page
    When the user enters customer Id "M Sriraj"
    And clicks on the delete submit button
    Then a customer should be deleted successfully

