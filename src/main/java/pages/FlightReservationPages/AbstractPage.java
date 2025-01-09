package pages.FlightReservationPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions actions;

    public AbstractPage(WebDriver driver){
        this.driver=driver;
         this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver,this);
        this.actions =new Actions(driver);
    }

    public abstract boolean isAt() ;


    public void moveToElementAndClick(WebElement element){
        actions.moveToElement(element).click().build().perform();
    }
}
