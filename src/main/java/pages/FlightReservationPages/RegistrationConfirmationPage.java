package pages.FlightReservationPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage {


    public RegistrationConfirmationPage(WebDriver driver){
        super(driver);
    }


    @FindBy(id ="go-to-flights-search")
    private WebElement flightSearch;



    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(this.flightSearch));
        return this.flightSearch.isDisplayed();

    }

    public void clickFlightSearch(){
        this.flightSearch.click();
    }

}
