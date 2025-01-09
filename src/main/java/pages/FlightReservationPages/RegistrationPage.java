package pages.FlightReservationPages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends AbstractPage {


    public RegistrationPage(WebDriver driver){
        super(driver);
    }


    @FindBy(id ="firstName")
    private WebElement firstName;

    @FindBy(id ="lastName")
    private WebElement lastName;

    @FindBy(id ="email")
    private WebElement email;

    @FindBy(id ="password")
    private WebElement password;

    @FindBy(name ="street")
    private WebElement street;

    @FindBy(id ="inputState")
    private WebElement inputState;

    @FindBy(name ="zip")
    private WebElement zip;

    @FindBy(id ="register-btn")
    private WebElement register;

    public void setuserInfo(String firstNameVal,String lastNameVal){
        this.firstName.sendKeys(firstNameVal);
        this.lastName.sendKeys(lastNameVal);

    }

    public void setAddress(String streetVal,String stateVal,String zipVal){
        this.street.sendKeys(streetVal);
        Select select = new Select(this.inputState);
        select.selectByVisibleText(stateVal);
        this.zip.sendKeys(zipVal);
    }

    public void clickRegister(){
        try{
            moveToElementAndClick(this.register);
        }catch (ElementClickInterceptedException e){
            moveToElementAndClick(this.register);

        }

    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(this.firstName));
        return this.firstName.isDisplayed();

    }

}
