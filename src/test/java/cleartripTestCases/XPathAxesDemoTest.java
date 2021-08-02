package cleartripTestCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class XPathAxesDemoTest {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://money.rediff.com/gainers/bse/daily/groupa");

        driver.manage().window().maximize();

        //self - select the current node
        String nameOfCompany = driver.findElement(By.xpath("//a[contains(text(),'Sun Pharma.')]/self::a")).getText();
        System.out.println(nameOfCompany);

        //parent - select of the parent of seld node
        String parentText = driver.findElement(By.xpath("//a[contains(text(),'Sun Pharma.')]/parent::td")).getText();
        System.out.println(parentText);

        //child - select of the child of the current node
        List<WebElement> chileElements = driver.findElements(By.xpath("//a[contains(text(),'Sun Pharma.')]/ancestor::tr/child::td"));
        System.out.println(chileElements.size());
        for (WebElement childElement : chileElements){
            System.out.println(childElement.getText());
        }

        //ancestor - select parent and grandparent
        WebElement ancestor = driver.findElement(By.xpath("//a[contains(text(),'Sun Pharma.')]/ancestor::tr"));
        System.out.println("ancestors text are : " + ancestor.getText());

        //descendant - select child and grandchild
        List<WebElement> descendantNodes = driver.findElements(By.xpath("//a[contains(text(),'Sun Pharma.')]/ancestor::tr/descendant::*"));
        for(WebElement descendantNode : descendantNodes) {
            System.out.println("descendants are " + descendantNode.getText());
        }
        System.out.println("Number of descendants nodes : " + descendantNodes.size());

        //Following - selects everything in the document after the closing tag of the current node
        List<WebElement> allFollowingNodes = driver.findElements(By.xpath("//a[contains(text(),'Sun Pharma.')]/ancestor::tr/following::tr"));
        System.out.println("Number of following nodes which has tr tag : " + allFollowingNodes.size());

        //Following-sibling : selects all siblings after the current node





        driver.close();
    }


}
