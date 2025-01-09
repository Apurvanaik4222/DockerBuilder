package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FlightReservationPages.*;

public class ReservationTest  extends BaseTest{


    @Test
    public void registration(){

        RegistrationPage registrationPage = new RegistrationPage(driver);
        driver.get("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.setuserInfo(map.get("firstName").toString(),map.get("lastName").toString());
        registrationPage.setAddress(map.get("street").toString(),map.get("state").toString(),map.get("zip").toString());
        registrationPage.clickRegister();
    }

    @Test(dependsOnMethods = "registration")
    public void registrationConfirmation(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        registrationConfirmationPage.clickFlightSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmation")
    public void flightSearch(){
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.setPassengers(map.get("noOfPassengers").toString());
        flightSearchPage.clickSearchFlights();

    }

    @Test(dependsOnMethods = "flightSearch")
    public void selectFlights(){
        SelectFlightPage selectFlightsPage = new SelectFlightPage(driver);
        selectFlightsPage.isAt();
        selectFlightsPage.selectFlight();
        selectFlightsPage.clickConfirmFlights();
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "selectFlights")
    public void reservationConfirmation(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getFlightConfirmationId(),map.get("expectedPrice").toString());

    }


}
