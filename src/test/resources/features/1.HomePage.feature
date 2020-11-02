Feature: Login Page

  Background: Login Page

  Scenario Outline: Login
    Given user is on Home Page with "<single>"
    And enter username "<username>" and password "<password>"
    When type info "<correct>"
    Then click expend all menu
    Then login should be "<expectedResult>"
    Then Logout with loginOutMode "<loginOutMode>"

    Examples: 
      | single | username | password      | correct | expectedResult | loginOutMode |
      | yes    | jasontan |               | no      | False          | SingleSignOn |
      | yes    |          | Security2017! | no      | False          | SingleSignOn |
      | yes    | chenya   | chenya        | no      | False          | SingleSignOn |
      | yes    | chenya   | Security2017! | yes     | True           | normal       |

  Scenario Outline: Forget Password
    Given user is on Home Page with "<single>"
    Then click forget password link
    And type in the user info "<userId>" and "<email>"
    Then click send button
    Then the behavior is "<expectedResult>"
    Then Logout with loginOutMode "<loginOutMode>"

    Examples: 
      | single | userId      | email                | expectedResult | loginOutMode |
      | yes    | uitester001 | uitester001@mail.com | True           | SingleSignOn |
      | yes    | chenya1     |                      | False          | SingleSignOn |
      | yes    |             | jacky@ncs.com.sg     | False          | SingleSignOn |
      | yes    | uitest      | uitest@mail.com      | False          | normal       |
