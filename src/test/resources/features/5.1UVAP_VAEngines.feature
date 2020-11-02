Feature: UVAP VA Engines

  Background: UVAP VA Engines
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: UVAP VA Engines Search
    Then navigate to uvap
    Then navigate to uvap va engines
    Then select engines search option "<filter>" and input values "<optionsValue>"
    Then add engines filter and search
    Then search engines results should like "<expectedResult>"

    Examples: 
      | filter  | optionsValue | expectedResult |
      | name    | uitest       | True           |
      | label   | FRS          | True           |
      | desc    | uitest       | True           |
      | gateway | vitg         | True           |
      | status  | ERROR        | True           |
      | vendor  | NCS          | True           |

  Scenario Outline: UVAP VA Engines Edit
    Then navigate to uvap
    Then navigate to uvap va engines
    Then select engines search option "<filter>" and input values "<optionsValue>"
    Then add engines filter and search
    Then click operation and select edit info
    Then input engine info with "<engineName>" "<vendor>" and "<engineDesc>"
    Then update the engine with "<expectedResult>"

    Examples: 
      | filter | optionsValue | engineName   | vendor     | engineDesc | expectedResult |
      | name   | crowd-count  | crowd-count1 | NCS - VITG | Test001    | True           |

  Scenario Outline: UVAP VA Engines Delete
    Then navigate to uvap
    Then navigate to uvap va engines
    Then select engines search option "<filter>" and input values "<optionsValue>"
    Then add engines filter and search
    Then click operation and select delete item

    Examples: 
      | filter | optionsValue |
      | name   | pc           |
