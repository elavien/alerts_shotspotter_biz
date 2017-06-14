package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

/**
 * Created by QA on 27.05.2017.
 */

public class LoginPage extends BasePage <LoginPage>{

    public LoginPage(WebDriver webDriver) {
            super(webDriver, LoginPage.class);
            PageFactory.initElements(webDriver, this);
          waitUnitElementDisplayed(GObutton);}

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

    public <T> T login(String user, String pw){
        emailField.sendKeys(user);
        passwordField.sendKeys(pw);
        GObutton.click();
            if  (isElementDisplayed(GObutton, 3)) {return (T) this;}
            else {return (T) PageFactory.initElements(webDriver, MainPage.class);}}

    public boolean isInvalidCredentialMsgDisplayed() {return errorLogin.isDisplayed();}
    public String getErrorMsgText(){return errorLogin.getText();}
    public boolean isPageLoaded(){return emailField.isDisplayed();}


}