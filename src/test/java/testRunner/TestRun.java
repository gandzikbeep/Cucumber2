package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {".//Features/"},
        glue = "stepDefinitions",
       // dryRun = false,
       monochrome = true,
        plugin = { "pretty","json:target/cucumber-json/cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml", "html:target/cucumber-reports"}

//        tags= "@sanity or @regression"

        //,"html:test-output"
)

public class TestRun {
}
