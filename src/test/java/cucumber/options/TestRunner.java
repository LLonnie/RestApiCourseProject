package cucumber.options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/features",
		glue = "stepDefinitions",
		tags = "@DeletePlace"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
