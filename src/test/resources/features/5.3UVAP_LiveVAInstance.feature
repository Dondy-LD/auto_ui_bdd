Feature: UVAP Live VA Instance

  Background: UVAP Live VA Instance
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  #Scenario Outline: UVAP Live VA Instance Search
    #Then navigate to uvap
    #Then navigate to uvap va instance
    #Then navigate to live va instance
    #Then select instance search option "<filter>" and input values "<optionsValue>"
    #Then add engines filter and search
    #Then search engines results should like "<expectedResult>"
#
    #Examples: 
      #| filter        | optionsValue             | expectedResult |
      #| engineName    | NCS VITG FR              | True           |
      #| instanceName2 | test_yitu_live_instance1 | True           |

  Scenario Outline: UVAP Live VA Instance Edit
    Then navigate to uvap
    Then navigate to uvap va instance
    Then navigate to live va instance
    Then select instance search option "<filter>" and input values "<optionsValue>"
    Then add engines filter and search
    Then click instance operation and select edit info
    Then input instance info with "<instanceName>"
    Then update the live instance with "<expectedResult>"

    Examples: 
      | filter        | optionsValue             | instanceName              | expectedResult |
      | instanceName2 | test_yitu_live_instance1 | test_yitu_live_instance12 | True           |

  #Scenario Outline: UVAP Job VA Instance Delete
    #Then navigate to uvap
    #Then navigate to uvap va instance
    #Then navigate to live va instance
    #Then select instance search option "<filter>" and input values "<optionsValue>"
    #Then add engines filter and search
    #Then click instance operation and select delete item
#
    #Examples: 
      #| filter        | optionsValue              |
      #| instanceName2 | test_crowd_live_instance1 |
