package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import common.Wait;

public class Uvms extends BasePage {

	public Uvms(WebDriver driver) {
		super(driver);
	}

	public void uvms() throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.expendRootMenu();
		this.click(By.xpath("//span[contains(text(),'UVMS')]"));
	}

	public void checkMenuIfExpend() throws Exception {
		if (this.isElementPresent(By.xpath("//span[contains(text(),'UVMS')]/ancestor::div[@aria-expanded='false']"))) {
			this.click(By.xpath("//span[contains(text(),'UVMS')]"));
		}
	}

	public void videoDevice() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/uvms/videoDevice']"));
	}

	public void channel() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/uvms/channel']"));
	}

	public void channelGroup() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/uvms/channelGroup']"));
	}

	public void addDevice() throws Exception {
		this.add();
	}

	public void addChannelGroup() throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.click(By.xpath("//h5[contains(text(),'Channel Group Tree')]/following-sibling::button"));
	}

	public void chooseParentGroup(String GroupName) throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.click(By.cssSelector("span[title='" + GroupName + "']"));
	}

	public void inputDeviceInfo(String deviceName, String deviceUri, String username, String pasword, String deviceMode)
			throws Exception {
		this.sendString(By.cssSelector("input[placeholder='Device Name']"), deviceName);
		if(deviceUri.length() > 0) {
			this.sendString(By.cssSelector("input[placeholder='Device URl']"), deviceUri);
		}
		this.sendString(By.cssSelector("input[placeholder='User Name']"), username);
		this.sendString(By.cssSelector("input[placeholder='Password']"), pasword);
		if(deviceMode.length() > 0) {
			this.click(By.xpath("//label[contains(text(),'Model')]/following-sibling::div/div"));
			this.click(By.cssSelector("li[data-value='" + deviceMode + "']"));
		}
	}

	public void updateChannelInfo(String channelName) throws Exception {
		this.sendString(By.cssSelector("input[placeholder='Channel Name']"), channelName);
	}

	public Boolean saveDevice(String expectedResult) throws Exception {
		this.save();
		Boolean flag = this.WaitElement(By.cssSelector("div[title='Add Device Success']"), implicitlyWait);
		if(!flag) {
			this.click(By.xpath("//span[contains(text(),'Cancel')]"));
		}
		return (Boolean.parseBoolean(expectedResult) == flag);
	}

	public Boolean updateDevice(String expectedResult) throws Exception {
		this.save();
		Boolean flag = this.WaitElement(By.cssSelector("div[title='Update Device Success']"), implicitlyWait);
		if(!flag) {
			this.click(By.xpath("//span[contains(text(),'Cancel')]"));
		}
		return (Boolean.parseBoolean(expectedResult) == flag);
	}

	public Boolean updateChannel(String expectedResult) throws Exception {
		this.save();
		Boolean flag = this.WaitElement(By.cssSelector("div[title='Save Channel Detail Successfully']"), implicitlyWait);
		if(!flag) {
			this.click(By.xpath("//span[contains(text(),'Cancel')]"));
		}
		return (Boolean.parseBoolean(expectedResult) == flag);
	}

	public Boolean deleteChannel() throws Exception {
		this.click(By.cssSelector("button[aria-label='Delete channel']"));
		this.click(By.xpath("//span[contains(text(),'Confirm')]"));
		return this.WaitElement(By.cssSelector("div[title='Delete Channel Successfully']"), implicitlyWait);
	}

	public void inputDeviceFilter(String Filter, String optionsValue) throws Exception {
        this.untilPageLoadComplete(implicitlyWait);
		this.click(By.cssSelector("button[aria-label='Menu']"));
		By element = null;
		switch (Filter) {
		case "name":
			this.click(By.cssSelector("li[value='DeviceName']"));
			this.sendString(By.cssSelector("input[placeholder=' Device Name']"), optionsValue);
			break;
		case "url":
			this.click(By.cssSelector("li[value='Device_URI']"));
			this.sendString(By.cssSelector("input[placeholder='Device URI']"), optionsValue);
			break;
		case "model":
			this.click(By.cssSelector("li[value='Model']"));
			element = By
					.xpath("//p[contains(text(),' Model')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
			break;
		case "channel":
			this.click(By.cssSelector("li[value='AvailableChannels']"));
			this.sendString(By.cssSelector("input[placeholder=' Available Channels']"), optionsValue);
			break;
		case "status":
			this.click(By.cssSelector("li[value='Status']"));
			element = By
					.xpath("//p[contains(text(),' Status')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
			break;
		}
	}

	public void inputChannelFilter(String Filter, String optionsValue) throws Exception {
		this.click(By.cssSelector("button[aria-label='Menu']"));
		By element = null;
		switch (Filter) {
		case "name":
			this.click(By.cssSelector("li[value='ChannelName']"));
			this.sendString(By.cssSelector("input[placeholder=' Channel Name']"), optionsValue);
			break;
		case "device":
			this.click(By.cssSelector("li[value='ParentDevice']"));
			this.sendString(By.cssSelector("input[placeholder=' Parent Device']"), optionsValue);
			break;
		case "url":
			this.click(By.cssSelector("li[value='URI_']"));
			this.sendString(By.cssSelector("input[placeholder='URI ']"), optionsValue);
			break;
		case "groupName":
			this.click(By.cssSelector("li[value='GroupName']"));
			this.sendString(By.cssSelector("input[placeholder=' Group Name']"), optionsValue);
			break;
		case "mode":
			this.click(By.cssSelector("li[value='Model']"));
			element = By
					.xpath("//p[contains(text(),' Model')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
			break;
		}
	}

	public void search() throws Exception {
		this.addFilter();
	}

	public Boolean searchResult(String expectedResult) throws Exception {
		Boolean flag = this.validateSearchResult(expectedResult);
		this.clearSearchCondition();
		return flag;
	}

	public void clickUpdateDevice(String filter) throws Exception {
		this.click(By.xpath("//span[contains(text(),'" + filter + "')]/parent::a"));
	}

	public void clickUpdateChannel(String filter) throws Exception {
		this.click(By.xpath("//span[contains(text(),'" + filter + "')]/parent::a"));
	}

	public void checkedDevice(String filter) throws Exception {
		if(this.WaitElement(By.xpath("//span[contains(text(),'" + filter + "')]/ancestor::tr/td/span/span"), implicitlyWait)) {
			this.click(By.xpath("//span[contains(text(),'" + filter + "')]/ancestor::tr/td/span/span"));
		}
	}

	public Boolean deleteDevice() throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.click(By.cssSelector("path[d='M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z']"));
		this.click(By.xpath("//span[contains(text(),'Confirm')]"));
		return this.WaitElement(By.cssSelector("div[title='Delete Device Success']"), implicitlyWait);
	}

	public void createGroupInfo(String groupName, String groupDesc, String buildName) throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.sendString(By.cssSelector("input[placeholder='Input Channel Group Name']"), groupName);
		this.sendString(By.cssSelector("input[placeholder='Input Channel Group Description']"), groupDesc);
		this.sendString(By.cssSelector("input[placeholder='Input Building Name']"), buildName);
	}

	public void saveChannelGroup() throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.save();
	}

	public Boolean validateUpdateResult(String expectedResult) throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		Boolean flag = this.WaitElement(By.cssSelector("div[title='Saved successfully.']"), implicitlyWait);
		if(!flag) {
			this.click(By.xpath("//span[contains(text(),'Cancel')]"));
		}
		this.closePopUpWindow();
		return (Boolean.parseBoolean(expectedResult) == flag);
	}

	public void searchChannelGroup(String filter) throws Exception {
		this.sendString(By.cssSelector("input[placeholder='Search']"), filter);
	}

	public void searchChannelGroup() throws Exception {
		this.click(By.cssSelector("input[placeholder='Search']/following-sibling::div/button"));
	}

	public Boolean checkSearchResult(String expectedResult) throws Exception {
		this.groupSearch();
		return (Boolean.parseBoolean(expectedResult) == this.WaitElement(By.cssSelector("span[style='color: rgb(255, 85, 0);']"), implicitlyWait));
	}
	
	public void saveAssignChannel() throws Exception {
		this.click(By.xpath("//span[contains(text(),'Save')]"));
		if (this.WaitElement(By.xpath("//span[contains(text(),'Yes')]"), implicitlyWait)) {
			this.click(By.xpath("//span[contains(text(),'No')]"));
		}
	}

	public Boolean assignChannels(String channelName, String expectedResult) throws Exception {
		Boolean flag = false;
		if ("all".equalsIgnoreCase(channelName)) {
			this.click(By.xpath("//th[contains(text(),'Channel Name')]/preceding-sibling::th//span//span"));
			this.saveAssignChannel();
			this.untilPageLoadComplete(implicitlyWait);
			flag = this.WaitElement(By.cssSelector("div[title='Saved successfully.']"), implicitlyWait);
		} else {
			if (this.isElementPresent(
					By.xpath("//td[contains(text(),'" + channelName + "')]/preceding-sibling::td//span//span"))
					&& Boolean.parseBoolean(expectedResult)) {
				this.click(By.xpath("//td[contains(text(),'" + channelName + "')]/preceding-sibling::td//span//span"));
				this.saveAssignChannel();
				this.untilPageLoadComplete(implicitlyWait);
				flag = this.WaitElement(By.cssSelector("div[title='Saved successfully.']"), implicitlyWait);
			}

			if (!this.isElementPresent(
					By.xpath("//td[contains(text(),'" + channelName + "')]/preceding-sibling::td//span//span"))
					&& !Boolean.parseBoolean(expectedResult)) {
				flag = true;
			}

		}
		return flag;
	}

	public Boolean updateChannelGroup(String expectedResult) throws Exception {
		this.click(By.xpath("//span[contains(text(), 'Update')]"));
		if (this.isElementPresent(By.xpath("//span[contains(text(),'Yes')]"))) {
			this.click(By.xpath("//span[contains(text(),'Yes')]"));
		}
		return (Boolean.parseBoolean(expectedResult) == this.WaitElement(By.cssSelector("div[title='Updated successfully.']"), implicitlyWait));
	}

	public Boolean delChannelGroup() throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.closePopUpWindow();
		this.click(By.xpath("//h5[contains(text(),'Channel Group Details')]/following::button"));
		this.click(By.xpath("//span[contains(text(),'Confirm')]"));
		return this.WaitElement(By.cssSelector("div[title='Success']"), implicitlyWait);
	}

	public void approveAccess() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/uvms/approveAccess']"));

	}

	public void inputAccessFilter(String Filter, String OptionsValue) throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		if(this.WaitElement(By.cssSelector("button[aria-label='Menu']"), implicitlyWait)) {
			this.click(By.cssSelector("button[aria-label='Menu']"));
			By clickElement = null;
			By inputElement = null;
			switch (Filter) {
			case "id":
				clickElement = By.cssSelector("li[value='RequestID']");
				inputElement = By.cssSelector("input[placeholder=' Request ID']");
				break;
			case "group":
				clickElement = By.cssSelector("li[value='RequestorGroup']");
				inputElement = By.cssSelector("input[placeholder=' Requestor Group']");
				break;
			case "by":
				clickElement = By.cssSelector("li[value='RequestBy']");
				inputElement = By.cssSelector("input[placeholder=' Request By']");
				break;
			case "status":
				clickElement = By.cssSelector("li[value='RequestStatus']");
				this.click(clickElement);
				By element = By.xpath(
						"//p[contains(text(),' Request Status')]/parent::div/following-sibling::div[@aria-pressed='false']");
				this.dropDown(element, By.cssSelector("li[data-value='" + OptionsValue + "']"));
				break;
			case "reason":
				clickElement = By.cssSelector("li[value='RequestReason']");
				inputElement = By.cssSelector("input[placeholder=' Request Reason']");
				break;
			case "actionBy":
				clickElement = By.cssSelector("li[value='ActionBy']");
				inputElement = By.cssSelector("input[placeholder=' Action By']");
				break;
			}
			if (!"status".equalsIgnoreCase(Filter)) {
				this.click(clickElement);
				this.sendString(inputElement, OptionsValue);
			}
		}

	}

	public void addAccessFilter() throws Exception {
		this.click(By.cssSelector("button[aria-label='Add']"));
	}

	public void searchAccess() throws Exception {
		this.click(By.cssSelector("button[aria-label='Directions']"));
	}

	public Boolean searchAccessResult(String filter, String filterValue, String expectedResult) throws Exception {
		Boolean flag = false;
		if ("id".equalsIgnoreCase(filter)) {
			int size = this.checkSearchResults(By.xpath("//span[contains(text(),'" + filterValue + "')]"));

			if (Boolean.parseBoolean(expectedResult) && size >= 1) {
				flag = true;
			}

			if (!Boolean.parseBoolean(expectedResult) && size == 1) {
				flag = true;
			}
		} else {
			int size = this.checkSearchResults(By.xpath("//td[contains(text(),'" + filterValue + "')]"));
			if (Boolean.parseBoolean(expectedResult) && size >= 0) {
				flag = true;
			}

			if (!Boolean.parseBoolean(expectedResult) && size < 1) {
				flag = true;
			}
		}
		return flag;

	}

	public void viewDetail(String filter, String filterValue) throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		if ("id".equalsIgnoreCase("id")) {
			if(this.WaitElement(By.xpath("//span[contains(text(),'" + filterValue + "')]/parent::a"), implicitlyWait)) {
//				this.click(By.xpath("//span[contains(text(),'" + filterValue + "')]/parent::a"));
				this.click(By.xpath("//span[contains(text(),'" + filterValue + "')]"));
			}
		} else {
			if(this.WaitElement(By.xpath("//td[contains(text(),'" + filterValue + "')]/ancestor::tr/td/a"), implicitlyWait)) {
				this.click(By.xpath("//td[contains(text(),'" + filterValue + "')]/ancestor::tr/td/a"));
			}
		}
	}

	public Boolean approve(String approveReason) throws Exception {
		this.sendString(By.xpath("//label[contains(text(),'Reason')]/ancestor::div/div/input[not(@disabled)]"), approveReason);
//		this.sendString(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/div[5]/div[2]/div/input"), approveReason);
		this.click(By.xpath("//span[contains(text(),'Approve')]/parent::button"));
		this.click(By.xpath("//span[contains(text(),'Confirm')]"));
		return this.WaitElement(By.cssSelector("div[title='Operation Success']"), implicitlyWait);

	}

	public Boolean reject(String rejectReason) throws Exception {
		this.sendString(By.xpath("//label[contains(text(),'Reason')]/ancestor::div/div/input[not(@disabled)]"), rejectReason);
//		this.sendString(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/div[5]/div[2]/div/input"), rejectReason);
		this.click(By.xpath("//span[contains(text(),'Reject')]"));
		this.click(By.xpath("//span[contains(text(),'Confirm')]"));
		return this.WaitElement(By.cssSelector("div[title='Operation Success']"), implicitlyWait);
	}

	public void icon() throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/uvms/icon-set-up']"));
	}

	public void changeIcon(String channelType) throws Exception {
		this.click(By.xpath(
				"//td[contains(text(),'" + channelType + "')]/following-sibling::td/button[@aria-label='Upload']"));
	}

	public void chooseFile() throws Exception {
		this.click(By.cssSelector("span[aria-label='Choose Files']"));
	}

	public void inputFilePath(String filepath) throws Exception {
		Wait.selfWait(5000);
		Runtime run = Runtime.getRuntime();
		String uploadFile = "cmd.exe /c start " + filepath;
		run.exec(uploadFile);
		Wait.selfWait(5000);
	}

	public Boolean upload() throws Exception {
		this.click(By.xpath("//span[contains(text(), 'Save')]/parent::button"));
		return (!this.WaitElement(By.cssSelector("h6[title='Icon Setup']"), implicitlyWait));
	}

	public void deleteIcon(String channelType) throws Exception {
		Actions action = new Actions(this.driver);
		this.WaitElement(By.xpath("//td[contains(text(),'" + channelType + "')]/following-sibling::td/div/div"), implicitlyWait);
		action.moveToElement(driver
				.findElement(By.xpath("//td[contains(text(),'" + channelType + "')]/following-sibling::td/div")))
				.perform();
		
		this.click(By.xpath("//td[contains(text(),'" + channelType + "')]/following-sibling::td/div/div/div/button[2]"));
	}
	

	public Boolean confirmDelete() throws Exception {
		this.click(By.xpath("//span[contains(text(), 'Confirm')]/parent::button"));
		return this.WaitElement(By.cssSelector("div[title='Deleted successfully.']"), implicitlyWait);
	}

	public void firmware() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/uvms/firmware']"));
	}

	public void uploadFirmware() throws Exception {
		this.click(By.cssSelector("button[aria-label='Upload']"));
	}

	public void firmwareInfo(String fileType, String version, String comment) throws Exception {
		this.dropDown(By.xpath("//label[contains(text(), 'File Type')]/following-sibling::div/div"),
				By.cssSelector("li[data-value='" + fileType + "']"));

		this.sendString(By.cssSelector("textarea[placeholder='Firmware version']"), version);

		this.sendString(By.cssSelector("textarea[placeholder='Comment']"), comment);

	}

	public void selectFile(String filepath) throws Exception {
		this.click(By.xpath("//label[contains(text(), 'Choose Files')]/following-sibling::div/div/label/span"));
		Wait.selfWait(5000);
		Runtime run = Runtime.getRuntime();
		String uploadFile = "cmd.exe /c start " + filepath;
		run.exec(uploadFile);
		Wait.selfWait(5000);
	}
	
	public Boolean saveFirmware() throws Exception {
		this.click(By.xpath("//span[contains(text(), 'Save')]/parent::button"));
		return this.WaitElement(By.cssSelector("div[title='Upload Firmware Success']"), 12 * implicitlyWait);
	}
	
	public void inputFirmwareFilter(String Filter, String optionsValue) throws Exception {
		this.click(By.cssSelector("button[aria-label='Menu']"));
		switch (Filter) {
		case "name":
			this.click(By.cssSelector("li[value='FirmwareName']"));
			this.sendString(By.cssSelector("input[placeholder=' Firmware Name']"), optionsValue);
			break;
		case "model":
			this.click(By.cssSelector("li[value='Model']"));
			By element = By
					.xpath("//p[contains(text(),' Model')]/parent::div/following-sibling::div[@aria-pressed='false']");
			this.dropDown(element, By.cssSelector("li[data-value='" + optionsValue + "']"));
			break;
		}
	}
	
	public void clickUpdateFirmware(String filter) throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		if(this.WaitElement(By.xpath("//span[contains(text(),'" + filter + "')]/parent::a"), implicitlyWait)) {
			this.click(By.xpath("//span[contains(text(),'" + filter + "')]/parent::a"));
		}
	}
	
	public Boolean updateFirmware(String expectedResult) throws Exception {
		this.click(By.xpath("//span[contains(text(), 'Save')]/parent::button"));
		Boolean flag = this.WaitElement(By.cssSelector("div[title='Update Firmware Success']"), implicitlyWait);
		if (!flag) {
			this.click(By.xpath("//span[contains(text(), 'Cancel')]/parent::button"));
		}
		return (Boolean.parseBoolean(expectedResult) == flag);
	}
	
	public Boolean deleteFirmware() throws Exception {
		this.delete2();
		return this.WaitElement(By.cssSelector("div[title='Delete Firmware Success']"), implicitlyWait);
	}
}
