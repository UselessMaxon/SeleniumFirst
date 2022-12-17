
package com.MA.tests.Sber;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ExampleScenarioTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void before() {
//      ��� ��� � Linux
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver");
//      ��� Windows
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 10, 1000);

        String baseUrl = "https://www.sberbank.ru/ru/person";
        driver.get(baseUrl);
    }

    @Test
    public void exampleScenario() {

        String cookiesClose = "//button[@class='kitt-cookie-warning__close']";
        WebElement cookiesBtnClose = driver.findElement(By.xpath(cookiesClose));
        cookiesBtnClose.click();

        // ������� ����� ���� - "�����������"
        String insuranceButtonXPath = "//a[contains(text(), '�����������') and contains(@role, 'button')]";
        WebElement insuranceButton = driver.findElement(By.xpath(insuranceButtonXPath));
        waitUtilElementToBeClickable(insuranceButton);
        insuranceButton.click();


        // ������� ����� ������� - "�����������"
        String sberInsuranceButtonXPath = "//a[text()='�����������']";
        WebElement travellersInsuranceButton = driver.findElement(By.xpath(sberInsuranceButtonXPath));
        waitUtilElementToBeClickable(travellersInsuranceButton);
        travellersInsuranceButton.click();

        // �������� �������� �������� "�����������"
        Assert.assertEquals("��������� �����������/�� ������������� ����������",
                "������ ������������� ��������� ��� ������ �� ������� � ��������", driver.getTitle());

        // ������ ������ "�������� ������"
        String checkoutOnlineXPath = "//*[text()='�������� ������']/../../a[@data-test-id]";
        WebElement checkoutOnlineButton = driver.findElement(By.xpath(checkoutOnlineXPath));
        waitUtilElementToBeClickable(checkoutOnlineButton);
        checkoutOnlineButton.click();

        // �������� �������� �������� "����������� ����������������"
        String pageTitleXPath = "//h2";
        waitUtilElementToBeVisible(By.xpath(pageTitleXPath));
        WebElement pageTitle = driver.findElement(By.xpath(pageTitleXPath));
        Assert.assertEquals("��������� �����������/�� ������������� ����������",
                "����������� ����������������", pageTitle.getText());

        // ������� ����� ����������� "�����������"
        String insuranceCoverageAmountXPath = "//h3[text()='�����������']";
        WebElement insuranceCoverageAmount = driver.findElement(By.xpath(insuranceCoverageAmountXPath));
        scrollToElementJs(insuranceCoverageAmount);
        waitUtilElementToBeClickable(insuranceCoverageAmount);
        insuranceCoverageAmount.click();

        // �������� �� ������ "��������"
        String checkoutButtonXPath = "//button[text()='��������']";
        WebElement checkoutButton = driver.findElement(By.xpath(checkoutButtonXPath));
        scrollToElementJs(checkoutButton);
        waitUtilElementToBeClickable(checkoutButton);
        checkoutButton.click();

        // ��������� ���� �������
        String fieldXPath = "//input[@id='%s']";
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "surname_vzr_ins_0"))), "�������������");
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "name_vzr_ins_0"))), "������");
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "birthDate_vzr_ins_0"))), "01.01.1990");
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "person_lastName"))), "������������");
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "person_firstName"))), "����");
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "person_middleName"))), "��������");
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "person_birthDate"))), "02.02.1992");
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "passportSeries"))), "4444");
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "passportNumber"))), "777666");
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "documentDate"))), "09.09.2009");
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "documentIssue"))), "���������� ����");

        // �������� �� ������ "����������"
        String continueButtonXPath = "//button[contains(text(),'����������')]";
        WebElement continueButton = driver.findElement(By.xpath(continueButtonXPath));
        scrollToElementJs(continueButton);
        waitUtilElementToBeClickable(continueButton);
        continueButton.click();

        // ��������� ��������� �� ������
        checkErrorMessageAtField(driver.findElement(By.xpath(String.format(fieldXPath, "phone"))), "���� �� ���������.");
        checkErrorMessageAtField(driver.findElement(By.xpath(String.format(fieldXPath, "email"))), "���� �� ���������.");
        checkErrorMessageAtField(driver.findElement(By.xpath(String.format(fieldXPath, "repeatEmail"))), "���� �� ���������.");

        // ��������� ��������� �� ������
        String errorAlertXPath = "//div[@class='alert-form alert-form-error']";
        WebElement errorAlert = driver.findElement(By.xpath(errorAlertXPath));
        scrollToElementJs(errorAlert);
        waitUtilElementToBeVisible(errorAlert);
        Assert.assertEquals("�������� ������ � alert �� �������� �� ���� ��������",
                "��� ���������� ������ ��������� ������", errorAlert.getText());
    }

    @After
    public void after(){
        driver.quit();
    }

    /**
     * ����� �� �������� �� js ����
     *
     * @param element - ��� ������� �� �������� ����� �����������
     */
    private void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * ����� �������� ���� ��� ������� ������ ������������
     *
     * @param element - ��� ������� �� �������� ����� �����������
     */
    private void waitUtilElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * ����� �������� ���� ��� ������� ������ �������
     *
     * @param locator - ������� �� ��� ������� ������� �� ������� ����� � ������� ����� �� ��������
     */
    private void waitUtilElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * ����� �������� ���� ��� ������� ������ �������
     *
     * @param element - ��� ������� ������� �� ������� ��� �����  ����� �� ��������
     */
    private void waitUtilElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * ���������� ����� ����������� ��������
     *
     * @param element - ��� ������� (���� �����-��) ������� ��������� ���������)
     * @param value - �������� ������ �� ��������� ��� ������� (���� �����-��)
     */
    private void fillInputField(WebElement element, String value) {
        scrollToElementJs(element);
        waitUtilElementToBeClickable(element);
        element.click();
        element.clear();
        element.sendKeys(value);
        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
        Assert.assertTrue("���� ���� ��������� �����������", checkFlag);
    }

    /**
     * �������� ������ ������ � ����������� ����
     *
     * @param element ��� ������� (���� �����-��) ������� �� ���������
     * @param errorMessage - ��������� ������ ��� ����� ������� �� �� ���������
     */
    private void checkErrorMessageAtField(WebElement element, String errorMessage) {
        element = element.findElement(By.xpath("./..//span"));
        Assert.assertEquals("�������� ������ � ���� �� ���� ��������",
                errorMessage, element.getText());
    }
}
