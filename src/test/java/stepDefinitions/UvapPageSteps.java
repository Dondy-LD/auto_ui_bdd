package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.TestContent;
import cucumber.api.java.en.Then;
import enums.Context;
import pageObjects.Uvap;

public class UvapPageSteps {
	private static TestContent testContent;
	private static Uvap uvap;

	public UvapPageSteps(TestContent content) {
		testContent = content;
		uvap = testContent.getPageObjectManager((WebDriver) testContent.scenarioContext.getContext(Context.Driver))
				.getUvap();
	}

	@Then("^navigate to uvap$")
	public void navigateToUvap() throws Exception {
		uvap.navigateToUvap();
	}

	// va engines search
	@Then("^navigate to uvap va engines$")
	public void navigateVaEngines() throws Exception {
		uvap.navigateVaEngines();
	}

	@Then("^select engines search option \"([^\"]*)\" and input values \"([^\"]*)\"$")
	public void inputEnginesFilter(String filter, String optionsValue) throws Exception {
		testContent.scenarioContext.setContext(Context.Filter, filter);
		testContent.scenarioContext.setContext(Context.FilterValue, optionsValue);
		uvap.inputEnginesFilter(filter, optionsValue);
	}
	
	@Then("^add engines filter$")
	public void addEngineFilter() throws Exception {
		uvap.addEngineFilter();
	}

	@Then("^add engines filter and search$")
	public void search() throws Exception {
		uvap.search();
	}
	
	
	@Then("^click the options menu and select delete$")
	public void clickOptionsDelete() throws Exception {
		uvap.clickOptionsDelete();
	}
	
	@Then("^validate if delete postIncident successfully$")
	public void deletePostIncident() throws Exception {
		Boolean flag = uvap.deletePostIncident();
		Assert.assertTrue(flag);
	}
	
	
	@Then("^search engines results should like \"([^\"]*)\"$")
	public void searchResult(String expectedResult) throws Exception {
		Boolean flag = uvap.searchResult2(expectedResult);
		Assert.assertTrue(flag);
	}

	// va engines edit
	@Then("^click operation and select edit info$")
	public void edit() throws Exception {
		uvap.edit();
	}

	@Then("^input engine info with \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
	public void inputEngineInfo(String engineName, String vendor, String engineDesc) throws Exception {
		uvap.inputEngineInfo(engineName, vendor, engineDesc);
	}

	@Then("^update the engine with \"([^\"]*)\"$")
	public void updateEngine(String expectedResult) throws Exception {
		Boolean flag = uvap.updateEngine(expectedResult);
		Assert.assertTrue(flag);
	}

	// va engine delete
	@Then("^click operation and select delete item$")
	public void delete() throws Exception {
		Boolean flag = uvap.deleteEngines();
		Assert.assertTrue(flag);
	}

	// job va instance search
	@Then("^navigate to uvap va instance$")
	public void navigateVaInstance() throws Exception {
		uvap.navigateVaInstance();
	}

	@Then("^navigate to job va instance$")
	public void jobInstance() throws Exception {
		uvap.jobInstance();
	}

	@Then("^select instance search option \"([^\"]*)\" and input values \"([^\"]*)\"$")
	public void inputInstanceFilter(String filter, String optionsValue) throws Exception {
		testContent.scenarioContext.setContext(Context.Filter, filter);
		testContent.scenarioContext.setContext(Context.FilterValue, optionsValue);
		uvap.inputInstanceFilter(filter, optionsValue);
	}

	// job va instance edit
	@Then("^click instance operation and select edit info$")
	public void editInstance() throws Exception {
		uvap.editInstance();
	}

	@Then("^input instance info with \"([^\"]*)\"$")
	public void inputInstanceInfo(String instanceName) throws Exception {
		uvap.inputInstanceInfo(instanceName);
	}

	@Then("^update the instance with \"([^\"]*)\"$")
	public void updateInstance(String expectedResult) throws Exception {
		Boolean flag = uvap.updateInstance(expectedResult);
		Assert.assertTrue(flag);
	}

	// job va instance delete
	@Then("^click instance operation and select delete item$")
	public void deleteInstance() throws Exception {
		Boolean flag = uvap.deleteEngines();
		Assert.assertTrue(flag);
	}

	// live instance
	@Then("^navigate to live va instance$")
	public void liveInstance() throws Exception {
		uvap.liveInstance();
	}

	@Then("^update the live instance with \"([^\"]*)\"$")
	public void updateLiveInstance(String expectedResult) throws Exception {
		Boolean flag = uvap.updateLiveInstance(expectedResult);
		Assert.assertTrue(flag);
	}

	// live license
	@Then("^navigate to uvap license$")
	public void uvapLicense() throws Exception {
		uvap.uvapLicense();
	}

	// post incident
	@Then("^navigate to uvap post incident$")
	public void postIncident() throws Exception {
		uvap.postIncident();
	}
	
	//add
	@Then("^click the create post incident button$")
	public void createPostIncident() throws Exception {
		uvap.createPostIncident();
	}  
	
	@Then("^input the incident info \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
	public void inputIncidentInfo(String incidentName, String incidentType, String incidentDesc) throws Exception {
		uvap.inputIncidentInfo(incidentName, incidentType, incidentDesc);
	}
	
	@Then("^save the new postIncident with \"([^\"]*)\"$")
	public void savePostIncident(String expectedResult) throws Exception {
		Boolean flag = uvap.savePostIncident(expectedResult);
		Assert.assertTrue(flag);
	}

	// post incident search
	@Then("^select incident search option \"([^\"]*)\" and input values \"([^\"]*)\"$")
	public void inputIncidentFilter(String filter, String optionsValue) throws Exception {
		testContent.scenarioContext.setContext(Context.Filter, filter);
		testContent.scenarioContext.setContext(Context.FilterValue, optionsValue);
		uvap.inputIncidentFilter(filter, optionsValue);
	}

	// report search
	@Then("^navigate to report$")
	public void navigateToReport() throws Exception {
		uvap.navigateToReport();
	}

	@Then("^navigate to report search$")
	public void reportSearch() throws Exception {
		uvap.reportSearch();
	}

	@Then("^select report search option \"([^\"]*)\" and \"([^\"]*)\"$")
	public void inputReportFilter(String vaType, String vaInstanceType) throws Exception {
		uvap.inputReportFilter(vaType, vaInstanceType);
	}

	@Then("^search report results should like \"([^\"]*)\"$")
	public void searchReport(String expectedResult) throws Exception {
		Boolean flag = uvap.searchReport(expectedResult);
		Assert.assertTrue(flag);
	}

	// overview
	@Then("^navigate to overview$")
	public void overview() throws Exception {
		uvap.overview();
	}

	@Then("^click system status$")
	public void systemStatus() throws Exception {
		uvap.systemStatus();
	}

	@Then("^run the check$")
	public void runTheCheck() throws Exception {
		Assert.assertTrue(uvap.runTheCheck());
	}

	// audit trail
	@Then("^navigate to auditTrail$")
	public void auditTrail() throws Exception {
		uvap.auditTrail();
	}
	
	@Then("^select auditTrail search option \"([^\"]*)\" and input values \"([^\"]*)\"$")
	public void inputTrailFilter(String filter, String optionsValue) throws Exception {
		testContent.scenarioContext.setContext(Context.Filter, filter);
		testContent.scenarioContext.setContext(Context.FilterValue, optionsValue);
		uvap.inputTrailFilter(filter, optionsValue);
	}
}
