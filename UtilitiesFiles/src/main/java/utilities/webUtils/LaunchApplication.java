package utilities.webUtils;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import utilities.fileUtils.FileUtility;
import utilities.mobileUtils.MobileOSUtility;

import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class LaunchApplication {

    public static String downloadFolderPath = null;
    public static boolean isNoReset = false;

    public static WebDriver launchApplication(Properties properties, String browserName, boolean isWebMode) {
        WebDriver driver = null;
        try {
            if (!isWebMode) {
                driver = launchMobileDevice(properties, browserName);
            } else {
                switch (browserName.toUpperCase()) {

                    case "CHROME":
                    case "":
                        if (System.getProperty("os.name").contains("Windows")) {
                            Process process = Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");

                            while (process.isAlive()) {
                                process.destroy();
                                Thread.sleep(1000);
                            }
                        }
                        //  setting the chrome properties
                        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\TestResources\\Driver\\chromedriver.exe");

                        //  create download folder
                        HashMap<String, Object> chromePrefs = new HashMap<>();
                        downloadFolderPath = FileUtility.createDownloadDirectory("Download");

                        //  clean folder
                        FileUtility.cleanDirectory(downloadFolderPath);
                        chromePrefs.put("profile.default_content_settings.popups", 0);
                        chromePrefs.put("download.default_directory", downloadFolderPath);

                        chromePrefs.put("plugins.always_open_pdf_externally", true);

                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.setExperimentalOption("prefs", chromePrefs);
                        driver = new ChromeDriver(chromeOptions);

                        driver.manage().window().maximize();
                        Thread.sleep(1000);
                        break;

                    case "FIREFOX":
                        break;

                    case "IE":
                        break;

                    default:
                        System.out.println("Please pass the correct environment name.");
                }
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return driver;
        } catch (Exception e) {
            throw new RuntimeException(
                    "LaunchApplication : launch_application || Error while launching the application.\n"
                            + e.getMessage(), e);
        }
    }

    public static WebDriver launchMobileDevice(Properties properties, String browserName) {
        WebDriver driver = null;
        try {
            if (browserName != null) {

            } else {
                switch (properties.getProperty("MobilePlatformName").toUpperCase()) {
                    case "ANDROID":

                        DesiredCapabilities capabilities = new DesiredCapabilities();
                        capabilities.setCapability("deviceName", MobileOSUtility.getDeviceName());
                        capabilities.setCapability("udid", MobileOSUtility.getUDId());
                        capabilities.setCapability("platformVersion", MobileOSUtility.getDeviceOsVersion());
                        capabilities.setCapability("platformName", "Android");
                        capabilities.setCapability("appPackage", properties.getProperty("AppPackage"));
                        capabilities.setCapability("appActivity", properties.getProperty("AppActivity"));

                        //  disable the application reset
                        capabilities.setCapability("noReset", isNoReset);
                        capabilities.setCapability("newCommandTimeout", "90000");
                        capabilities.setCapability("autoGrantPermissions", true);

                        //  launch the mobile application
                        driver = new AndroidDriver<MobileElement>(new URL(properties.getProperty("AppiumNodeURL")), capabilities);
                        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

                        break;

                    case "IOS":
                        break;

                    default:
                        System.out.println("Please pass the correct environment name.");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "LaunchApplication : launchMobileDevice || Error while launching the mobile application.\n"
                            + e.getMessage(), e);
        }
        return driver;
    }
}
