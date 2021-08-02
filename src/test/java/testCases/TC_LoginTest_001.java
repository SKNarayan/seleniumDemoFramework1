package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

    @Test
    public void loginTest(){
        driver.get(baseURL);
        logger.info("URL is opend");

        LoginPage lp = new LoginPage(driver);
        lp.setUseId(userId);
        logger.info("Entered username");
        lp.setPassword(password);
        logger.info("Entered password");
        lp.clickLogin();
        logger.info("clicked on login button");

        if(driver.getTitle().equals("Guru99 Bank Manager HomePage")){
            Assert.assertTrue(true);
            logger.info("verified the title successfully");
        }else{
            Assert.assertTrue(false);
            logger.info("title verification is failed");
        }


    }

}
