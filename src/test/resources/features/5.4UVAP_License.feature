Feature: UVAP License

  Background: UVAP License
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: UVAP License Search
    Then navigate to uvap
    Then navigate to uvap license
    Then select engines search option "<filter>" and input values "<optionsValue>"
    Then add engines filter and search
    Then search engines results should like "<expectedResult>"

    Examples: 
      | filter  | optionsValue | expectedResult |
      | gateway | vitg         | True           |
