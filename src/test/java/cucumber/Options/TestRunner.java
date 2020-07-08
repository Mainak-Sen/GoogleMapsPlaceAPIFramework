package cucumber.Options;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features="src/test/java/features",glue= {"StepDefinition"},
dryRun=false,monochrome=true,strict=true,plugin= {"json:target/jsonReports/API_Framework.json"}//,tags="@AddPlace"
		)
public class TestRunner extends AbstractTestNGCucumberTests{

}
