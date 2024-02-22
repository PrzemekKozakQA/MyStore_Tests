package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPageMyStore {
    public MainPageMyStore(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "s")
    private WebElement searchInput;

    public void searchProduct(String productName) {
        searchInput.click();
        searchInput.sendKeys(productName);
        searchInput.submit();
    }
}
