package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPageAbout extends BasePage {

    /**
     * Заголовок на странице http://www.shotspotter.com/apps/tos
     */
    @FindBy(xpath = "//div[@class ='entry']//h3[@class='black' and text ()='ShotSpotter - Terms of Service']")
    private WebElement termsOfServiceApp;
    /**
     * Надпись 'terms of service' в открытом диалоговом окне About
     */
    @FindBy(xpath = "//div[@class='about-dialog-content']//*[text()='terms of service']")
    private WebElement termsofservice;

    /**
     * Constructor AppsTocPage have
     * super(webDriver)
     * init Elements
     * termsOfServiceApp is Displayed
     *
     * @param webDriver super(webDriver)
     */
    public MainPageAbout(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        waitUnitElementDisplayed(termsOfServiceApp, 10);}

    /**
     * AppsTocPage is Loaded when WebElement termsOfServiceApp is displayed
     *
     * @return termsOfServiceApp is displayed or not (tru or false)
     */
    public boolean isLoaded() {
        return waitUnitElementDisplayed(termsOfServiceApp, 15).isDisplayed();
    }

}
