Feature: Overview

  Background: Overview
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario: Overview
    Then navigate to overview
    Then click system status
    Then run the check
