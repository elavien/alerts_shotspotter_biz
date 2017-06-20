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

    /**
     * Constructor for the initialization of wevDriver
     *
     * @param webDriver It's object of WebDriver
     */
    public LoginPage(WebDriver webDriver) {
            super(webDriver, LoginPage.class);
            PageFactory.initElements(webDriver, this);
          waitUnitElementDisplayed(GObutton);}

    /**
     * Locator of "E-mail" field at the page of site
     */
    @FindBy(xpath = "//input[@type='email']")
        private WebElement emailField;
    /**
     * Locator of "Password" field at the page of site
     */
    @FindBy(xpath = "//input[@type='password']")
        private WebElement passwordField;
    /**
     * Locator of "GO" button at the page of site
     */
    @FindBy(xpath = "//*[@class='button' and text()='GO']")
        private WebElement GObutton;
    /**
     * Locator of error message at the page of site
     */
    @FindBy(className = "invalid-credentials")
        private WebElement errorLogin;

    /**
     * Getter of a private element
     *
     * @return Element emailFied
     */
    public WebElement getEmailField(){return emailField;}
    /**
     * Getter of a private element
     *
     * @return Element passwordField
     */
    public WebElement getPasswordField(){return passwordField;}
    /**
     * Getter of a private element
     *
     * @return Element GObutton
     */
    public WebElement getGObutton(){return GObutton;}


    /**
     * Авторизация на сайте
     *
     * @param user Formal parameter of type String (for e-mail)
     * @param pw Formal parameter of type String (for password)
     * @param <T>
     * @return Либо страницу авторизации либо, если не находит элемент кнопки GO, - заходит на сайт
     */
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