import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import static java.lang.Thread.sleep;

/**
 * Created by Java Script on 20.05.2017.
 */
public class LoginTest {


    public static void main(String[] args) {
        WebDriver webDriver=new FirefoxDriver();
        webDriver.navigate().to("https://alerts.shotspotter.biz");
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(webDriver.getTitle(), "Shotspotter - Login");
        webDriver.findElement(By.xpath("//input[@type='email']")).sendKeys("denvert1@shotspotter.net");
        webDriver.findElement(By.xpath("//input[@type='password']")).sendKeys("Test123!");
        webDriver.findElement(By.xpath("//label[@for='chkRemember']")).click();
        webDriver.findElement(By.xpath("//*[@class='button' and text()='GO']")).click();
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(webDriver.getTitle(), "Shotspotter");
        webDriver.findElement(By.xpath("//div[@class='map']")).click();
        webDriver.findElement(By.xpath("//div[@class='drawer-toggle footer-button list']")).click();
        webDriver.findElement(By.xpath("//div[@class='address' and text()=' 3868 Adams St']")).click();

        /**webDriver.quit();*/


    }
}
