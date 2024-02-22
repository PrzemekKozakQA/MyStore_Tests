package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageMyStore {

    public LoginPageMyStore(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(id = "submit-login")
    private WebElement signInButton;

    public void loginAs(String userEmail, String userPassword) {
        emailInput.click();
        emailInput.sendKeys(userEmail);

        passwordInput.click();
        passwordInput.sendKeys(userPassword);

        signInButton.click();
    }
}
