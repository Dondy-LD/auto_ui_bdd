package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.TestContent;
import cucumber.api.java.en.Then;
import enums.Context;
import pageObjects.AlarmPage;

public class AlarmPageSteps {

	private static TestContent testContent;
	private static AlarmPage alarmPage;

	public AlarmPageSteps(TestContent content) {
		testContent = content;
		alarmPage = testContent.getPageObjectManager((WebDriver) testContent.scenarioContext.getContext(Context.Driver))
				.getAlarmPage();
	}
	
	//check current user
	@Then("^check current corresponding user$")
	public void checkUser() throws Exception {
		alarmPage.checkUser();
	}
	// Alarm
	@Then("^navigate to alarm$")
	public void click_alarm() throws Exception {
		alarmPage.navigateAlarm();
	}

	// Alarm History - vms history search
	@Then("^navigate to alarm history$")
	public void alarmHistory() throws Exception {
		alarmPage.navigateAlarmHistory();
	}

	@Then("^click vms alarm history$")
	public void vmsAlarmHistory() throws Exception {
		alarmPage.vmsAlarmHistory();
	}

	@Then("^select search option \"([^\"]*)\" and input values \"([^\"]*)\" and \"([^\"]*)\"$")
	public void inputAlarmFilter(String filter, String optionsValue1, String optionsValue2) throws Exception {
		testContent.scenarioContext.setContext(Context.Filter, filter);
		testContent.scenarioContext.setContext(Context.FilterValue, optionsValue1);
		testContent.scenarioContext.setContext(Context.FilterValue1, optionsValue2);
		alarmPage.inputAlarmFilter(filter, optionsValue1, optionsValue2);
	}

	// Alarm History - vap
	@Then("^click vap alarm history$")
	public void vmpAlarmHistory() throws Exception {
		alarmPage.vmpAlarmHistory();
	}

	// alarm configuration
	@Then("^navigate to alarm configuration$")
	public void alarmConfiguration() throws Exception {
		alarmPage.navigateAlarmConfiguration();
	}

	// alarm configuration create
	@Then("^click create alarm button$")
	public void alarmAddBtn() throws Exception {
		alarmPage.alarmAddBtn();
	}

	@Then("^input alarm info \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
	public void fillInfo(String alarmName, String severity, String status, String type) throws Exception {
		alarmPage.fillInfo(alarmName, severity, status, type);
	}

	@Then("^save the new alarm with \"([^\"]*)\"$")
	public void saveNewAlarm(String expectedResult) throws Exception {
		Boolean flag = alarmPage.saveNewAlarm(expectedResult);
		Assert.assertTrue(flag);
	}

	// alarm configuration search
	@Then("^input search alarm conf option \"([^\"]*)\" and \"([^\"]*)\"$")
	public void inputAlarmConfFilter(String filter, String optionsValue) throws Exception {
		testContent.scenarioContext.setContext(Context.Filter, filter);
		testContent.scenarioContext.setContext(Context.FilterValue, optionsValue);
		alarmPage.inputAlarmConfFilter(filter, optionsValue);
	}
	
	@Then("^add alarm filter and search$")
	public void filterSearch() throws Exception {
		alarmPage.filterSearch();
	}
	
	@Then("^clear search condition$")
	public void clearConditions() throws Exception {
		String filter = (String) testContent.scenarioContext.getContext(Context.Filter);
		if(!"range".equalsIgnoreCase(filter)) {
			alarmPage.clearConditions();
		}
	}
	
	@Then("^search alarm conf results should like \"([^\"]*)\"$")
	public void searchAlarmConfResult(String expectedResult) throws Exception {
		String filter = (String) testContent.scenarioContext.getContext(Context.Filter);
		Boolean flag = alarmPage.searchAlarmConfResult(filter, expectedResult);
		Assert.assertTrue(flag);
	}
	
	//alarm configuration Update
	@Then("^select item to update$")
	public void checkItem() throws Exception {
		String text = (String)testContent.scenarioContext.getContext(Context.FilterValue);
		alarmPage.checkItem(text);
	}
	
	@Then("^click update alarm$")
	public void updateAlarm() throws Exception {
		alarmPage.updateAlarm();
	}
	
	@Then("^update the alarm with \"([^\"]*)\"$")
	public void updateAlarm(String expectedResult) throws Exception {
		Boolean flag = alarmPage.updateAlarm(expectedResult);
		Assert.assertTrue(flag);
	}
	
	
	//alarm configuration setting
	@Then("^click alarm setting$")
	public void alarmSetting() throws Exception {
		alarmPage.alarmSetting();
	}
	
	@Then("^input alarm setting info \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
	public void alarmSettingInfo(String method, String ifsubscribe, String singleNotification, String timePeriod) throws Exception {
		alarmPage.alarmSettingInfo(method, ifsubscribe, singleNotification, timePeriod);
	}
	
	//alarm configuration delivery
	@Then("^click alarm delivery$")
	public void alarmDelivery() throws Exception {
		alarmPage.alarmDelivery();
	}
	
	@Then("^search user with \"([^\"]*)\"$")
	public void chooseUser(String userId) throws Exception {
		testContent.scenarioContext.setContext(Context.FilterValue1, userId);
		alarmPage.chooseUser(userId);
	}
	
	@Then("^checked user$")
	public void checkedUser() throws Exception {
		String userId = (String) testContent.scenarioContext.getContext(Context.FilterValue1);
		alarmPage.checkedUser(userId);
	}
	
	//alarm configuration delete
	@Then("^click delete button$")
	public void deleteAlarmConf() throws Exception {
		Boolean flag = alarmPage.deleteAlarmConf();
		Assert.assertTrue(flag);
	}
	
	//navigate to alarm Subscribe
	@Then("^navigate to alarm subscribe$")
	public void alarmSubscribe() throws Exception {
		alarmPage.navigateAlarmSubscribe();
	}
	
	@Then("^update the notification method \"([^\"]*)\"$")
	public void updateSubscribe(String methodName) throws Exception {
		String filter = (String)testContent.scenarioContext.getContext(Context.FilterValue);
		alarmPage.updateSubscribe(methodName, filter);
	}
	
	@Then("^save the update result$")
	public void saveSubscribe() throws Exception {
		Boolean flag = alarmPage.saveSubscribe();
		Assert.assertTrue(flag);
	}
	
	//navigate to alarm event query
	@Then("^navigate to alarm event query$")
	public void alarmEventQuery() throws Exception {
		alarmPage.navigateAlarmEventQuery();
	}
	
	@Then("^search alarm event results should like \"([^\"]*)\"$")
	public void searchAlarmEventResult(String expectedResult) throws Exception {
		String filter = (String) testContent.scenarioContext.getContext(Context.Filter);
		Boolean flag = alarmPage.searchAlarmConfResult(filter, expectedResult);
		Assert.assertTrue(flag);
	}
}
