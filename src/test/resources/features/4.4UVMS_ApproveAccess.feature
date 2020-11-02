Feature: UVMS Approve Access

  Background: UVMS Approve Access
    Given user is on Home Page with "yes"
    And enter username "systemuser" and password "systemuser"
    Then expend left menu

  Scenario Outline: Approve access search
    Then navigate to uvms
    Then navigate to uvms approve access
    Then input search approve access option "<filter>" and "<optionsValue>"
    Then click add access filter button
    Then click search access button
    Then search access results should like "<expectedResult>"
    Then Logout with loginOutMode "<loginOutMode>"

    Examples: 
      | filter | optionsValue | expectedResult | loginOutMode |
      | id     | RQ           | True           | SingleSignOn |
      | group  | Singapore    | True           | SingleSignOn |
      | status | P            | True           | normal       |

  Scenario Outline: Approve access approve
    Then navigate to uvms
    Then navigate to uvms approve access
    Then input search approve access option "<filter>" and "<optionsValue>"
    Then click add access filter button
    Then click search access button
    Then view the request detail
    Then approve the request and "<approveReason>"

    Examples: 
      | filter | optionsValue | approveReason  |
      | id     | RQ10         | ApproveRequest |

  Scenario Outline: Approve access reject
    Then navigate to uvms
    Then navigate to uvms approve access
    Then input search approve access option "<filter>" and "<optionsValue>"
    Then click add access filter button
    Then click search access button
    Then view the request detail
    Then reject the request and "<rejectReason>"

    Examples: 
      | filter | optionsValue | rejectReason  |
      | id     | RQ5          | rejectRequest |
