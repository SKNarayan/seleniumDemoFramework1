package utilities.webUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsUtility {

    public static void hoverToElement(WebDriver driver, WebElement ele) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(ele).perform();
            Thread.sleep(1000);

        } catch (Exception e) {
            throw new RuntimeException("Error while performing the hover operation.", e);
        }
    }
}
