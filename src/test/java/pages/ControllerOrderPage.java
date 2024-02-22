package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ControllerOrderPage {

    public ControllerOrderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "confirm-addresses")
    private WebElement confirmAddressButton;

    @FindBy(xpath = "//span[input[@id='delivery_option_1']]/span")
    private WebElement pickupInStoreInput;

    @FindBy(name = "confirmDeliveryOption")
    private WebElement confirmDeliveryButton;

    @FindBy(id = "payment-option-1")
    private WebElement payByCheckInput;

    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    private WebElement confirmTermsButton;

    @FindBy(css = ".btn.btn-primary.center-block")
    private WebElement orderButton;

    public void clickConfirmAddressButton() {
        confirmAddressButton.click();
    }

    public ControllerOrderPage clickPickupInStoreInput() {
        pickupInStoreInput.click();
        return this;
    }

    public void clickConfirmDeliveryButton() {
        if (!confirmDeliveryButton.isSelected()) {
            confirmDeliveryButton.click();
        }
    }

    public void clickPayByCheckInput() {
        payByCheckInput.click();
    }

    public ControllerOrderPage clickConfirmTermsButton() {
        confirmTermsButton.click();
        return this;
    }

    public void clickOrderButton() {
        orderButton.click();
    }
}
