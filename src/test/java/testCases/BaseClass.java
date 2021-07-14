package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

    public String baseURL= "http://demo.guru99.com/v4/";
    public String userId = "mngr336393";
    public String password = "YpUhEvU";
    public static WebDriver driver;

    @BeforeClass
    public void setUp(){
       System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void tearDown(){

        //driver.quit();
    }

}
