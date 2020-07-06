Feature: SQL injection Execution
  SQL injection vulnerability

  Scenario: Attacker perform simple SQL injection and gain access to the users list
    Given user login the application with 'admin' and 'password'
    And go to 'vulnerabilities/sqli/' page
    When user enters "%' or '0'='0" and clicks 'Submit'
    Then user gains access to the following list of users
        | admin | admin |
        | Gordon | Brown |
        | Hack | Me |
        | Pablo | Picasso |
        | Bob | Smith |