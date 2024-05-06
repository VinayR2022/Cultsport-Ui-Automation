import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Checkout extends BaseTest {

    @BeforeMethod
    public void setUp() {
        initializeDriver();
    }

    @Test
    public void GuestCheckout() throws InterruptedException {
        driver.get("https://cultsport.com/mens-sportswear");
        scrollUntilElementClickable(By.xpath("//div[contains(@class, 'style-prefix-wlzv28') and contains(text(), 'cult')]")).click();

        scrollUntilElementClickable(By.xpath(("//*[contains(text(), 'Select Size')]")));

        clickOnFirstAvailableSize();

        WebElement addToCartButton = driver.findElement(By.className("style-prefix-br5nx7"));
        addToCartButton.click();
        WebElement goToCartButton = driver.findElement(By.className("style-prefix-r6n4c1"));
        goToCartButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[contains(text(), 'Existing User?')]")))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[contains(text(), 'Use another email')]")))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("input[class='style-prefix-1l39b9m'][placeholder='Enter email address']")))).sendKeys("culttest@curefit.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[contains(text(), 'CONTINUE')]")))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.className("style-prefix-18e1pgr")))).sendKeys("132465");



    }
}
