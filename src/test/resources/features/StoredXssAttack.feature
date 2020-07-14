Feature: Stored XSS attack
  Stored XSS attack demonstration

  Scenario: User perform stored XSS attack on Guestbook
    Given user login the application with 'admin' and 'password'
    And go to 'vulnerabilities/xss_s/' page
    When user enter name 'John Doe' and message '<script>alert(document.cookie)</script>' and clicks 'Sign Guestbook'
    Then user refresh the page and see the alert with 'PHPSESSID' cookie