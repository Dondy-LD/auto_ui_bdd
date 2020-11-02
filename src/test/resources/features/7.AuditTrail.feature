Feature: Audit Trail

  Background: Audit Trail
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: Audit Trail Search
    Then navigate to auditTrail
    Then select auditTrail search option "<filter>" and input values "<optionsValue>"
    Then add engines filter and search
    Then search engines results should like "<expectedResult>"

    Examples: 
      | filter     | optionsValue    | expectedResult |
      | userId     | chenya          | True           |
      | actionType | Real Time Alarm | True           |
      | response   | Success         | True           |
