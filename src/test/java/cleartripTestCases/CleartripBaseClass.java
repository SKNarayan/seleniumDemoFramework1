package cleartripTestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class CleartripBaseClass {

    public String baseURL = "https://www.cleartrip.com/flights";
    public static WebDriver driver;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void tearDown(){
       // driver.quit();
    }



}
