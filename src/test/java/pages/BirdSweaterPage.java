package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BirdSweaterPage {

    public BirdSweaterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".regular-price")
    private WebElement regularPrice;

    @FindBy(css = ".current-price-value")
    private WebElement newPrice;

    @FindBy(id = "group_1")
    private WebElement sizeDropDown;

    @FindBy(id = "quantity_wanted")
    private WebElement quantityInput;

    @FindBy(xpath = "//button[@class='btn btn-primary add-to-cart']")
    private WebElement addToCardButton;

    @FindBy(xpath = "//a[text()='Proceed to checkout']")
    private WebElement checkoutButton;

    public double getRegularPrice() {
        return Double.parseDouble(regularPrice.getText().replaceAll("[^0-9.]", ""));
    }

    public double getNewPrice() {
        return Double.parseDouble(newPrice.getText().replaceAll("[^0-9.]", ""));
    }

    public BirdSweaterPage setSize(String size) {
        Select dropDownSize = new Select(sizeDropDown);
        dropDownSize.selectByVisibleText(size);
        return this;
    }

    public void setQuantity(String quantity) {
        quantityInput.sendKeys(Keys.LEFT_CONTROL + "A");
        quantityInput.sendKeys(quantity);
    }

    public void clickAddToCard() {
        addToCardButton.click();
    }

    public WebElement getCheckoutButton() {
        return checkoutButton;
    }

    public void clickCheckoutButton() {
        checkoutButton.click();
    }
}
