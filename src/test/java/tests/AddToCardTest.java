package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class AddToCardTest {
    private WebDriver driver;

    @Test
    //Choose how many different products you want to buy in the test:
    public void testMyStore() {
        testMyStoreBuy(5);
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    //Test content:
    public void testMyStoreBuy(int numberOfDifferentProductsToBuy) {
        Random random = new Random();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        BigDecimal totalCost = new BigDecimal(0);
        int totalQuantity = 0;
        driver.get("https://prod-kurs.coderslab.pl");

        for (int i = 0; i < numberOfDifferentProductsToBuy; i++) {
            //Creating a list of all products in the store
            driver.findElement(By.cssSelector(".all-product-link.float-xs-left.float-md-right.h4")).click();
            List<WebElement> products = driver.findElements(By.cssSelector(".thumbnail.product-thumbnail"));

            //Selecting a random product from the list of products
            int randomInt = random.nextInt(products.size());
            products.get(randomInt).click();

            //Checking if the selected product is available (is the button "add to cart" enabled?)
            WebElement buyButton = driver.findElement(By.xpath("//button[@class='btn btn-primary add-to-cart']"));
            if (buyButton.isEnabled()) {
                //purchase of a random quantity (from 1 to 10) of the product
                driver.findElement(By.id("quantity_wanted")).clear();
                int randomQuantity = random.nextInt(9) + 1;
                driver.findElement(By.id("quantity_wanted")).sendKeys(Integer.toString(randomQuantity));

                //counting the value of purchased product
                BigDecimal price = new BigDecimal(driver.findElement(By.xpath("//span[@itemprop='price']")).getAttribute("content"));
                BigDecimal productsCost = price.multiply(new BigDecimal(randomQuantity));
                String productName = driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();

                //displaying information about the selected product,  its quantity and total price
                System.out.println("Product name: " + productName + ", price: " + price + ", quantity: " + randomQuantity + ", cost: " + productsCost);

                //summing up the value and quantity of all products
                totalCost = totalCost.add(productsCost);
                totalQuantity += randomQuantity;

                //adding the product to cart
                buyButton.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Continue shopping']")));
                driver.findElement(By.xpath("//button[text()='Continue shopping']")).click();
            }
            driver.findElement(By.xpath("//span[text()='Home']")).click();
        }

        //getting information about purchases from the cart
        driver.findElement(By.cssSelector(".material-icons.shopping-cart")).click();
        String totalCart = driver.findElement(By.id("cart-subtotal-products")).getText();
        System.out.println("In cart we have: " + totalCart);

        //displaying information about real cost
        System.out.println("Total quantity of all products: " + totalQuantity + " items." + "\nTotal cost of all products: " + totalCost);

        //verification of the total quantity and price in the card
        Assert.assertTrue(totalCart.contains(totalCost.toString()));
        Assert.assertTrue(totalCart.contains(totalQuantity + " items"));
    }
}

