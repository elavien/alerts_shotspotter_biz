package page;

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
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void LoginBase(String userEmail, String userPassword) {
       LoginPage loginBase = new LoginPage(webDriver);
        loginBase.getEmailField().sendKeys(userEmail);
        loginBase.getPasswordField().sendKeys(userPassword);
        loginBase.getGObutton().click();
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
