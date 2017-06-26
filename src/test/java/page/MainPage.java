package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;



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
    @FindBy(xpath = "//*[@class='result-count']")
    private WebElement resultsCount;
    @FindBy(xpath = "//div//*[text()='List']")
    private WebElement listButton;
    @FindBy(xpath = "//incident-list//incident-card")
    private List<WebElement> incidentsCardList;

    private WebElement getTimeFramePeriodOption(int period){
        return webDriver.findElement(By.xpath(String.format("//filter-menu//div[@class='available-options']//*[@class='time-increment' and text()='%d']", period)));
    }

    public MainPage(WebDriver webDriver){
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        waitUnitElementDisplayed(settingsIcon);

    }

    public LoginPage logOut(){
        settingsIcon.click();
       waitUnitElementClickable(logOutMenuItem, 5).click();
        return PageFactory.initElements(webDriver, LoginPage.class);}

    public boolean isPageLoaded(){return settingsIcon.isDisplayed();}

    public int getResultsCount() {
        return Integer.parseInt(resultsCount.getText().replace(" Results", ""));
    }
    public void switchTimeFramePeriod(int period) {
        incidentsTimeSwitch.click();
        getTimeFramePeriodOption(period).click();
        waitResultCountUpdated(5);
    }

    public void waitResultCountUpdated(int maxTimeoutSec){
        int initialResultCount = getResultsCount();
        for (int i = 0; i<maxTimeoutSec; i++){
            try {
                Thread.sleep(1000);
                int currentResultCount = getResultsCount();
                if (initialResultCount != currentResultCount) {
                    break;
                }
            }
            catch (InterruptedException ie){
            }
        }
    }

    public int getIncidentCardsCount() {
        listButton.click();
        return incidentsCardList.size();
    }


}
