package steps_definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyStoreAddressSteps {
    private WebDriver driver;
    private static final String EMAIL = "johnDoe@unknow.com";
    private static final String PASSWORD = "admin";
    private List<String> expectedAddress = new ArrayList<String>();

    @Given("User is logged into MyStore account")
    public void userIsLoggedIntoMyStoreAccount() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");
        driver.findElement(By.name("email")).sendKeys(EMAIL);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("submit-login")).click();
    }

    @When("User goes to Your addressesPage")
    public void userGoesToYourAddressesPage() {
        driver.findElement(By.cssSelector(".account")).click();
        driver.findElement(By.id("addresses-link")).click();
    }

    @And("User clicks \"Create new address\"")
    public void userClicks() {
        driver.findElement(By.xpath("//span[text()='Create new address']")).click();
    }

    @When("User fills form fields: (.*), (.*), (.*), (.*), (.*) and (.*)")
    public void userFillsFormFieldsAliasAddressCityZipPostalCodeCountryAndPhone(String alias, String address, String city, String postcode, String country, String phone) {
        // after every sendKeys, part of address that was given is added to list "expectedAddress"

        driver.findElement(By.name("alias")).sendKeys(alias);
        expectedAddress.add(alias);

        driver.findElement(By.name("address1")).sendKeys(address);
        expectedAddress.add(address);

        driver.findElement(By.name("city")).sendKeys(city);
        expectedAddress.add(city);

        driver.findElement(By.name("postcode")).sendKeys(postcode);
        expectedAddress.add(postcode);

        // select country from drop down
        Select selectCountry = new Select(driver.findElement(By.name("id_country")));
        selectCountry.selectByVisibleText(country);
        expectedAddress.add(country);

        driver.findElement(By.name("phone")).sendKeys(phone);
        expectedAddress.add(phone);
    }

    @And("User saves new address")
    public void userSavesNewAddress() {
        driver.findElement(By.cssSelector(".btn.btn-primary.float-xs-right")).click();
    }

    @Then("User can see {string}")
    public void userCanSee(String successText) {

        //get displayed information text
        WebElement addAddressInfo = driver.findElement(By.cssSelector(".alert.alert-success"));

        // check that displayed text equals to expected text
        assertEquals(successText, addAddressInfo.getText());

        //get last added address from user all addresses page
        List<WebElement> allAddresses = driver.findElements(By.cssSelector(".address-body"));
        String fullActualAddress = allAddresses.get(allAddresses.size() - 1).getText();

        // and print this address in terminal
        System.out.println("Actual last added address is:\n" + fullActualAddress);

        //or

        // get address found by given alias
//        String alias = expectedAddress.get(0);
//        String fullActualAddress = driver.findElement(By.xpath("//div[h4[text()='"+alias+"']]")).getText();
//        System.out.println("Actual last added address is:\n"+fullActualAddress);

        // check that displayed address contains all parts of address from "expectedAddress" list and print expected address in terminal
        System.out.println("\nExpected last added address is:");
        for (String partOfAddress : expectedAddress) {
            assertTrue(fullActualAddress.contains(partOfAddress));
            System.out.println(partOfAddress);
        }
    }

    @When("User clicks \"Delete\" below last added address")
    public void userClicksBelowLastAddedAddress() {
        //create list of all delete address button
        List<WebElement> delete = driver.findElements(By.xpath("//span[text()='Delete']"));

        // click on last added address delete button
        delete.get(delete.size() - 1).click();
    }

    @Then("Address is deleted and users can see {string}")
    public void addressIsDeletedAndUsersCanSee(String successDelete) {

        // get displayed text and check that is equals to expected text
        WebElement deleteAddressInfo = driver.findElement(By.cssSelector(".alert.alert-success"));
        assertEquals(successDelete, deleteAddressInfo.getText());

        // check that last address on the page do not contain alias from address that was deleted
        List<WebElement> allAddresses = driver.findElements(By.cssSelector(".address-body"));
        String lastAddress = allAddresses.get(allAddresses.size() - 1).getText();
        assertFalse(lastAddress.contains(expectedAddress.get(0)));
    }

    @And("User close browser")
    public void userCloseBrowser() {
        driver.quit();
    }
}