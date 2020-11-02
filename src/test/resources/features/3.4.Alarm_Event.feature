Feature: Alarm Event Query

  Background: Alarm Event Query
    Given user is on Home Page with "yes"
    And enter username "normaluser" and password "normaluser"
    When type info "yes"
    Then expend left menu

  Scenario Outline: Alarm Event Query Search
    Then navigate to alarm
    Then navigate to alarm event query
    Then select search option "<filter>" and input values "<optionsValue1>" and "<optionsValue2>"
    Then add alarm filter and search
    Then search alarm event results should like "<expectedResult>"
    Then Logout with loginOutMode "<loginOutMode>"

    Examples: 
      | filter | optionsValue1         | optionsValue2 | expectedResult | loginOutMode |
      | source | uitest002             |               | True           | SingleSignOn |
      | event  | vms.stream.video.loss |               | True           | SingleSignOn |
      | range2 |          160620201526 |  170620201538 | True           | normal       |
