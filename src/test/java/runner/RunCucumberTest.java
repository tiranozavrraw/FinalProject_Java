package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith( Cucumber.class )
@CucumberOptions(glue = {"tests"}, features = "src/test/resources/")
public class RunCucumberTest {
}
