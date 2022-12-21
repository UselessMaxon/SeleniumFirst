package project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

public class BasePage {

    @FindBy(xpath = "//div[@class='loader-mask shown']")
    private WebElement loadingIcon;

    protected static WebDriver driver = DriverManager.getWebDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    public void loading() {
        wait.until(invisibilityOf(loadingIcon));
    }
}
