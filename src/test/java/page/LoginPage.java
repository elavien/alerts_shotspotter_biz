package page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by QA on 27.05.2017.
 */
public class LoginPage {
    public static WebDriver webDriver;
    public static WebElement emailField=webDriver.findElement(By.xpath("//input[@type='email']"));
    public static WebElement passwordField=webDriver.findElement(By.xpath("//input[@type='password']"));
    public static WebElement chkRemember=webDriver.findElement(By.xpath("//label[@for='chkRemember']"));
    public static WebElement GObutton=webDriver.findElement(By.xpath("//*[@class='button' and text()='GO']"));
}
