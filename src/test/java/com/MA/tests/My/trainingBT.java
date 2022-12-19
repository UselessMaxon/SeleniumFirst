package com.MA.tests.My;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

public class trainingBT {

    String user1 = "Taraskina Valeriya";
    String user2 = "Irina Filippova";
    String user3 = "Sekretar Kompanii";
    String user4 = "���������";
    String passUser = "testing";

    Date dateNow = new Date();
    DateFormat dateNorm = new SimpleDateFormat("dd.MM.yyyy");

    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10, 1000);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void makeBusinessTrip() {

        //��� �1: ������� �� �������� http://training.appline.ru/user/login.
        driver.get("http://training.appline.ru/user/login");

        //��� �2: ������ �����������.
        wait.until(visibilityOf(driver.findElement(By.xpath("//form[contains(@id, 'login-form')]"))));
        driver.findElement(By.xpath("//input[contains(@name, 'username')]")).sendKeys(user1);
        driver.findElement(By.xpath("//input[contains(@name, 'password')]")).sendKeys(passUser);
        driver.findElement(By.xpath("//button[text()='�����']")).click();

        //��� �3: ��������� ������� �� �������� ��������� ������ �������� �������.
        wait.until(visibilityOf(driver.findElement(By.xpath("//h1[text()='������ �������� �������']"))));

        //��� �4: � ����������� ���� ������� ������� ������ �� ������������.
        WebElement costsList = driver.findElement(By.xpath("//ul[contains(@class, 'main-menu')]/li/a/span[text()='�������']"));
        costsList.click();
        wait.until(visibilityOf(costsList.findElement(By.xpath
                ("./ancestor::li//ul[@class='dropdown-menu menu_level_1']"))));
        driver.findElement(By.xpath("//span[text()='������������']")).click();
        loading();

        //��� �5: ������ �� "������� ������������".
        wait.until(visibilityOf(
                driver.findElement(By.xpath("//h1[@class= 'oro-subtitle' and contains(text(), '��������')]"))));
        driver.findElement(By.xpath("//div[@class= 'btn-group']/descendant::a[@title = '������� ������������']")).click();
        loading();

        //��� �6: ��������� ������� �� �������� ��������� "������� ������������".
        WebElement titleCreateTrip = driver.findElement(By.xpath("//h1[@class= 'user-name']"));
        wait.until(visibilityOf(titleCreateTrip));
        assertEquals("������� ������������", titleCreateTrip.getText(), "��������� '������� ������������' �� ������");

        //��� �7: �� �������� �������� ������������ ��������� ��� ������� ����:...

        WebElement  departmentsChoice = driver.findElement(By.xpath("//select[contains(@name, 'businessUnit')]")); //������������� - ������� ����� ���������� ����������
        departmentsChoice.click();
        String departmentList = "//div[contains(@id, 'uniform-crm_business_trip') and contains (@class, 'focus')]";
        wait.until(visibilityOf(driver.findElement(
                By.xpath(departmentList))));
        driver.findElement(By.xpath(departmentList +"/select/option[text() ='����� ���������� ����������']")).click();

        driver.findElement(By.xpath("//a[@id='company-selector-show']")).click(); //����������� ����������� - ������ "������� ������" � � ���� "������� �����������" ������� ����� ��������
        driver.findElement(By.xpath("//a/span[@class='select2-chosen']")).click();
        wait.until(visibilityOf(driver.findElement(
                By.xpath("//div[@class= 'select2-search']/input[ contains(@class, 'select2-focused')]"))));
        WebElement selectedHostOrg = driver.findElement(By.xpath("//div/ul/li/div[@class= 'select2-result-label']"));
        String stringSelectedHostOrg = selectedHostOrg.getText();
        selectedHostOrg.click();

        WebElement CheckBOrderTick =  driver.findElement(   // � ������� ��������� ������� �� "����� �������"
                By.xpath("//label[text()='����� �������']/preceding-sibling::input[@type= 'checkbox']"));
        CheckBOrderTick.click();

        WebElement fieldDepCity = driver.findElement(By.xpath("//input[contains(@data-name, 'departure-city')]")); // ������� ����� �������
        fieldDepCity.clear();
        fieldDepCity.sendKeys("������");

        WebElement fieldArrivCity = driver.findElement(By.xpath("//input[contains(@data-name, 'arrival-city')]")); // ������� ����� ��������
        fieldArrivCity.sendKeys("�����");

        WebElement datePlan = driver.findElement(By.xpath("//input[contains(@id, 'departureDatePlan') and contains(@class, 'input')]")); // ������� ���� ������
        datePlan.click();
        driver.findElement(By.xpath("//button[@data-handler='today']")).click();
        wait.until(invisibilityOf(driver.findElement(
                By.xpath("//div[@id='ui-datepicker-div' and contains(@style, 'none')]"))));

        WebElement dateReturn = driver.findElement(By.xpath("//input[contains(@id, 'returnDatePlan') and contains(@class, 'input')]")); // ������� ���� �����������
        dateReturn.sendKeys("01.01.2029");
        driver.findElement(By.xpath("//div[@id='oro-dropdown-mask']")).click(); // ���� ��� �������� ���� � ������� ����

        //���: 8: ���������, ��� ��� ���� ��������� ���������.
        WebElement selectedDepartment = driver.findElement(
                By.xpath("//div[@class='selector input-widget-select' and contains(@id, 'businessUnit')]/span")); //�������������
        assertEquals("����� ���������� ����������", selectedDepartment.getText(), "������������� ������������ �����������");

        WebElement vieHostOrg = driver.findElement(By.xpath("//span[@class='select2-chosen']")); //����������� �����������
        assertEquals(stringSelectedHostOrg, vieHostOrg.getText(), "����������� ����������� ������������ �����������");

        assertTrue(CheckBOrderTick.isSelected(), "������� '������� ������� �� ������'"); //�������� ��������

        assertEquals("������", fieldDepCity.getAttribute("value"), "����� ������� ������ �� ���������"); // �������� ������ �������

        assertEquals("�����", fieldArrivCity.getAttribute("value"), "����� �������� ������ �� ���������"); // �������� ������ ��������

        assertEquals(dateNorm.format(dateNow), datePlan.getAttribute("value"), "���� ������ ������� �� ���������"); // �������� ���� ������

        assertEquals("01.01.2029", dateReturn.getAttribute("value"), "���� ������� ������� �� ���������"); // �������� ���� �������

        //��� 9: ������ ��������� � �������.
        driver.findElement(By.xpath("//button[@class='btn btn-success action-button']")).click();
        wait.until(invisibilityOf(driver.findElement(By.xpath("//input[@name='input_action' and contains(@value,'route')]"))));
        loading();

        //��� 10: ���������, ��� �� �������� ��������� ���������: "������ ������������� ����������� �� ����� ���� ������".
        String PathError =
                "//span[@class='validation-failed' and text()='������ ������������� ����������� �� ����� ���� ������']" +
                "/ancestor::div/h5/span[text()='��������������� ����������']";
        wait.until(visibilityOf(driver.findElement(By.xpath(PathError))));

        System.out.println("���� �1 makeBusinessTrip '��������� ������ �� �������������� ���������������� ����������' = �������");
    }

    @AfterEach
    public void after() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }

    public void loading() {
        wait.until(invisibilityOf(driver.findElement(By.xpath("//div[@class='loader-mask shown']"))));
    }

}
