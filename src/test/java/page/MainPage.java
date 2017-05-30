package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by QA on 30.05.2017.
 */
public class MainPage {
    private WebDriver webDriver;
    private WebElement settingsIcon;

    private void InitMainPageWebElements(){
        settingsIcon = webDriver.findElement(By.className("settings"));
    }
    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        InitMainPageWebElements();}

    public boolean isPageLoaded(){
        return settingsIcon.isDisplayed();
    }
}
