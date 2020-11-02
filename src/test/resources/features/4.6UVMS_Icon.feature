Feature: UVMS Icon Setup

  Background: UVMS Icon Setup
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: Upload Icon
    Then navigate to uvms
    Then navigate to uvms icon
    Then change icon for select "<channelType>"
    Then click choose file
    Then input upload file "<filepath>"
    Then save the icon

    Examples: 
      | channelType | filepath                                                                                  |
      | onvif-ipc   | D:\\vms\\framework\\ui\\framework\\bdd\\src\\test\\resources\\uploadFiles\\iconUpload.bat |

  Scenario Outline: Delete Icon
    Then navigate to uvms
    Then navigate to uvms icon
    Then move cursor icon for select "<channelType>"
    Then confirm delete

    Examples: 
      | channelType  |
      | general-rtsp |
