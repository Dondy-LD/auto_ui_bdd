Feature: Setup

  Background: Setup

  #Scenario Outline: Create System admin account and User admin user account
  #Given user is on Home Page with "yes"
  #And enter username "superadmin" and password "IVHa123@2020"
  #Then expend left menu
  #Then navigate to security
  #Then navigate to user management
  #Then input search option "<filter>" and "<optionsValue>"
  #Then add filter and click search user
  #Then check user whether exist
  #Then if not find specify user create user "<UserId>" "<UserName>" "<Email>" "<PhoneNumber>" else skip it
  #Then if need to logout
  #Then if need to relogin with "<username>" and "<password>"
  #Then if need to change the password "<defaultPassword>" and "<newPassword>"
  #
  #Examples:
  #| filter | optionsValue   | UserId         | UserName       | Email                   | PhoneNumber | username       | password | defaultPassword | newPassword   |
  #| userId | chenya         | chenya         | chenya         | chenya@mail.com         |    88989991 | chenya         | P@ssw0rd | P@ssw0rd        | Security2017! |
  #| userId | securitytester | securitytester | securitytester | securitytester@mail.com |    88989992 | securitytester | P@ssw0rd | P@ssw0rd        | Security2017! |
  #
  #Scenario Outline: User Management Assign Role
  #Given user is on Home Page with "yes"
  #And enter username "superadmin" and password "IVHa123@2020"
  #Then expend left menu
  #Then navigate to security
  #Then navigate to user management
  #Then input search option "<filter>" and "<optionsValue>"
  #Then add filter and click search user
  #Then click assign role button
  #Then assign role with "<roleName>"
  #Then click save assign role button
  #
  #Examples:
  #| filter | optionsValue   | roleName             |
  #| userId | chenya         | System Administrator |
  #| userId | securitytester | User Administrator   |
  Scenario Outline: User Group Add Group
    Given user is on Home Page with "yes"
    And enter username "chenya" and password "Security2017!"
    Then expend left menu
    Then navigate to security
    Then navigate to user group
    Then click add Group button
    Then select parent user group "<parentGroupName>"
    Then input group info with "<groupName>" "<groupDesc>" and "<licenseNum>"
    Then click save add group button
    Then save group result like "<expectedResult>"

    Examples: 
      | parentGroupName | groupName  | groupDesc  | licenseNum | expectedResult |
      #| Singapore       | uiauto     | uiauto     |         20 | True           |
      | Singapore       | Uitest003  | Uitest003  |         20 | True           |
      | Singapore       | Uitest004  | Uitest004  |         20 | True           |
      | Singapore       | testdelete | testdelete |         20 | True           |

  #Scenario Outline: User Group Assign User To Group
  #Given user is on Home Page with "yes"
  #And enter username "chenya" and password "Security2017!"
  #Then expend left menu
  #Then navigate to security
  #Then navigate to user group
  #Then select parent user group "<parentGroupName>"
  #Then search assign user "<userId>"
  #Then select the user
  #Then assign user
  #
  #Examples:
  #| parentGroupName | userId         |
  #| uiauto          | securitytester |
  Scenario Outline: Security Role Management Add Role
    Given user is on Home Page with "yes"
    And enter username "chenya" and password "Security2017!"
    Then expend left menu
    Then navigate to security
    Then navigate to role management
    Then click add role button
    Then input role info "<roleName>" and "<roleDesc>"
    Then click save role button with "<expectedResult>"

    Examples: 
      | roleName   | roleDesc   | expectedResult |
      | uitest001  | uitest001  | True           |
      | uitest002  | uitest002  | True           |
      | uitest003  | uitest003  | True           |
      | testdelete | testdelete | True           |

  Scenario Outline: Create normal user account
    Given user is on Home Page with "yes"
    And enter username "chenya" and password "Security2017!"
    Then expend left menu
    Then navigate to security
    Then navigate to user management
    Then input search option "<filter>" and "<optionsValue>"
    Then add filter and click search user
    Then check user whether exist
    Then if not find specify user create user "<UserId>" "<UserName>" "<Email>" "<PhoneNumber>" else skip it

    Examples: 
      | filter | optionsValue | UserId      | UserName    | Email                | PhoneNumber |
      | userId | uitester001  | uitester001 | uitester001 | uitester001@mail.com |    88909991 |
      | userId | uitester002  | uitester002 | uitester002 | uitester002@mail.com |    88909992 |
      | userId | testdelete   | testdelete  | testdelete  | testdelete@mail.com  |    88909993 |

  #| userId | securitytester1 | securitytester1 | securitytester1 | securitytester1@mail.com |    88909994 |
  Scenario Outline: UVMS Video Device Add
    Given user is on Home Page with "yes"
    And enter username "chenya" and password "Security2017!"
    Then expend left menu
    Then navigate to uvms
    Then navigate to uvms video device
    Then click add device button
    Then input device info with "<deviceName>" "<deviceUri>" "<username>" "<password>" and "<deviceMode>"
    Then save the device with "<expectedResult>"

    Examples: 
      | deviceName | deviceUri             | username | password | deviceMode   | expectedResult |
      | uitest003  | rtsp://10.20.5.6:554/ | admin    | ms1234   | general-rtsp | True           |

  Scenario Outline: Alarm Configuration Create
    Given user is on Home Page with "yes"
    And enter username "normaluser" and password "normaluser"
    Then expend left menu
    Then navigate to alarm
    Then navigate to alarm configuration
    Then click create alarm button
    Then input alarm info "<alarmName>" "<severity>" "<status>" and "<type>"
    Then save the new alarm with "<expectedResult>"

    Examples: 
      | alarmName | severity | status  | type       | expectedResult |
      | uitest001 | Critical | Enabled | Add Device | True           |
      | uitest003 | Critical | Enabled | VAP FRS    | True           |

  Scenario Outline: UVMS Video Channel Group Add
    Given user is on Home Page with "yes"
    And enter username "chenya" and password "Security2017!"
    Then expend left menu
    Then navigate to uvms
    Then navigate to uvms channel group
    Then click add channel group button
    Then select parent channel group "<parentGroupName>"
    Then input channel group info with "<groupName>" "<groupDesc>" and "<buildName>"
    Then click save add channel group button
    Then save channel group result like "<expectedResult>"

    Examples: 
      | parentGroupName | groupName  | groupDesc  | buildName  | expectedResult |
      | Singapore       | testdelete | testdelete | testdelete | True           |

  Scenario Outline: Vade Data Type Add
    Given user is on Home Page with "yes"
    And enter username "chenya" and password "Security2017!"
    Then expend left menu
    Then navigate to vade
    Then navigate to data type
    Then click the add button
    Then input data type info "<dataTypeName>" "<desc>" "<entry>" and "<label>"
    Then click save dataType button with "<expectedResult>"

    Examples: 
      | dataTypeName | desc      | entry | label | expectedResult |
      | uitest002    | uitest002 | model |       | True           |

  Scenario Outline: Vade Task Type Add
    Given user is on Home Page with "yes"
    And enter username "chenya" and password "Security2017!"
    Then expend left menu
    Then navigate to vade
    Then navigate to task type
    Then click the add button
    Then input task type info "<taskTypeName>" "<entry>" and "<desc>"
    Then click save taskType button with "<expectedResult>"

    Examples: 
      | taskTypeName | entry    | desc    | expectedResult |
      | test002      | training | test002 | True           |
  #need to prepare firmware、va engine、pending access、 job vainstance and live vainstance
