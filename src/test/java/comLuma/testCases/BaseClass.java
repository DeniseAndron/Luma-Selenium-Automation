package comLuma.testCases;

import comLuma.utilities.ReadConfig;
import org.apache.commons.io.FileUtils;

import org.apache.log4j.PropertyConfigurator;


import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.logging.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    static ReadConfig readconfig = new ReadConfig();

    public static String baseURL = readconfig.getApplicationURL();

    public static WebDriver driver;

    public static Logger logger;


    @BeforeClass
    @Parameters("browser")
    public static void setup(String br) {

        //Reporting tool
        logger = Logger.getLogger("LumaAutomatedSelenium");
        PropertyConfigurator.configure("log4j.properties");

        if (br.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
            // Instantiation of the driver
            driver = new ChromeDriver();
        } else if (br.equals("firefox")) {

            //Code for firefox browser, similar with above but with firefox properties

        }
        driver.get(baseURL);

    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    public void captureScreen(WebDriver driver, String tname) throws IOException {

        //Code for capturing screenshots
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot taken");

    }

    protected void waitForElementVisible(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds)); // Convert int to Duration
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));


    }
}
