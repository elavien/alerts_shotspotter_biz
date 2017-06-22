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

    /**
     * Constructor for the initialization of wevDriver
     *
     * @param webDriver instance
     */
    public BasePage(WebDriver webDriver){this.webDriver=webDriver;}

    /**
     * Current URL
     *
     * @return current string value as url adress from open page
     */
    public String getPageURL(){return webDriver.getCurrentUrl();}

    /**
     * Common method to get current Page title
     *
     * @return String with current Page title
     */
    public String getPageTitle(){return webDriver.getTitle();}

    /**
     * Waits until the element is clickable using specific max timeout.
     *
     * @param element Webelement to wait for
     * @param timeout Max timeout in seconds
     * @return WebElement after expected contition
     */
    public WebElement waitUnitElementClickable(WebElement element, int timeout){
        WebDriverWait wait= new WebDriverWait(webDriver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));}

    /**
     * Waiting for the element becomes visible at page of site using specific max timeout
     *
     * @param element Webelement to wait for
     * @param timeout Max timeout in seconds
     * @return WebElement after expected contition
     */
    public WebElement waitUnitElementDisplayed(WebElement element, int timeout){
        WebDriverWait wait= new WebDriverWait(webDriver, timeout);
        return wait.until(ExpectedConditions.visibilityOf(element));}

    /**
     * Waiting for the web element of up to 10 seconds
     *
     * @param element Webelement to wait for
     * @return Web item for up to 10 seconds
     */
    public WebElement waitUnitElementDisplayed(WebElement element){
        return waitUnitElementDisplayed(element, 10);}

    /**
     * Waiting for true or false when searching for a locator of WebElement at page of site
     *
     * @param element Webelement to wait for
     * @param timeout Max timeout in seconds
     * @return True or False after waiting for locator of WebElement
     */
    public boolean isElementDisplayed(WebElement element, int timeout){
        try{waitUnitElementDisplayed(element).isDisplayed();}
        catch (TimeoutException e){return false;}
        return true;}


}
