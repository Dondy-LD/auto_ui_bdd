Feature: UVMS Video Device

  Background: UVMS Video Device
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: UVMS Video Device Add
    Then navigate to uvms
    Then navigate to uvms video device
    Then click add device button
    Then input device info with "<deviceName>" "<deviceUri>" "<username>" "<password>" and "<deviceMode>"
    Then save the device with "<expectedResult>"

    Examples: 
      | deviceName | deviceUri             | username | password | deviceMode   | expectedResult |
      | uitest001  | rtsp://10.20.5.2:554/ | admin    | ms1234   | general-rtsp | True           |
      | uitest002  | rtsp://10.20.5.3:554/ | admin    | ms1234   | general-rtsp | True           |

  Scenario Outline: UVMS Video Device Search
    Then navigate to uvms
    Then navigate to uvms video device
    Then select search option "<filter>" and input values "<optionsValue>"
    Then add filter and search
    Then search device results should like "<expectedResult>"

    Examples: 
      | filter | optionsValue | expectedResult |
      | name   | uitest002    | True           |
      | url    | 10.20.5.9    | True           |
      | model  | general-rtsp | True           |
      | status | active       | True           |

  Scenario Outline: UVMS Video Device Update
    Then navigate to uvms
    Then navigate to uvms video device
    Then select search option "<filter>" and input values "<optionsValue>"
    Then add filter and search
    Then click device to update
    Then input device info with "<deviceName>" "<deviceUri>" "<username>" "<password>" and "<deviceMode>"
    Then update the device with "<expectedResult>"

    Examples: 
      | filter | optionsValue | deviceName | deviceUri | username | password | deviceMode | expectedResult |
      | name   | uitest002    | uitest0021 |           | admin    | ms1234   |            | True           |

  Scenario Outline: UVMS Video Device Delete
    Then navigate to uvms
    Then navigate to uvms video device
    Then select search option "<filter>" and input values "<optionsValue>"
    Then add filter and search
    Then checked item to delete
    Then click delete device button

    Examples: 
      | filter | optionsValue |
      | name   | uitest0021   |
