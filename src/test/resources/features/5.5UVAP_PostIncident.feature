Feature: UVAP Post Incident

  Background: UVAP Post Incident
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: UVAP Post Incident Add
    Then navigate to uvap
    Then navigate to uvap post incident
    Then click the create post incident button
    Then input the incident info "<incidentName>" "<incidentType>" and "<incidentDesc>"
    Then save the new postIncident with "<expectedResult>"

    Examples: 
      | incidentName | incidentType | incidentDesc | expectedResult |
      | uitest001    | uitest001    | uitest001    | True           |

  Scenario Outline: UVAP Post Incident Search
    Then navigate to uvap
    Then navigate to uvap post incident
    Then select incident search option "<filter>" and input values "<optionsValue>"
    Then add engines filter and search
    Then search engines results should like "<expectedResult>"

    Examples: 
      | filter       | optionsValue | expectedResult |
      | incidentName | uitest001    | True           |
      | incidentType | ERROR        | True           |

  Scenario Outline: UVAP Post Incident Delete
    Then navigate to uvap
    Then navigate to uvap post incident
    Then select incident search option "<filter>" and input values "<optionsValue>"
    Then add engines filter
    Then select incident search option "<filter2>" and input values "<optionsValue2>"
    Then add engines filter and search
    Then click the options menu and select delete
    Then validate if delete postIncident successfully

    Examples: 
      | filter       | optionsValue | filter2 | optionsValue2 | expectedResult |
      | incidentName | uitest002    | range   |  020720201335 | expectedResult |
