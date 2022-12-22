package project.steps;

import project.pages.BusinessTripPage;

public class BusinessTripSteps {

    public void createTrip() {
        BusinessTripPage businessTripPage = new BusinessTripPage();
        businessTripPage.checkWhatPage();
        businessTripPage.createTripClick();
    }

}
