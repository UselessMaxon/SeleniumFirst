package com.MA.tests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SberBank {


    @Test
    public void startChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.sberbank.ru/ru/person");
    }

    @Test
    public void test() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();



    }





}
