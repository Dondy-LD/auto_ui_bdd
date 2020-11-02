package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.TestContent;
import cucumber.api.java.en.Then;
import enums.Context;
import pageObjects.Uvms;

public class UvmsPageSteps {
	private static TestContent testContent;
	private static Uvms uvms;

	public UvmsPageSteps(TestContent content) {
		testContent = content;
		System.out.println("-----------------------Init-----------------------------");
		uvms = testContent.getPageObjectManager((WebDriver) testContent.scenarioContext.getContext(Context.Driver))
				.getUvms();
	}

	@Then("^navigate to uvms$")
	public void uvms() throws Exception {
		uvms.uvms();
	}

	// video device
	@Then("^navigate to uvms video device$")
	public void videoDevice() throws Exception {
		uvms.videoDevice();
	}

	// video device add
	@Then("^click add device button$")
	public void addDevice() throws Exception {
		uvms.addDevice();
	}

	@Then("^input device info with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
	public void inputDeviceInfo(String deviceName, String deviceUri, String username, String pasword, String deviceMode)
			throws Exception {
		uvms.inputDeviceInfo(deviceName, deviceUri, username, pasword, deviceMode);
	}

	@Then("^save the device with \"([^\"]*)\"$")
	public void saveDevice(String expectedResult) throws Exception {
		Boolean flag = uvms.saveDevice(expectedResult);
		Assert.assertTrue(flag);
	}

	// video device search
	@Then("^select search option \"([^\"]*)\" and input values \"([^\"]*)\"$")
	public void inputDeviceFilter(String filter, String optionsValue) throws Exception {
		testContent.scenarioContext.setContext(Context.Filter, filter);
		testContent.scenarioContext.setContext(Context.FilterValue, optionsValue);
		uvms.inputDeviceFilter(filter, optionsValue);
	}

	@Then("^add filter and search$")
	public void search() throws Exception {
		uvms.search();
	}

	@Then("^search device results should like \"([^\"]*)\"$")
	public void searchResult(String expectedResult) throws Exception {
		Boolean flag = uvms.searchResult(expectedResult);
		Assert.assertTrue(flag);
	}

	// video device update
	@Then("^click device to update$")
	public void clickUpdateDevice() throws Exception {
		String filter = (String) testContent.scenarioContext.getContext(Context.FilterValue);
		uvms.clickUpdateDevice(filter);
	}

	@Then("^update the device with \"([^\"]*)\"$")
	public void updateDevice(String expectedResult) throws Exception {
		Boolean flag = uvms.updateDevice(expectedResult);
		Assert.assertTrue(flag);
	}

	// video device delete
	@Then("^checked item to delete$")
	public void checkedItem() throws Exception {
		String filter = (String) testContent.scenarioContext.getContext(Context.FilterValue);
		uvms.checkedDevice(filter);
	}

	@Then("^click delete device button$")
	public void deleteDevice() throws Exception {
		Boolean flag = uvms.deleteDevice();
		Assert.assertTrue(flag);
	}

	// uvms channel
	@Then("^navigate to uvms channel$")
	public void channel() throws Exception {
		uvms.channel();
	}

	@Then("^select search channel option \"([^\"]*)\" and input values \"([^\"]*)\"$")
	public void inputChannelFilter(String filter, String optionsValue) throws Exception {
		testContent.scenarioContext.setContext(Context.Filter, filter);
		testContent.scenarioContext.setContext(Context.FilterValue, optionsValue);
		uvms.inputChannelFilter(filter, optionsValue);
	}

	// uvms channel update
	@Then("^click channel to update$")
	public void clickUpdateChannel() throws Exception {
		String filter = (String) testContent.scenarioContext.getContext(Context.FilterValue);
		uvms.clickUpdateChannel(filter);
	}

	@Then("^input update channel info \"([^\"]*)\"$")
	public void updateChannelInfo(String channelName) throws Exception {
		uvms.updateChannelInfo(channelName);
	}

	@Then("^update the channel with \"([^\"]*)\"$")
	public void updateChannel(String expectedResult) throws Exception {
		Boolean flag = uvms.updateChannel(expectedResult);
		Assert.assertTrue(flag);
	}

	// delete click delete Channel button
	@Then("^click delete Channel button$")
	public void deleteChannel() throws Exception {
		Boolean flag = uvms.deleteChannel();
		Assert.assertTrue(flag);
	}

	// navigate to uvms channel group
	@Then("^navigate to uvms channel group$")
	public void channelGroup() throws Exception {
		uvms.channelGroup();
	}

	// channel group add
	@Then("^click add channel group button$")
	public void addChannelGroup() throws Exception {
		uvms.addChannelGroup();
	}

	@Then("^select parent channel group \"([^\"]*)\"$")
	public void chooseParentGroup(String groupName) throws Exception {
		uvms.chooseParentGroup(groupName);
	}

	@Then("^input channel group info with \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
	public void createGroupInfo(String groupName, String groupDesc, String buildName) throws Exception {
		uvms.createGroupInfo(groupName, groupDesc, buildName);
	}

	@Then("^click save add channel group button$")
	public void saveChannelGroup() throws Exception {
		uvms.saveChannelGroup();
	}

	@Then("^save channel group result like \"([^\"]*)\"$")
	public void validateUpdateResult(String expectedResult) throws Exception {
		Boolean flag = uvms.validateUpdateResult(expectedResult);
		Assert.assertTrue(flag);
	}

	// channel Group Search
	@Then("^input search channel group \"([^\"]*)\"$")
	public void searchChannelGroup(String filter) throws Exception {
		uvms.searchChannelGroup(filter);
	}

	@Then("^click search channel group button$")
	public void searchChannelGroup() throws Exception {
		uvms.searchChannelGroup();
	}

	@Then("^search channel group result like \"([^\"]*)\"$")
	public void checkSearchResult(String expectedResult) throws Exception {
		Boolean flag = uvms.checkSearchResult(expectedResult);
		Assert.assertTrue(flag);
	}

	// Assign channel to channel group
	@Then("^assign channel to channel group with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void assignChannels(String channelName, String expectedResult) throws Exception {
		Boolean flag = uvms.assignChannels(channelName, expectedResult);
		Assert.assertTrue(flag);
	}

	// channel group update
	@Then("^update group result like \"([^\"]*)\"$")
	public void updateChannelGroup(String expectedResult) throws Exception {
		Boolean flag = uvms.updateChannelGroup(expectedResult);
		Assert.assertTrue(flag);
	}

	// channel group Delete
	@Then("^click delete channel group button$")
	public void delChannelGroup() throws Exception {
		Boolean flag = uvms.delChannelGroup();
		Assert.assertTrue(flag);
	}

	// approve access
	@Then("^navigate to uvms approve access$")
	public void approveAccess() throws Exception {
		uvms.approveAccess();
	}

	// approve access search
	@Then("^input search approve access option \"([^\"]*)\" and \"([^\"]*)\"$")
	public void inputAccessFilter(String filter, String optionsValue) throws Exception {
		testContent.scenarioContext.setContext(Context.Filter, filter);
		testContent.scenarioContext.setContext(Context.FilterValue, optionsValue);
		uvms.inputAccessFilter(filter, optionsValue);
	}

	@Then("^click add access filter button$")
	public void addAccessFilter() throws Exception {
		uvms.addAccessFilter();
	}

	@Then("^click search access button$")
	public void searchAccess() throws Exception {
		uvms.searchAccess();
	}

	@Then("^search access results should like \"([^\"]*)\"$")
	public void searchAccessResult(String expectedResult) throws Exception {
		String filter = (String) testContent.scenarioContext.getContext(Context.Filter);
		String filterValue = (String) testContent.scenarioContext.getContext(Context.FilterValue);
		Boolean flag = uvms.searchAccessResult(filter, filterValue, expectedResult);
		Assert.assertTrue(flag);
	}

	@Then("^view the request detail$")
	public void viewDetail() throws Exception {
		String filter = (String) testContent.scenarioContext.getContext(Context.Filter);
		String filterValue = (String) testContent.scenarioContext.getContext(Context.FilterValue);
		uvms.viewDetail(filter, filterValue);
	}

	@Then("^approve the request and \"([^\"]*)\"$")
	public void approve(String approveReason) throws Exception {
		Boolean flag = uvms.approve(approveReason);
		Assert.assertTrue(flag);
	}

	@Then("^reject the request and \"([^\"]*)\"$")
	public void reject(String rejectReason) throws Exception {
		Boolean flag = uvms.reject(rejectReason);
		Assert.assertTrue(flag);
	}

	// icon setup
	@Then("^navigate to uvms icon$")
	public void icon() throws Exception {
		uvms.icon();
	}

	@Then("^change icon for select \"([^\"]*)\"$")
	public void changeIcon(String channelType) throws Exception {
		uvms.changeIcon(channelType);
	}

	@Then("^click choose file$")
	public void chooseFile() throws Exception {
		uvms.chooseFile();
	}

	@Then("^input upload file \"([^\"]*)\"$")
	public void inputFilePath(String filepath) throws Exception {
		uvms.inputFilePath(filepath);
	}

	@Then("^save the icon$")
	public void upload() throws Exception {
		Boolean flag = uvms.upload();
		Assert.assertTrue(flag);
	}

	@Then("^move cursor icon for select \"([^\"]*)\"$")
	public void deleteIcon(String channelType) throws Exception {
		uvms.deleteIcon(channelType);
	} 
	
	@Then("^confirm delete$")
	public void confirmDelete() throws Exception {
		Boolean flag = uvms.confirmDelete();
		Assert.assertTrue(flag);
	}

	// Firmware
	@Then("^navigate to uvms firmware$")
	public void firmware() throws Exception {
		uvms.firmware();
	}
	
	@Then("^click upload button$")
	public void uploadFirmware() throws Exception {
		uvms.uploadFirmware();
	}
	
	@Then("^input firmware info \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
	public void firmwareInfo(String fileType, String version, String comment) throws Exception {
		uvms.firmwareInfo(fileType, version, comment);
	}
	
	@Then("^select file \"([^\"]*)\" to upload$")
	public void selectFile(String filepath) throws Exception {
		uvms.selectFile(filepath);
	}
	
	@Then("^save the firmware$")
	public void saveFirmware() throws Exception {
		Boolean flag = uvms.saveFirmware();
		Assert.assertTrue(flag);
	}
	
	@Then("^select search firmware option \"([^\"]*)\" and input values \"([^\"]*)\"$")
	public void inputFirmwareFilter(String filter, String optionsValue) throws Exception {
		testContent.scenarioContext.setContext(Context.Filter, filter);
		testContent.scenarioContext.setContext(Context.FilterValue, optionsValue);
		uvms.inputFirmwareFilter(filter, optionsValue);
	}
	
	@Then("^select firmware to update$")
	public void clickUpdateFirmware() throws Exception {
		String filter = (String) testContent.scenarioContext.getContext(Context.FilterValue);
		uvms.clickUpdateFirmware(filter);
	}
	
	@Then("^update the firmware with \"([^\"]*)\"$")
	public void updateFirmware(String expectedResult) throws Exception {
		Boolean flag = uvms.updateFirmware(expectedResult);
		Assert.assertTrue(flag);
	}	
	
	@Then("^click delete firmware button$")
	public void deleteFirmware() throws Exception {
		Boolean flag = uvms.deleteFirmware();
		Assert.assertTrue(flag);
	}
	
}
