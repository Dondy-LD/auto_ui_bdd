Feature: Security User Management

  Background: Security User Management
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario: User Management Add User
    Then navigate to security
    Then navigate to user management
    Then click adduser button
    Then load data from excel and createUser

  Scenario Outline: User Management Search User
    Then navigate to security
    Then navigate to user management
    Then input search option "<filter>" and "<optionsValue>"
    Then add filter and click search user
    Then search results should like "<expectedResult>"

    Examples: 
      | filter    | optionsValue         | expectedResult |
      | userId    | uitester001          | True           |
      | fullName  | uitester001          | True           |
      | groupName | invalideGroup        | False          |
      | email     | uitester001@mail.com | True           |

  Scenario Outline: User Management Update User
    Then navigate to security
    Then navigate to user management
    Then input search option "<filter>" and "<optionsValue>"
    Then add filter and click search user
    Then click update userInfo button
    Then enter update user info "<userName>" "<email>" and "<phoneNumber>"
    Then update user result should like "<expectedResult>"

    Examples: 
      | filter | optionsValue | userName    | email | phoneNumber | expectedResult |
      | userId | uitester002  |             |       |             | False          |
      | userId | uitester002  |             | N     | N           | False          |
      | userId | uitester002  | uitester009 | N     | N           | True           |

  Scenario Outline: User Management Assign Role
    Then navigate to security
    Then navigate to user management
    Then input search option "<filter>" and "<optionsValue>"
    Then add filter and click search user
    Then click assign role button
    Then assign role with "<roleName>"
    Then click save assign role button

    Examples: 
      | filter | optionsValue | roleName  |
      | userId | uitester001  | uitest001 |

  Scenario Outline: User Management Delete User
    Then navigate to security
    Then navigate to user management
    Then input search option "<filter>" and "<optionsValue>"
    Then add filter and click search user
    Then selected item
    Then click batch delete user button
    Then click confirm delete user button

    Examples: 
      | filter | optionsValue |
      | userId | testdelete   |
