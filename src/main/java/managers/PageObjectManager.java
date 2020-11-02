package managers;

import org.openqa.selenium.WebDriver;

import pageObjects.HomePage;
import pageObjects.SecurityPage;
import pageObjects.AlarmPage;
import pageObjects.Uvms;
import pageObjects.Uvap;
import pageObjects.Vade;

public class PageObjectManager {

    private WebDriver driver;

    private HomePage homePage;
    
    private SecurityPage securityPage;
    
    private AlarmPage alarmPage;

    private Uvms uvms;
    
    private Uvap uvap;
    
    private Vade vade;
    
    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage(){
        return (homePage == null) ? homePage = new HomePage(this.driver) : homePage;
    }
    
    public SecurityPage getSecurityPage(){
        return (securityPage == null) ? securityPage = new SecurityPage(this.driver) : securityPage;
    }
    
    public AlarmPage getAlarmPage(){
        return (alarmPage == null) ? alarmPage = new AlarmPage(this.driver) : alarmPage;
    }
    
    public Uvms getUvms(){
        return (uvms == null) ? uvms = new Uvms(this.driver) : uvms;
    }
    
    public Uvap getUvap(){
        return (uvap == null) ? uvap = new Uvap(this.driver) : uvap;
    }
    
    public Vade getVade(){
        return (vade == null) ? vade = new Vade(this.driver) : vade;
    }
}