package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import org.junit.runners.MethodSorters;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class  HardCodedExamples {


    /*
     * NOTE:
     *
     * Given - Preparing the request
     *
     * When - making the request/making the call/hitting the endpoint
     *
     * Then - verification/assertions
     */
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjcwNTMzNzgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYyNzA5NjU3OCwidXNlcklkIjoiMjk2NSJ9.agbLUGn2DKGExQTOKAZYZpUz3RgUKxlFnCPR3ilSyQE";
    static String employee_id;

    //    @Test
    public void sampleTest() {

        RequestSpecification preparedRequest = given().header("Authorization", token)
                .header("Content-Type", "application/json").queryParam("employee_id", "22890A");

        Response response = preparedRequest.when().get("/getOneEmployee.php");

        System.out.println(response.asString());

    }

    @Test
    public void aPostCreateEmployee() {

        RequestSpecification preparedrequest = given().header("Authorization", token).header("Content-Type", "application/json").body("{\n" +
                "  \"emp_firstname\": \"rwerwerw\",\n" +
                "  \"emp_lastname\": \"sfsdfdsfsdfsdfsdf\",\n" +
                "  \"emp_middle_name\": \"wertyuytujerer\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"1991-09-10\",\n" +
                "  \"emp_status\": \"Employee\",\n" +
                "  \"emp_job_title\": \"API Tester\"\n" +
                "}");
        Response response = preparedrequest.when().post("/createEmployee.php");
//prettyprint() prints all information of your request including token name and other details.
        //response.prettyPrint();
//Jason path allow us to retrieve specific data from a jason object- just like xpath with selenium

        employee_id = response.jsonPath().getString("Employee.employee_id");

//        System.out.println(employee_id);
        //Performing assertions.
        response.then().assertThat().statusCode(201);
        //Using Hamcrast Matchers class equals TO()
        response.then().assertThat().body("Message", equalTo("Employee Created"));
//write an assertion that verifies the response body has the name you used.
        response.then().assertThat().body(("Employee.emp_firstname"), equalTo("rwerwerw"));
        //verifying server name
        response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");


    }

    @Test
    public void bGetCreatedEmployee() {

        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json").queryParam("employee_id", employee_id);

        Response response = preparedRequest.when().get("/getOneEmployee.php");
//response.prettyPrint();
        String empID = response.jsonPath().getString("employee.employee_id");
        boolean camparingEmpIDS = empID.contentEquals(employee_id);
        Assert.assertTrue(camparingEmpIDS);

        //retrive the first name and assert that the first name is the same as the one you used do not use hamcrest matchers.

        String firstname = response.jsonPath().getString("employee.emp_firstname");
        Assert.assertTrue(firstname.contentEquals("rwerwerw"));

    }

//    @Test
    public void cGetAllEmployees() {

        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json");

        Response response = preparedRequest.when().get("/getAllEmployees.php");
        String allEmployees = response.prettyPrint();

        JsonPath js = new JsonPath(allEmployees);
        int count = js.getInt("Employees.size()");
        System.out.println(count);
//print out all employees IDS from the response
        for (int i = 0; i < count; i++) {
            String employeeIDs = js.getString("Employees[" + i + "].employee_id");
//        System.out.println(employeeIDs);

            if (employeeIDs.contentEquals(employee_id)) {
                System.out.println("Employee ID" + employee_id + " is present in response body");
//            retrieving employee name and veryfing the firstname is the same
                String firstName = js.getString("Employees[" + i + "].emp_fristname");
                System.out.println("Employee name is" + firstName);
                break;


            }

        }
    }
    @Test
public void dPutUpateCreatedEmployee(){

        //update the created employee with new data.
        RequestSpecification preparedRequest = given().header("Authorization", token)
                .header("Content-Type", "application/json").body("{\n" + "  \"employee_id\": \"" + employee_id + "\",\n"
                        + "  \"emp_firstname\": \"updatdmjmayar\",\n"
                        + "  \"emp_lastname\": \"sdfsdf\",\n"
                        + "  \"emp_middle_name\": \"sdfsdfff\",\n" + "  \"emp_gender\": \"F\",\n"
                        + "  \"emp_birthday\": \"1991-09-10\",\n" + "  \"emp_status\": \"Employee\",\n"
                        + "  \"emp_job_title\": \"Tester\"\n" + "}");

        Response response = preparedRequest.when().put("/updateEmployee.php");

        response.prettyPrint();

    }
}




