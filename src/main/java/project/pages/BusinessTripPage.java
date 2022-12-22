package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.BasePage;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class BusinessTripPage extends BasePage {

    @FindBy(xpath = "//h1[@class= 'oro-subtitle' and contains(text(), 'Командир')]")
    private WebElement nameThisPage;

    @FindBy(xpath = "//div[@class= 'btn-group']/descendant::a[@title = 'Создать командировку']")
    private WebElement createBtn;


    public void checkWhatPage() {
         wait.until(visibilityOf(nameThisPage));
    }

    public void createTripClick() {
        createBtn.click();
        loading();
    }

}
