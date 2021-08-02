package cleartripTestCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utilities.ReadConfig;

public class CleartripBaseClass {

   // public String baseURL = "https://www.cleartrip.com/flights";
    public static WebDriver driver;
    public static Logger logger;

    @Parameters("browser")
    @BeforeClass
    public void setUp(String nameOfBrowser) {
        //   System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");

        logger = Logger.getLogger(CleartripBaseClass.class);
        PropertyConfigurator.configure("Log4j.properties");

        ReadConfig readConfig = new ReadConfig();

       if(nameOfBrowser.equals("chrome")) {

           try {
               ChromeOptions chromeOptions = new ChromeOptions();
               chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
               WebDriverManager.chromedriver().setup();
               driver = new ChromeDriver(chromeOptions);

           } catch (Exception e) {
               throw new RuntimeException("Error while setting up chrome browser");
           }

       }else if(nameOfBrowser.equals("firefox")) {
           try {
               FirefoxOptions firefoxOptions = new FirefoxOptions();
               firefoxOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
               WebDriverManager.firefoxdriver().browserVersion("89.0").setup();
               driver = new FirefoxDriver(firefoxOptions);
           } catch (Exception e) {
               throw new RuntimeException("Error while setting up firefox browser");
           }
       }

        driver.get(readConfig.getApplicationURL());

    }
        @AfterClass
        public void tearDown () {
             driver.quit();
        }

    }
