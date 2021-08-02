package testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class BaseClass {

    public String baseURL= "http://demo.guru99.com/v4/";
    public String userId = "mngr336393";
    public String password = "YpUhEvU";
    public static WebDriver driver;
    public static Logger logger;

    @BeforeClass
    public void setUp(){
      /* System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver");
        driver = new ChromeDriver();
*/
        try {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
            WebDriverManager.chromedriver().browserVersion("92.0").setup();
            driver = new ChromeDriver(chromeOptions);
        }catch (Exception e){
            throw new RuntimeException("Error while setting up the broser driver");
        }

        logger = Logger.getLogger(BaseClass.class);
        PropertyConfigurator.configure("Log4j.properties");
    }

    @AfterClass
    public void tearDown(){

        //driver.quit();
    }

}
