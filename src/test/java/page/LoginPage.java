package page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.LoginTest;

/**
 * Created by QA on 27.05.2017.
 */
/**public class LoginPage {


    /** public static WebDriver webDriver;*/
   /** public static WebElement emailField=webDriver.findElement(By.xpath("//input[@type='email']"));
    public static WebElement passwordField=webDriver.findElement(By.xpath("//input[@type='password']"));
    public static WebElement chkRemember=webDriver.findElement(By.xpath("//label[@for='chkRemember']"));
    public static WebElement GObutton=webDriver.findElement(By.xpath("//*[@class='button' and text()='GO']"));
}*/
    public class LoginPage {
       public LoginPage(WebDriver webDriver) {
           PageFactory.initElements(webDriver, this);
           this.webDriver = webDriver;
       }

       public WebDriver webDriver;

       @FindBy(xpath = "//input[@type='email']")
       WebElement emailField;

       @FindBy(xpath = "//input[@type='password']")
       WebElement passwordField;

       @FindBy(xpath = "//label[@for='chkRemember']")
       WebElement chkRemember;

       @FindBy(xpath = "//*[@class='button' and text()='GO']")
       WebElement GObutton;

       public void inputEmail(String email){
           emailField.sendKeys(email);
       }
       public void inputPassword(String password){
           passwordField.sendKeys(password);
       }
       public void clickChkRemember(){
           chkRemember.click();
       }
       public void clickGObutton(){
           GObutton.click();
       }
   }