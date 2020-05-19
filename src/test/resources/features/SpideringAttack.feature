Feature: DVWA application spidering
  The attacker spiders the application to discover new resources or URLs

  Scenario: Spider the main page
    Given perform spidering with 'Zap' DAST tool on the main page: 'http://localhost/'
    Then there is no any unrestricted URLs, functionality or High security issues found