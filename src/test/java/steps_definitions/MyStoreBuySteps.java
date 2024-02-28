package steps_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.io.IOException;
import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyStoreBuySteps {
    private WebDriver driver;
    private static final String EMAIL = "johnDoe@unknow.com";
    private static final String PASSWORD = "admin";
    private String totalPrice;
    String orderDetails;

    private YourAccountPage yourAccountPage;
    private BirdSweaterPage birdSweaterPage;
    private ControllerOrderPage controllerOrderPage;
    private ConfirmationPage confirmationPage;
    private WebDriverWait wait;

    @Given("User is logged into MyStore")
    public void userIsLoggedIntoMyStore() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

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

        //if first searched product contains searched  text, then click it
        SearchPageMyStore searchPageMyStore = new SearchPageMyStore(driver);
        if (searchPageMyStore.getFullProductName().toLowerCase().contains(productName.toLowerCase()))
            searchPageMyStore.chooseProduct();
    }

    @Then("User can see that product has {int}% discount")
    public void userCanSeeThatProductHasDiscount(int discount) {
        birdSweaterPage = new BirdSweaterPage(driver);

        //print in terminal regular price of product
        System.out.println("Regular price is:" + birdSweaterPage.getRegularPrice());

        //count expected new price by percent of discount and print it in terminal
        double expectedNewPrice = birdSweaterPage.getRegularPrice() * (1 - discount * 0.01);
        System.out.println("Expected price is:" + expectedNewPrice);

        //print actual price from page and compare it to expected price
        System.out.println("Actual price is:" + birdSweaterPage.getNewPrice());
        assertEquals(expectedNewPrice, birdSweaterPage.getNewPrice(), 0.01);
    }

    @When("User chooses size {string} and quantity {string}")
    public void userChoosesSizeAndQuantity(String size, String quantity) {
        birdSweaterPage.setSize(size);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("size-s"));
        birdSweaterPage.setQuantity(quantity);
    }

    @And("User adds product to card")
    public void userAddsProductToCard() {
        birdSweaterPage.clickAddToCard();
    }

    @And("User chooses proceed to checkout")
    public void userChoosesProceedToCheckout() {

        birdSweaterPage.clickCheckoutButton();

        ShoppingCardPage shoppingCardPage = new ShoppingCardPage(driver);
        shoppingCardPage.clickCheckoutButton();
    }

    @And("User confirm address")
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
//        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(scrFile, new File("target\\ScreenShots\\screenshot.png"));

        //take screenshot of whole page - in second way
//        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500)).takeScreenshot(driver);
//        ImageIO.write(screenshot.getImage(), "png", new File("target\\ScreenShots\\screenshotAShot.png"));
    }

    @When("User goes to Order history and detail of last order")
    public void userGoesToOrderHistoryAndDetailOfLastOrder() {
        confirmationPage.goToAccount();
        yourAccountPage.clickHistoryButton();
    }

    @Then("User can see order status {string} and total prize")
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