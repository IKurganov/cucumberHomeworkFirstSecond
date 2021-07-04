Feature: Getting to Google


  @homework
  Scenario: Successful access to Google
    Given We have webdriver
    When Getting to Google.com
    Then We get right statuscode from server


