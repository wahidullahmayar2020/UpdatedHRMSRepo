Feature: Adding employees
Background:
  And user is logged in with valid admin credentials
  When user clicks on PIM option
  And user clicks on Add employee button
@Smoke
Scenario: :Adding employee from employee page

  And user enters firstname and middlename and lastname
  And user clicks on save button option
  Then employee added successfully

  @Smoke
  Scenario: :Adding employee from employee page
#    And user is logged in with valid admin credentials
#    When user clicks on PIM option
#    And user clicks on Add employee button
    And user enters firstname "Yulia123" and middlename "MS" and lastname "Yulia456
    And user clicks on save button option
    Then employee added successfully

  @Smoke
  Scenario: :Adding employee from add employee page via feature file
#    And user is logged in with valid admin credentials
#    When user clicks on PIM option
#    And user clicks on Add employee button
    And user enters firstname "Yulia123" and middlename "MS" and lastname "Yulia456
    And user clicks on save button option
    Then employee added successfully

    @example
    Scenario Outline: Adding employee from add employee page via feature file
      And user enters"<firstname>" "<middlename>" and "<lastname>" in application
      And user clicks on save button option
      Then employee added successfully

      Examples:
      |firstname|middlename|lastname|
      |Test123456|MS       |Test9876|
      |Test1212  |MS       |Test7654|
      |Test3232  |MS       |Test5454|


      @datatablewithheader
      Scenario: Adding multiple employees in a single execution
        When add multiple employees and verify they are added successfully

        |firstname|middlename|lastname|
        |Jon0404  |MS        |US      |
        |Jack0404 |MS        |US      |
        |MS0909   |MS        |US      |


        @excel
        Scenario:Adding the employee from excel file
          When user adds multiple employees from excel file "newdata" sheet and verify they are added
