package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver ldriver;

   public LoginPage(WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(name="uid")
    @CacheLookup
    WebElement textUserName;

    @FindBy(name="password")
    @CacheLookup
    WebElement textPass;

    @FindBy(name="btnLogin")
    @CacheLookup
    WebElement btnLogin;

    public void setUseId(String userID){
        textUserName.sendKeys(userID);
    }

    public void setPassword(String pass){
        textPass.sendKeys(pass);
    }

    public void clickLogin(){
        btnLogin.click();
    }












}
