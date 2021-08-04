package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features ="src/resources/features/APIWorkflow.feature",
        glue = "APISteps",

        //if we set dry run to true it will quickly scan all gherkin steps are implemented or not.
        //if it is true, then no actual execution happens
        dryRun=false,
        //means that console output for the cucumber test is easily readible
        //it will remove unreadable charecter.
       monochrome=true,
//if strict is = true at the time of execution if cucumber encounters any undefined steps it i will
       // give and error and stops execution and also gives code nipet for the unimplemented steps.
//
//        strict = true,

        tags= "@APIWorkflow",
//tags will indentify the scanarios based on the tags will provide such as @smoke, @regression etc
        //we can add multiple tags in the runner class to execute scenarios belong to different tag such as
        //as and or.
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json", "rerun:target/failed.txt"}



)
public class APIRunner {

}
