package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainPage extends BasePage {

    /**
     * Вебэлемент - ссылается на свойства
     */
    @FindBy(className = "settings")
    private WebElement settingIcon;
    /**
     * Вебэлемент, который ссылается на нажатую кнопку свойств
     */
    @FindBy(xpath = "//div[@class='settings isOpen']")
    private WebElement settingMenu;
    /**
     * Вебэлемент - кнопка "Log Out"
     */
    @FindBy(xpath = "//settings-drop-down//li[text()='Logout']")
    private WebElement logOutMenuItem;
    /**
     * Вебэлемент - кнопка "About"
     */
    @FindBy(xpath = "//settings-drop-down//li[text()='About']")
    private WebElement aboutMenuItem;
    /**
     * Кнопка-меню для выбора тайм-фрейма
     */
    @FindBy(xpath = "//filter-menu//div[@class='selected-option']")
    private WebElement incidentsTimeSwitch;
    /**
     * Вебэлемент, который отображает results на странице после выбора в тайм-фрейме
     */
    @FindBy(xpath = "//*[@class='result-count']")
    private WebElement resultsCount;
    /**
     * Кнопка-меню LIST
     */
    @FindBy(xpath = "//div//*[text()='List']")
    private WebElement listButton;
    /**
     * Вебэлемент - группа карточек с адресами, котоыре доступны после выбора тайм-фрейма и после нажатия на LIST
     */
    @FindBy(xpath = "//incident-list//incident-card")
    private List<WebElement> incidentsList;
    /**
     * Кнопка Close - этот вебэлемент доступен только после нажатия на About
     */
    @FindBy(xpath = "//*[@class='btn btn-primary']")
    private WebElement buttonClose;
    /**
     * Ссылка с текстом "terms of service". Доступна только после нажатия на About
     */
    @FindBy(linkText = "terms of service")
    private WebElement linkTermsOfService;
    /**
     * Надпись "ShotSpotter - Terms of Service" черным жирным шрифтом на странице http://www.shotspotter.com/apps/tos (после перехода по ссылке "terms of service")
     */
    @FindBy (xpath = "//*[@class='black' and text()='ShotSpotter - Terms of Service']")
    private WebElement textTermsOfService;
    /**
     *  Ссылка с текстом "terms of service". Доступна только после нажатия на About
     */
    @FindBy(xpath = "//div[@class='about-dialog-content']//*[text()='terms of service']")
    private WebElement termsofservice;
    /**
     * Кнопка Close. Доступна после нажатия на About
     */
    @FindBy(xpath = "//div [@class='modal-footer']//*[@class='btn btn-primary']")
    private WebElement close;

    /**Ожидает визуализацию вебэлемента
     * @return вебэлемент с надписью "ShotSpotter - Terms of Service"
     * черным жирным шрифтом на странице http://www.shotspotter.com/apps/tos (после перехода по ссылке "terms of service")
     */
    public WebElement waitPageTermOfServisLoaded() {
        return waitUnitElementDisplayed(textTermsOfService);
    }
    /**
     * @param period формальная величина - выбор периода - 3 days, 7 days, 24 horus
     * @return найденный вебэлемент с формальным интовым значением периода
     */
    private WebElement getTimeFramePeriodOption(int period) {
        return webDriver.findElement(By.xpath(String.format("//filter-menu//div[@class='available-options']//*[@class='time-increment' and text()='%d']", period)));}

    /**С помощью parseInt преобразовывает полученный текст вебэлемента в интовое число.
     * Надпись "RESULTS" перезаписывает в пустое значение " "
     * @return интовое число выданного результата в поле Results, которое активируется после выбора тайм-фрейма
     */
    public int getResultsCount() {
        return Integer.parseInt(resultsCount.getText().replace(" RESULTS", ""));
    }

    /** Конструктор класса с инициализацией по WebDriver
     * Ждет пока отобразится элемент страницы
     */
    public MainPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        waitUnitElementDisplayed(settingIcon);}

    /**Вылогинивается
     * @return
     */
    public LoginPage logOut() {
        settingIcon.click();
        waitUnitElementDisplayed(settingMenu);
        waitUnitElementClickable(logOutMenuItem, 5).click();
        return PageFactory.initElements(webDriver, LoginPage.class);}

    /**
     * Ожидает отображение кнопки Settings Icon на странице Main Page
     */
    public boolean isPageLoaded() {return settingIcon.isDisplayed();}

    /** Нажимает на кнопку выбора тайм-фрейм периода. Выбор периода производится в тестах.
     * Ожидает, пока значения results озагрузятся - максимум 15 секунд
     * @param period интовое значение периода
     */
    public void switchTimeFramePeriod(int period) {
        incidentsTimeSwitch.click();
        getTimeFramePeriodOption(period).click();
        waitResultCountUpdated(15);}

    /** Вэйтер. Ждет, пока данные по results загрузятся
     * @param maxTimeoutSec интовое значение - секунды
     *  Стартовая переменная  initialResultCount  через 100 миллисекунд не должна равняться  currentResult.
     *   Обе ссылаются на полученное значение results. Их неравенство через промежуток времени означает,
     *                      что results изменились, т.е. загрузились новые значения
     */
    public void waitResultCountUpdated(int maxTimeoutSec) {
        int initialResultCount = getResultsCount();
        for (int i = 0; i < maxTimeoutSec; i++) {
            try {Thread.sleep(100);
                int currentResult = getResultsCount();
                if (initialResultCount != currentResult) {break;}}
                    catch (InterruptedException e) { }}}

    /** Кликает на кнопку LIST
     * @return выдает количество объектов в LIST
     */
    public int getIncidentCardsCount() {
        listButton.click();
        return incidentsList.size();}
    /**
     * Ждет, пока первый эелемент LIST станет кликабельным
     */
    public void openIncidentsList() {
        listButton.click();
        waitUnitElementClickable(incidentsList.get(1), 10);}

    /** Создает стринговый массив, в который прописывает полученный текст по xpath (XpathElement) из свитча getIncident
     * @param detal формальное значение, которое будет уточняться в тесте
     * @return объект массива с прописаннымив  его позициях полученный из карточек текст времени
     * Это будет нужно для поиска дубликатов внутри этого массива в тестах
     */
    public List<String> getIncidentCards(String detal) {
        List<String> listTimeStamps = new ArrayList<String>();
        String XpathElement = getIncident(detal);
        for (WebElement incidentTimeStamps : incidentsList) {
            String TimeStampsText = incidentTimeStamps.findElement(By.xpath(XpathElement)).getText();
            listTimeStamps.add(TimeStampsText);}
        return listTimeStamps;}
    /** Свитч для извлечения групповых вебэлементов по xpath внутри карточек LIST
     * @param detal формальнеое значение, которое задастся в тестах
     * @return стринговое xpath-значение
     */
    public String getIncident(String detal) {
        switch (detal.toLowerCase()) {
            case "time": return "//div[@class='cell day']//div [@class='content']";
            case "street": return "//div[@class='address']";
            case "city": return "//div[@class='city S']";
            default: return "";}}

    /**
     * Ждет, пока About станет кликабельным
     */
    public void abOutMenuItem() {
        settingIcon.click();
        waitUnitElementDisplayed(settingMenu);
        waitUnitElementClickable(aboutMenuItem, 5).click();}

    private List<WebElement> incidentTimeStamp;
    public boolean TimeStampsUnique() {
        Set incidentTimeStampCount = new HashSet(incidentTimeStamp);
        if (incidentTimeStampCount.size() == incidentTimeStamp.size()) {return true;}
        return false;}

    /**
     * Работа с новыми вкладками. Определяет стринговые значения открытых вкладок. Если полученное значение окна не эквивалентно
     * предыдущему, переключается на вкладку по этому новому значению. Затем проверяет загрузилась ли MainPageAbout, ждет.
     * Проверяет title вклыдки и адрес страницы
     * Затем закрывает вкладку и переключается на предыдущую вкладку. Нажимает кнопку Close
     */
    public void WebWindoww() {
        termsofservice.click();
        String parentWindow = webDriver.getWindowHandle();
        Set<String> handles = webDriver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                webDriver.switchTo().window(windowHandle);
                MainPageAbout appsTocPage = new MainPageAbout(webDriver);
                appsTocPage.isLoaded();
                Assert.assertEquals(getPageURL(), "http://www.shotspotter.com/apps/tos", "Wrong URL on Apps-Toc page");
                Assert.assertEquals(appsTocPage.getPageTitle(), "Apps-TOS", "Apps-Toc page page title is wrong");
                webDriver.close();
                webDriver.switchTo().window(parentWindow);}
            close.click();}}
}