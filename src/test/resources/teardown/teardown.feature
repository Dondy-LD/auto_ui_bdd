Feature: TearDown

  Background: TearDown

  Scenario Outline: User Management Delete User
    Given user is on Home Page with "yes"
    And enter username "superadmin" and password "IVHa123@2020"
    Then expend left menu
    Then navigate to security
    Then navigate to user management
    Then input search option "<filter>" and "<optionsValue>"
    Then add filter and click search user
    Then selected item
    Then click batch delete user button
    Then click confirm delete user button
    Then clear search conditions

    Examples: 
      | filter | optionsValue |
      | userId | uitester001  |
      | userId | uitester002  |
      | userId | uitester005  |

  Scenario Outline: User Group Delete
    Given user is on Home Page with "yes"
    And enter username "superadmin" and password "IVHa123@2020"
    Then expend left menu
    Then navigate to security
    Then navigate to user group
    Then select parent user group "<parentGroupName>"
    Then click delete user group button
    Then click confirm delete user group button

    Examples: 
      | parentGroupName |
      | Uitest0011      |
      | Uitest002       |
      | Uitest003       |
      | Uitest004       |

  Scenario Outline: Security Role Management Delete Role
    Given user is on Home Page with "yes"
    And enter username "superadmin" and password "IVHa123@2020"
    Then expend left menu
    Then navigate to security
    Then navigate to role management
    Then input search role option "<filter>" and "<optionsValue>"
    Then add filter and click search user
    Then selected item
    Then click delete role button
    Then click delete role confirm button
    Then Logout with loginOutMode "<loginOutMode>"

    Examples: 
      | filter | optionsValue | loginOutMode |
      | name   | uitest001    | SingleSignOn |
      | name   | uitest002    | SingleSignOn |
      | name   | uitest003    | SingleSignOn |
      | name   | uitest004    | SingleSignOn |
      | name   | uitest005    | normal       |

  Scenario Outline: Alarm Configuration Delete
    Given user is on Home Page with "yes"
    And enter username "normaluser" and password "normaluser"
    Then expend left menu
    Then navigate to alarm
    Then navigate to alarm configuration
    Then input search alarm conf option "<filter>" and "<optionsValue>"
    Then add alarm filter and search
    Then select item to update
    Then click delete button

    Examples: 
      | filter | optionsValue |
      | name   | uitest001    |
      | name   | uitest003    |

  Scenario Outline: UVMS Video Device Delete
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu
    Then navigate to uvms
    Then navigate to uvms video device
    Then select search option "<filter>" and input values "<optionsValue>"
    Then add filter and search
    Then checked item to delete
    Then click delete device button

    Examples: 
      | filter | optionsValue |
      | name   | uitest001    |
      | name   | uitest003    |

  Scenario Outline: Channel Group Delete
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu
    Then navigate to uvms
    Then navigate to uvms channel group
    Then select parent channel group "<parentGroupName>"
    Then click delete channel group button

    Examples: 
      | parentGroupName |
      | uitest1         |
      | uitest2         |
      | uitest3         |
      | uitest4         |

  Scenario Outline: Firmware Delete
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu
    Then navigate to uvms
    Then navigate to uvms firmware
    Then select search firmware option "<filter>" and input values "<optionsValue>"
    Then add filter and search
    Then checked item to delete
    Then click delete firmware button

    Examples: 
      | filter | optionsValue |
      | name   | uitest.dav   |

  Scenario Outline: Vade Data Type Delete
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu
    Then navigate to vade
    Then navigate to data type
    Then select vade search option "<filter>" and input values "<optionsValue>"
    Then add filter and click search
    Then selected item
    Then delete the datatype
    Then validate if delete dataType successfully

    Examples: 
      | filter       | optionsValue |
      | dataTypeName | uitest001    |

  Scenario Outline: Vade Data Type Delete
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu
    Then navigate to vade
    Then navigate to task type
    Then select vade search option "<filter>" and input values "<optionsValue>"
    Then add filter and click search
    Then selected item
    Then delete the tasktype
    Then validate if delete taskType successfully

    Examples: 
      | filter       | optionsValue |
      | taskTypeName | test001      |
      | taskTypeName | test003      |
