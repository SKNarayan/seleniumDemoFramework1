package cleartripPageObject;

import cleartripTestCases.CleartripBaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchFlightsPage extends CleartripBaseClass {

    WebDriver ldriver;

    public SearchFlightsPage(WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(xpath = "//p[contains(text(),'Round trip')]")
    @CacheLookup
    WebElement roundTrip;

    @FindBy(xpath = "//body/div[@id='root']/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")
    @CacheLookup
    WebElement departureFrom_cityname;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[5]/div[1]/div[1]/div[1]/input[1]")
    @CacheLookup
    WebElement arrivalTo_cityname;

    @FindBy(xpath = "//div[@class='row pt-3 pb-6 p-relative px-4']//button[1]")
    @CacheLookup
    WebElement departOn;

    @FindBy(xpath = "//button[@class='flex flex-middle flex-between t-all fs-2 focus:bc-secondary-500 bg-transparent bc-secondary-500 c-pointer pr-2 pl-3 pt-2 pb-2 ba br-4 h-8 c-neutral-900']//div[@class='fs-2 c-inherit flex flex-nowrap'][normalize-space()='Tue, Jul 20']")
    @CacheLookup
    WebElement departOnDate;

    @FindBy(xpath = "//div[@class='col-13 homeba']//button[2]")
    @CacheLookup
    WebElement returnOn;

    @FindBy(xpath = "//div[normalize-space()='Thu, Jul 22']")
    @CacheLookup
    WebElement returnOnDate;


    @FindBy(xpath = "//div[@class='mb-4']//select[@class='bc-neutral-100 bg-transparent']")
    @CacheLookup
    WebElement selectAdults;

    @FindBy(xpath = "//div[@class='mb-4']//option[@value='2'][normalize-space()='2']")
    @CacheLookup
    WebElement selectNumberOfAdults;

    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[4]/div[1]/div[3]/select[1]")
    @CacheLookup
    WebElement selectChild;

    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[4]/div[1]/div[3]/select[1]/option[2]")
    @CacheLookup
    WebElement selectNumberOfChild;

    @FindBy(xpath = "//button[normalize-space()='Search flights']")
    @CacheLookup
    WebElement searchFlightsButton;

    public void selectRoundTrip(){
        roundTrip.click();
    }

    public void enterDepartureCityName(String departureCityName){
        departureFrom_cityname.sendKeys(departureCityName);
    }

    public void enterArrivalCityName(String arrivalCityName){
        arrivalTo_cityname.sendKeys(arrivalCityName);
    }

    public void scrollToPixel(int pixelValue){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+pixelValue+")","");

    }

    public void scrollToElement(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement element = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollPageTillBottom(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

    }

    public void clickOndepartureOn(){
        departOn.click();
    }

    public void setDepartOnDate(){
        departOnDate.click();
    }

    public void clickOnReturnDate(){
        returnOn.click();
    }

    public void setReturnOnDate(){
        returnOnDate.click();
    }

    public void selectAdults(){
        selectAdults.click();
    }

    public void setSelectNumberOfAdults(){
        selectNumberOfAdults.click();
    }

    public void setSelectChild(){
        selectChild.click();
    }

    public void setSelectNumberOfChild(){
        selectNumberOfChild.click();
    }

    public void clickOnSearchFlightsButton(){
        searchFlightsButton.click();
    }

}
