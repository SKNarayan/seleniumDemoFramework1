package testCases;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.Properties;

public class BaseClass {

    public String baseURL= "http://demo.guru99.com/v4/";
    public String userId = "mngr336393";
    public String password = "YpUhEvU";
    public static WebDriver driver;
    public static Logger logger;

    @BeforeClass
    public void setUp(){
       System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver");
        driver = new ChromeDriver();

   /* logger = Logger.getLogger("ebanking");
        PropertyConfigurator.configure("Log4j.properties");
*/

    }

    @AfterClass
    public void tearDown(){

        //driver.quit();
    }

}
