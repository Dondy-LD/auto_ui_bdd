package common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class Unility {
    public Unility() {

    }

    public static void screenshot(WebDriver driver)
    {
        try {
            System.out.println("ScreenShot starting");
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./src/test/resources/ScreenShots/"+ System.currentTimeMillis() +".png"));
            System.out.println("ScreenShot done");
        }
        catch (Exception e)
        {
            System.out.println("Exception while taking ScreenShot "+e.getMessage());
        }

    }
}
