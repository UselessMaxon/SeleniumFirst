package project.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.BasePage;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class MainPage extends BasePage {

    @FindBy(xpath = "//ul[contains(@class, 'main-menu')]/li/a/span[text()='Расходы']")
    private WebElement costsBtn;

    @FindBy(xpath = "./ancestor::li//ul[@class='dropdown-menu menu_level_1']")
    private WebElement dropDownList;

    @FindBy(xpath = "//span[text()='Командировки']")
    private WebElement assignmentBtn;

    public void costsClick() {
        costsBtn.click();
    }

    public void assignmentClick() {
        wait.until(visibilityOf(dropDownList));
        assignmentBtn.click();
        loading();
    }
}
