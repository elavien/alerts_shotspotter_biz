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
public class BasePage <T>{
    public WebDriver webDriver;

    public BasePage(WebDriver webDriver, Class<T> clazz){this.webDriver=webDriver;}

    public String getPageURL(){return webDriver.getCurrentUrl();}

    /**
     * Common method to get current Page title
     *
     * @return String with current Page title
     */
    public String getPageTitle(){return webDriver.getTitle();}

    /**
     * Waits until element is clickable using specific max timeout.
     *
     * @param element Webelement to wait for
     * @param timeout Max timeout in seconds
     * @return WebElement after expected contition
     */
    public WebElement waitUnitElementClickable(WebElement element, int timeout){
        WebDriverWait wait= new WebDriverWait(webDriver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));}
    public WebElement waitUnitElementDisplayed(WebElement element, int timeout){
        WebDriverWait wait= new WebDriverWait(webDriver, timeout);
        return wait.until(ExpectedConditions.visibilityOf(element));}
    public WebElement waitUnitElementDisplayed(WebElement element){
        return waitUnitElementDisplayed(element, 10);}

    public boolean isElementDisplayed(WebElement element, int timeout){
        try{waitUnitElementDisplayed(element).isDisplayed();}
        catch (TimeoutException e){return false;}
        return true;}


}
