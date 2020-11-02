Feature: Security User Group

  Background: Security User Group
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: User Group Add Group
    Then navigate to security
    Then navigate to user group
    Then click add Group button
    Then select parent user group "<parentGroupName>"
    Then input group info with "<groupName>" "<groupDesc>" and "<licenseNum>"
    Then click save add group button
    Then save group result like "<expectedResult>"

    Examples: 
      | parentGroupName | groupName | groupDesc | licenseNum | expectedResult |
      | Singapore       | Uitest001 | Uitest001 |          1 | True           |
      | Singapore       | Uitest002 | Uitest002 |          1 | True           |

  Scenario Outline: User Group Search Group
    Then navigate to security
    Then navigate to user group
    Then input search user group "<filter>"
    Then search group result like "<expectedResult>"

    Examples: 
      | filter        | expectedResult |
      | Singapore     | True           |
      | invalideGroup | False          |

  Scenario Outline: User Group Update Group
    Then navigate to security
    Then navigate to user group
    Then select parent user group "<parentGroupName>"
    Then input group info with "<groupName>" and "<groupDesc>"
    Then save group result like "<expectedResult>"

    Examples: 
      | parentGroupName | groupName  | groupDesc | expectedResult |
      | Uitest001       | Uitest0011 | Uitest001 | True           |
      | Uitest002       |            |           | False          |

  Scenario Outline: User Group Assign User To Group
    Then navigate to security
    Then navigate to user group
    Then select parent user group "<parentGroupName>"
    Then search assign user "<userId>"
    Then select the user
    Then assign user

    Examples: 
      | parentGroupName | userId      |
      | Uitest003       | uitester002 |

  Scenario Outline: User Group Assign channel To Group
    Then navigate to security
    Then navigate to user group
    Then select parent user group "<parentGroupName>"
    Then click assign channels
    Then search assign channel "<channelName>"
    Then select the channel
    Then assign channels

    Examples: 
      | parentGroupName | channelName |
      | Uitest004       | uitest003   |

  Scenario Outline: User Management Assign engine To Group
    Then navigate to security
    Then navigate to user group
    Then select parent user group "<parentGroupName>"
    Then click assign va engines
    Then search assign engine "<engineName>"
    Then select the engine
    Then assign va engines

    Examples: 
      | parentGroupName | engineName |
      | Uitest003       | Yitu       |

  Scenario Outline: User Group Delete
    Then navigate to security
    Then navigate to user group
    Then select parent user group "<parentGroupName>"
    Then click delete user group button
    Then click confirm delete user group button

    Examples: 
      | parentGroupName |
      | testdelete      |
