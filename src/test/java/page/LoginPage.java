package page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.LoginTest;

import static java.lang.Thread.sleep;

/**
 * Created by QA on 27.05.2017.
 */

    public class LoginPage {
    private WebDriver webDriver;
        public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
        webDriver.navigate().to("https://alerts.shotspotter.biz");
        try {sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
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

    public MainPage LoginAs(String userEmail, String userPassword) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        GObutton.click();
        try {sleep(7000);} catch (InterruptedException e) {e.printStackTrace();}
        return new MainPage(webDriver);
    }

    public boolean isInvalidCredentialMsgDisplayed() {return errorLogin.isDisplayed();}
    public String getErrorMsgText(){return errorLogin.getText();}
    public boolean isPageLoaded(){return emailField.isDisplayed();}

    public LoginPage LoginAsReturnToLogin(String userEmail, String userPassword){
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        GObutton.click();
        try {sleep(7000);} catch (InterruptedException e) {e.printStackTrace();}
        return this;}

    public String getPageURL(){return webDriver.getCurrentUrl();}
    public String getPageTitle(){return webDriver.getTitle();}
}