Feature: Command Execution
  DVWA remote code execution vulnerability test

  Scenario: User try to read passwd file content
    Given user logins with 'admin' and 'password'
    And go to 'vulnerabilities/exec' page
    When user enters '192.168.1.106; cat /etc/passwd' and clicks 'Submit'
    Then user sees the content of passwd file and root's 'root:x:0:0:root:/root:/bin/bash' pwd string