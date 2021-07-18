package cleartripPageObject;

import cleartripTestCases.CleartripBaseClass;
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
