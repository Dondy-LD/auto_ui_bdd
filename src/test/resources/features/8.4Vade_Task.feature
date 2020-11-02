Feature: Vade Task

  Background: Vade Task
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: Vade Task Search
    Then navigate to vade
    Then navigate to task
    Then select vade search option "<filter>" and input values "<optionsValue>"
    Then add filter and click search
    Then validate search result with "<expectedResult>"

    Examples: 
      | filter   | optionsValue | expectedResult |
      | taskName | eee          | True           |
