package Tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BuySizeMTest {
    private WebDriver driver;
    String size = "L";
    String quantity = "11";

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testBuySizeM() {
        driver.get("https://prod-kurs.coderslab.pl/index.php?id_product=2&id_product_attribute=9&rewrite=brown-bear-printed-sweater&controller=product#/1-size-s");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement sizeDropDown = driver.findElement(By.id("group_1"));
        Select dropDownSize = new Select(sizeDropDown);
        dropDownSize.selectByVisibleText(size);

        wait.until(ExpectedConditions.urlContains("size-" + size.toLowerCase()));

        WebElement quantityInput = driver.findElement(By.id("quantity_wanted"));
        quantityInput.clear();
        quantityInput.sendKeys(quantity);

        WebElement addToCardButton = driver.findElement(By.xpath("//button[@class='btn btn-primary add-to-cart']"));
        addToCardButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Proceed to checkout']")));
        WebElement checkoutButton = driver.findElement(By.xpath("//a[text()='Proceed to checkout']"));

        checkoutButton.click();
    }
}
