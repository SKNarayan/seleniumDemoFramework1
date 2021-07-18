package cleartripTestCases;

import cleartripPageObject.SearchFlightsPage;
import org.testng.annotations.Test;

public class TC_SearchFlights_001 extends CleartripBaseClass {

    @Test
    public void searchFlightsTest(){
        driver.get(baseURL);
        driver.manage().window().maximize();

        SearchFlightsPage searchFlightsPage = new SearchFlightsPage(driver);

        searchFlightsPage.selectRoundTrip();
        searchFlightsPage.enterDepartureCityName("Mumbai");
        searchFlightsPage.enterArrivalCityName("Delhi");

        searchFlightsPage.selectAdults();
        searchFlightsPage.setSelectNumberOfAdults();

        searchFlightsPage.setSelectChild();
        searchFlightsPage.setSelectNumberOfChild();

        searchFlightsPage.clickOnSearchFlightsButton();


    }


}
