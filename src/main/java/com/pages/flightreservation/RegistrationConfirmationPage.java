package com.pages.flightreservation;

import com.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage {

    @FindBy(id = "go-to-flights-search")
    private WebElement goTOFlightsSearchButton;

    public RegistrationConfirmationPage(WebDriver driver){
       super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.goTOFlightsSearchButton));
        return this.goTOFlightsSearchButton.isDisplayed();
    }

    public void goTOFlightsSearch(){
        this.goTOFlightsSearchButton.click();
    }
}
