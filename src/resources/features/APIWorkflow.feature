Feature: Syntax HRMS API Workflow
  Description: This feature files tests Syntax HRMS API Workflow
Background:
  Given a JWT is generated
  @APIWorkflow
  Scenario: Creating an Employee
    Given a request is prepared to create an employee
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employeeID "Employee.employee_id" is stored as a global variable to be used for other calls

    Scenario: Retrieving created employee
      Given a request is prepared to retrieve the created employee
      When a GET call is made to retrieve the created employee
      Then the status code for retrieving the created employee is 200
      And the retrieved employee ID "" matches the globally stored emplooyee ID
      And the retrieved data at "" matches the data used to create an employee with employee ID ""
   |emp_firstname|emp_middle_name |emp_lastname |emp_gender |emp_birthday |emp_status |emp_job_title |
   |rwerwerw |wertyuytujerer | sfsdfdsfsdfsdfsdf|F |1991-09-10           |Employee   |API Tester |
