package pageObjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import managers.FileReaderManager;

public class SecurityPage extends BasePage {

	public SecurityPage(WebDriver driver) {
		super(driver);
	}

	public void security() throws Exception {
		this.expendRootMenu();
		this.click(By.xpath("//span[contains(text(),'Security')]"));
	}

	public void checkMenuIfExpend() throws Exception {
		if (this.isElementPresent(
				By.xpath("//span[contains(text(),'Security')]/ancestor::div[@aria-expanded='false']"))) {
			this.click(By.xpath("//span[contains(text(),'Security')]"));
		}
	}

	public void userManagement() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/security/user']"));
	}

	public void userGroup() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/security/user-group']"));
	}

	public void navigateRoleManagement() throws Exception {
		this.checkMenuIfExpend();
		this.click(By.cssSelector("a[href='/security/role']"));
	}

	public void addUser() throws Exception {
		this.closePopUpWindow();
		if (this.WaitElement(By.cssSelector("button[aria-label='Create User']"), implicitlyWait)) {
			this.click(By.cssSelector("button[aria-label='Create User']"));
		}
	}

	public void clickAddRole() throws Exception {
		this.closePopUpWindow();
		this.click(By.cssSelector("button[aria-label='Add Role']"));
	}

	public void addUserGroup() throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.closePopUpWindow();
		this.add();
	}

	public void chooseParentGroup(String GroupName) throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.click(By.cssSelector("span[title='" + GroupName + "']"));
	}

	public void createUser(String userId, String userName, String email, String phoneNumber) throws Exception {
		this.closePopUpWindow();
		this.click(By.cssSelector("button[aria-label='Create User']"));
		this.sendString(By.cssSelector("input[placeholder='user id']"), userId);
		this.sendString(By.cssSelector("input[placeholder='full name']"), userName);
		this.sendString(By.cssSelector("input[placeholder='email']"), email);
		this.sendString(By.cssSelector("input[placeholder='phone']"), phoneNumber);
		this.click(By.xpath("//span[contains(text(),'Save')]"));

		if (!this.WaitElement(By.cssSelector("div[title='Saved successfully.']"), implicitlyWait)) {
			this.click(By.xpath("//span[contains(text(),'Cancel')]"));
		} else {
			this.closePopUpWindow();
		}
	}

	public void ifNeedToLogout() throws Exception {
		this.normalLogout();
	}

	public void ifNeedToRelogin(String username, String password) throws Exception {
		this.sendString(By.id("userName"), username);
		this.sendString(By.id("password"), password);
		this.click(By.id("btnSignIn"));
	}

	public void changePassword(String defaultPassword, String newPassword) throws Exception {
		this.sendString(By.cssSelector("input[placeholder='Old Password']"), defaultPassword);
		this.sendString(By.cssSelector("input[placeholder='New Password']"), newPassword);
		this.sendString(By.cssSelector("input[placeholder='Confirm New Password']"), newPassword);
		this.click(By.xpath("//span[contains(text(),'Update')]"));

		if (!this.WaitElement(By.cssSelector("div[title='Success']"), implicitlyWait)) {
			this.click(By.xpath("//span[contains(text(),'Cancel')]"));
		}
	}

	public Boolean createUser() throws Exception {
		Boolean flag = true;
		List<Map<String, String>> userList = FileReaderManager.getInstance().getExcelReader()
				.readExcel2("src/test/resources/testDataResources/TestData.xls", "SecurityUser");

		for (int i = 0; i < userList.size(); i++) {
			System.out.println(userList.get(i).get("UserId"));
			if (i >= 1) {
				this.addUser();
			}
			this.sendString(By.cssSelector("input[placeholder='user id']"), userList.get(i).get("UserId"));
			this.sendString(By.cssSelector("input[placeholder='full name']"), userList.get(i).get("UserName"));
			this.sendString(By.cssSelector("input[placeholder='email']"), userList.get(i).get("Email"));
			this.sendString(By.cssSelector("input[placeholder='phone']"), userList.get(i).get("PhoneNumber"));
			this.click(By.xpath("//span[contains(text(),'Save')]"));

			Boolean condition = Boolean.valueOf(userList.get(i).get("ExpectedResult"));
			if (!validateAddUser(condition)) {
				this.click(By.xpath("//span[contains(text(),'Cancel')]"));
			} else {
				flag = true;
			}

		}
		return flag;
	}

	public void searchUserFilter(String Filter, String OptionsValue) throws Exception {
		this.WaitElement(By.cssSelector("button[aria-label='Menu']"), implicitlyWait);
		this.click(By.cssSelector("button[aria-label='Menu']"));
//		this.click(By.cssSelector("path[d='M3 18h18v-2H3v2zm0-5h18v-2H3v2zm0-7v2h18V6H3z']"));
		By clickElement = null;
		By inputElement = null;
		switch (Filter) {
		case "userId":
			clickElement = By.cssSelector("li[value='UserID']");
			inputElement = By.cssSelector("input[placeholder=' User ID']");
			break;
		case "fullName":
			clickElement = By.cssSelector("li[value='FullName']");
			inputElement = By.cssSelector("input[placeholder=' Full Name']");
			break;
		case "groupName":
			clickElement = By.cssSelector("li[value='GroupName']");
			inputElement = By.cssSelector("input[placeholder=' Group Name']");
			break;
		case "email":
			clickElement = By.cssSelector("li[value='Email']");
			inputElement = By.cssSelector("input[placeholder=' Email']");
			break;
		case "loginDate":
			clickElement = By.cssSelector("li[value='LastLoginDate']");
			break;
		}
		this.click(clickElement);
		if (!Filter.equalsIgnoreCase("loginDate")) {
			this.sendString(inputElement, OptionsValue);
		}
	}

	public void inputRoleFilter(String Filter, String OptionsValue) throws Exception {
		this.click(By.cssSelector("button[aria-label='Menu']"));
		By clickElement = null;
		By inputElement = null;
		switch (Filter) {
		case "name":
			clickElement = By.cssSelector("li[value='Name']");
			inputElement = By.cssSelector("input[placeholder=' Name']");
			break;
		case "desc":
			clickElement = By.cssSelector("li[value='Description']");
			inputElement = By.cssSelector("input[placeholder=' Description']");
			break;
		}
		this.click(clickElement);
		this.sendString(inputElement, OptionsValue);
	}

	public Boolean searchRoleResult(String filter, String filterValue, String expectedResult) throws Exception {
		Boolean flag = false;
		if (filter.equalsIgnoreCase("name")) {
			int size = this.checkSearchResults(By.xpath("//span[contains(text(),'" + filterValue + "')]"));
			if (Boolean.parseBoolean(expectedResult) && size >= 1) {
				flag = true;
			}

			if (!Boolean.parseBoolean(expectedResult) && size == 1) {
				flag = true;
			}
		} else {
			int size = this.checkSearchResults(By.xpath("//td[contains(text(),'" + filterValue + "')]"));
			if (Boolean.parseBoolean(expectedResult) && size >= 0)
				flag = true;

			if (!Boolean.parseBoolean(expectedResult) && size < 1)
				flag = true;
		}
		this.clearSearchCondition();
		return flag;

	}

	public void filterSearch() throws Exception {
		this.addFilter();
	}

	public Boolean checkUserIfExist(String filter, String filterValue) throws Exception {
		Boolean flag = false;
		if (filter.equalsIgnoreCase("userId") || filter.equalsIgnoreCase("groupName")) {
			this.WaitElement(By.xpath("//span[contains(text(),'" + filterValue + "')]"), implicitlyWait);
			int size = this.checkSearchResults(By.xpath("//span[contains(text(),'" + filterValue + "')]"));
			flag = size > 1;
		} else {
			this.WaitElement(By.xpath("//td[contains(text(),'" + filterValue + "')]"), implicitlyWait);
			int size = this.checkSearchResults(By.xpath("//td[contains(text(),'" + filterValue + "')]"));
			flag = size > 0;
		}
		return flag;
	}

	public Boolean checkSearchResult(String filter, String filterValue, String expectedResult) throws Exception {
		// wait for the result
		this.untilPageLoadComplete(implicitlyWait);
		Boolean flag = false;
		if (filter.equalsIgnoreCase("userId") || filter.equalsIgnoreCase("groupName")) {
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

		// clear search condition
		this.clearSearchCondition();
		return flag;
	}

	public void updateUser(String filterValue) throws Exception {
		if (this.WaitElement(By.xpath("//span[text()='Update']"), implicitlyWait)) {
//			this.click(By.xpath("//span[text()='Update']"));
			this.click(By.xpath("//span[contains(text(),'" + filterValue
					+ "')]/ancestor::td/following-sibling::td/button/span[contains(text(),'Update')]"));
		}

//		if (!this.WaitElement(By.xpath("//span[contains(text(),'Update Role')]"), implicitlyWait)) {
//			this.click(By.xpath("//span[contains(text(),'" + filterValue
//					+ "')]/ancestor::td/following-sibling::td/button/span[contains(text(),'Update')]"));
//		}
	}

	public void assignRole(String filterValue) throws Exception {
//		this.click(By.xpath("//span[contains(text(),'Role')]/ancestor::button"));
		this.click(By.xpath("//span[contains(text(),'" + filterValue
				+ "')]/ancestor::td/following-sibling::td/button/span[contains(text(),'Role')]"));
	}

	public void updateUsrInfo(String userName, String email, String phoneNumber) throws Exception {
		if (!"N".equalsIgnoreCase(userName)) {
			System.out.println("N".equalsIgnoreCase(userName));
			this.sendString(By.cssSelector("input[placeholder='full name']"), userName);
		}

		if (!"N".equalsIgnoreCase(email)) {
			System.out.println("N".equalsIgnoreCase(email));
			this.sendString(By.cssSelector("input[placeholder='email']"), email);
		}

		if (!"N".equalsIgnoreCase(phoneNumber)) {
			System.out.println("N".equalsIgnoreCase(phoneNumber));
			this.sendString(By.cssSelector("input[placeholder='phone']"), phoneNumber);
		}
	}

	public Boolean chkUpdateUsrResult(String expectedResult) throws Exception {
		this.save();
		this.untilPageLoadComplete(implicitlyWait);
		Boolean flag = null;
		if (Boolean.parseBoolean(expectedResult)) {
			flag = this.WaitElement(By.cssSelector("div[title='Updated successfully.']"), 3 * implicitlyWait);
			// this.isElementPresent(By.cssSelector("div[title='Updated successfully.']"));
			this.closePopUpWindow();
		} else {
			flag = this.isElementPresent(By.cssSelector("h6[title='Update User']"))
					|| this.isElementPresent(By.xpath("//p[contains(text(),'Mandatory Field')]"));
			if (flag) {
				this.click(By.xpath("//span[contains(text(),'Cancel')]"));
			}
		}
		this.clearSearchCondition();
		return flag;

	}

	public Boolean validateAddUser(Boolean ExpectedResult) throws Exception {
		Boolean flag = null;
		if (ExpectedResult) {
			flag = this.WaitElement(By.cssSelector("div[title='Saved successfully.']"), implicitlyWait);
		} else {
			flag = this.isElementPresent(By.cssSelector("div[title='Create User']"))
					|| this.isElementPresent(By.xpath("//p[contains(text(),'Mandatory Field')]"));
			System.out.println("Create user failed : " + flag);
		}
		return flag;
	}

	public void assignRoleAccess(String roleName) throws Exception {
		this.click(By.xpath("//td[text()='" + roleName + "']/preceding-sibling::td/span/span"));
	}

	public Boolean saveAssignRole() throws Exception {
		this.save();
		if (this.WaitElement(By.cssSelector("div[title='Saved successfully.']"), implicitlyWait)) {
			return true;
		} else {
			this.click(By.xpath("//span[contains(text(),'Cancel')]"));
			return false;
		}
	}

	public void deleteUser(String filterValue) throws Exception {
		this.click(By.xpath("//span[contains(text(),'Delete')]"));
//		this.click(By.xpath("//span[contains(text(),'"+ filterValue +"')]/ancestor::tr/td/span/span"));
//		this.click(By.xpath("//span[contains(text(),'"+ filterValue +"')]/ancestor::tr/td/span/span/svg"));
//		this.click(By.cssSelector("path[d='M19 5v14H5V5h14m0-2H5c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2z']"));
	}

	public void batchDeleteUser() throws Exception {
		this.closePopUpWindow();
		this.click(By.cssSelector("button[aria-label='Batch Delete']"));
	}

	public void clearConditions() throws Exception {
		this.clearSearchCondition();
		this.closePopUpWindow();
	}

	public Boolean clickConfDelBtn() throws Exception {
		this.click(By.xpath("//span[contains(text(),'Confirm')]"));
		Boolean flag = this.WaitElement(By.cssSelector("div[title='Deleted successfully.']"), implicitlyWait);
		return flag;
	}

	public void createGroupInfo(String groupName, String groupDesc, String licenseNum) throws Exception {
		if (this.WaitElement(By.xpath("//label[contains(text(),'User Group Name*')]/ancestor::div//div//textarea"),
				3 * implicitlyWait)) {
			this.sendString(By.xpath("//label[contains(text(),'User Group Name*')]/ancestor::div//div//textarea"),
					groupName);
		}

		if (this.WaitElement(By.xpath("//label[contains(text(),'User Group Description*')]/following::div//textarea"),
				3 * implicitlyWait)) {
			this.sendString(By.xpath("//label[contains(text(),'User Group Description*')]/following::div//textarea"),
					groupDesc);
		}

		if (this.WaitElement(By.cssSelector("input[type='number']"), 3 * implicitlyWait)) {
			this.sendString(By.cssSelector("input[type='number']"), licenseNum);
		}
	}

	public void updateGroupInfo(String groupName, String groupDesc) throws Exception {
		if (this.WaitElement(By.xpath("//label[contains(text(),'User Group Name')]/ancestor::div//div//textarea"),
				implicitlyWait)) {
			this.sendString(By.xpath("//label[contains(text(),'User Group Name')]/ancestor::div//div//textarea"),
					groupName);
			this.sendString(By.xpath("//label[contains(text(),'User Group Description')]/following::div//textarea"),
					groupDesc);
			this.click(By.xpath("//span[contains(text(), 'Update')]"));
		}
	}

	public void saveGroup() throws Exception {
		this.save();
	}

	public void userGroupFilter(String filter) throws Exception {
		this.sendString(By.cssSelector("input[placeholder='Search']"), filter);
	}

	public Boolean submitSearchUserGroup(String expectedResult) throws Exception {
		this.groupSearch();
		Boolean result = this.WaitElement(By.cssSelector("span[style='color: rgb(255, 85, 0);']"), implicitlyWait);
		return (Boolean.parseBoolean(expectedResult) == result);
	}

	public Boolean validateUpdateResult(String expectedResult) throws Exception {
//		this.untilPageLoadComplete(implicitlyWait);
		return (Boolean.parseBoolean(expectedResult) == this.WaitElement(By.cssSelector("div[title='Updated successfully.']"), implicitlyWait));
	}

	public void searchAssignUser(String userId) throws Exception {
		if (this.WaitElement(By.cssSelector("input[placeholder='User ID']"), implicitlyWait)) {
			this.sendString(By.cssSelector("input[placeholder='User ID']"), userId);
			this.addFilter();
		}
	}

	public void searchAssignChannel(String channelName) throws Exception {
		if (this.WaitElement(By.cssSelector("input[placeholder=' Channel Name']"), implicitlyWait)) {
			this.sendString(By.cssSelector("input[placeholder=' Channel Name']"), channelName);
			this.addFilter();
		}
	}

	public void searchAssignEngine(String engineName) throws Exception {
		if (this.WaitElement(By.cssSelector("input[placeholder='VA Engine Name']"), implicitlyWait)) {
			this.sendString(By.cssSelector("input[placeholder='VA Engine Name']"), engineName);
			this.addFilter();
		}
	}

	public void selectUser(String userId) throws Exception {
		if (this.WaitElement(By.xpath("//td[contains(text(),'" + userId + "')]/preceding-sibling::td//span//span"),
				3 * implicitlyWait)) {
			this.click(By.xpath("//td[contains(text(),'" + userId + "')]/preceding-sibling::td//span//span"));
		}
	}

	public void selectChannel(String channelName) throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.click(By.xpath("//td[contains(text(),'" + channelName + "')]/preceding-sibling::td//span//span"));
	}

	public void selectEngine(String engineName) throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.WaitElement(By.xpath("//td[contains(text(),'" + engineName + "')]/preceding-sibling::td//span//label"),
				implicitlyWait);
		this.click(By.xpath("//td[contains(text(),'" + engineName + "')]/preceding-sibling::td//span//label"));
	}

	public Boolean assignUserToGroup() throws Exception {
		this.closePopUpWindow();
		this.save();
		this.untilPageLoadComplete(implicitlyWait);
		return this.WaitElement(By.cssSelector("div[title='Saved successfully.']"), implicitlyWait);
	}

	public void clickAssignChannels() throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		if (this.WaitElement(By.xpath("//span[contains(text(),'Assign Channels')]"), 3 * implicitlyWait)) {
			this.click(By.xpath("//span[contains(text(),'Assign Channels')]"));
		}
	}

	public Boolean assignChannels() throws Exception {
		this.closePopUpWindow();
		this.save();
		this.untilPageLoadComplete(implicitlyWait);
		return this.WaitElement(By.cssSelector("div[title='Assign Channel Successfully']"), implicitlyWait);
	}

	public void clickAssignVAEngines() throws Exception {
		if (this.WaitElement(By.xpath("//span[contains(text(),'Assign VA Engines')]"), implicitlyWait)) {
			this.click(By.xpath("//span[contains(text(),'Assign VA Engines')]"));
		}
	}

	public void clickFeatureBtn() throws Exception {
		if (this.WaitElement(By.xpath("//span[contains(text(),'Feature')]"), implicitlyWait)) {
			this.click(By.xpath("//span[contains(text(),'Feature')]"));
		}
	}

	public void assignFeature(String featureName) throws Exception {
		if (this.WaitElement(
				By.xpath("//div[contains(text(),'" + featureName + "')]/preceding-sibling::div//span//span"),
				implicitlyWait)) {
			this.click(By.xpath("//div[contains(text(),'" + featureName + "')]/preceding-sibling::div//span//span"));
		}
	}

	public Boolean submitAssignFeature() throws Exception {
		this.click(By.xpath("//span[text()='Assign']"));
		return this.WaitElement(By.xpath("//span[contains(text(),'Saved successfully.')]"), implicitlyWait);
	}

	public void deleteRole() throws Exception {
		if (this.WaitElement(By.xpath("//span[text()='Delete']"), implicitlyWait)) {
			this.click(By.xpath("//span[text()='Delete']"));
		}
	}

	public Boolean deleteRoleConfirm() throws Exception {
		this.click(By.xpath("//span[contains(text(),'Confirm')]"));
		return this.WaitElement(By.xpath("//span[contains(text(),'Deleted successfully.')]"), implicitlyWait);
	}

	public Boolean assignEngines() throws Exception {
		this.closePopUpWindow();
		this.save();
		return this.WaitElement(By.cssSelector("div[title='Saved successfully.']"), implicitlyWait);
	}

	public void deleteUserGroup() throws Exception {
		this.untilPageLoadComplete(implicitlyWait);
		this.closePopUpWindow();
		if (this.WaitElement(By.cssSelector("span[title='Delete User Group']"), implicitlyWait)) {
			this.click(By.cssSelector("span[title='Delete User Group']"));
		}
	}

	public void inputRoleInfo(String roleName, String roleDesc) throws Exception {
		this.sendString(By.cssSelector("input[placeholder='Name']"), roleName);
		this.sendString(By.cssSelector("input[placeholder='Description']"), roleDesc);
	}

	public void updateRoleInfo(String roleName, String roleDesc) throws Exception {

		if (!"N".equalsIgnoreCase(roleName)) {
//			this.sendString(By.cssSelector("input[placeholder='Name']"), roleName);
			this.sendString(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div[2]/div[1]/div/div/input"), roleName);
		}
		if (!"N".equalsIgnoreCase(roleDesc)) {
//			this.sendString(By.cssSelector("input[placeholder='Description']"), roleDesc);
			this.sendString(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div[2]/div[2]/div/div/input"), roleDesc);
		}
	}

	public Boolean saveRole(String expectedResult) throws Exception {
		this.closePopUpWindow();
		this.save();
		return (Boolean.parseBoolean(expectedResult) == this
				.WaitElement(By.cssSelector("div[title='Saved successfully.']"), implicitlyWait));
	}

	public Boolean saveUpdateRoleInfo(String expectedResult) throws Exception {
		this.closePopUpWindow();
		this.click(By.xpath("//span[contains(text(),'Update')]/parent::button[@style='display: block;']"));
		return (Boolean.parseBoolean(expectedResult) == this
				.WaitElement(By.cssSelector("div[title='Updated successfully.']"), implicitlyWait));
	}

}
