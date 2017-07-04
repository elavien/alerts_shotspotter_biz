package test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;


/**
 * Created by Java Script on 20.05.2017.
 */
public class LoginTest {
    public WebDriver webDriver;
    public LoginPage loginPage;
    public String username="denvert1@shotspotter.net";
    public String password="Test123!";
    BrowserVersion browser;

    @Parameters("browser")
    @BeforeMethod
    public void beforeMethod(String browser) {
        loginPage = new LoginPage(webDriver);
        if (browser.equalsIgnoreCase("firefox")) {
            webDriver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
            webDriver = new ChromeDriver();
        }else {
            throw new IllegalArgumentException("The Browser Type is Undefined");}
        webDriver.navigate().to("https://alerts.shotspotter.biz");}

    @AfterMethod
    public void beforeClass() {
        webDriver.quit();
    }

    @Test
    public void testLoginPositive() {
        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Main page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", "Wrong URL on Login test");
        MainPage mainPage = loginPage.login(username, password);
        Assert.assertEquals(mainPage.getPageTitle(), "Shotspotter");
        Assert.assertTrue(mainPage.getPageURL().contains("https://alerts.shotspotter.biz/main"), "Wrong URL on Main page");
        Assert.assertTrue(mainPage.isPageLoaded(), "Settings icon is not displayed");
    }
    @Test
    public void testLoginNegative(){
        String expextedError = "The provided credentials are not correct.";
        loginPage = loginPage.login("esgtr@gh.ss", "Tes3!");
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        Assert.assertTrue(loginPage.isInvalidCredentialMsgDisplayed(), "Error message was not displayed on login page");
        Assert.assertEquals(loginPage.getErrorMsgText(), expextedError, "Error msg has wrong text");
    }
    @Test
    public void testLogOut() {
        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Main page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", "Wrong URL on Login test");
        MainPage mainPage = loginPage.login(username, password);
        Assert.assertEquals(mainPage.getPageTitle(), "Shotspotter");
        Assert.assertTrue(mainPage.getPageURL().contains("https://alerts.shotspotter.biz/main"), "Wrong URL on Main page");
        Assert.assertTrue(mainPage.isPageLoaded(), "Settings icon is not displayed");
        loginPage = mainPage.logOut();
        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Main page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", "Wrong URL on Login test");
    }
    @DataProvider
    public static Object[][] falseLoginEmail() {
        return new Object[][]{
                {"", "P@ssword123", "The provided credentials are not correct."},
                {"24sst.tau@gmailcom", "P@ssword123", "The provided credentials are not correct."},
                {"24sst.taugmail.com", "P@ssword123", "The provided credentials are not correct."},
                {"24 sst.tau@gmail.com", "P@ssword123", "The provided credentials are not correct."},
                {"24sst.tau@gmai l.com", "P@ssword123", "The provided credentials are not correct."},
                {"@gmail.com", "P@ssword123", "The provided credentials are not correct."},
                {"24sst.tau@", "P@ssword123", "The provided credentials are not correct."},
                {"24sst.tau@@gmail.com", "P@ssword123", "The provided credentials are not correct."},
                {"24sst.tau@gmail.com", "", "The provided credentials are not correct."},
                {"24sst.tau@gmail.com", "P@ssword", "The provided credentials are not correct."},
                {"", "", "The provided credentials are not correct."}};
    }

    @Test(dataProvider = "falseLoginEmail")
    public void testLoginNegativDataProvider(String Email, String Password, String Errormsg) {
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Login page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", "Login URL on Login page");

        loginPage.login(Email, Password);
        loginPage.isPageLoaded();

        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", "Login URL on Login page");
        Assert.assertTrue(loginPage.isPageLoaded(), "LoginPage is not loaded");
        Assert.assertTrue(loginPage.invalidCredentialsMsgDisplayed(),"Error messege was not displayd on LoginPage");
        Assert.assertEquals(loginPage.getErrormsgText(), Errormsg, "Invalid Text not correct");}

}
