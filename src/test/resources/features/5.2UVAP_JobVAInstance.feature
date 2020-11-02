Feature: UVAP Job VA Instance

  Background: UVAP Job VA Instance
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: UVAP Job VA Instance Search
    Then navigate to uvap
    Then navigate to uvap va instance
    Then navigate to job va instance
    Then select instance search option "<filter>" and input values "<optionsValue>"
    Then add engines filter and search
    Then search engines results should like "<expectedResult>"

    Examples: 
      | filter       | optionsValue | expectedResult |
      | engineName   | NCS VITG FR  | True           |
      | instanceName | QA Job       | True           |
      | status       | COMPLETED    | True           |
      | priority     | HIGH         | True           |

  Scenario Outline: UVAP Job VA Instance Edit
    Then navigate to uvap
    Then navigate to uvap va instance
    Then navigate to job va instance
    Then select instance search option "<filter>" and input values "<optionsValue>"
    Then add engines filter and search
    Then click instance operation and select edit info
    Then input instance info with "<instanceName>"
    Then update the instance with "<expectedResult>"

    Examples: 
      | filter       | optionsValue            | instanceName             | expectedResult |
      | instanceName | test_crowd_job_instance2 | test_crowd_job_instance21 | True           |

  Scenario Outline: UVAP Job VA Instance Delete
    Then navigate to uvap
    Then navigate to uvap va instance
    Then navigate to job va instance
    Then select instance search option "<filter>" and input values "<optionsValue>"
    Then add engines filter and search
    Then click instance operation and select delete item

    Examples: 
      | filter       | optionsValue             |
      | instanceName | test_yitu_job_instance4 |
