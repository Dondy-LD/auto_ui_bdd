package cucumber;

import org.openqa.selenium.WebDriver;

import managers.PageObjectManager;
import managers.WebDriverManager;


public class TestContent {

    public static WebDriverManager webDriverManager;
    public static PageObjectManager pageObjectManager;
    
    //share data between steps
    public ScenarioContext scenarioContext;

    public TestContent(){
//    	webDriverManager = new WebDriverManager();
    	scenarioContext = new ScenarioContext();
//        pageObjectManager = new PageObjectManager(webDriverManager.getDriver());
    }
     

    public static WebDriverManager getWebDriverManager() { 
    	return (webDriverManager == null) ? new WebDriverManager() : webDriverManager;
    }

    public PageObjectManager getPageObjectManager(WebDriver driver) { 
    	pageObjectManager = new PageObjectManager(driver);
    	return pageObjectManager; 
    }

    public ScenarioContext getScenarioContext() { return scenarioContext; }

}