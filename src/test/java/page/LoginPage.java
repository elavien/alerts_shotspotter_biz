package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

/**
 * Created by QA on 27.05.2017.
 */

public class LoginPage extends BasePage{

    public LoginPage(WebDriver webDriver) {
            super(webDriver);
            PageFactory.initElements(webDriver, this);
        webDriver.navigate().to("https://alerts.shotspotter.biz");

        waitUnitElementDisplayed(GObutton, 5);
    }
    @FindBy(xpath = "//input[@type='email']")
        private WebElement emailField;
    @FindBy(xpath = "//input[@type='password']")
        private WebElement passwordField;
    @FindBy(xpath = "//*[@class='button' and text()='GO']")
        private WebElement GObutton;
    @FindBy(className = "invalid-credentials")
        private WebElement errorLogin;

    public WebElement getEmailField(){return emailField;}
    public WebElement getPasswordField(){return passwordField;}
    public WebElement getGObutton(){return GObutton;}

         protected BasePage basePage;

    public MainPage LoginAs(String userEmail, String userPassword) {
        new BasePage(webDriver).LoginBase(userEmail, userPassword);
        return new MainPage(webDriver);
    }

    public LoginPage LoginAsReturnToLogin(String userEmail, String userPassword){
        new BasePage(webDriver).LoginBase(userEmail, userPassword);
        return this;}


    public boolean isInvalidCredentialMsgDisplayed() {return errorLogin.isDisplayed();}
    public String getErrorMsgText(){return errorLogin.getText();}
    public boolean isPageLoaded(){return emailField.isDisplayed();}


}