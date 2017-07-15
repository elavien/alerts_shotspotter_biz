package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.List;



/**
 * MainPage have:
 * settingIcon,
 * logOut,
 * map.
 */
public class MainPage extends BasePage {

    @FindBy(className = "settings")
    private WebElement settingIcon;
    /**
     * drop down menu "settings"
     */
    @FindBy(xpath = "//div[@class='settings isOpen']")
    private WebElement settingMenu;
    /**
     * WebElement "logOut" in drop down menu "settings"
     */
    @FindBy(xpath = "//settings-drop-down//li[text()='Logout']")
    private WebElement logOutMenuItem;
    @FindBy(xpath = "//settings-drop-down//li[text()='About']")
    private WebElement aboutMenuItem;
    @FindBy(xpath = "//filter-menu//div[@class='selected-option']")
    private WebElement incidentsTimeSwitch;
    @FindBy(xpath = "//*[@class='result-count']")
    private WebElement resultsCount;
    @FindBy(xpath = "//div//*[text()='List']")
    private WebElement listButton;
    @FindBy(xpath = "//incident-list//incident-card")
    private List<WebElement> incidentsList;
    @FindBy(xpath="//*[@class='btn btn-primary']")
    private  WebElement buttonClose;
    @FindBy (linkText = "terms of service")
    private WebElement linkTermsOfService;


       /**
     * @param period
     * @return
     */
    private WebElement getTimeFramePeriodOption(int period) {
        return webDriver.findElement(By.xpath(String.format("//filter-menu//div[@class='available-options']//*[@class='time-increment' and text()='%d']", period)));
    }


    /**
     * Numeric from RESULTS
     *
     * @return quantity of RESULTS
     */
    public int getResultsCount() {
        return Integer.parseInt(resultsCount.getText().replace(" RESULTS", ""));
    }

    /**
     * Constructor MainPage have
     * super(webDriver)
     * init Elements
     * settingIcon is Displayed
     *
     * @param webDriver super(webDriver)
     */
    public MainPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        waitUnitElementDisplayed(settingIcon);
    }

    /**
     * Click logOut in MainPage to return in LoginPage
     *
     * @return LoginPage after click logOut
     */
    public LoginPage logOut() {
        settingIcon.click();
        waitUnitElementDisplayed(settingMenu);
        waitUnitElementClickable(logOutMenuItem, 5).click();
        return PageFactory.initElements(webDriver, LoginPage.class);
    }

    /**
     * MainPage is Loaded when Settings is displayed
     *
     * @return Settings is displayed (true or false)
     */
    public boolean isPageLoaded() {
        return settingIcon.isDisplayed();
    }

    /**
     * Choose days in timeFrameSwitch
     *
     * @param period days
     */
    public void switchTimeFramePeriod(int period) {
        incidentsTimeSwitch.click();
        getTimeFramePeriodOption(period).click();
        waitResultCountUpdated(15);
    }

    public void waitResultCountUpdated(int maxTimeoutSec) {
        int initialResultCount = getResultsCount();

        for (int i = 0; i < maxTimeoutSec; i++) {
            try {
                Thread.sleep(100);
                int currentResult = getResultsCount();
                if (initialResultCount != currentResult) {
                    break;
                }
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Count Element in AdresList
     *
     * @return quantity of Elements
     */
    public int getIncidentCardsCount() {
        listButton.click();
        return incidentsList.size();
    }

    public void openIncidentsList() {
        listButton.click();
        waitUnitElementClickable(incidentsList.get(1), 5);
    }

    public List<String> getIncidentCardsCities() {
        List<String> listCities = new ArrayList<String>();
        for (WebElement incidentCard : incidentsList) {String cityText = incidentCard.findElement(By.xpath("//div[@class='city S']")).getText();
        listCities.add(cityText);}
        return listCities;
    }

    public List<String> getIncidentCardsStreets() {
        List<String> listStreets = new ArrayList<String>();
        for (WebElement incidentCard : incidentsList) {String streetText = incidentCard.findElement(By.xpath("//div[@class='address']")).getText();
            listStreets.add(streetText);}
        return listStreets;
    }

    public List<String> getIncidentCardsTimeStamps() {
        List<String> listTimeStamps = new ArrayList<String>();
        for (WebElement incidentCard : incidentsList) {String timeStampsText = incidentCard.findElement(By.xpath("//div[@class='cell day']/div[@class='content']")).getText();
            listTimeStamps.add(timeStampsText);}
        return listTimeStamps;
    }
    @FindBy (xpath = "//*[@class='about-dialog-content'']")
    private WebElement fieldLinks;

    public void prst(){
        settingIcon.click();
        waitUnitElementClickable(aboutMenuItem, 5);
        aboutMenuItem.click();
        waitUnitElementClickable(buttonClose, 5);
        waitUnitElementDisplayed(fieldLinks, 5);


}