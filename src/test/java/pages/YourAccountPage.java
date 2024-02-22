package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YourAccountPage {

    public YourAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "_desktop_logo")
    private WebElement desktopButton;

    @FindBy(id = "history-link")
    private WebElement historyButton;

    public void clickDesktopButton() {
        desktopButton.click();
    }

    public void clickHistoryButton() {
        historyButton.click();
    }
}
