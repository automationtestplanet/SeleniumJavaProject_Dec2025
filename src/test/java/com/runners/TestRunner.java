package com.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features", glue = "com.bdd.cucumber",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        dryRun = false

)

public class TestRunner extends AbstractTestNGCucumberTests {
}
