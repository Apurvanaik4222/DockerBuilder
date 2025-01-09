package pages.FlightReservationPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SelectFlightPage extends AbstractPage {


    public SelectFlightPage(WebDriver driver){
        super(driver);
    }


    @FindBy(name ="departure-flight")
   private List<WebElement> departureFlight;

    @FindBy(name ="arrival-flight")
   private List<WebElement> arrivalFlight;

    @FindBy(id ="confirm-flights")
    private WebElement confirmFlights;



    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(this.confirmFlights));
        return this.confirmFlights.isDisplayed();

    }

    public void selectFlight(){

        int number = ThreadLocalRandom.current().nextInt(0,departureFlight.size());
        departureFlight.get(number).click();
        moveToElementAndClick(arrivalFlight.get(number));
    }

    public void clickConfirmFlights(){

        moveToElementAndClick(this.confirmFlights);
    }

}
