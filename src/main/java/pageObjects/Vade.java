package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Vade extends BasePage {

	public Vade(WebDriver driver) {
		super(driver);
	}

	public void vade() throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.expendRootMenu();
		this.click(By.xpath("//span[contains(text(),'VADE')]"));
	}

	public void checkMenuIfExpend() throws Exception {
		if (this.isElementPresent(By.xpath("//span[contains(text(),'VADE')]/ancestor::div[@aria-expanded='false']"))) {
			this.click(By.xpath("//span[contains(text(),'VADE')]"));
		}
	}

	public void dataType() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/vade/datatype']"));
	}

	public void filter(String Filter, String optionsValue) throws Exception {
		this.click(By.cssSelector("button[aria-label='Menu']"));
		By element = null;
		switch (Filter) {
		case "dataTypeName":
			this.click(By.cssSelector("li[value='DataTypeName']"));
			this.sendString(By.cssSelector("input[placeholder=' Data Type Name']"), optionsValue);
			break;
		case "entry":
			this.click(By.cssSelector("li[value='Entry']"));
			element = By
					.xpath("//p[contains(text(),' Entry')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
			break;
		case "taskTypeName":
			this.click(By.cssSelector("li[value='TaskTypeName']"));
			this.sendString(By.cssSelector("input[placeholder=' Task Type Name']"), optionsValue);
			break;
		case "dataName":
			this.click(By.cssSelector("li[value='DataName']"));
			this.sendString(By.cssSelector("input[placeholder=' Data Name']"), optionsValue);
			break;
		case "programName":
			this.click(By.cssSelector("li[value='ProgramName']"));
			this.sendString(By.cssSelector("input[placeholder=' Program Name']"), optionsValue);
			break;
		case "taskType":
			this.click(By.cssSelector("li[value='TaskType']"));
			element = By.xpath(
					"//p[contains(text(),' Task Type')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
		case "status":
			this.click(By.cssSelector("li[value='Status']"));
			element = By
					.xpath("//p[contains(text(),' Status')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
		case "taskName":
			this.click(By.cssSelector("li[value='TaskName']"));
			this.sendString(By.cssSelector("input[placeholder=' Task Name']"), optionsValue);
			break;
		}
	}

	public void addButton() throws Exception {
		this.closePopUpWindow();
		this.add();
	}

	public void search() throws Exception {
		this.addFilter();
	}

	public void dataTypeInfo(String dataTypeName, String desc, String entry, String label) throws Exception {
		this.sendString(By.cssSelector("input[placeholder='Data Type Name']"), dataTypeName);
		this.sendString(By.cssSelector("input[placeholder='Description']"), desc);
		this.dropDown(By.xpath("//label[contains(text(),'Entry')]/parent::div/div/div"), By.cssSelector("li[data-value='" + entry + "']"));
		if ("dataset".equalsIgnoreCase(entry)) {
			this.dropDown(By.xpath("//label[contains(text(),'Labeled')]/parent::div/div/div"), By.cssSelector("li[data-value='" + label + "']"));
		} 
	}

	public Boolean saveDataType(String expectedResult) throws Exception {
		this.save();
		Boolean flag = this.WaitElement(By.cssSelector("div[title='Saved Data Type Successfully']"), implicitlyWait);
		if (!flag) {
			this.click(By.xpath("//span[contains(text(),'Cancel')]"));
		}
		return (Boolean.parseBoolean(expectedResult) == flag);
	}

	public Boolean searchResult(String expectedResult) throws Exception {
		Boolean flag = this.validateSearchResult(expectedResult);
		this.clearSearchCondition();
		return flag;
	}

	public void taskType() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/vade/tasktype']"));
	}

	public void data() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/vade/data']"));
	}

	public void program() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/vade/program']"));
	}

	public void task() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/vade/task']"));
	}

	public void deleteDataType() throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.delete2();
	}

	public Boolean validateDelDataType() throws Exception {
		return this.WaitElement(By.cssSelector("div[title='Deleted Data Type Successfully']"), implicitlyWait);
	}

	public void checkedItem(String filter) throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		if(this.WaitElement(By.xpath("//span[contains(text(),'" + filter + "')]/ancestor::tr/td/span/span"), implicitlyWait)) {
			this.click(By.xpath("//span[contains(text(),'" + filter + "')]/ancestor::tr/td/span/span"));
		}
	} 
	
	public void taskTypeInfo(String taskTypeName, String entry, String desc) throws Exception {
		this.sendString(By.cssSelector("input[placeholder='Task Type Name']"), taskTypeName);
        this.dropDown(By.xpath("//label[contains(text(),'Entry')]/parent::div/div/div"), By.cssSelector("li[data-value='" + entry + "']"));	
		this.sendString(By.cssSelector("input[placeholder='Description']"), desc);
	}
	
	public Boolean saveTaskType(String expectedResult) throws Exception {
		this.save();
		Boolean flag = this.WaitElement(By.cssSelector("div[title='Saved Task Type Successfully']"), implicitlyWait);

		if (!flag) {
			this.click(By.xpath("//span[contains(text(),'Cancel')]"));
		}
		return (Boolean.parseBoolean(expectedResult) == flag);
	}
	
	public Boolean validateDelTaskType() throws Exception {
		return this.WaitElement(By.cssSelector("div[title='Deleted Task Type Successfully']"), implicitlyWait);
	}
}
