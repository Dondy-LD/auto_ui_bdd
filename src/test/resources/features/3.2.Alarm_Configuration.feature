Feature: Alarm Configuration

  Background: Alarm Configuration
    Given user is on Home Page with "yes"
    And enter username "normaluser" and password "normaluser"
    Then expend left menu

  Scenario Outline: Alarm Configuration Create
    Then navigate to alarm
    Then navigate to alarm configuration
    Then click create alarm button
    Then input alarm info "<alarmName>" "<severity>" "<status>" and "<type>"
    Then save the new alarm with "<expectedResult>"

    Examples: 
      | alarmName | severity | status  | type          | expectedResult |
      | uitest002 | Minor    | Enabled | Delete Device | True           |

  Scenario Outline: Alarm Configuration Search
    Then navigate to alarm
    Then navigate to alarm configuration
    Then input search alarm conf option "<filter>" and "<optionsValue>"
    Then add alarm filter and search
    Then search alarm conf results should like "<expectedResult>"

    Examples: 
      | filter   | optionsValue     | expectedResult |
      | status   | Enabled          | True           |
      | name     | uitest001        | True           |
      | type     | vms.device.added | True           |
      | severity | H                | True           |

  Scenario Outline: Alarm Configuration Update
    Then navigate to alarm
    Then navigate to alarm configuration
    Then input search alarm conf option "<filter>" and "<optionsValue>"
    Then add alarm filter and search
    Then select item to update
    Then click update alarm
    Then input alarm info "<alarmName>" "<severity>" "<status>" and "<type>"
    Then update the alarm with "<expectedResult>"

    Examples: 
      | filter | optionsValue | alarmName | severity | status | type | expectedResult |
      | name   | uitest003    | uitest003 | Major    |        |      | True           |

  Scenario Outline: Alarm Configuration Setting
    Then navigate to alarm
    Then navigate to alarm configuration
    Then input search alarm conf option "<filter>" and "<optionsValue>"
    Then add alarm filter and search
    Then select item to update
    Then click alarm setting
    Then input alarm setting info "<method>" "<ifsubscribe>" "<singleNotification>" and "<timePeriod>"
    Then update the alarm with "<expectedResult>"

    Examples: 
      | filter | optionsValue | method | ifsubscribe | singleNotification | timePeriod | expectedResult |
      | name   | uitest003    | all    | Yes         | Yes                |         30 | True           |

  Scenario Outline: Alarm Configuration Delivery
    Then navigate to alarm
    Then navigate to alarm configuration
    Then input search alarm conf option "<filter>" and "<optionsValue>"
    Then add alarm filter and search
    Then select item to update
    Then click alarm delivery
    Then search user with "<userId>"
    Then checked user
    Then update the alarm with "<expectedResult>"

    Examples: 
      | filter | optionsValue | userId          | expectedResult |
      | name   | uitest003    | securitytester1 | True           |

  Scenario Outline: Alarm Configuration Delete
    Then navigate to alarm
    Then navigate to alarm configuration
    Then input search alarm conf option "<filter>" and "<optionsValue>"
    Then add alarm filter and search
    Then select item to update
    Then click delete button

    Examples: 
      | filter | optionsValue |
      | name   | uitest002    |
