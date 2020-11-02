package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.NoSuchSessionException;

import common.Constant;
import cucumber.TestContent;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.Context;
import managers.FileReaderManager;
import managers.WebDriverManager;
import pageObjects.HomePage;

public class HomePageSteps {

	private static WebDriverManager webDriverManager;
	private static TestContent testContent;
	private static HomePage homePage;

	public HomePageSteps(TestContent content) {
		testContent = content;
	}

	@Given("^user is on Home Page with \"([^\"]*)\"$")
	public void user_is_on_Home_Page(String single) throws Exception {
		if (single.equals("no")) {
			webDriverManager = TestContent.getWebDriverManager();
			homePage = testContent.getPageObjectManager(webDriverManager.createDriver()).getHomePage();
			homePage.navigateTo_HomePage();
		} else {
			if (Boolean.parseBoolean(FileReaderManager.getInstance().getConfigReader().readProperty("SingleSignOn"))
					&& homePage == null) {
				webDriverManager = TestContent.getWebDriverManager();
				homePage = testContent.getPageObjectManager(webDriverManager.getDriver()).getHomePage();
				homePage.navigateTo_HomePage();
			}
			
			Boolean flag = false;
			try {
				webDriverManager.getDriver().getTitle();
			}catch(Exception e) {
				System.out.println(e);
				flag = e instanceof NoSuchSessionException;
				System.out.println(e.getClass().getName());
			}
			
			if(flag) {
				System.out.println("Restart the browser");
				homePage = testContent.getPageObjectManager(webDriverManager.createDriver()).getHomePage();
				homePage.navigateTo_HomePage();
			}
		}
		
		
		// Set global value which can share between different steps.
		testContent.scenarioContext.setContext(Context.SingleSignOn, single);
		testContent.scenarioContext.setContext(Context.Driver, webDriverManager.getDriver());
	}

	@And("^enter username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void fillLoginInfo(String userName, String password) throws Throwable {
		// baseOn login mode and singleSignOn if already login then no need to login
		// multiple times
		if (testContent.scenarioContext.getContext(Context.SingleSignOn).equals("no")) {
			homePage.submitLogin(userName, password);
		} else {
			if (Boolean.parseBoolean(FileReaderManager.getInstance().getConfigReader().readProperty("SingleSignOn"))
					&& !homePage.validateIfLogin()) {
				homePage.submitLogin(userName, password);
			}
		}
	}

	@And("^expend left menu$")
	public void expendLeftMenu() throws Throwable {
			homePage.expendAll();
	}

	@And("^click expend all menu$")
	public void expendAll() throws Throwable {
		if (Constant.Yes.equalsIgnoreCase((String) testContent.scenarioContext.getContext(Context.InvalideLogin))) {
			homePage.expendAll();
		}
	}

	@When("^type info \"([^\"]*)\"$")
	public void setInfoOptions(String infoCondition) throws Throwable {
		testContent.scenarioContext.setContext(Context.InvalideLogin, infoCondition);
	}

	@Then("^login should be \"([^\"]*)\"$")
	public void validateLogin(String expectedResult) throws Throwable {
		Boolean flag = homePage.validateResult((String) testContent.scenarioContext.getContext(Context.InvalideLogin),
				expectedResult);
		Assert.assertTrue(flag);
	}

	@Then("^Logout with loginOutMode \"([^\"]*)\"$")
	public void logout(String loginOutMode) throws Throwable {
		String infoCondition = (String) testContent.scenarioContext.getContext(Context.InvalideLogin);
		if (loginOutMode.equals("normal")) {
			if (Constant.Yes.equalsIgnoreCase(infoCondition)) {
				homePage.logout(infoCondition);
			} else {
				homePage.quitDriver();
			}
		} else {
			if (!Boolean.parseBoolean(FileReaderManager.getInstance().getConfigReader().readProperty("SingleSignOn"))) {
				homePage.logout(infoCondition);
			}
		}
	}

	@Then("^click forget password link$")
	public void forgetPassword() throws Throwable {
		homePage.forgetPassword();
	}

	@And("^type in the user info \"([^\"]*)\" and \"([^\"]*)\"$")
	public void fillForgetPwdInfo(String userId, String email) throws Throwable {
		homePage.fillForgetPwdInfo(userId, email);
	}
	
	@Then("^click send button$")
	public void clickSendBtn() throws Throwable {
		Boolean flag = homePage.clickSendBtn();
		testContent.scenarioContext.setContext(Context.ValidateResult, flag);
	}

	@Then("^the behavior is \"([^\"]*)\"$")
	public void validateForgetPwd(String expectedResult) throws Throwable {
		Boolean validateResult = (Boolean) testContent.scenarioContext.getContext(Context.ValidateResult);
		Boolean flag = homePage.validateForgetPwd(validateResult, expectedResult);
		Assert.assertTrue(flag);
	}

	@Then("^close the browser$")
	public void closeBrowser() throws Throwable {
		homePage.quitDriver();
	}

}