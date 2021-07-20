package cleartripTestCases;

import cleartripPageObject.SearchFlightsPage;
import org.testng.annotations.Test;

public class TC_SearchFlights_001 extends CleartripBaseClass {

    @Test
    public void searchFlightsTest() throws InterruptedException {
        driver.get(baseURL);
       // driver.manage().window().maximize();
        driver.manage().window().fullscreen();

        SearchFlightsPage searchFlightsPage = new SearchFlightsPage(driver);

        searchFlightsPage.selectRoundTrip();
        searchFlightsPage.enterDepartureCityName("Mumbai");
        searchFlightsPage.enterArrivalCityName("Delhi");

        //searchFlightsPage.scrollToPixel(400);
        //searchFlightsPage.scrollToElement();
        searchFlightsPage.scrollPageTillBottom();
        Thread.sleep(2000);

        searchFlightsPage.clickOndepartureOn();
        searchFlightsPage.setDepartOnDate();
       // searchFlightsPage.setDepartOnDate();
        searchFlightsPage.clickOnReturnDate();
        searchFlightsPage.setReturnOnDate();
       // searchFlightsPage.setReturnOnDate();

        searchFlightsPage.selectAdults();
        searchFlightsPage.setSelectNumberOfAdults();

        searchFlightsPage.setSelectChild();
        searchFlightsPage.setSelectNumberOfChild();

        searchFlightsPage.clickOnSearchFlightsButton();


    }


}
