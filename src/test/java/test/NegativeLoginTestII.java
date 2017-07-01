package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.LoginPage;

/**
 * Created by SvetLana on 01.07.2017.
 */
public class NegativeLoginTestII {
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
    public Object[][] getData()
    {
        Object[][] data = {
                {"",""},
                {"denvert1@shotspotter.net","sd"},
                {"sd","Test123!"}
        };

        return data;
    }

    @SetTestName(idx=0)
    @Test(dataProvider="userData")
    public void test1(String userName, String pass)
    {System.out.println("Testcase 1 - Empty fields");
        loginPage = loginPage.login(userName, pass);
        loginPage.assertsForNegativeTests();}

    @SetTestName(idx=1)
    @Test(dataProvider="userData")
    public void test2(String userName, String pass)
    {System.out.println("Testcase 2 - Wrong password");
        loginPage = loginPage.login(userName, pass);
        loginPage.assertsForNegativeTests();}

    @SetTestName(idx=2)
    @Test(dataProvider="userData")
    public void test3(String userName, String pass)
    {System.out.println("Testcase 3 - Wrong login");
        loginPage = loginPage.login(userName, pass);
        loginPage.assertsForNegativeTests();}
}
