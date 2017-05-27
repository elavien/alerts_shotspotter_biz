package test;

import net.sourceforge.htmlunit.corejs.javascript.tools.shell.Global;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;

import static java.lang.Thread.sleep;

/**
 * Created by Java Script on 20.05.2017.
 */
public class LoginTest {
    public static WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {
        webDriver=new FirefoxDriver();
        webDriver.navigate().to("https://alerts.shotspotter.biz");
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void beforeClass(){
         webDriver.quit();
    }

    @Test
    public void testLoginPositive() {
        Assert.assertEquals(webDriver.getTitle(), "Shotspotter - Login", "Main page title is wrong");
        Assert.assertEquals(webDriver.getCurrentUrl(),"https://alerts.shotspotter.biz/", "Wrong URL on Login test");

        LoginPage.emailField.sendKeys("denvert1@shotspotter.net");
        LoginPage.passwordField.sendKeys("Test123!");
        LoginPage.chkRemember.click();
        LoginPage.GObutton.click();
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(webDriver.getTitle(), "Shotspotter");
        Assert.assertTrue(webDriver.getCurrentUrl().contains("https://alerts.shotspotter.biz/main"),"Wrong URL on Main page");

        WebElement map=webDriver.findElement(By.xpath("//div[@class='map']"));
        map.click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(webDriver.findElement(By.className("settings")).isDisplayed(), "Settings icons is not displayed");
        /**webDriver.findElement(By.xpath("//div[@class='drawer-toggle footer-button list']")).click();
        webDriver.findElement(By.xpath("//div[@class='address' and text()=' 3868 Adams St']")).click();*/


    }
}
