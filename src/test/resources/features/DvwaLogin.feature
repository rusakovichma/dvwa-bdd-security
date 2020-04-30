Feature: Login Feature
  Verify if user is able to Login and Logout in to DVWA

  Scenario: Login as a authenticated user
    Given user is on DVMA homepage
    When user enters admin and password
    Then Home page is displayed