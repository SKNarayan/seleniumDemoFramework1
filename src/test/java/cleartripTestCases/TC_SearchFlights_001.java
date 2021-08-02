package cleartripTestCases;

import cleartripPageObject.SearchFlightsPage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ReadConfig;

public class TC_SearchFlights_001 extends CleartripBaseClass {


    @Test
    public void searchFlightsTest() throws InterruptedException {

        Logger.getLogger(TC_SearchFlights_001.class);

        ReadConfig readConfig = new ReadConfig();

        driver.get(readConfig.getApplicationURL());
        logger.info("successfully open the webpage");

       // driver.manage().window().maximize();
        driver.manage().window().fullscreen();

        SearchFlightsPage searchFlightsPage = new SearchFlightsPage(driver);

        searchFlightsPage.selectRoundTrip();
        logger.info("Selected round trip");
        searchFlightsPage.enterDepartureCityName("Mumbai");
        logger.info("Entered Departure city name");
        searchFlightsPage.enterArrivalCityName("Delhi");
        logger.info("Entered arrival city name");

        searchFlightsPage.scrollToPixel(400);
        logger.info("Scroll down succesfully");
        //searchFlightsPage.scrollToElement();
        //searchFlightsPage.scrollPageTillBottom();
        Thread.sleep(2000);

        searchFlightsPage.clickOndepartureOn();
        searchFlightsPage.setDepartOnDate();
        logger.info("set departure date");
       // searchFlightsPage.setDepartOnDate();
        searchFlightsPage.clickOnReturnDate();
        searchFlightsPage.setReturnOnDate();
        logger.info("set return date");
       // searchFlightsPage.setReturnOnDate();

        searchFlightsPage.selectAdults();
        searchFlightsPage.setSelectNumberOfAdults();
        logger.info("Set number of adults successfully");

        searchFlightsPage.setSelectChild();
        searchFlightsPage.setSelectNumberOfChild();
        logger.info("set number of child successfully");

        searchFlightsPage.clickOnSearchFlightsButton();
        logger.info("clicked on search flight button");


    }


}
