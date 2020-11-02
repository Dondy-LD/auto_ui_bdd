Feature: Vade Task Type

  Background: Vade Task Type
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: Vade Task Type Add
    Then navigate to vade
    Then navigate to task type
    Then click the add button
    Then input task type info "<taskTypeName>" "<entry>" and "<desc>"
    Then click save taskType button with "<expectedResult>"

    Examples: 
      | taskTypeName | entry    | desc    | expectedResult |
      | test001      | training | test001 | True           |
      | test003      | training | test003 | True           |

  Scenario Outline: Vade Task Type Search
    Then navigate to vade
    Then navigate to task type
    Then select vade search option "<filter>" and input values "<optionsValue>"
    Then add filter and click search
    Then validate search result with "<expectedResult>"

    Examples: 
      | filter       | optionsValue | expectedResult |
      | entry        | training     | True           |
      | taskTypeName | test001      | True           |

  Scenario Outline: Vade Data Type Delete
    Then navigate to vade
    Then navigate to task type
    Then select vade search option "<filter>" and input values "<optionsValue>"
    Then add filter and click search
    Then selected item
    Then delete the tasktype
    Then validate if delete taskType successfully

    Examples: 
      | filter       | optionsValue |
      | taskTypeName | test002      |
