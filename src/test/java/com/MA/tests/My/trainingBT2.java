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
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class trainingBT2 {

    String user1 = "Taraskina Valeriya";
    String user2 = "Irina Filippova";
    String user3 = "Sekretar Kompanii";
    String user4 = "Секретарь";
    String passUser = "testing";

    Date dateNow = new Date();
    DateFormat dateNorm = new SimpleDateFormat("dd.MM.yyyy");

    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10,1000);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void makeBusinessTrip() {

        //Шаг №1: Перейти на страницу http://training.appline.ru/user/login.
        driver.get("http://training.appline.ru/user/login");

        //Шаг №2: Пройти авторизацию.
        wait.until(visibilityOf(driver.findElement(By.xpath("//form[contains(@id, 'login-form')]"))));
        driver.findElement(By.xpath("//input[contains(@name, 'username')]")).sendKeys(user1);
        driver.findElement(By.xpath("//input[contains(@name, 'password')]")).sendKeys(passUser);
        driver.findElement(By.xpath("//button[text()='Войти']")).click();

        //Шаг №3: Проверить наличие на странице заголовка Панель быстрого запуска.
        wait.until(visibilityOf(driver.findElement(By.xpath("//h1[text()='Панель быстрого запуска']"))));

        //Шаг №4: В выплывающем окне раздела Расходы нажать на Командировки.
        WebElement costsList = driver.findElement(By.xpath("//ul[contains(@class, 'main-menu')]/li/a/span[text()='Расходы']"));
        costsList.click();
        wait.until(visibilityOf(costsList.findElement(By.xpath
                ("./ancestor::li//ul[@class='dropdown-menu menu_level_1']"))));
        driver.findElement(By.xpath("//span[text()='Командировки']")).click();
        loading();

        //Шаг №5: Нажать на "Создать командировку".
        wait.until(visibilityOf(
                driver.findElement(By.xpath("//h1[@class= 'oro-subtitle' and contains(text(), 'Командир')]"))));
        driver.findElement(By.xpath("//div[@class= 'btn-group']/descendant::a[@title = 'Создать командировку']")).click();
        loading();

        //Шаг №6: Проверить наличие на странице заголовка "Создать командировку".
        WebElement titleCreateTrip = driver.findElement(By.xpath("//h1[@class= 'user-name']"));
        wait.until(visibilityOf(titleCreateTrip));
        assertEquals("Создать командировку", titleCreateTrip.getText(), "Заголовок 'Создать командировку' не найден");

        //Шаг №7: На странице создания командировки заполнить или выбрать поля:...

        WebElement  departmentsChoice = driver.findElement(By.xpath("//select[contains(@name, 'businessUnit')]")); //Подразделение - выбрать Отдел внутренней разработки
        departmentsChoice.click();
        String departmentList = "//div[contains(@id, 'uniform-crm_business_trip') and contains (@class, 'focus')]";
        wait.until(visibilityOf(driver.findElement(
                By.xpath(departmentList))));
        driver.findElement(By.xpath(departmentList +"/select/option[text() ='Отдел внутренней разработки']")).click();

        driver.findElement(By.xpath("//a[@id='company-selector-show']")).click(); //Принимающая организация - нажать "Открыть список" и в поле "Укажите организацию" выбрать любое значение
        driver.findElement(By.xpath("//a/span[@class='select2-chosen']")).click();
        wait.until(visibilityOf(driver.findElement(
                By.xpath("//div[@class= 'select2-search']/input[ contains(@class, 'select2-focused')]"))));
        WebElement selectedHostOrg = driver.findElement(By.xpath("//div/ul/li/div[@class= 'select2-result-label']"));
        String stringSelectedHostOrg = selectedHostOrg.getText();
        selectedHostOrg.click();

        WebElement CheckBOrderTick =  driver.findElement(   // В задачах поставить чекбокс на "Заказ билетов"
                By.xpath("//label[text()='Заказ билетов']/preceding-sibling::input[@type= 'checkbox']"));
        CheckBOrderTick.click();

        WebElement fieldDepCity = driver.findElement(By.xpath("//input[contains(@data-name, 'departure-city')]")); // Указать город выбытия
        fieldDepCity.clear();
        fieldDepCity.sendKeys("Ижевск");

        WebElement fieldArrivCity = driver.findElement(By.xpath("//input[contains(@data-name, 'arrival-city')]")); // Указать город прибытия
        fieldArrivCity.sendKeys("Пермь");

        WebElement datePlan = driver.findElement(By.xpath("//input[contains(@id, 'departureDatePlan') and contains(@class, 'input')]")); // Указать дату выезда
        datePlan.click();
        driver.findElement(By.xpath("//button[@data-handler='today']")).click();
        wait.until(invisibilityOf(driver.findElement(
                By.xpath("//div[@id='ui-datepicker-div' and contains(@style, 'none')]"))));

        WebElement dateReturn = driver.findElement(By.xpath("//input[contains(@id, 'returnDatePlan') and contains(@class, 'input')]")); // Указать дату возвращения
        dateReturn.sendKeys("01.01.2029");
        driver.findElement(By.xpath("//div[@id='oro-dropdown-mask']")).click(); // Клик для закрытия окна с выбором даты

        //Шаг: 8: Проверить, что все поля заполнены правильно.
        WebElement selectedDepartment = driver.findElement(
                By.xpath("//div[@class='selector input-widget-select' and contains(@id, 'businessUnit')]/span")); //Подразделение
        assertEquals("Отдел внутренней разработки", selectedDepartment.getText(), "Подразделение отображается некорректно");

        WebElement vieHostOrg = driver.findElement(By.xpath("//span[@class='select2-chosen']")); //Принимающая организация
        assertEquals(stringSelectedHostOrg, vieHostOrg.getText(), "Принимающая организация отображается некорректно");

        assertTrue(CheckBOrderTick.isSelected(), "Чекбокс 'Покупка билетов не выбран'"); //Проверка чекбокса

        assertEquals("Ижевск", fieldDepCity.getAttribute("value"), "Город выбытия введен не правильно"); // Проверка города выбытия

        assertEquals("Пермь", fieldArrivCity.getAttribute("value"), "Город прибытия введен не правильно"); // Проверка города прибытия

        assertEquals(dateNorm.format(dateNow), datePlan.getAttribute("value"), "Дата выезда введена не правильно"); // Проверка даты выезда

        assertEquals("01.01.2029", dateReturn.getAttribute("value"), "Дата приезда введена не правильно"); // Проверка даты приезда

        //Шаг 9: Нажать Сохранить и закрыть.
        driver.findElement(By.xpath("//button[@class='btn btn-success action-button']")).click();
        wait.until(invisibilityOf(driver.findElement(By.xpath("//input[@name='input_action' and contains(@value,'route')]"))));
        loading();

        //Шаг 10: Проверить, что на странице появилось сообщение: "Список командируемых сотрудников не может быть пустым".
        String PathError =
                "//span[@class='validation-failed' and text()='Список командируемых сотрудников не может быть пустым']" +
                "/ancestor::div/h5/span[text()='Командированные сотрудники']";
        wait.until(visibilityOf(driver.findElement(By.xpath(PathError))));

        System.out.println("Кейс №1 makeBusinessTrip 'Появление ошибки на незаполненного Командированного сотрудника' = Успешен");
    }


    @Test
    public void testExample() {

        //Шаг №1: Авторизация.
        driver.get("http://training.appline.ru/user/login");
        wait.until(visibilityOf(driver.findElement(By.xpath("//form[contains(@id, 'login-form')]"))));
        driver.findElement(By.xpath("//input[contains(@name, 'username')]")).sendKeys(user4);
        driver.findElement(By.xpath("//input[contains(@name, 'password')]")).sendKeys(passUser);
        driver.findElement(By.xpath("//button[text()='Войти']")).click();
        wait.until(visibilityOf(driver.findElement(By.xpath("//h1[text()='Панель быстрого запуска']"))));

        //Шаг №2: Перейти в командировки.
        WebElement costsList = driver.findElement(By.xpath("//ul[contains(@class, 'main-menu')]/li/a/span[text()='Расходы']"));
        costsList.click();

        wait.until(visibilityOf(costsList.findElement(By.xpath
                ("./ancestor::li//ul[@class='dropdown-menu menu_level_1']"))));
        driver.findElement(By.xpath("//span[text()='Командировки']")).click();

        //Проверить своим методом 25:10
        loading();

        //Шаг 3: Фильтр.
        driver.findElement(By.xpath("//div[@class='filter-item oro-drop']/div[contains(text(), 'Стадия')]")).click();
        wait.until(visibilityOf(
                driver.findElement(By.xpath("//div[contains(@class, 'ui-multiselect-menu ui-corner-all')]"))));
        driver.findElement(By.xpath
                        ("//div[contains(@class, 'ui-multiselect-menu ui-corner-all')]//input[@type='search']")).
                sendKeys("Согласование с ОСР");
        driver.findElement(By.xpath("//label[@title='Согласование с ОСР']")).click();
        loading();

        String id = driver.findElement(By.xpath("//td[text()='Питер']/parent::tr/td[contains(@class, 'name')]")).getText();
        driver.findElement(By.xpath("//div[@class='filter-item oro-drop']/div[contains(text(), 'Номер')]")).click();
        wait.until(visibilityOf(
                driver.findElement(By.xpath("//input[@name='value']")))).sendKeys(id, Keys.ENTER);
        loading();

        //Шаг №4: Переход в командировку.
        driver.findElement(By.xpath(String.format("//td[contains(@class, 'grid-body-cell-name')][text() = '%s']", id))).click();

        WebElement actualId = driver.findElement(By.xpath("//h1[@class= 'user-name']"));
        wait.until(visibilityOf(actualId));
        assertEquals(id, actualId.getText(), "Мы попали на неверную страницу");

        //Шаг 5: Отменить.
        driver.findElement(By.xpath("//a[@data-transition-label='Отменить']")).click();
        loading();
        wait.until(visibilityOf(driver.findElement(By.xpath("//div[@role='dialog']"))));
        driver.findElement(By.xpath("//div[@role='dialog']//button[@type='submit']")).click();

        String massage = "Не удалось выполнить переход";
        Assertions.assertTrue(driver.findElement(By.xpath("//div[@class = 'flash-message-frame']")).isDisplayed(),
                "Нотификация не отобразилась");
        assertEquals(massage, driver.findElement(By.xpath("//div[@class='message']")).getText(),
                String.format("Текст нотификации не совпадает. Ожидаемое значение [%s]", massage));


        System.out.println("Тест кейс testExample выполнен успешно");
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
