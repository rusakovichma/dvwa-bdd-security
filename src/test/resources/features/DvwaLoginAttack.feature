Feature: Login Feature
  Verify if user is able to Login and Logout at DVWA

  Scenario: Login as a authenticated user
    Given user is on DVMA homepage
    When user enters 'admin' and 'password'
    Then the message 'Welcome to Damn Vulnerable Web Application!' is displayed

  Scenario: Logout from the application
    Given user login the application with 'admin' and 'password'
    When user perform logout
    Then the users returns to the Home page
    And he sees the 'You have logged out' message