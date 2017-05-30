package page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.LoginTest;

import static java.lang.Thread.sleep;

/**
 * Created by QA on 27.05.2017.
 */
/**public class LoginPage {


    /** public WebDriver webDriver;
    private WebElement emailField;
    private WebElement passwordField;
     private WebElement GObutton; */
   /**private void InitLoginPageWebElements(){
    *   WebElement emailField=webDriver.findElement(By.xpath("//input[@type='email']"));
    WebElement passwordField=webDriver.findElement(By.xpath("//input[@type='password']"));
    WebElement chkRemember=webDriver.findElement(By.xpath("//label[@for='chkRemember']"));
     GObutton=webDriver.findElement(By.xpath("//*[@class='button' and text()='GO']"));}
}*/
    public class LoginPage {
       public LoginPage(WebDriver webDriver) {
           PageFactory.initElements(webDriver, this);
           this.webDriver = webDriver;
           webDriver.navigate().to("https://alerts.shotspotter.biz");
           try {
               sleep(5000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }

       private WebDriver webDriver;

       @FindBy(xpath = "//input[@type='email']")
       private WebElement emailField;

       @FindBy(xpath = "//input[@type='password']")
       private WebElement passwordField;

       @FindBy(xpath = "//label[@for='chkRemember']")
       private WebElement chkRemember;

       @FindBy(xpath = "//*[@class='button' and text()='GO']")
       private WebElement GObutton;



       public MainPage LoginAs(String userEmail, String userPassword){
           emailField.sendKeys(userEmail);
            passwordField.sendKeys(userPassword);
            GObutton.click();
        try {
        sleep(7000);
        } catch (InterruptedException e) {
        e.printStackTrace();
        }
           return new MainPage(webDriver);
       }

   }