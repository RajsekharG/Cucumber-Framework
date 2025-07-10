Feature: Login Functionality

  @SmokeTest
  Scenario Outline: Login with valid credentials
    Given the user is on the login page.
    When user enter's "<username>" and "<password>".
    And user clicks on the login button.
    Then the user navigates to the homepage.

    Examples: 
      | username | password |
      | Admin    | admin123 |
