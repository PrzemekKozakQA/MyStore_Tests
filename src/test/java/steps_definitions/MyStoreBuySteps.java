package steps_definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import javax.imageio.ImageIO;

import static org.junit.Assert.*;

public class MyStoreBuySteps {
    private WebDriver driver;
    private static final String EMAIL = "johnDoe@unknow.com";
    private static final String PASSWORD = "admin";
    private String totalPrice;
    private String orderDetails;

    private YourAccountPage yourAccountPage;
    private BirdSweaterPage birdSweaterPage;
    private ControllerOrderPage controllerOrderPage;
    private ConfirmationPage confirmationPage;

    @Given("User is logged into MyStore")
    public void userIsLoggedIntoMyStore() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        //login to MyStore account
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication");
        LoginPageMyStore loginPageMyStore = new LoginPageMyStore(driver);
        loginPageMyStore.loginAs(EMAIL, PASSWORD);
    }

    @When("User chooses {string} to buy")
    public void userChoosesToBuy(String productName) {
        //go to main page
        yourAccountPage = new YourAccountPage(driver);
        yourAccountPage.clickDesktopButton();

        //search product by name in MyStore
        MainPageMyStore mainPageMyStore = new MainPageMyStore(driver);
        mainPageMyStore.searchProduct(productName);

        //if first searched product contains searched text, then click it
        SearchPageMyStore searchPageMyStore = new SearchPageMyStore(driver);
        if (searchPageMyStore.getFullProductName().contains(productName.toLowerCase()))
            searchPageMyStore.chooseProduct();
    }

    @Then("User can see that product has {int}% discount")
    public void userCanSeeThatProductHasDiscount(int discount) {
        birdSweaterPage = new BirdSweaterPage(driver);

        //print in terminal regular price of product
        System.out.println("Regular price is: " + birdSweaterPage.getRegularPrice());

        //count expected new price by percent of discount and print it in terminal
        BigDecimal expectedNewPrice = birdSweaterPage.getRegularPrice().multiply(BigDecimal.ONE.subtract(new BigDecimal(discount).movePointLeft(2)));
        System.out.println("Expected price is: " + expectedNewPrice);

        //print actual price from page and compare it to expected price
        System.out.println("Actual price is: " + birdSweaterPage.getNewPrice());
        assertTrue(expectedNewPrice.compareTo(birdSweaterPage.getNewPrice()) == 0);

    }

    @When("User chooses size {string} and quantity {string}")
    public void userChoosesSizeAndQuantity(String size, String quantity) {
        // set size of product
        birdSweaterPage.setSize(size);

        //set quantity of product
        birdSweaterPage.setQuantity(quantity);
    }

    @And("User adds product to card")
    public void userAddsProductToCard() {
        birdSweaterPage.clickAddToCard();
    }

    @And("User chooses proceed to checkout")
    public void userChoosesProceedToCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(birdSweaterPage.getCheckoutButton()));
        birdSweaterPage.clickCheckoutButton();

        ShoppingCardPage shoppingCardPage = new ShoppingCardPage(driver);
        shoppingCardPage.clickCheckoutButton();
    }

    @And("User confirms address")
    public void userConfirmAddress() {
        controllerOrderPage = new ControllerOrderPage(driver);
        controllerOrderPage.clickConfirmAddressButton();
    }

    @And("User chooses shipping method - \"pick up in store\"")
    public void userChoosesShippingMethod() {
        controllerOrderPage
                .clickPickupInStoreInput()
                .clickConfirmDeliveryButton();
    }

    @And("User chooses payment \"Pay by Check\"")
    public void userChoosesPayment() {
        controllerOrderPage.clickPayByCheckInput();
    }

    @And("User clicks \"Order with an obligation to pay\"")
    public void userClicks() {
        controllerOrderPage
                .clickConfirmTermsButton()
                .clickOrderButton();
    }

    @Then("User can see information {string}")
    public void userCanSeeInformation(String confirmation) throws IOException {
        confirmationPage = new ConfirmationPage(driver);

        //confirm that correct text is displayed
        assertTrue(confirmationPage.getConfText().contains(confirmation));

        //get total price of products from confirmation page
        totalPrice = confirmationPage.getTotalPrice();
        System.out.println("Total price is: " + totalPrice);

        //get order details include order references from page and print it in terminal
        orderDetails = confirmationPage.getOrderDetails();
        System.out.println(orderDetails);

        //take screenshot of part of the page - in first way
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("target/screenshot.png"));

        //take screenshot of whole page - in second way
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500)).takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(), "png", new File("target/screenshotAShot.png"));
    }

    @When("User goes to Order history and detail of last order")
    public void userGoesToOrderHistoryAndDetailOfLastOrder() {
        confirmationPage.goToAccount();
        yourAccountPage.clickHistoryButton();
    }

    @Then("User can see order status {string} and total price")
    public void userCanSeeOrderStatusAndTotalPrize(String orderStatus) {
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);

        //get order references from history paige and print it in terminal
        String orderReferences = orderHistoryPage.getOrderReferences();
        System.out.println("Order references in history is:" + orderReferences);

        //compare order references from confirmation page and from history
        assertTrue(orderDetails.contains(orderReferences));

        //confirm correct order status
        assertTrue(orderHistoryPage.getStatusText().contains(orderStatus));

        //get order price from history page and print it in terminal
        String orderHistoryPrice = orderHistoryPage.getOrderPrice();
        System.out.println("Price in orders history is:" + orderHistoryPrice);

        //compare order price from confirmation page and from history page
        assertTrue(totalPrice.contains(orderHistoryPrice));
    }

    @And("close browser")
    public void closeBrowser() {
        driver.quit();
    }
}