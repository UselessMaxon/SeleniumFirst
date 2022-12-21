package project.steps;

import project.pages.MainPage;

public class MainPageSteps {

    public void filterByAssignment() {
        MainPage mainPage = new MainPage();
        mainPage.costsClick();
        mainPage.assignmentClick();
    }
}
