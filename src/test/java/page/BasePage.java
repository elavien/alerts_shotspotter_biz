package page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Created by UI дизайн on 03.06.2017.
 */
public class BasePage {
    public WebDriver webDriver;

    public BasePage(WebDriver webDriver){this.webDriver=webDriver;}

    public String getPageURL(){return webDriver.getCurrentUrl();}
    public String getPageTitle(){return webDriver.getTitle();}

    public WebElement waitUnitElementDisplayed(WebElement element, int timeout){
        WebDriverWait wait= new WebDriverWait(webDriver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));}
    public WebElement waitUnitElementDisplayed(WebElement element){
        return waitUnitElementDisplayed(element, 10);}

    public boolean isElementDisplayed(WebElement element, int timeout){
        try{waitUnitElementDisplayed(element).isDisplayed();}
        catch (TimeoutException e){return false;}
        return true;}


}
