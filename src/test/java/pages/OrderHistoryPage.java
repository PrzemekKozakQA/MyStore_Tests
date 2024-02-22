package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryPage {

    public OrderHistoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".label.label-pill.bright")
    private WebElement statusText;

    @FindBy(css = ".text-xs-right")
    private WebElement orderPrice;

    @FindBy (xpath = "//tr//th[@scope='row']")
    private WebElement orderReference;

    public String getStatusText() {
        return statusText.getText();
    }

    public String getOrderPrice() {
        return orderPrice.getText();
    }

    public String getOrderReferences(){
        return orderReference.getText();
    }

}
