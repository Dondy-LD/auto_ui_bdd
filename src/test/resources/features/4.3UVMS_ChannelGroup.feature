Feature: UVMS Video Channel Group

  Background: UVMS Video Channel Group
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: UVMS Video Channel Group Add
    Then navigate to uvms
    Then navigate to uvms channel group
    Then click add channel group button
    Then select parent channel group "<parentGroupName>"
    Then input channel group info with "<groupName>" "<groupDesc>" and "<buildName>"
    Then click save add channel group button
    Then save channel group result like "<expectedResult>"

    Examples: 
      | parentGroupName | groupName | groupDesc | buildName | expectedResult |
      | Singapore       | uitest3   | uitest3   | uitest1   | True           |
      | Singapore       | uitest4   | uitest4   | uitest2   | True           |

  Scenario Outline: Channel Group Search Group
    Then navigate to uvms
    Then navigate to uvms channel group
    Then input search channel group "<groupName>"
    Then search channel group result like "<expectedResult>"

    Examples: 
      | groupName | expectedResult |
      | uitest    | True           |

  Scenario Outline: Channel Group Assign channel To Group
    Then navigate to uvms
    Then navigate to uvms channel group
    Then select parent channel group "<parentGroupName>"
    Then assign channel to channel group with "<channelName>" and "<expectedResult>"

    Examples: #all mean select all current page users
      | parentGroupName | channelName | expectedResult |
      | uitest          | all         | True           |

  Scenario Outline: Channel Group Update
    Then navigate to uvms
    Then navigate to uvms channel group
    Then select parent channel group "<parentGroupName>"
    Then input channel group info with "<groupName>" "<groupDesc>" and "<buildName>"
    Then update group result like "<expectedResult>"

    Examples: 
      | parentGroupName | groupName | groupDesc  | buildName | expectedResult |
      | uitest          | uitest    | uitest1234 | uitest    | True           |

  Scenario Outline: Channel Group Delete
    Then navigate to uvms
    Then navigate to uvms channel group
    Then select parent channel group "<parentGroupName>"
    Then click delete channel group button

    Examples: 
      | parentGroupName |
      | testdelete      |
