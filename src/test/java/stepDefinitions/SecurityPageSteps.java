package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.TestContent;
import cucumber.api.java.en.Then;
import enums.Context;
import pageObjects.SecurityPage;

public class SecurityPageSteps {

	private static TestContent testContent;
	private static SecurityPage securityPage;

	public SecurityPageSteps(TestContent content) {
		testContent = content;
		securityPage = testContent
				.getPageObjectManager((WebDriver) testContent.scenarioContext.getContext(Context.Driver))
				.getSecurityPage();
	}

	@Then("^navigate to security$")
	public void security() throws Exception {
		securityPage.security();
	}

	// User Management
	@Then("^navigate to user management$")
	public void userManagement() throws Exception {
		securityPage.userManagement();
	}

	// User Management Add
	@Then("^click adduser button$")
	public void addUser() throws Exception {
		securityPage.addUser();
	}

	@Then("^load data from excel and createUser$")
	public void createUser() throws Exception {
		Boolean flag = securityPage.createUser();
		Assert.assertTrue(flag);
	}

	// User Management Search
	@Then("^input search option \"([^\"]*)\" and \"([^\"]*)\"$")
	public void searchUserFilter(String filter, String optionsValue) throws Exception {
		testContent.scenarioContext.setContext(Context.Filter, filter);
		testContent.scenarioContext.setContext(Context.FilterValue, optionsValue);
		securityPage.searchUserFilter(filter, optionsValue);
	}

	@Then("^add filter and click search user$")
	public void filterSearch() throws Exception {
		securityPage.filterSearch();
	}
	
	@Then("^check user whether exist$")
	public void checkUserIfExist() throws Exception {
		String filter = (String) testContent.scenarioContext.getContext(Context.Filter);
		String filterValue = (String) testContent.scenarioContext.getContext(Context.FilterValue);
		Boolean exist = securityPage.checkUserIfExist(filter, filterValue);
		testContent.scenarioContext.setContext(Context.GlobalExist, exist);
	}
	
