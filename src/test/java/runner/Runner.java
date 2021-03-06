package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
		(

		//features = "C:/Users/fariya.wani/eclipse-workspace/DRL/src/test/java/feature/drl.feature",
	   features="src/test/java/feature",
		glue = { "stepdefinition" },
		plugin = {
				"pretty", "html:target/cucumber-reports/cucumberhtml.html",
				"json:target/cucumber-reports/CucumberTestReport.json",
				"junit:target/cucumber-reports/Cucumberjunit.xml" },
		// plugin = ("json:target/cucumber-reports/CucumberTestReport.json"),

		tags = "@Smoketest1 or @Smoketest2 or @Smoketest3 or @Smoketest4 or @Smoketest5",
		publish = true,
		monochrome = true
		)

public class Runner
{

	

}
