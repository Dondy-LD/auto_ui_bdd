package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import common.Constant;
import managers.FileReaderManager;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void navigateTo_HomePage() {
		try {
			this.driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		}catch(TimeoutException e) {
			System.out.println("Page loading timeout" + e);
		}
		
	}

	public void submitLogin(String username, String password) throws Exception {
		String name = username;
		String pass = password;
		
		if("systemuser".equalsIgnoreCase(username)) {
			name = FileReaderManager.getInstance().getConfigReader().readProperty("username");
			pass = FileReaderManager.getInstance().getConfigReader().readProperty("password");
		} else if("normaluser".equalsIgnoreCase(username)) {
			name = FileReaderManager.getInstance().getConfigReader().readProperty("normalUser");
			pass = FileReaderManager.getInstance().getConfigReader().readProperty("normalPass");
		}
		
		this.sendString(By.id("userName"), name);
		this.sendString(By.id("password"), pass);
		this.click(By.id("btnSignIn"));
	}

	public void expendAll() throws Exception {
		if(!this.WaitElement(By.xpath("//span[contains(text(),'INTEGRATED VIDEO HUB')]"), implicitlyWait)) {
			this.expendLeftMenu();
		}
	}

	public void forgetPassword() throws Exception {
		this.click(By.partialLinkText("forget your password"));
	}

	public Boolean clickSendBtn() throws Exception {
		this.click(By.xpath("//span[contains(text(),'Send')]"));
		return this.WaitElement(By.cssSelector("div[title='Success']"), implicitlyWait);
	}

	public void fillForgetPwdInfo(String userId, String email) throws Exception {
		this.sendString(By.cssSelector("input[placeholder='User ID']"), userId);
		this.sendString(By.cssSelector("input[placeholder='Email']"), email);
	}

	public Boolean validateResult(String infoCondition, String expectedResult) throws Exception {
		Boolean flag = false;
		if (Constant.Yes.equalsIgnoreCase(infoCondition) && Boolean.parseBoolean(expectedResult)) {
			if (isElementPresent(By.cssSelector("img[alt='IVH']"))) {
				flag = true;
				System.out.print("ivh login true");
			}
		} else {
			By usernameMiss = By.id("userName-helper-text");
			By passwordMiss = By.id("password-helper-text");
			By LoginError = By.cssSelector("div[title='Incorrect credentials']");
			if ((isElementPresent(usernameMiss) || isElementPresent(passwordMiss) || isElementPresent(LoginError))) {
				flag = true;
				System.out.print("ivh login false");
			}
		}
		return flag;
	}

	public Boolean validateForgetPwd(Boolean validateResult, String expectedResult) throws Exception {
		Boolean flag = false;
		if (Boolean.parseBoolean(expectedResult) && validateResult) {
			flag = true;
		}

		if (!Boolean.parseBoolean(expectedResult) && !validateResult) {
			flag = true;
		}
		
		if(!validateResult && this.isElementPresent(By.xpath("//p[contains(text(),'Mandatory Field')]"))) {
			this.click(By.xpath("//span[contains(text(),'Cancel')]"));
		}
		return flag;

	}

	public void logout(String infoCondition) throws Exception {
		if (Constant.Yes.equalsIgnoreCase(infoCondition)) {
			this.closePopUpWindow();
			this.normalLogout();
		}
	}

	public void quitDriver() throws Exception {
		this.driver.close();
	}

	public Boolean validateIfLogin() throws Exception {
		Boolean flag = false;
		By icon = By.cssSelector("img[alt='IVH']");

		if (!isElementPresent(icon) && !isElementPresent(By.id("userName"))) {
			this.expendAll();
		}

		if (isElementPresent(icon)) {
			flag = true;
		}
		return flag;
	}

}
