package runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(glue = {"tests"}, features = "src/test/resources", tags = "@UiTest", plugin = {"pretty", "html:target/site/cucumber-pretty",
        "json:target/cucumber.json"})

public class RunCucumberTests {

}