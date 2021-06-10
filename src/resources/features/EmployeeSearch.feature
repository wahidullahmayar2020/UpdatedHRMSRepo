Feature: Employee Search

  Background:
    And user is logged in with valid admin credentials
    And user navigates to employee list page


  @Smoke
  Scenario: Search employee by id
#    Given user is navigated to hrms

    When user enter valid employee id
    And click on search button
    Then user see employee information is displayed

@Smoke
  Scenario: Search employee by name
#    Given user is navigated to hrms

    And user navigates to employee list page
    When user enter valid employee name
    And click on search button
    Then user see employee information is displayed