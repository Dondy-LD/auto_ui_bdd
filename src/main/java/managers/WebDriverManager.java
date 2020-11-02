package managers;


//import java.util.concurrent.TimeUnit;
//import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.logging.LogType;
//import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import enums.DriverType;



public class WebDriverManager {
    private WebDriver driver;
    private static DriverType driverType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String CHROME_Binary_PROPERTY = "webdriver.chrome.bin";

    public WebDriverManager() {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
    }

    public WebDriver getDriver() {
        if(driver == null) driver = createDriver();
        return driver;
    }

    public WebDriver createDriver() { //using public instead of private
        switch (driverType) {
            case FIREFOX : 
//            	System.setProperty(CHROME_Binary_PROPERTY, "C:\\Program Files\\Mozilla Firefox");
//            	System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/firefox/geckodriver.exe");
//            	FirefoxProfile profile=new FirefoxProfile();
//            	profile.setAcceptUntrustedCertificates(true);
            	driver = new FirefoxDriver();
                break;
            case CHROME :
//            	ChromeOptions options = new ChromeOptions();
//            	options.setExperimentalOption("w3c", false);
//            	options.addArguments( "--disable-infobars", "--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
            	
                System.setProperty(CHROME_Binary_PROPERTY, FileReaderManager.getInstance().getConfigReader().getBinaryPath());
                System.setProperty(CHROME_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getDriverPath());
                DesiredCapabilities handlSSLErr = DesiredCapabilities.chrome();      
//                handlSSLErr.setCapability(ChromeOptions.CAPABILITY, options);
//                
//                LoggingPreferences logPrefs = new LoggingPreferences();
//                logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
//                handlSSLErr.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
                // Accept SSL Warning
                // handlSSLErr.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
                // Accept Insecure SSL Certificate
                handlSSLErr.setCapability (CapabilityType.ACCEPT_INSECURE_CERTS, true);
                handlSSLErr.setJavascriptEnabled(true);
                driver = new ChromeDriver(handlSSLErr);
                break;
            case INTERNETEXPLORER : driver = new InternetExplorerDriver();
                break;
            default:
                throw new java.lang.IllegalStateException("Unexpected value: " + driverType);
        }
        
//        if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize())
        	//maximize the window
            driver.manage().window().maximize();
            //clear the cookies
//            driver.manage().deleteAllCookies();
//            driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }


    public void closeDriver() {
        driver.close();
        driver.quit();
    }

}