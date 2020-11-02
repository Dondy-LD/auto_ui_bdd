package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.TestContent;
import cucumber.api.java.en.Then;
import enums.Context;
import pageObjects.Vade;

public class VadePageSteps {
	private static TestContent testContent;
	private static Vade vade;

	public VadePageSteps(TestContent content) {
		testContent = content;
		vade = testContent.getPageObjectManager((WebDriver) testContent.scenarioContext.getContext(Context.Driver))
				.getVade();
	}

	@Then("^navigate to vade$")
	public void vade() throws Exception {
		vade.vade();
	}

	// data type
	@Then("^navigate to data type$")
	public void dataType() throws Exception {
		vade.dataType();
	}

	// add
	@Then("^click the add button$")
	public void addButton() throws Exception {
		vade.addButton();
	}

	@Then("^input data type info \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
	public void dataTypeInfo(String dataTypeName, String desc, String entry, String label) throws Exception {
		vade.dataTypeInfo(dataTypeName, desc, entry, label);
	}

	@Then("^click save dataType button with \"([^\"]*)\"$")
	public void saveDataType(String expectedResult) throws Exception {
		Boolean flag = vade.saveDataType(expectedResult);
		Assert.assertTrue(flag);
	}

	// search
	@Then("^select vade search option \"([^\"]*)\" and input values \"([^\"]*)\"$")
	public void filter(String filter, String optionsValue) throws Exception {
		testContent.scenarioContext.setContext(Context.Filter, filter);
		testContent.scenarioContext.setContext(Context.FilterValue, optionsValue);
		vade.filter(filter, optionsValue);
	}

	@Then("^add filter and click search$")
	public void search() throws Exception {
		vade.search();
	}

	@Then("^validate search result with \"([^\"]*)\"$")
	public void validateResult(String expectedResult) throws Exception {
		Boolean flag = vade.searchResult(expectedResult);
		Assert.assertTrue(flag);
	}

	// delete
	@Then("^selected item$")
	public void checkedItem() throws Exception {
		String filter = (String) testContent.scenarioContext.getContext(Context.FilterValue);
		vade.checkedItem(filter);
	}
	
	@Then("^delete the datatype$")
	public void deleteDataType() throws Exception {
		vade.deleteDataType();
	}
	
	@Then("^validate if delete dataType successfully$")
	public void validateDelDataType() throws Exception {
		Boolean flag = vade.validateDelDataType();
		Assert.assertTrue(flag);
	}

	// task type
	@Then("^navigate to task type$")
	public void taskType() throws Exception {
		vade.taskType();
	}
	
	// task type add
	@Then("^input task type info \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
	public void taskTypeInfo(String taskTypeName, String entry, String desc) throws Exception {
		vade.taskTypeInfo(taskTypeName, entry, desc);
	}
	
	@Then("^click save taskType button with \"([^\"]*)\"$")
	public void saveTaskType(String expectedResult) throws Exception {
		Boolean flag = vade.saveTaskType(expectedResult);
		Assert.assertTrue(flag);
	}
	
	//task type delete
	@Then("^delete the tasktype$")
	public void deleteTaskType() throws Exception {
		vade.deleteDataType();
	}
	
	@Then("^validate if delete taskType successfully$")
	public void validateDelTaskType() throws Exception {
		Boolean flag = vade.validateDelTaskType();
		Assert.assertTrue(flag);
	}

	// data
	@Then("^navigate to data$")
	public void data() throws Exception {
		vade.data();
	}

	// program
	@Then("^navigate to program$")
	public void program() throws Exception {
		vade.program();
	}

	// task
	@Then("^navigate to task$")
	public void task() throws Exception {
		vade.task();
	}
}
