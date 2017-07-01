package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;

/**
 * Created by SvetLana on 01.07.2017.
 */
public class NegativeLoginTest {
    public WebDriver webDriver;
    public LoginPage loginPage;

    @BeforeTest
    public void beforeMethod() {
        webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://alerts.shotspotter.biz");
        loginPage = new LoginPage(webDriver); }

    @AfterTest
    public void beforeClass() {
        webDriver.quit();
    }

    @DataProvider(name="userData")
    public Object[][] loginDataProvider()
    {
        Object[][] data = {
                {"",""},
                {"denvert1@shotspotter.net","sd"},
                {"sd","Test123!"}
        };

        return data;
    }

    @Test(dataProvider="userData")
    public void checkNegativeScenaries(String userName, String pass){
        MainPage mainPage = loginPage.login(userName, pass);
            loginPage.assertsForNegativeTests();}

}
