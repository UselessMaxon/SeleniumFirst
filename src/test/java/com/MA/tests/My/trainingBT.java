package com.MA.tests.My;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class trainingBT {

    String user1 = "Taraskina Valeriya";
    String user2 =  "Irina Filippova";
    String user3 =  "Sekretar Kompanii";
    String user4 =  "���������";
    String passUser = "testing";


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


        driver.get("http://training.appline.ru/user/login");
    }

    @Test
    public void testExample() {

        //��� �1: �����������.
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//form[contains(@id, 'login-form')]"))));
        driver.findElement(By.xpath("//input[contains(@name, 'username')]")).sendKeys(user2);
        driver.findElement(By.xpath("//input[contains(@name, 'password')]")).sendKeys(passUser);
        driver.findElement(By.xpath("//button[text()='�����']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[text()='������ �������� �������']"))));

        //��� �2: ������� � ������������.
        WebElement costsList = driver.findElement(By.xpath("//ul[contains(@class, 'main-menu')]/li/a/span[text()='�������']"));
        costsList.click();

        wait.until(ExpectedConditions.visibilityOf(costsList.findElement(By.xpath
                ("./ancestor::li//ul[@class='dropdown-menu menu_level_1']"))));
        driver.findElement(By.xpath("//span[text()='������������']")).click();

                    //��������� ����� ������� 25:10
        loading();

        //��� 3: ������.
        driver.findElement(By.xpath("//div[@class='filter-item oro-drop']/div[contains(text(), '������')]")).click();
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//div[contains(@class, 'ui-multiselect-menu ui-corner-all')]"))));
        driver.findElement(By.xpath
                ("//div[contains(@class, 'ui-multiselect-menu ui-corner-all')]//input[@type='search']")).
                sendKeys("������������ � ���");
        driver.findElement(By.xpath("//label[@title='������������ � ���']")).click();
        loading();

        String id = driver.findElement(By.xpath("//td[text()='�����']/parent::tr/td[contains(@class, 'name')]")).getText();
        driver.findElement(By.xpath("//div[@class='filter-item oro-drop']/div[contains(text(), '�����'")).click();
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//input[@name='value']")))).sendKeys(id, Keys.ENTER);
        loading();

        //��� �4: ������� � ������������.







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
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='loader-mask shown']"))));
    }





}
