Feature: Vade Data Type

  Background: Vade Data Type
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: Vade Data Type Add
    Then navigate to vade
    Then navigate to data type
    Then click the add button
    Then input data type info "<dataTypeName>" "<desc>" "<entry>" and "<label>"
    Then click save dataType button with "<expectedResult>"

    Examples: 
      | dataTypeName | desc      | entry | label | expectedResult |
      | uitest001    | uitest001 | model |       | True           |

  Scenario Outline: Vade Data Type Search
    Then navigate to vade
    Then navigate to data type
    Then select vade search option "<filter>" and input values "<optionsValue>"
    Then add filter and click search
    Then validate search result with "<expectedResult>"

    Examples: 
      | filter       | optionsValue | expectedResult |
      | entry        | model        | True           |
      | dataTypeName | uitest001    | True           |

  Scenario Outline: Vade Data Type Delete
    Then navigate to vade
    Then navigate to data type
    Then select vade search option "<filter>" and input values "<optionsValue>"
    Then add filter and click search
    Then selected item
    Then delete the datatype
    Then validate if delete dataType successfully

    Examples: 
      | filter       | optionsValue |
      | dataTypeName | uitest002    |
