package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPageMyStore {

    public SearchPageMyStore(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".thumbnail-container")
    private WebElement product;

    public String getFullProductName() {
        return product.getText().toLowerCase();
    }

    public void chooseProduct() {
        product.click();
    }
}
