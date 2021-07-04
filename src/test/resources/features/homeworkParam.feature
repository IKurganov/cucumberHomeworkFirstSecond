Feature: Search in Google

  @homeworkParams
  Scenario Outline: Successful search in Google
    Given We have webdriver for parametrized test
    When Getting to Google.com to search something "<something>"
    Then Can find some information about something and see it in titles "<titles>"

    Examples:
      | something | titles |
      | 1 | 1 |
      | 2 | 2 |
      | текст | текст |
