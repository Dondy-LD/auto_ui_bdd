Feature: Security Role Management

  Background: Security Role Management
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: Security Role Management Add Role
    Then navigate to security
    Then navigate to role management
    Then click add role button
    Then input role info "<roleName>" and "<roleDesc>"
    Then click save role button with "<expectedResult>"

    Examples: 
      | roleName  | roleDesc  | expectedResult |
      | uitest004 | uitest004 | True           |
      | uitest005 | uitest005 | True           |

  Scenario Outline: Security Role Management Search Role
    Then navigate to security
    Then navigate to role management
    Then input search role option "<filter>" and "<optionsValue>"
    Then add filter and click search user
    Then search role results should like "<expectedResult>"

    Examples: 
      | filter | optionsValue | expectedResult |
      | name   | uitest001    | True           |
      | desc   | uitest002    | True           |

  Scenario Outline: Security Role Management Update Role
    Then navigate to security
    Then navigate to role management
    Then input search role option "<filter>" and "<optionsValue>"
    Then add filter and click search user
    Then click update role info button
    Then enter update role info "<roleName>" and "<roleDesc>"
    Then save update role info with "<expectedResult>"

    Examples: 
      | filter | optionsValue | roleName  | roleDesc   | expectedResult |
      | name   | uitest003    | uitest003 | uitest0031 | True           |

  Scenario Outline: Security Role Management Assign Feature
    Then navigate to security
    Then navigate to role management
    Then input search role option "<filter>" and "<optionsValue>"
    Then add filter and click search user
    Then click feature button
    Then select feature options "<featureName>"
    Then save assign features

    Examples: 
      | filter | optionsValue | featureName |
      | name   | uitest002    | Menu        |

  Scenario Outline: Security Role Management Delete Role
    Then navigate to security
    Then navigate to role management
    Then input search role option "<filter>" and "<optionsValue>"
    Then add filter and click search user
    Then selected item
    Then click delete role button
    Then click delete role confirm button

    Examples: 
      | filter | optionsValue |
      | name   | testdelete   |
