package stepDefinitions;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import common.WebsocketClient;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UvmsApiPageSteps {
	private static final String USERNAME = "chenya";
	private static final String PASSWORD = "116c8fe4b1b451241f2de3fcf68a8adabb6b2c81d10b9eeb8d2aa336e54c6703";
	private static final String BASE_URL = "https://web.qa.ivh.local";
	private static final String codeCategory = "IVH_VERSION_CODE";
	private static final String codeValue = "VERSION";

	public String accessToken;
	public Response response;
	public String channelId;
	public String deviceId;
	public String deviceName;
	public String sessionId;
	public String channelName;
	public String playBackSessionId;

	@Given("I am an authorized user")
	public void iAmAnAuthorizedUser() throws InterruptedException {

		RestAssured.baseURI = BASE_URL;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given().log().all();
		RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames());
		request.header("Content-Type", "application/json");
		request.header("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
		response = request
				.body("{ \"username\":\"" + USERNAME + "\", \"password\":\"" + PASSWORD + "\" , \"codeCategory\":\""
						+ codeCategory + "\" , \"codeValue\":\"" + codeValue + "\"}")
				.post("/api/ummi-admin/admin/login");

		JSONObject jsonObj = new JSONObject(response.asString());
		accessToken = jsonObj.getJSONObject("data").getString("access_token");
		System.out.println("---------------------------------------------------");

	}

	@Then("search channel with \"([^\"]*)\" and \"([^\"]*)\"")
	public void searchChannel(String ChannelName, String sharedListType) throws InterruptedException {
		channelName = ChannelName;
		RestAssured.baseURI = BASE_URL;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
		request.header("Authorization", accessToken);
		request.header("requestUserId", "chenya");
		response = request
				.body("{ \"userId\":\"chenya\", \"channelName\":\"" + channelName
						+ "\" , \"sharedListType\":\"" + sharedListType + "\"}")
				.post("/api/ummi-device/vms/channel/searchTop10");
		if (response.getStatusCode() == 200) {
			JSONObject searchResults = new JSONObject(response.asString());
			JSONArray channel = searchResults.getJSONObject("data").getJSONArray("items");
			if (channel.length() > 0) {
				channelId = (String) channel.getJSONObject(0).get("channelId");
				deviceId = (String) channel.getJSONObject(0).get("deviceId");
				deviceName = (String) channel.getJSONObject(0).get("deviceName");
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} else {
			Assert.assertTrue(false);
		}
		System.out.println("---------------------------------------------------");
	}
	
	
	@Then("search recorded video of the channel with \"([^\"]*)\" and \"([^\"]*)\"")
	public void searchVideo(String fromTime, String toTime) throws InterruptedException {
		RestAssured.baseURI = BASE_URL;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
		request.header("Authorization", accessToken);
		request.header("requestUserId", "chenya");
		response = request
				.body("{ \"userId\":\"" + USERNAME + "\", \"channelName\":\"" + channelName + "\" , \"channelId\":\""
						+ channelId + "\" , \"deviceId\":\"" + deviceId + "\" , \"deviceName\":\"" + deviceName
						+ "\" , \"from\":\""+ fromTime +"\" , \"to\":\""+ toTime +"\" , \"streamId\":\"0\" , \"type\":\"rtsp/h264\"}")
				.post("/api/ummi-device/vms/sms/streams/playback");
		if (response.getStatusCode() == 200) {
			JSONObject searchResults = new JSONObject(response.asString());
			playBackSessionId = searchResults.getJSONObject("data").getString("sessionId");
		} else {
			Assert.assertTrue(false);
		}
		System.out.println("---------------------------------------------------");
	}
	

	@Then("select channel to live view")
	public void getSessionId() throws InterruptedException {
		RestAssured.baseURI = BASE_URL;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
		request.header("Authorization", accessToken);
		request.header("requestUserId", "chenya");
		response = request
				.body("{ \"userId\":\"" + USERNAME + "\", \"channelName\":\"" + channelName + "\" , \"channelId\":\""
						+ channelId + "\" , \"deviceId\":\"" + deviceId + "\" , \"deviceName\":\"" + deviceName
						+ "\" , \"ptzInd\":\"Y\" , \"streamId\":\"0\" , \"type\":\"rtsp/h264\"}")
				.post("/api/ummi-device/vms/sms/streams/live");
		if (response.getStatusCode() == 200) {
			JSONObject searchResults = new JSONObject(response.asString());
			sessionId = searchResults.getJSONObject("data").getString("sessionId");
		} else {
			Assert.assertTrue(false);
		}
		System.out.println("---------------------------------------------------");
	}

	@Then("playback should be ok")
	public void playback() throws URISyntaxException, InterruptedException {
		// wss://app.qa.ivh.local:30011/bb8fbc95-b3ff-4c43-afd4-2c403a6b30a1
		final WebsocketClient clientEndPoint = new WebsocketClient(
				new URI("wss://app.qa.ivh.local:30011/" + playBackSessionId));
		// add listener
		clientEndPoint.addMessageHandler(new WebsocketClient.MessageHandler() {
			public void handleMessage(String message) {
				if (message != null) {
					System.out.println(message);
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}

			}
		});

		// send message to websocket
		clientEndPoint.sendMessage(
				"OPTIONS * RTSP/1.0\r\nCSeq: 1\r\nUser-Agent: Performance Test Python RTSP client\r\n\r\n");

		// wait 5 seconds for messages from websocket
		Thread.sleep(5000);
	}
	
	@Then("live view should be ok")
	public void liveView() throws URISyntaxException, InterruptedException {
		// wss://app.qa.ivh.local:30011/bb8fbc95-b3ff-4c43-afd4-2c403a6b30a1
		final WebsocketClient clientEndPoint = new WebsocketClient(
				new URI("wss://app.qa.ivh.local:30011/" + sessionId));
		// add listener
		clientEndPoint.addMessageHandler(new WebsocketClient.MessageHandler() {
			public void handleMessage(String message) {
				if (message != null) {
					System.out.println(message);
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}

			}
		});

		// send message to websocket
		clientEndPoint.sendMessage(
				"OPTIONS * RTSP/1.0\r\nCSeq: 1\r\nUser-Agent: Performance Test Python RTSP client\r\n\r\n");

		// wait 5 seconds for messages from websocket
		Thread.sleep(5000);
	}

	@Then("close the live view")
	public void closeLiveView() {
//		https://support.i.kaisquare.com.cn:42501/api/ummi-device/vms/sms/streams/66e5b8d0-0954-4709-bafc-40ab8d900dcd/HIK%2011-6352
		RestAssured.baseURI = BASE_URL;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
		request.header("Authorization", accessToken);
		request.header("requestUserId", "chenya");
		response = request.delete("/api/ummi-device/vms/sms/streams/" + sessionId + "/" + channelName);
		if (response.getStatusCode() == 200) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		System.out.println("---------------------------------------------------");
	}
}