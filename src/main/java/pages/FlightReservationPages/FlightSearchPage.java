package pages.FlightReservationPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage {


    public FlightSearchPage(WebDriver driver){
        super(driver);
    }


    @FindBy(id ="passengers")
    private WebElement passengers;

    @FindBy(id ="search-flights")
    private WebElement searchflights;



    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(this.passengers));
        return this.passengers.isDisplayed();

    }

    public void setPassengers(String noOfPassengers){
        Select select = new Select(this.passengers);
        select.selectByVisibleText(noOfPassengers);
    }

    public void clickSearchFlights(){
        moveToElementAndClick(this.searchflights);
    }

}
