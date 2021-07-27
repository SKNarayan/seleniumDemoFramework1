package utilities.webUtils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShadowDOM {

    public static WebElement getShadowDOM(WebElement element, WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement shadowDom1 = (WebElement) js.executeScript("return arguments[0].shadowRoot", element);
        return shadowDom1;
    }

}
