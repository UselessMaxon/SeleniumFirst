package com.MA.tests.Sber;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SberBank {


    @Test
    public void startChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();


    }

    @Test
    public void test() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.sberbank.ru/ru/person");

        //сертификат сбера истек, тыкнуть рыуками
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement baseMenu = driver.findElement(By.xpath("//a[@aria-label='Страхование' and contains(@role, 'button')]"));
        baseMenu.click();

        WebElement subMenu = driver.findElement(By.xpath("//a[contains(@data-cga_click_top_menu, 'Страхование_Путешествия')]"));
        subMenu.click();

        WebElement titleTraverPage = driver.findElement(By.xpath("//h1[@data-test-id='PageTeaserDict_header']"));
        Assert.assertTrue("Страница \"путешествия\", не загрузилась", titleTraverPage.isDisplayed());

        String errorMessage = "Текст заголовка не совпал\n" +
                "Ожидали: Страхование путешественников\n" +
                "Фактическое: " + titleTraverPage.getText();
        Assert.assertEquals(errorMessage, "Страхование путешественников", titleTraverPage.getText());





        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();





    }





}
