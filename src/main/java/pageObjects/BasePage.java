package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.Unility;
import managers.FileReaderManager;


//import cucumber.runtime.CucumberException;

public class BasePage {
	protected WebDriver driver;
	public static int implicitlyWait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		implicitlyWait = Integer.parseInt(FileReaderManager.getInstance().getConfigReader().readProperty("implicitlyWait"));
	}

	protected void sendString(By element, String s) throws Exception {
		this.WaitElement(element, implicitlyWait);
		new WebDriverWait(this.driver, implicitlyWait).until(ExpectedConditions.visibilityOf(driver.findElement(element)));
		driver.findElement(element).clear();
		System.out.print(driver.findElement(element).getText());
		System.out.print(driver.findElement(element).getAttribute("value"));
		if (driver.findElement(element).getText().length() > 0
				|| driver.findElement(element).getAttribute("value").length() > 0) {
			driver.findElement(element).click();
			driver.findElement(element).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			driver.findElement(element).sendKeys(Keys.DELETE);
		}
		driver.findElement(element).sendKeys(s);
//		Wait.defaultWait();
	}

	protected void tab(By element) throws Exception {
		this.WaitElement(element, implicitlyWait);
		new WebDriverWait(this.driver, implicitlyWait).until(ExpectedConditions.visibilityOf(driver.findElement(element)));
		driver.findElement(element).sendKeys(Keys.TAB);
	}

	protected void click(By element) throws Exception {
		this.WaitElement(element, implicitlyWait);
		new WebDriverWait(this.driver, implicitlyWait).until(ExpectedConditions.visibilityOf(driver.findElement(element)));
        //screen shoot
//		 Unility.screenshot(this.driver);
		//click
		driver.findElement(element).click();
	}

	protected void dropDown(By element1, By element2) throws Exception {
		this.WaitElement(element1, implicitlyWait);
		new WebDriverWait(this.driver, implicitlyWait).until(ExpectedConditions.visibilityOf(driver.findElement(element1)));
		driver.findElement(element1).click();
		
		this.WaitElement(element2, implicitlyWait);
		new WebDriverWait(this.driver, implicitlyWait).until(ExpectedConditions.visibilityOf(driver.findElement(element2)));
		driver.findElement(element2).click();
	}

	// Check whether the checkbox is selected
	protected boolean checkBoxSelected(By element) {
		// If exist return true, else return false.
		return driver.findElement(element).isSelected();
	}

	// Check whether the element exist or not!
	protected boolean isElementPresent(By element) throws Exception {
		// If exist return true, else return false.
		return driver.findElements(element).size() > 0;
	}

	protected int checkSearchResults(By element) {
		return driver.findElements(element).size();
	}

	protected void closePopUpWindow() throws Exception {
		By closeButton = By.cssSelector("button[aria-label='Close']");
		if (this.isElementPresent(closeButton)) {
			this.click(closeButton);
		}
	}
	
	protected void save() throws Exception {
		Thread.sleep(5000);
		if(this.WaitElement(By.xpath("//span[contains(text(),'Save')]"), implicitlyWait)) {
			this.click(By.xpath("//span[contains(text(),'Save')]"));
		}
	}
	
	protected void update() throws Exception {
		if(this.WaitElement(By.xpath("//span[contains(text(),'Update')]"), implicitlyWait)) {
			this.click(By.xpath("//span[contains(text(),'Update')]"));
		}
	}
	
	protected void expendLeftMenu() throws Exception {
		if(this.WaitElement(By.xpath("/html/body/div/div[1]/main/header/h6/button"), implicitlyWait)) {
//			this.click(By.cssSelector(
//					"path[d='M3 21h18v-2H3v2zM3 8v8l4-4-4-4zm8 9h10v-2H11v2zM3 3v2h18V3H3zm8 6h10V7H11v2zm0 4h10v-2H11v2z']"));
			this.click(By.xpath("/html/body/div/div[1]/main/header/h6/button"));
		}
	}

	protected void add() throws Exception {
		if(this.WaitElement(By.cssSelector(
				"path[d='M4 6H2v14c0 1.1.9 2 2 2h14v-2H4V6zm16-4H8c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V4c0-1.1-.9-2-2-2zm-1 9h-4v4h-2v-4H9V9h4V5h2v4h4v2z']"), 3 * implicitlyWait)) {
			this.click(By.cssSelector(
					"path[d='M4 6H2v14c0 1.1.9 2 2 2h14v-2H4V6zm16-4H8c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V4c0-1.1-.9-2-2-2zm-1 9h-4v4h-2v-4H9V9h4V5h2v4h4v2z']"));
		}
		
	}

	protected void addFilter() throws Exception {
		this.click(By.cssSelector("button[aria-label='Add']"));
		this.click(By.cssSelector("button[aria-label='Directions']"));
	}
	
	protected void groupSearch() throws Exception {
		this.click(By.xpath("//input[@placeholder='Search']/following-sibling::div//button"));
	}

	protected void delete() throws Exception {
		this.click(By.cssSelector("button[title='Delete']"));
		this.click(By.xpath("//span[contains(text(),'Confirm')]"));
	}

	protected void delete2() throws Exception {
		this.click(By.cssSelector("button[aria-label='Delete']"));
		this.click(By.xpath("//span[contains(text(),'Confirm')]"));
	}
	
	protected void normalLogout() throws Exception {
		this.click(By.xpath("//h5[1]//div[2]"));
		this.click(By.xpath("//li[contains(text(),'Logout')]"));
		this.click(By.xpath("//span[contains(text(),'Confirm')]"));
	}

	protected void expendRootMenu() throws Exception {
//		if (!this.WaitElement(By.cssSelector("img[alt='IVH']"), implicitlyWait)) {
//			this.click(By.cssSelector(
//					"path[d='M11 17h10v-2H11v2zm-8-5l4 4V8l-4 4zm0 9h18v-2H3v2zM3 3v2h18V3H3zm8 6h10V7H11v2zm0 4h10v-2H11v2z']"));
//		}
		if(!this.WaitElement(By.xpath("//span[contains(text(),'INTEGRATED VIDEO HUB')]"), implicitlyWait)) {
			if(this.WaitElement(By.xpath("/html/body/div/div[1]/main/header/h6/button"), implicitlyWait)) {
				this.click(By.xpath("/html/body/div/div[1]/main/header/h6/button"));
			}
		}
	}
	
	public void clearSearchCondition() throws Exception {
		if(this.isElementPresent(By.cssSelector("path[d='M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z']"))) {
			this.click(By.cssSelector("path[d='M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z']"));
		}
	}                                 

	protected Boolean validateSearchResult(String expectedResult) throws Exception {
		Boolean flag = false;
		if(Boolean.parseBoolean(expectedResult)) {
			flag = true;
		}else {
			if(!this.WaitElement(By.xpath("//span[contains(text(),'Rows per page')]"), implicitlyWait)){
				flag = true;
			}
		}
		return flag;
	}
	
	protected Boolean WaitElement(By element, int seconds) throws Exception {
		Boolean flag = false;
		long initTime = System.currentTimeMillis();
        while(true){
            long currentTime = System.currentTimeMillis();
            if(currentTime - initTime > seconds * 1000){
                break;
            }else{
                if(this.isElementPresent(element)) {
                	flag = true;
                	break;
                }
            }
        }		
//        if(!flag) {
//			throw new Exception("Wait element timeout!");
//		}  
        return flag;
	}
	
	
	protected void untilPageLoadComplete(int seconds) throws Exception {
		if(this.isElementPresent(By.xpath("//div[contains(text(),'Loading...')]")) || this.isElementPresent(By.cssSelector("div[class='common_loading_container animation-4']")) ) {
			long initTime = System.currentTimeMillis();
	        while(true){
	            long currentTime = System.currentTimeMillis();
	            if(currentTime - initTime > seconds * 1000){
	                break;
	            }else{
	                if(!this.WaitElement(By.cssSelector("div[class='common_loading_container animation-4']"), seconds)) {
	                	break;
	                }
	            }
	        }		
		}
	}
}
