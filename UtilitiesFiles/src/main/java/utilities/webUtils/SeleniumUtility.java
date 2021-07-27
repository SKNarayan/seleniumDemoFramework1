package utilities.webUtils;

import org.openqa.selenium.WebElement;

import java.util.List;

public class SeleniumUtility {

    public static void clickElementUsingName(List<WebElement> listElement, String elementTxt) {
        try {
            for (WebElement ele : listElement) {
                if (ele.getText().trim().equalsIgnoreCase(elementTxt.trim())) {
                    System.out.println(ele.getText());
                    ele.click();
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("error while clicking on the element.\n" + e.getMessage(), e);
        }
    }

    public static WebElement getElementUsingName(List<WebElement> listElement, String elementTxt) {
        WebElement element = null;
        try {
            for (WebElement ele : listElement) {
                if (ele.getText().trim().equalsIgnoreCase(elementTxt.trim())) {
                    element = ele;
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("error while getting the element.\n" + e.getMessage(), e);
        }
        return element;
    }

    public static WebElement getElementUsingContainsText(List<WebElement> listElement, String elementTxt) {
        WebElement element = null;
        try {
            for (WebElement ele : listElement) {
                if (ele.getText().trim().contains(elementTxt)) {
                    element = ele;
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("error while getting the element.\n" + e.getMessage(), e);
        }
        return element;
    }

    public static void clickElementUsingContainsText(List<WebElement> listElement, String elementTxt) {
        try {
            for (WebElement ele : listElement) {
                if (ele.getText().trim().contains(elementTxt.trim())) {
                    ele.click();
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("error while clicking on the element.\n" + e.getMessage(), e);
        }
    }

    public static WebElement getDisplayElement(List<WebElement> listElement) {
        try {
            for (WebElement ele : listElement) {
                if (ele.isDisplayed()) {
                    return ele;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("error while getting the element.\n" + e.getMessage(), e);
        }
        return null;
    }
}
