Feature: Vade Data

  Background: Vade Data
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: Vade Data Search
    Then navigate to vade
    Then navigate to data
    Then select vade search option "<filter>" and input values "<optionsValue>"
    Then add filter and click search
    Then validate search result with "<expectedResult>"

    Examples: 
      | filter   | optionsValue | expectedResult |
      | dataName | training     | True           |
