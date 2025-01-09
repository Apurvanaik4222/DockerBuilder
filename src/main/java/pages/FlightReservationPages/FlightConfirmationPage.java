package pages.FlightReservationPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class FlightConfirmationPage extends AbstractPage {
private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);


    public FlightConfirmationPage(WebDriver driver){
        super(driver);
    }


    @FindBy(xpath ="(//div[@class='col'])[1]//p")
   private WebElement flightConfirmationId;

    @FindBy(xpath ="(//div[@class='col'])[5]//p")
    private WebElement totalPrice;


    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationId));
        return this.flightConfirmationId.isDisplayed();

    }

    public String getFlightConfirmationId(){
        String flightId = this.flightConfirmationId.getText();
        String price = this.totalPrice.getText();
        log.info("Flight Confirmation Id :{}",flightId);
        log.info("Total Price :{}",price);
        return price;
    }



}
