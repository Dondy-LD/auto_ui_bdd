Feature: UVMS Video Channel

  Background: UVMS Video Channel
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: UVMS Video Channel Search
    Then navigate to uvms
    Then navigate to uvms channel
    Then select search channel option "<filter>" and input values "<optionsValue>"
    Then add filter and search
    Then search device results should like "<expectedResult>"

    Examples: 
      | filter    | optionsValue | expectedResult |
      | name      | uitest001    | True           |
      | device    | general-rtsp | True           |
      | groupName | uitest       | True           |
      | mode      | General RTSP | True           |

  Scenario Outline: UVMS Video Channel Update
    Then navigate to uvms
    Then navigate to uvms channel
    Then select search channel option "<filter>" and input values "<optionsValue>"
    Then add filter and search
    Then click channel to update
    Then input update channel info "<channelName>"
    Then update the channel with "<expectedResult>"

    Examples: 
      | filter | optionsValue | channelName | expectedResult |
      | name   | uitest001    | uitest001_1 | True           |

  Scenario Outline: UVMS Video Channel Delete
    Then navigate to uvms
    Then navigate to uvms channel
    Then select search channel option "<filter>" and input values "<optionsValue>"
    Then add filter and search
    Then checked item to delete
    Then click delete Channel button

    Examples: 
      | filter | optionsValue |
      | name   | uitest001    |