	@Then("^if not find specify user create user \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" else skip it$")
	public void createUser(String userId, String userName, String email, String phoneNumber) throws Exception {
		Boolean GlobalExist = (Boolean) testContent.scenarioContext.getContext(Context.GlobalExist);
		if(!GlobalExist) {
			securityPage.createUser(userId, userName, email, phoneNumber);
		}
	}
	
	@Then("^if need to logout$")
	public void ifNeedToLogout() throws Exception {
		Boolean GlobalExist = (Boolean) testContent.scenarioContext.getContext(Context.GlobalExist);
		if(!GlobalExist) {
			securityPage.ifNeedToLogout();
		}
	}

	
	@Then("^if need to relogin with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void ifNeedToRelogin(String username, String password) throws Exception {
		Boolean GlobalExist = (Boolean) testContent.scenarioContext.getContext(Context.GlobalExist);
		if(!GlobalExist) {
			securityPage.ifNeedToRelogin(username, password);
		}
	}
	
	@Then("^if need to change the password \"([^\"]*)\" and \"([^\"]*)\"$")
	public void ifNeedToChangePassword(String defaultPassword, String newPassword) throws Exception {
		Boolean GlobalExist = (Boolean) testContent.scenarioContext.getContext(Context.GlobalExist);
		if(!GlobalExist) {
			securityPage.changePassword(defaultPassword, newPassword);
		}
	}

	@Then("^search results should like \"([^\"]*)\"$")
	public void checkSearchResult(String expectedResult) throws Exception {
		String filter = (String) testContent.scenarioContext.getContext(Context.Filter);
		String filterValue = (String) testContent.scenarioContext.getContext(Context.FilterValue);
		Boolean flag = securityPage.checkSearchResult(filter, filterValue, expectedResult);
		Assert.assertTrue(flag);
	}
	
	// User Management Update
	@Then("^click update userInfo button$")
	public void updateUser() throws Exception {
		String filterValue = (String)testContent.scenarioContext.getContext(Context.FilterValue);
		securityPage.updateUser(filterValue);
	}

	@Then("^enter update user info \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
	public void updateUsrInfo(String userName, String email, String phoneNumber) throws Exception {
		securityPage.updateUsrInfo(userName, email, phoneNumber);
	}

	@Then("^update user result should like \"([^\"]*)\"$")
	public void chkUpdateUsrResult(String expectedResult) throws Exception {
		Boolean flag = securityPage.chkUpdateUsrResult(expectedResult);
		Assert.assertTrue(flag);
	}

	// User Management Assign Role
	@Then("^click assign role button$")
	public void assignRole() throws Exception {
		String filterValue = (String) testContent.scenarioContext.getContext(Context.FilterValue);
		securityPage.assignRole(filterValue);
	}

	@Then("^assign role with \"([^\"]*)\"$")
	public void assignRole(String roleName) throws Exception {
		securityPage.assignRoleAccess(roleName);
	}

	@Then("^click save assign role button$")
	public void saveAssignRole() throws Exception {
		Boolean flag = securityPage.saveAssignRole();
		Assert.assertTrue(flag);
	}

	// User Management Delete User
	@Then("^click delete user button$")
	public void deleteUser() throws Exception {
		String filterValue = (String) testContent.scenarioContext.getContext(Context.FilterValue);
		securityPage.deleteUser(filterValue);
	}
	
	@Then("^click batch delete user button$")
	public void batchDeleteUser() throws Exception {
		securityPage.batchDeleteUser();
	}

	@Then("^click confirm delete user button$")
	public void clickConfDelUsrBtn() throws Exception {
		Boolean flag = securityPage.clickConfDelBtn();
		Assert.assertTrue(flag);
	}
	
	@Then("^clear search conditions$")
	public void clearConditions() throws Exception {
		securityPage.clearConditions();
	}
	
	
	// User Group
	@Then("^navigate to user group$")
	public void userGroup() throws Exception {
		securityPage.userGroup();
	}

	// User Group Add
	@Then("^click add Group button$")
	public void addUserGroup() throws Exception {
		securityPage.addUserGroup();
	}

	@Then("^select parent user group \"([^\"]*)\"$")
	public void chooseParentGroup(String groupName) throws Exception {
		securityPage.chooseParentGroup(groupName);
	}

	@Then("^input group info with \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
	public void createGroupInfo(String groupName, String groupDesc, String licenseNum) throws Exception {
		securityPage.createGroupInfo(groupName, groupDesc, licenseNum);
	}

	@Then("^click save add group button$")
	public void saveGroup() throws Exception {
		securityPage.saveGroup();
	}

	@Then("^save group result like \"([^\"]*)\"$")
	public void validateUpdateResult(String expectedResult) throws Exception {
		Boolean flag = securityPage.validateUpdateResult(expectedResult);
		Assert.assertTrue(flag);
	}

	// User Group Search
	@Then("^input search user group \"([^\"]*)\"$")
	public void userGroupFilter(String filter) throws Exception {
		securityPage.userGroupFilter(filter);
	}

	@Then("^search group result like \"([^\"]*)\"$")
	public void submitSearchUserGroup(String expectedResult) throws Exception {
		Boolean flag = securityPage.submitSearchUserGroup(expectedResult);
		Assert.assertTrue(flag);
	}

	// User Group Update
	@Then("^input group info with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void updateGroupInfo(String groupName, String groupDesc) throws Exception {
		securityPage.updateGroupInfo(groupName, groupDesc);
	}

	// User Group Delete
	@Then("^click delete user group button$")
	public void deleteUserGroup() throws Exception {
		securityPage.deleteUserGroup();
	}

	@Then("^click confirm delete user group button$")
	public void confDelUsrGroupBtn() throws Exception {
		Boolean flag = securityPage.clickConfDelBtn();
		Assert.assertTrue(flag);
	}

	// User Group Assign User
	@Then("^search assign user \"([^\"]*)\"$")
	public void searchAssignUser(String userId) throws Exception {
		testContent.scenarioContext.setContext(Context.FilterValue, userId);
		securityPage.searchAssignUser(userId);
	}
	
	@Then("^select the user$")
	public void selectUser() throws Exception {
		String userId = (String) testContent.scenarioContext.getContext(Context.FilterValue);
		securityPage.selectUser(userId);
	}
	
	@Then("^assign user$")
	public void assignUserToGroup() throws Exception {
		Boolean flag = securityPage.assignUserToGroup();
		Assert.assertTrue(flag);
	}

	// User Group Assign Channels
	@Then("^click assign channels$")
	public void clickAssignChannels() throws Exception {
		securityPage.clickAssignChannels();
	}
	
	@Then("^search assign channel \"([^\"]*)\"$")
	public void searchAssignChannel(String channelName) throws Exception {
		testContent.scenarioContext.setContext(Context.FilterValue, channelName);
		securityPage.searchAssignChannel(channelName);
	}
	
	@Then("^select the channel$")
	public void selectChannel() throws Exception {
		String channelName = (String) testContent.scenarioContext.getContext(Context.FilterValue);
		securityPage.selectChannel(channelName);
	}

	@Then("^assign channels$")
	public void assignChannels() throws Exception {
		Boolean flag = securityPage.assignChannels();
		Assert.assertTrue(flag);
	}

	// User Group Assign VA Engine
	@Then("^click assign va engines$")
	public void clickAssignVAEngines() throws Exception {
		securityPage.clickAssignVAEngines();
	}
	
	@Then("^search assign engine \"([^\"]*)\"$")
	public void searchAssignEngine(String engineName) throws Exception {
		testContent.scenarioContext.setContext(Context.FilterValue, engineName);
		securityPage.searchAssignEngine(engineName);
	}
	
	@Then("^select the engine$")
	public void selectEngine() throws Exception {
		String engineName = (String) testContent.scenarioContext.getContext(Context.FilterValue);
		securityPage.selectEngine(engineName);
	}

	@Then("^assign va engines$")
	public void assignEngines() throws Exception {
		Boolean flag = securityPage.assignEngines();
		Assert.assertTrue(flag);
	}

	// Role Management
	@Then("^navigate to role management$")
	public void click_roleManagement() throws Exception {
		securityPage.navigateRoleManagement();
	}

	// Role Management add
	@Then("^click add role button$")
	public void click_addRole() throws Exception {
		securityPage.clickAddRole();
	}

	@Then("^input role info \"([^\"]*)\" and \"([^\"]*)\"$")
	public void inputRoleInfo(String roleName, String roleDesc) throws Exception {
		securityPage.inputRoleInfo(roleName, roleDesc);
	}
	
	@Then("^click save role button with \"([^\"]*)\"$")
	public void saveRole(String expectedResult) throws Exception {
		Boolean flag = securityPage.saveRole(expectedResult);
		Assert.assertTrue(flag);
	}
	
	// Role Management search
	@Then("^input search role option \"([^\"]*)\" and \"([^\"]*)\"$")
	public void inputRoleFilter(String filter, String optionsValue) throws Exception {
		testContent.scenarioContext.setContext(Context.Filter, filter);
		testContent.scenarioContext.setContext(Context.FilterValue, optionsValue);
		securityPage.inputRoleFilter(filter, optionsValue);
	}
	
	@Then("^search role results should like \"([^\"]*)\"$")
	public void searchRoleResult(String expectedResult) throws Exception {
		String filter = (String) testContent.scenarioContext.getContext(Context.Filter);
		String filterValue = (String) testContent.scenarioContext.getContext(Context.FilterValue);
		Boolean flag = securityPage.searchRoleResult(filter, filterValue, expectedResult);
		Assert.assertTrue(flag);
	}
	
	// Role Management update
	@Then("^click update role info button$")
	public void updateRoleInfoBtn() throws Exception {
		String filterValue = (String) testContent.scenarioContext.getContext(Context.FilterValue);
		securityPage.updateUser(filterValue);
	}
	
	@Then("^enter update role info \"([^\"]*)\" and \"([^\"]*)\"$")
	public void updateRoleInfo(String roleName, String roleDesc) throws Exception {
		securityPage.updateRoleInfo(roleName, roleDesc);
	}
	
	@Then("^save update role info with \"([^\"]*)\"$")
	public void saveUpdateRoleInfo(String expectedResult) throws Exception {
		Boolean flag = securityPage.saveUpdateRoleInfo(expectedResult);
		Assert.assertTrue(flag);
	}
	
	// Role Management Assign Feature
	@Then("^click feature button$")
	public void clickFeatureBtn() throws Exception {
		securityPage.clickFeatureBtn();
	}
	
	@Then("^select feature options \"([^\"]*)\"$")
	public void assignFeature(String featureName) throws Exception {
		securityPage.assignFeature(featureName);
	}
	
	@Then("^save assign features$")
	public void submitAssignFeature() throws Exception {
		Boolean flag = securityPage.submitAssignFeature();
		Assert.assertTrue(flag);
	}
	
	// Role Management Delete Role
	@Then("^click delete role button$")
	public void deleteRole() throws Exception {
		securityPage.deleteRole();
	}
	
	@Then("^click delete role confirm button$")
	public void deleteRoleConfirm() throws Exception {
		Boolean flag = securityPage.deleteRoleConfirm();
		Assert.assertTrue(flag);
	}
	
	

//	@Then("^create user with below data$")
//	public void createUser(DataTable dt) throws Exception {
//		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
//		// Loop the data table
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).get("userId"));
//			if (i >= 1) {
//				securityPage.eventClick(Constant.UsrManAddUser);
//			}
//			securityPage.fillValue(Constant.UsrManUserId, list.get(i).get("userId"));
//			securityPage.fillValue(Constant.UsrManUserName, list.get(i).get("userName"));
//			securityPage.fillValue(Constant.UsrManUserEmail, list.get(i).get("email"));
//			securityPage.fillValue(Constant.UsrManUserPhone, list.get(i).get("phone"));
//			securityPage.eventClick(Constant.UsrManSave);
//			Thread.sleep(3000);
////			this.validateAddedUser(list.get(i).get("userId"));
//			securityPage.closePopUpWindow();
//		}
//	}

}
