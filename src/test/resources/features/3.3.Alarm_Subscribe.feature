Feature: Alarm Subscribe

  Background: Alarm Subscribe
    Given user is on Home Page with "yes"
    And enter username "normaluser" and password "normaluser"
    Then expend left menu

  Scenario Outline: Alarm Subscribe Search
    Then navigate to alarm
    Then navigate to alarm subscribe
    Then input search alarm conf option "<filter>" and "<optionsValue>"
    Then add alarm filter and search
    Then search alarm conf results should like "<expectedResult>"

    Examples: 
      | filter            | optionsValue     | expectedResult |
      | status            | Enabled          | True           |
      | subscribeName     | uitest002        | True           |
      | subscribeType     | vms.device.added | True           |
      | subscribeSeverity | H                | True           |

  Scenario Outline: Alarm Subscribe Update
    Then navigate to alarm
    Then navigate to alarm subscribe
    Then input search alarm conf option "<filter>" and "<optionsValue>"
    Then add alarm filter and search
    Then update the notification method "<methodName>"
    Then save the update result

    Examples: 
      | filter        | optionsValue | methodName |
      | subscribeName | uitest001    | Email      |
