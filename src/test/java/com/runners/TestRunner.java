package com.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features", glue = "com.bdd.cucumber",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        tags = "@SmokeTest",
        dryRun = false


)

public class TestRunner extends AbstractTestNGCucumberTests {
}
