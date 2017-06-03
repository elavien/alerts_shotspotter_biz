package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by QA on 30.05.2017.
 */
public class MainPage {
     private WebDriver webDriver;


    @FindBy(className="settings")
    private WebElement settingsIcon;

public MainPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);}

    public boolean isPageLoaded(){
        return settingsIcon.isDisplayed();
    }

    public String getPageURL(){return webDriver.getCurrentUrl();}
    public String getPageTitle(){return webDriver.getTitle();}


}
