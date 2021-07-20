package utilities.mobileUtils;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.WebDriver;

public class MobileUtility {

    public static void mobileScrollToElementByDisplayText(WebDriver driver, String displayText) {
        try {
            driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" + "new UiSelector().text(\"" + displayText + "\"))"));
        } catch (Exception e) {
            System.out.println("error while scrolling to the element.\n" + e.getMessage());
        }
    }

    public static void mobileScrollToElementByContainsText(WebDriver driver, String displayText) {
        try {
            String uiSelector = "new UiSelector().textContains(\"" + displayText + "\")";
            String command = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" + uiSelector + ");";
            driver.findElement(MobileBy.AndroidUIAutomator(command));

        } catch (Exception e) {
            System.out.println("error while scrolling to the element.\n" + e.getMessage());
        }
    }
}
