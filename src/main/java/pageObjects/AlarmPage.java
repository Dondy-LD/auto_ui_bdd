package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import managers.FileReaderManager;

public class AlarmPage extends BasePage {

	public AlarmPage(WebDriver driver) {
		super(driver);
	}

	public void checkUser() throws Exception {
		String userId = FileReaderManager.getInstance().getConfigReader().readProperty("normalUser");
		if (!this.WaitElement(By.xpath("//span[contains(text(), '" + userId + "')"), implicitlyWait)) {
			this.normalLogout();
		}
	}

	public void navigateAlarm() throws Exception {
		this.expendRootMenu();
		if(this.WaitElement(By.xpath("//span[contains(text(),'Alarm')]"), implicitlyWait)) {
			this.click(By.xpath("//span[contains(text(),'Alarm')]"));
		}
	}

	public void checkMenuIfExpend() throws Exception {
		if (this.isElementPresent(By.xpath("//span[contains(text(),'Alarm')]/ancestor::div[@aria-expanded='false']"))) {
			this.click(By.xpath("//span[contains(text(),'Alarm')]"));
		}
	}

	public void navigateAlarmHistory() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/alarm/history']"));
	}

	public void navigateAlarmSubscribe() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/alarm/subscribe']"));
	}

	public void navigateAlarmEventQuery() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/alarm/event-query']"));
	}

	public void vmsAlarmHistory() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.xpath("//span[contains(text(),'VMS Alarm History')]"));
	}

	public void navigateAlarmConfiguration() throws Exception {
		this.checkMenuIfExpend();
		if(this.WaitElement(By.cssSelector("a[href='/alarm/configuration']"), implicitlyWait)) {
			this.click(By.cssSelector("a[href='/alarm/configuration']"));
		}
	}

	public void alarmAddBtn() throws Exception {
//		this.click(By.cssSelector("button[title='Create Alarm']"));
		this.untilPageLoadComplete(implicitlyWait);
		this.add();
	}

	public void fillInfo(String alarmName, String severity, String status, String type) throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.sendString(By.cssSelector("input[placeholder='Input Alarm Name']"), alarmName);

		By element = By.xpath("//label[contains(text(),'Alarm Severity')]/following-sibling::div/div");
		this.dropDown(element, By.xpath("//span[contains(text(),'" + severity + "')]"));

		if (status.length() > 0) {
			By element1 = By.xpath("//label[contains(text(),'Status')]/following-sibling::div/div");
			this.dropDown(element1, By.xpath("//span[contains(text(),'" + status + "')]"));
		}

		if (type.length() > 0) {
			By element2 = By.xpath("//label[contains(text(),'Event Type')]/following-sibling::div/div");
			this.dropDown(element2, By.xpath("//span[contains(text(),'" + type + "')]"));
		}

	}

	public void alarmSettingInfo(String method, String ifsubscribe, String singleNotification, String timePeriod)
			throws Exception {
		By element = By.xpath("//label[contains(text(),'Notification Method')]/following-sibling::div/div");
		this.WaitElement(By.xpath("//label[contains(text(),'Notification Method')]/following-sibling::div/div"),
				implicitlyWait);
		if ("all".equalsIgnoreCase(method)) {
			this.dropDown(element, By.xpath("//div[contains(text(),'Select All')]"));
		} else if ("clear".equalsIgnoreCase(method)) {
			this.dropDown(element, By.xpath("//div[contains(text(),'Clear')]"));
		} else {
			this.dropDown(element, By.xpath("//span[contains(text(),'" + method + "')]"));
		}
		this.tab(By.xpath("//div[contains(text(),'Select All')]/ancestor::ul"));

		By element1 = By.xpath("//label[contains(text(),'Allow To Unsubscribe Alarm')]/following-sibling::div/div/div");
		this.dropDown(element1, By.cssSelector("li[data-value='" + ifsubscribe + "']"));
		By element2 = By
				.xpath("//label[contains(text(),'Consolidate To Single Notification')]/following-sibling::div/div/div");
		this.dropDown(element2, By.cssSelector("li[data-value='" + singleNotification + "']"));
		this.sendString(By.xpath("//label[contains(text(),'Consolidate Time Period')]/following-sibling::div/input"),
				timePeriod);
	}

	public Boolean saveNewAlarm(String expectedResult) throws Exception {
		this.save();

		Boolean flag = this.WaitElement(By.cssSelector("div[title='Saved successfully.']"), implicitlyWait);
		if (!flag) {
			this.click(By.xpath("//span[contains(text(),'Cancel')]"));
		}
		this.closePopUpWindow();
		return (Boolean.parseBoolean(expectedResult) == flag);
	}

	public void inputAlarmConfFilter(String filter, String optionsValue) throws Exception {
		this.click(By.cssSelector("button[aria-label='Menu']"));
		By element = null;
		switch (filter) {
		case "name":
			this.click(By.cssSelector("li[value='Alarm Name']"));
			this.sendString(By.cssSelector("input[placeholder=' Alarm  Name']"), optionsValue);
			break;
		case "type":
			this.click(By.cssSelector("li[value='Event Type']"));
			element = By.xpath(
					"//p[contains(text(),' Event  Type')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
			break;
		case "severity":
			this.click(By.cssSelector("li[value='Alarm Severity']"));
			element = By.xpath(
					"//p[contains(text(),' Alarm  Severity')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
			break;
		case "status":
			this.click(By.cssSelector("li[value='Status']"));
			element = By
					.xpath("//p[contains(text(),' Status')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
			break;
		case "subscribeName":
			this.click(By.cssSelector("li[value='AlarmName']"));
			this.sendString(By.cssSelector("input[placeholder=' Alarm Name']"), optionsValue);
			break;
		case "subscribeType":
			this.click(By.cssSelector("li[value='EventType']"));
			element = By.xpath(
					"//p[contains(text(),' Event Type')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
			break;
		case "subscribeSeverity":
			this.click(By.cssSelector("li[value='AlarmSeverity']"));
			element = By.xpath(
					"//p[contains(text(),' Alarm Severity')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
			break;
		}
	}

	public Boolean searchAlarmConfResult(String filter, String expectedResult) throws Exception {
		Boolean flag = this.validateSearchResult(expectedResult);
		if(!filter.contains("range")) {
			this.clearSearchCondition();
		}
		return flag;
	}

	public void filterSearch() throws Exception {
		this.addFilter();
	}

	public void clearConditions() throws Exception {
		this.clearSearchCondition();
	}

	public void checkItem(String text) throws Exception {
		this.click(By.xpath("//td[contains(text(),'" + text + "')]/preceding-sibling::td//span//span"));
	}

	public void updateSubscribe(String methodName, String filter) throws Exception {
		if (this.checkBoxSelected(
				By.xpath("//td[contains(text(),'" + filter + "')]/preceding-sibling::td//span//span/input"))) {
			this.click(By.xpath("//input[@value='" + methodName + "']/parent::span"));
		} else {
			this.dropDown(By.xpath("//td[contains(text(),'" + filter + "')]/preceding-sibling::td//span//span"),
					By.xpath("//input[@value='" + methodName + "']/parent::span"));
		}

	}

	public Boolean saveSubscribe() throws Exception {
		this.save();
		return this.WaitElement(By.cssSelector("div[title='Alarm subscribe information is updated successfully']"),
				implicitlyWait);
	}

	public void updateAlarm() throws Exception {
		this.click(By.xpath("//span[contains(text(),'Update Alarm')]"));
	}

	public void alarmSetting() throws Exception {
		this.click(By.xpath("//span[contains(text(),'Settings')]"));
	}

	public void alarmDelivery() throws Exception {
		this.click(By.xpath("//span[contains(text(),'Delivery To')]"));
	}

	public void chooseUser(String userId) throws Exception {
		this.sendString(By.cssSelector("input[placeholder='User ID']"), userId);
		this.addFilter();
	}

	public void checkedUser(String userId) throws Exception {
		this.click(By.xpath("//td[contains(text(),'" + userId + "')]/preceding-sibling::td//span//span"));
	}

	public Boolean deleteAlarmConf() throws Exception {
		this.closePopUpWindow();
		this.delete();
		return this.WaitElement(By.cssSelector("div[title='Deleted successfully.']"), implicitlyWait);
	}

	public Boolean updateAlarm(String expectedResult) throws Exception {
		this.save();
		Boolean flag = this.WaitElement(By.cssSelector("div[title='Updated successfully.']"), implicitlyWait);
		if (!flag) {
			this.click(By.xpath("//span[contains(text(),'Cancel')]"));
		}
		return (Boolean.parseBoolean(expectedResult) == flag);
	}

	public void vmpAlarmHistory() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.xpath("//span[contains(text(),'VAP Alarm History')]"));
	}

	public void inputAlarmFilter(String Filter, String optionsValue1, String optionsValue2) throws Exception {
		this.click(By.cssSelector("button[aria-label='Menu']"));
		By element = null;
		switch (Filter) {
		case "source":
			this.click(By.cssSelector("li[value='Source']"));
			this.sendString(By.cssSelector("input[placeholder=' Source']"), optionsValue1);
			break;
		case "event":
			this.click(By.cssSelector("li[value='Event Type']"));
			element = By.xpath(
					"//p[contains(text(),' Event  Type')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue1 + "']"));
			break;
		case "status":
			this.click(By.cssSelector("li[value='Status']"));
			element = By
					.xpath("//p[contains(text(),' Status')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue1 + "']"));
			break;
		}

		By element1 = null;
		By element2 = null;
		if ("range".equalsIgnoreCase(Filter)) {
			this.click(By.cssSelector("li[value='Range']"));
			element1 = By
					.xpath("/html/body/div/div[1]/main/div/div/div[1]/div[3]/div[1]/div[1]/div/div/div[1]/div/input");
			element2 = By
					.xpath("/html/body/div/div[1]/main/div/div/div[1]/div[3]/div[1]/div[1]/div/div/div[2]/div/input");
		} else if ("range1".equalsIgnoreCase(Filter)) {
			this.click(By.cssSelector("li[value='Range']"));
			element1 = By
					.xpath("/html/body/div/div[1]/main/div/div/div[1]/div[4]/div[1]/div[1]/div/div/div[1]/div/input");
			element2 = By
					.xpath("/html/body/div/div[1]/main/div/div/div[1]/div[4]/div[1]/div[1]/div/div/div[2]/div/input");
		} else if ("range2".equalsIgnoreCase(Filter)) {
			this.click(By.cssSelector("li[value='Range']"));
			element1 = By
					.xpath("/html/body/div/div[1]/main/div/div/div[1]/div[2]/div[1]/div[1]/div/div/div[1]/div/input");
			element2 = By
					.xpath("/html/body/div/div[1]/main/div/div/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div/input");
		}
		
		if(Filter.contains("range")) {
			this.sendString(element1, optionsValue1);
			this.sendString(element2, optionsValue2);
		}
	}

}
