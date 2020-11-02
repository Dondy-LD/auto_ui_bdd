//package runners;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.testng.annotations.AfterClass;
//
//import com.cucumber.listener.Reporter;
//
//import cucumber.api.CucumberOptions;
//import cucumber.api.testng.AbstractTestNGCucumberTests;
//import managers.FileReaderManager;
//import net.masterthought.cucumber.Configuration;
//import net.masterthought.cucumber.ReportBuilder;
//import net.masterthought.cucumber.Reportable;
//
//@CucumberOptions(
//        features = "src/test/resources/features/HomePage.feature",
//        format = {
//                "json:target/cucumber-report.json",
//                "html:target/site/cucumber-pretty",
//                "pretty",
////                "rerun:target/site/return.text"
//        },
//        glue = {"stepDefinitions"}
//)
//@CucumberOptions(
//        features = "src/test/resources/features/BaiduSearch.feature",
//        format = {
//                "json:target/cucumber-report.json",
//                "html:target/site/cucumber-pretty",
//                "pretty",
////                "rerun:target/site/return.text"
//        },
//        tags = {"@BaiduSearch"},
//        glue = {"cucumber.steps"}
//
//
////@RunWith(Cucumber.class)
////@CucumberOptions(features = "src/test/resources/features/BaiduSearchBackground.feature", glue = { "stepDefinitions" }, plugin = {
////				"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html" })
//
//public class Runner extends AbstractTestNGCucumberTests {
//	@AfterClass
//	public static void tearDown() {
//		File reportOutputDirectory = new File("target");
//		List<String> jsonFiles = new ArrayList<>();
//		jsonFiles.add("./target/cucumber-report.json");
////                File file = new File("./target/cucumber.json");
////                jsonFiles.add(file.getAbsolutePath());
//
//		String buildNumber = "1";
//		String projectName = "cucumberProject";
////                boolean runWithJenkins = false;
//
//		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
//		// optional configuration - check javadoc
////                configuration.setRunWithJenkins(runWithJenkins);
//		configuration.setBuildNumber(buildNumber);
//		// addidtional metadata presented on main page
//		configuration.addClassifications("Platform", "Windows");
//		configuration.addClassifications("Browser", "Firefox");
//		configuration.addClassifications("Branch", "release/1.0");
//
//		// optionally add metadata presented on main page via properties file
//		// List<String> classificationFiles = new ArrayList<>();
//		// classificationFiles.add("properties-1.properties");
//		// classificationFiles.add("properties-2.properties");
//		// configuration.addClassificationFiles(classificationFiles);
//
//		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
//		Reportable result = reportBuilder.generateReports();
//		System.out.println(result);
//		// and here validate 'result' to decide what to do if report has failed
//	}
//
//	@AfterClass
//	public static void writeExtentReport() {
//		Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));
//	}
//}