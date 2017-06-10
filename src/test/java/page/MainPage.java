package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;


/**
 * Created by QA on 30.05.2017.
 */
public class MainPage extends BasePage {

    @FindBy(className="settings")
    private WebElement settingsIcon;
        @FindBy(xpath = "//settings-drop-down//li[text()='Logout']")
    private WebElement logOutMenuItem;

    public MainPage(WebDriver webDriver){
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        waitUnitElementDisplayed(settingsIcon);}

    public LoginPage logOut(){
        settingsIcon.click();
        waitUnitElementDisplayed(logOutMenuItem).click();
        return PageFactory.initElements(webDriver, LoginPage.class);}

    public boolean isPageLoaded(){return settingsIcon.isDisplayed();}


}
