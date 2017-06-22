package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static java.lang.Thread.sleep;


/**
 * Created by QA on 30.05.2017.
 */
public class MainPage extends BasePage {

    @FindBy(className="settings")
    private WebElement settingsIcon;
    @FindBy(xpath = "//settings-drop-down//li[text()='Logout']")
    private WebElement logOutMenuItem;
    @FindBy(xpath = "//div[@class='settings isOpen']")
    private WebElement settingsMenu;
    @FindBy(xpath = "//filter-menu//div[@class='selected-option']")
    private WebElement incidentsTimeSwitch;
    @FindBy(xpath = "//filter-menu//div[@class='available-options']//*[@class='time-increment' and text()='24']")
    private WebElement timeFrameSwitch24h;
    @FindBy(xpath = "//filter-menu//div[@class='available-options']//*[@class='time-increment' and text()='3']")
    private WebElement timeFrameSwitch3d;
    @FindBy(xpath = "//filter-menu//div[@class='available-options']//*[@class='time-increment' and text()='7']")
    private WebElement timeFrameSwitch7d;
    @FindBy(xpath = "//*[@class='result-count']")
    private WebElement resultsCount;
    @FindBy(xpath = "//div//*[text()='List']")
    private WebElement listButton;
    @FindBy(xpath = "//incident-list//incident-card")
    private List<WebElement> incidentsList;

    public MainPage(WebDriver webDriver){
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        waitUnitElementDisplayed(settingsIcon);}

    public LoginPage logOut(){
        settingsIcon.click();
       waitUnitElementClickable(logOutMenuItem, 5).click();
        return PageFactory.initElements(webDriver, LoginPage.class);}

    public boolean isPageLoaded(){return settingsIcon.isDisplayed();}

    public int getResultsCount() {
        return Integer.parseInt(resultsCount.getText().replace(" Results", ""));
    }
    public void switchTimeFramePeriod(int i) {

    }

    public int getIncidentCardsCount() {

        return 0;
    }


}
