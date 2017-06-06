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

    public MainPage(WebDriver webDriver){
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        try {sleep(7000);} catch (InterruptedException e){e.printStackTrace();}
     /*waitUnitElementDisplayed(settingsIcon, 5);*/}

    public boolean isPageLoaded(){return settingsIcon.isDisplayed();}


}
