Feature: Vade Program

  Background: Vade Program
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: Vade Program Search
    Then navigate to vade
    Then navigate to program
    Then select vade search option "<filter>" and input values "<optionsValue>"
    Then add filter and click search
    Then validate search result with "<expectedResult>"

    Examples: 
      | filter      | optionsValue | expectedResult |
      | entry       | training     | True           |
      | programName | eee          | True           |
