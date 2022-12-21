package project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import project.properties.TestProperties;

import java.time.Duration;
import java.util.Properties;

import static project.properties.TestProperties.getInstance;

public class DriverManager {

    private static WebDriver driver;
    private static Properties properties = getInstance().getProperties();

    public static WebDriver getWebDriver() {
        if (driver ==null){
            initDriver();
        }
        return driver;
    }

    public static void initDriver() {
        driver = new ChromeDriver();
        System.setProperty(properties.getProperty("WEB_DRIVER"),properties.getProperty ("WEB_DRIVER_PATH"));
        driver.get(properties.getProperty("HOSTNAME"));
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().setScriptTimeout(Duration.ofMillis(500));
    }

    public static void closeDriver(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }


}
