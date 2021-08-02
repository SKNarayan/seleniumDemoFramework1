package cleartripTestCases;

import cleartripPageObject.SearchFlightsPage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ReadConfig;

public class TC_SearchFlights_002 extends CleartripBaseClass{
    @Test
    public void bookFlights(){
        Logger.getLogger(TC_SearchFlights_001.class);

        ReadConfig readConfig = new ReadConfig();

        driver.get(readConfig.getApplicationURL());
        logger.info("successfully open the webpage");

        // driver.manage().window().maximize();
        driver.manage().window().fullscreen();

        SearchFlightsPage searchFlightsPage = new SearchFlightsPage(driver);

        searchFlightsPage.selectRoundTrip();
        Assert.assertTrue(false);
    }


}
