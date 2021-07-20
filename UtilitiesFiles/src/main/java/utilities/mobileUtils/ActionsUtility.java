package utilities.mobileUtils;


import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.webUtils.SeleniumUtility;

import java.util.List;

import static io.appium.java_client.touch.offset.PointOption.point;

public class ActionsUtility {

    public static void swipeUp(WebDriver driver) {
        try {
            Dimension size = driver.manage().window().getSize();
            int anchor = size.width / 2;

            // Swipe up to scroll down
            int startPoint = size.height - 10;
            int endPoint = 10;

            new TouchAction((PerformsTouchActions) driver)
                    .longPress(point(anchor, startPoint))
                    .moveTo(point(anchor, endPoint))
                    .release()
                    .perform();
        } catch (Exception e) {
            throw new RuntimeException("error while scrolling up." + e.getMessage(), e);
        }
    }

    public static WebElement mobileScrollToElement(AndroidDriver driver, List<WebElement> listElements, String elementText, int numberOfTimes) {
        WebElement element = null;
        Dimension size = driver.manage().window().getSize();
        int anchor = size.width / 2;

        // Swipe up to scroll down
        int startPoint = size.height - 10;
        int endPoint = 10;

        for (int i = 0; i < numberOfTimes; i++) {
            try {
                element = SeleniumUtility.getElementUsingContainsText(listElements, elementText);
                if (element == null) {
                    throw new Exception();
                }
                break;
            } catch (Exception ex) {
                new TouchAction(driver)
                        .longPress(point(anchor, startPoint))
                        .moveTo(point(anchor, endPoint))
                        .release()
                        .perform();
            }
        }
        return element;
    }

    public static void scrollToElement(WebDriver driver, WebElement element, int numberOfTimes) {
        int i = 0;
        try {
            while (i < numberOfTimes) {
                try {
                    Thread.sleep(1500);

                    Dimension size = driver.manage().window().getSize();
                    int anchor = size.width / 2;
                    int y = element.getLocation().getY();

                    Thread.sleep(1500);

                    new TouchAction((PerformsTouchActions) driver)
                            .longPress(point(anchor, size.height - 10))
                            .moveTo(point(anchor, y))
                            .release()
                            .perform();
                    break;
                } catch (Exception e) {
                    swipeUp(driver);
                    i++;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while performing scroll.");
        }
    }

    public static void scrollToMobileElement(WebDriver driver, WebElement element, int numberOfTimes) {
        int i = 0;
        boolean flag = true;
        try {
            while (i < numberOfTimes) {
                Thread.sleep(1500);

                Dimension size = driver.manage().window().getSize();
                int anchor = size.width / 2;
                int y = element.getLocation().getY();

                Thread.sleep(1500);
                try {
                    try {
                        new TouchAction((PerformsTouchActions) driver)
                                .longPress(point(anchor, size.height - 10))
                                .moveTo(point(anchor, y))
                                .release()
                                .perform();
                    } catch (Exception e) {
                        flag = false;
                    }

                    if (flag) {
                        new TouchAction((PerformsTouchActions) driver)
                                .longPress(point(anchor, y))
                                .moveTo(point(anchor, size.height - 10))
                                .release()
                                .perform();
                        break;

                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    flag = false;
                    swipeUp(driver);
                    i++;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while performing scroll.", e);
        }
    }
}
