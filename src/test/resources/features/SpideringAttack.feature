Feature: DVWA application spidering
  The attacker can spider the application to discover new resources or URLs

  Scenario: Spider the main page
    Given perform spidering with 'Zap' DAST tool on the main page: 'http://localhost/'
    Then there is no any unrestricted URLs, functionality, Medium or High security issues found