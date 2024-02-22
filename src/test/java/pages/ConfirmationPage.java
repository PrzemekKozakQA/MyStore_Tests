package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {

    public ConfirmationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".h1.card-title")
    private WebElement confirmationText;

    @FindBy(css = ".account")
    private WebElement accountButton;

    @FindBy(xpath = "//tr[td[span[text()='Total']]]")
    private WebElement totalPrice;

    @FindBy(id = "order-details")
    private WebElement orderDetails;

    public String getConfText() {
        return confirmationText.getText();
    }

    public void goToAccount() {
        accountButton.click();
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public String getOrderDetails() {
        return orderDetails.getText();
    }
}
