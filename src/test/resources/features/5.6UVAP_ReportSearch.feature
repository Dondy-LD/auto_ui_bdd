Feature: UVAP Report

  Background: UVAP Report
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: UVAP Report Search
    Then navigate to uvap
    Then navigate to report
    Then navigate to report search
    Then select report search option "<vaType>" and "<vaInstanceType>"
    Then search report results should like "<expectedResult>"

    Examples: 
      | vaType           | vaInstanceType | expectedResult |
      | Face Recognition | JOB_VA         | True           |
      #| VAP CROWD        | LIVE_VA        | True           |
