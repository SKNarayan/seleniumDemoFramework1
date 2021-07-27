package utilities.webUtils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptExecutorUtility {

    public static void clickOnElement(WebDriver driver, WebElement ele) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", ele);
        } catch (Exception e) {
            throw new RuntimeException("Error while performing the click operation.");
        }
    }

    public static String getElementText(WebDriver driver, WebElement ele) {
        String elementText;
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            elementText = (String) executor.executeScript("return arguments[0].value;", ele);
        } catch (Exception e) {
            throw new RuntimeException("Error while performing the click operation.");
        }
        return elementText;
    }
}
