@CreateMultipleCustomersOnly
Feature: Create Multiple Customers

  @CreateMultipleCustomer @SanityTest @RegressionTest
  Scenario Outline: Create a new Customer
    Given user is on the Create "New Customer" page
    When the user enters Name as "<Name>", Gender as "<Gender>", DateOfBirth as "<DateOfBirth>", Address as "<Address>", City as "<City>", State as "<State>", PinCode as "<PinCode>", Mobile Number as "<PhoneNumber>", Email as "<Email>" and Password as "<Password>"
    And clicks on the submit button
    Then a new customer account should be created successfully
    And Customer Name "<Name>" and PhoneNumber "<PhoneNumber>" should be displayed
    And the user saves customer Id as "<Name>" in test properties file for future use

    Examples:
      | Name      | Gender | DateOfBirth | Address           | City      | State     | PinCode | PhoneNumber | Email                 | Password         |
      | P Pavan   | Male   | 2000-03-06  | HNO 25 S R Nagar  | Hyderabad | Telangana | 500038  | 9987654321  | customer456@gmail.com | TestCustomer@123 |
      | T Surabhi | Female | 2000-03-06  | HNO 35 Jublihills | Hyderabad | Telangana | 500045  | 9987654322  | customer567@gmail.com | TestCustomer@234 |
      | M Kishore | Male   | 2000-03-06  | HNO 45 Madhapur   | Hyderabad | Telangana | 500081  | 9987654323  | customer678@gmail.com | TestCustomer@345 |



