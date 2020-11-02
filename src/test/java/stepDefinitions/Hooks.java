package stepDefinitions;


import org.openqa.selenium.WebDriver;

import common.Unility;
import cucumber.TestContent;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import enums.Context;


public class Hooks{
    TestContent testContent;

    public Hooks(TestContent content) {
        testContent = content;
    }

    @Before(order=0)
    public void beforeScenarioStart(){
        System.out.println("-----------------Start of Scenario-----------------");
    }


    @Before(order=1)
    public void beforeScenario(){
        System.out.println("Start the browser and Clear the cookies");
    }


    // An important thing to note about the after hook is that even in case of test fail, after hook will execute for sure.
    @After(order=1)
    public void afterScenario(){
        System.out.println("Log out the user and close the browser");
    }

    @After(order=0)
    public void afterScenarioFinish(Scenario scenario){
        // Error Handle (Skipped, Failed, Pending, Undefined)
        if (!scenario.getStatus().equalsIgnoreCase("passed")) {
        	System.out.println("-----------------Handle Expection-----------------" + scenario.getName());
            Unility.screenshot((WebDriver) testContent.scenarioContext.getContext(Context.Driver));
         // Scenario.embed(((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES), "image/png");
         // Close browser
//            ((WebDriver) testContent.scenarioContext.getContext(Context.Driver)).quit();
        }
        System.out.println("-----------------End of Scenario-----------------");
    }

    @Then("^Close Browser$")
    public void closeBrowser() throws Throwable {
    	TestContent.webDriverManager.getDriver().close();
    }

}

