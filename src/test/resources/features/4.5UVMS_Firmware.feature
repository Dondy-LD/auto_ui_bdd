Feature: UVMS Firmware

  Background: UVMS Firmware
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: Firmware Upload
    Then navigate to uvms
    Then navigate to uvms firmware
    Then click upload button
    Then input firmware info "<fileType>" "<version>" and "<comment>"
    Then select file "<filepath>" to upload
    Then save the firmware

    Examples: 
      | fileType     | version | comment | filepath                                                                                      |
      | general-rtsp | uitest  | upload  | D:\\vms\\framework\\ui\\framework\\bdd\\src\\test\\resources\\uploadFiles\\firmwareUpload.bat |

  Scenario Outline: Firmware Search
    Then navigate to uvms
    Then navigate to uvms firmware
    Then select search firmware option "<filter>" and input values "<optionsValue>"
    Then add filter and search
    Then search device results should like "<expectedResult>"

    Examples: 
      | filter | optionsValue | expectedResult |
      | model  | general-rtsp | True           |
      | name   | digicap      | True           |

  Scenario Outline: Firmware Update
    Then navigate to uvms
    Then navigate to uvms firmware
    Then select search firmware option "<filter>" and input values "<optionsValue>"
    Then add filter and search
    Then select firmware to update
    Then input firmware info "<fileType>" "<version>" and "<comment>"
    Then update the firmware with "<expectedResult>"

    Examples: 
      | filter | optionsValue | fileType  | version | comment    | expectedResult |
      | name   | uitest.dav   | onvif-ipc |     1.0 | testUpdate | True           |

  Scenario Outline: Firmware Delete
    Then navigate to uvms
    Then navigate to uvms firmware
    Then select search firmware option "<filter>" and input values "<optionsValue>"
    Then add filter and search
    Then checked item to delete
    Then click delete firmware button

    Examples: 
      | filter | optionsValue   |
      | name   | testdelete.dav |
