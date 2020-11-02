Feature: Alarm History

  Background: Alarm History
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    When type info "yes"
    Then expend left menu

  Scenario Outline: VMS Alarm History
    Then navigate to alarm
    Then navigate to alarm history
    Then click vms alarm history
    Then select search option "<filter>" and input values "<optionsValue1>" and "<optionsValue2>"
    Then add alarm filter and search

    Examples: 
      | filter | optionsValue1    | optionsValue2 |
      | source | uitest           |               |
      | event  | vms.device.added |               |
      | status | Open             |               |
      | range  |     160620201526 |  170620201538 |

  Scenario Outline: VAP Alarm History
    Then navigate to alarm
    Then navigate to alarm history
    Then click vap alarm history
    Then select search option "<filter>" and input values "<optionsValue1>" and "<optionsValue2>"
    Then add alarm filter and search
    Then Logout with loginOutMode "<loginOutMode>"

    Examples: 
      | filter | optionsValue1              | optionsValue2 | loginOutMode |
      | source | uitest                     |               | SingleSignOn |
      | event  | vap.event.person.detection |               | SingleSignOn |
      | status | Open                       |               | SingleSignOn |
      | range1 |               160620201526 |  170620201538 | normal       |
