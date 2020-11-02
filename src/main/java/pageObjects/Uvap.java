package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.Wait;

public class Uvap extends BasePage {

	public Uvap(WebDriver driver) {
		super(driver);
	}

	public void navigateToUvap() throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.expendRootMenu();
		this.click(By.xpath("//span[contains(text(),'UVAP')]"));
	}

	public void checkMenuIfExpend() throws Exception {
		if (this.isElementPresent(By.xpath("//span[contains(text(),'UVAP')]/ancestor::div[@aria-expanded='false']"))) {
			this.click(By.xpath("//span[contains(text(),'UVAP')]"));
		}
	}

	public void navigateVaEngines() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/uvap/va-engines']"));
	}
	

	public void inputEnginesFilter(String Filter, String optionsValue) throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.WaitElement(By.cssSelector("button[aria-label='Menu']"), 2 * implicitlyWait);
		this.click(By.cssSelector("button[aria-label='Menu']"));
		By element = null;
		switch (Filter) {
		case "name":
			this.click(By.cssSelector("li[value='VA_Engine_Name']"));
			this.sendString(By.cssSelector("input[placeholder='VA Engine Name']"), optionsValue);
			break;
		case "name2":
			this.click(By.cssSelector("li[value='VA_Engine_Name']"));
			this.sendString(By.cssSelector(
					"/html/body/div[1]/div[1]/main/div/div/div[1]/div[3]/div/div[1]/div/div/div/div[2]/div[1]/div/div/div/div[1]/input"),
					optionsValue);
			break;
		case "label":
			this.click(By.cssSelector("li[value='VA_Engine_Label']"));
			element = By.xpath(
					"//p[contains(text(),'VA Engine Label')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
			break;
		case "desc":
			this.click(By.cssSelector("li[value='VA_Engine_Description']"));
			this.sendString(By.cssSelector("input[placeholder='VA Engine Description']"), optionsValue);
			break;
		case "gateway":
			this.click(By.cssSelector("li[value='VA_Engine_Gateway']"));
			element = By.xpath(
					"//p[contains(text(),'VA Engine Gateway')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
			break;
		case "status":
			this.click(By.cssSelector("li[value='Status']"));
			element = By
					.xpath("//p[contains(text(),' Status')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
			break;
		case "vendor":
			this.click(By.cssSelector("li[value='Vendor']"));
			this.sendString(By.cssSelector("input[placeholder=' Vendor']"), optionsValue);
			break;
		case "version":
			this.click(By.cssSelector("li[value='VA_Engine_Version']"));
			this.sendString(By.cssSelector("input[placeholder='VA Engine Version']"), optionsValue);
			break;
		case "version2":
			this.click(By.cssSelector("li[value='Version']"));
			this.sendString(By.cssSelector("input[placeholder=' Version']"), optionsValue);
			break;
		}
	}

	public void addEngineFilter() throws Exception {
		this.click(By.cssSelector("button[aria-label='Add']"));
	}

	public void search() throws Exception {
		this.addFilter();
	}

	public void clickOptionsDelete() throws Exception {
		if (this.WaitElement(By.cssSelector("button[aria-owns^='operation-menu']"), implicitlyWait)) {
			this.dropDown(By.cssSelector("button[aria-owns^='operation-menu']"),
					By.xpath("//span[contains(text(),'Delete')]"));
			this.click(By.xpath("//span[contains(text(),'Confirm')]"));
		}

	}

	public Boolean deletePostIncident() throws Exception {
//		this.untilPageLoadComplete(implicitlyWait);
		return this.WaitElement(By.cssSelector("div[title='Deleted successfully.']"), implicitlyWait);
	}

	public Boolean searchResult(String expectedResult) throws Exception {
		Boolean flag = this.validateSearchResult(expectedResult);
		this.clearSearchCondition();
		return flag;
	}

	public Boolean searchResult2(String expectedResult) throws Exception {
		Boolean flag = this.validateSearchResult(expectedResult);
		return flag;
	}

	public void edit() throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.WaitElement(By.cssSelector("button[aria-owns^='operation-menu']"), implicitlyWait);
		this.dropDown(By.cssSelector("button[aria-owns^='operation-menu']"),
				By.xpath("//span[contains(text(),'Edit Info')]"));
	}

	public void inputEngineInfo(String engineName, String vendor, String engineDesc) throws Exception {
		this.sendString(By.cssSelector("input[placeholder='Input VA Engine Name']"), engineName);
		this.sendString(By.cssSelector("input[placeholder='Input Vendor']"), vendor);
		this.sendString(By.cssSelector("input[placeholder='Input VA Engine Description']"), engineDesc);
	}

	public Boolean updateEngine(String expectedResult) throws Exception {
		this.save();
		Boolean flag = this.validateMessage("update", expectedResult);
		return flag;
	}

	public Boolean deleteEngines() throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		if (this.WaitElement(By.cssSelector("button[aria-owns^='operation-menu']"), implicitlyWait)) {
			this.click(By.cssSelector("button[aria-owns^='operation-menu']"));
			this.click(By.xpath("//span[contains(text(),'Delete')]"));
			this.click(By.xpath("//span[contains(text(),'Confirm')]"));
		}
//		this.dropDown(By.cssSelector("button[aria-owns^='operation-menu']"),
//				By.xpath("//span[contains(text(),'Delete')]"));
		return this.WaitElement(By.cssSelector("div[title='Deleted successfully.']"), implicitlyWait);
	}

	public void navigateVaInstance() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("div[title='VA Instance']"));
	}

	public void jobInstance() throws Exception {
		if (this.isElementPresent(By.xpath("//div[@title='VA Instance']/ancestor::li/div[@aria-expanded='false']"))) {
			this.click(By.cssSelector("div[title='VA Instance']"));
		}
		this.click(By.cssSelector("a[href='/uvap/va-instance/job-va-instance']"));
	}

	public void inputInstanceFilter(String Filter, String optionsValue) throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.WaitElement(By.cssSelector("button[aria-label='Menu']"), 2 * implicitlyWait);
		this.click(By.cssSelector("button[aria-label='Menu']"));
		By element = null;
		switch (Filter) {
		case "engineName":
			this.click(By.cssSelector("li[value='VA_Engine_Name']"));
			this.sendString(By.cssSelector("input[placeholder='VA Engine Name']"), optionsValue);
			break;
		case "instanceName":
			this.click(By.cssSelector("li[value='Instance_Name']"));
			this.sendString(By.cssSelector("input[placeholder='Instance Name']"), optionsValue);
			break;
		case "status":
			this.click(By.cssSelector("li[value='Status']"));
			element = By
					.xpath("//p[contains(text(),' Status')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
			break;
		case "priority":
			this.click(By.cssSelector("li[value='Priority']"));
			element = By.xpath(
					"//p[contains(text(),' Priority')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
			break;
		case "instanceName2":
			this.click(By.cssSelector("li[value='InstanceName']"));
			this.sendString(By.cssSelector("input[placeholder=' Instance Name']"), optionsValue);
			break;
		}
	}

	public void editInstance() throws Exception {
		Wait.defaultWait();
		if (this.WaitElement(By.cssSelector("button[aria-owns='operation-menu-']"), implicitlyWait)) {
			this.click(By.cssSelector("button[aria-owns='operation-menu-']"));
			this.click(By.xpath("//span[contains(text(),'Edit Configuration')]"));
//			this.dropDown(By.cssSelector("button[aria-owns='operation-menu-']"),By.xpath("//span[contains(text(),'Edit Configuration')]"));
			// aria-owns="operation-menu-" // aria-owns^='operation-menu'
		}
	}

	public void inputInstanceInfo(String instanceName) throws Exception {
		this.sendString(By.cssSelector("input[placeholder='Input Instance Name']"), instanceName);
	}

	public Boolean updateInstance(String expectedResult) throws Exception {
		this.save();
		Boolean flag = this.validateMessage("update", expectedResult);
		return flag;
	}

	public Boolean updateLiveInstance(String expectedResult) throws Exception {
		if (this.WaitElement(By.xpath("//span[contains(text(),'Save')]/parent::button[@style='text-transform: none;']"),
				implicitlyWait)) {
			this.click(By.xpath("//span[contains(text(),'Save')]/parent::button[@style='text-transform: none;']"));
		}
		Boolean flag = this.validateMessage("update", expectedResult);
		this.closePopUpWindow();
		return flag;
	}

	public void liveInstance() throws Exception {
		if (this.isElementPresent(By.xpath("//div[@title='VA Instance']/ancestor::li/div[@aria-expanded='false']"))) {
			this.click(By.cssSelector("div[title='VA Instance']"));
		}
		this.click(By.cssSelector("a[href='/uvap/va-instance/live-va-instance']"));
	}

	public void uvapLicense() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/uvap/license']"));
	}

	public void postIncident() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/uvap/post-incident']"));
	}

	public void createPostIncident() throws Exception {
		this.add();
	}

	public void inputIncidentInfo(String incidentName, String incidentType, String incidentDesc) throws Exception {
		this.sendString(By.cssSelector("input[placeholder='Input Incident Name']"), incidentName);
		this.sendString(By.cssSelector("input[placeholder='Input Incident Type']"), incidentType);
		this.sendString(By.cssSelector("input[placeholder='Input Incident Description']"), incidentDesc);
	}

	public Boolean savePostIncident(String expectedResult) throws Exception {
		this.save();
		Boolean flag = this.WaitElement(By.cssSelector("div[title='SUCCESS']"), implicitlyWait);
		if (!flag) {
			this.click(By.xpath("//span[contains(text(),'Cancel')]"));
		}
		this.closePopUpWindow();
		return (Boolean.parseBoolean(expectedResult) == flag);
	}

	public void inputIncidentFilter(String Filter, String optionsValue) throws Exception {
		this.untilPageLoadComplete(2 * implicitlyWait);
		if (this.WaitElement(By.cssSelector("button[aria-label='Menu']"), implicitlyWait)) {
			this.click(By.cssSelector("button[aria-label='Menu']"));
			By element = null;
			switch (Filter) {
			case "incidentName":
				this.click(By.cssSelector("li[value='Incident_Name']"));
				this.sendString(By.cssSelector("input[placeholder='Incident Name']"), optionsValue);
				break;
			case "incidentType":
				this.click(By.cssSelector("li[value='Incident_Type']"));
				this.sendString(By.cssSelector("input[placeholder='Incident Type']"), optionsValue);
				break;
			case "status":
				this.click(By.cssSelector("li[value='Status']"));
				element = By.xpath(
						"//p[contains(text(),' Status')]/parent::div/following-sibling::div[@aria-pressed='false']");
				this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
				break;
			case "range":
				this.click(By.cssSelector("li[value='Range']"));
				this.sendString(By.xpath(
						"/html/body/div[1]/div[1]/main/div/div/div[1]/div/div/div[1]/div/div[2]/div/div[1]/div/div/div/div[1]/div/input"),
						optionsValue);
				break;
			}
		}
	}

	public void navigateToReport() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("div[title='Report']"));
	}

	public void reportSearch() throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		if (this.WaitElement(By.xpath("//div[@title='Report']/ancestor::li/div[@aria-expanded='false']"),
				implicitlyWait)) {
			this.click(By.cssSelector("div[title='Report']"));
		}

		if (this.WaitElement(By.xpath("//span[contains(text(),'Report Search')]"), implicitlyWait)) {
			this.click(By
					.xpath("//span[contains(text(),'Report Search')]/ancestor::a[@href='/uvap/report/report-search']"));
		}
	}

	public void inputReportFilter(String vaType, String vaInstanceType) throws Exception {
		this.untilPageLoadComplete(2 * implicitlyWait);
		// Face Recognition VAP CROWD
		this.dropDown(By.xpath("//label[contains(text(),'VA Type')]/following-sibling::div/div"),
				By.xpath("//span[contains(text(),'" + vaType + "')]"));
		// JOB_VA LIVE_VA
		this.dropDown(By.xpath("//label[contains(text(),'VA Instance Type')]/following-sibling::div/div"),
				By.xpath("//span[contains(text(),'" + vaInstanceType + "')]"));
		this.click(By.xpath("//span[contains(text(),'Filter')]"));
	}

	public Boolean searchReport(String expectedResult) throws Exception {
		Boolean flag = this.validateSearchResult(expectedResult);
		return flag;
	}

	public Boolean validateMessage(String Operation, String expectedResult) throws Exception {
		Boolean flag = false;
		if ("update".equalsIgnoreCase(Operation)) {
			flag = (Boolean.parseBoolean(expectedResult) == this
					.WaitElement(By.cssSelector("div[title='Updated successfully.']"), implicitlyWait));
		}

		return flag;
	}

	public void overview() throws Exception {
		this.expendRootMenu();
		this.click(By.cssSelector("a[href='/overview']"));
//		this.click(By.xpath("//span[contains(text(),'Overview')]"));
	}

	public void systemStatus() throws Exception {
		this.click(By.xpath("//span[contains(text(),'System Status')]/parent::button"));
	}

	public Boolean runTheCheck() throws Exception {
		this.click(By.xpath("//span[contains(text(),'Run Check')]"));
		return this.WaitElement(By.xpath("//h6[contains(text(),'The next System BIT check can only be run after')]"),
				implicitlyWait);
	}

	// audit trail
	public void auditTrail() throws Exception {
		this.expendRootMenu();
		this.click(By.cssSelector("a[href='/auditTrail']"));
//		this.click(By.xpath("//span[contains(text(),'Audit Trail')]"));
	}

	// search
	public void inputTrailFilter(String Filter, String optionsValue) throws Exception {
		this.click(By.cssSelector("button[aria-label='Menu']"));
		By element = null;
		switch (Filter) {
		case "userId":
			this.click(By.cssSelector("li[value='UserID']"));
			this.sendString(By.cssSelector("input[placeholder=' User ID']"), optionsValue);
			break;
		case "actionType":
			this.click(By.cssSelector("li[value='ActionType']"));
			this.sendString(By.cssSelector("input[placeholder=' Action Type']"), optionsValue);
			break;
		case "response":
			this.click(By.cssSelector("li[value='Response']"));
			element = By.xpath(
					"//p[contains(text(),' Response')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
			break;
		}
	}
}
