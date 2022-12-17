package com.MA.tests.My;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class trainingBT {

    String user1 = "Taraskina Valeriya";
    String user2 =  "Irina Filippova";
    String user3 =  "Sekretar Kompanii";
    String user4 =  "Секретарь";
    String passUser = "testing";

    private WebDriver driver;


    @BeforeEach
    public void before() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://training.appline.ru/user/login");
    }

    @Test
    public void test() {

        //Шаг №1. Авторизация.
        driver.findElement(By.xpath("//input[contains(@name, 'username')]")).sendKeys(user2);
        driver.findElement(By.xpath("//input[contains(@name, 'password')]")).sendKeys(passUser);
        driver.findElement(By.xpath("//button[text()='Войти']")).click();



    }

    @AfterEach
    public void after() {
    }


}
