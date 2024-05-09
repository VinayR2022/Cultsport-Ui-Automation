
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class VerifyPDPs extends BaseTest {

    @BeforeMethod
    public void setUp() {
        initializeDriver();
    }


    @Test
    public void MenPDP() throws InterruptedException {
        driver.get("https://cultsport.com/mens-sportswear");
        scrollUntilElementClickable(By.xpath("//div[contains(@class, 'style-prefix-wlzv28') and contains(text(), 'cult')]")).click();
        WebElement brand = wait.until(ExpectedConditions.visibilityOfElementLocated((By.className("style-prefix-8ha1uc"))));
        Assert.assertTrue(brand.isDisplayed(), "Men's Brand element displayed");

        scrollUntilElementClickable(By.xpath(("//*[contains(text(), 'Select Size')]")));

        clickOnFirstAvailableSize();

        clickOnDeliveryAvailability("560060");

        scrollUntilElementClickable(By.xpath(("//*[contains(text(), 'Technical Information')]")));

        WebElement addToCartButton = driver.findElement(By.className("style-prefix-br5nx7"));
        addToCartButton.click();
        WebElement goToCartButton = driver.findElement(By.className("style-prefix-r6n4c1"));
        goToCartButton.click();

    }

    @Test
    public void WomenPDP() throws InterruptedException {
        try {
            driver.get("https://cultsport.com/womens-sportswear");
        scrollUntilElementClickable(By.xpath("//div[contains(@class, 'style-prefix-wlzv28') and contains(text(), 'cult')]")).click();
        WebElement brand = wait.until(ExpectedConditions.visibilityOfElementLocated((By.className("style-prefix-8ha1uc"))));
        Assert.assertTrue(brand.isDisplayed(), "Men's Brand element displayed");

        scrollUntilElementClickable(By.xpath(("//*[contains(text(), 'Select Size')]")));

        clickOnFirstAvailableSize();

        clickOnDeliveryAvailability("560060");


        scrollUntilElementClickable(By.xpath(("//*[contains(text(), 'Technical Information')]")));

        WebElement addToCartButton = driver.findElement(By.className("style-prefix-br5nx7"));
        addToCartButton.click();
        WebElement goToCartButton = driver.findElement(By.className("style-prefix-r6n4c1"));
        goToCartButton.click();
    } catch (Exception e) {
        captureScreenshot("guestCheckoutTest");
        System.out.println("Exception occurred  Women PDP category: " + e.getMessage());
        Assert.fail("Test failed: " + e.getMessage());
    }

    }

    @Test
    public void FootwearPDP() throws InterruptedException {
       try {
           driver.get("https://cultsport.com/footwear");
        WebElement targetElement = scrollUntilElementClickable(By.xpath("//div[contains(@class, 'style-prefix-wlzv28') and contains(text(), 'cult')]"));
        if (targetElement != null) {
            // Click using JavaScript to bypass potential click interceptors
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", targetElement);
        } else {
            // Handle if target element is not found after scrolling
            System.out.println("Target element not found after scrolling.");
        }
        WebElement brand = wait.until(ExpectedConditions.visibilityOfElementLocated((By.className("style-prefix-8ha1uc"))));
        Assert.assertTrue(brand.isDisplayed(), "Men's Brand element displayed");

        scrollUntilElementClickable(By.xpath(("//*[contains(text(), 'Select Size')]")));

        clickOnFirstAvailableSize();

        clickOnDeliveryAvailability("560060");


        scrollUntilElementClickable(By.xpath(("//*[contains(text(), 'Technical Information')]")));

        WebElement addToCartButton = driver.findElement(By.className("style-prefix-br5nx7"));
        addToCartButton.click();
        WebElement goToCartButton = driver.findElement(By.className("style-prefix-r6n4c1"));
        goToCartButton.click();
    } catch (Exception e) {
        captureScreenshot("guestCheckoutTest");
        System.out.println("Exception occurred Men PDP category: " + e.getMessage());
        Assert.fail("Test failed: " + e.getMessage());
    }

    }

    @Test
    public void CardioPDP() {
        try {
            driver.get("https://cultsport.com/treadmills");

        scrollUntilElementClickable(By.xpath("//div[contains(@class, 'style-prefix-1ea0jd6') and contains(text(), 'cult')]")).click();

        WebElement brand = wait.until(ExpectedConditions.visibilityOfElementLocated((By.className("style-prefix-1l5g5m3"))));
        Assert.assertTrue(brand.isDisplayed(), "Cardio Brand element displayed");

        scrollUntilElementClickable(By.xpath("//*[contains(text(), 'Check delivery availability')]"));


        scrollUntilElementClickable(By.xpath(("//*[contains(text(), 'Specifications')]")));
        handlePopup();
    } catch (Exception e) {
        captureScreenshot("guestCheckoutTest");
        System.out.println("Exception occurred Cardio PDP category: " + e.getMessage());
        Assert.fail("Test failed: " + e.getMessage());
    }


    }

    @Test
    public void CyclePDP() throws InterruptedException {
        try {
            driver.get("https://cultsport.com/cycles");
        WebElement targetElement = scrollUntilElementClickable(By.xpath("//div[contains(@class, 'style-prefix-1mchdjx') or contains(@class, 'style-prefix-1ea0jd6') and contains(text(), 'cult')]"));
        if (targetElement != null) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", targetElement);
        } else {
            System.out.println("Target element not found after scrolling.");
        }
        Thread.sleep(2000);
        WebElement brand = scrollUntilElementClickable((By.className("style-prefix-1l5g5m3")));
        Assert.assertTrue(brand.isDisplayed(), "Cycle's Brand element displayed");

        clickOnDeliveryAvailability("560060");

        Thread.sleep(2000);

        scrollUntilElementClickable(By.xpath(("//*[contains(text(), 'Specifications')]")));
        WebElement Specs = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(("//*[contains(@class, 'style-prefix-1aas2g5')]")))));
        Assert.assertTrue(Specs.isDisplayed(), "Cycles's Specs element displayed");

        WebElement addToCartButton = driver.findElement(By.className("style-prefix-1gdrvzp"));
        addToCartButton.click();
    } catch (Exception e) {
        captureScreenshot("guestCheckoutTest");
        System.out.println("Exception occurred Cycle PDP category: " + e.getMessage());
        Assert.fail("Test failed: " + e.getMessage());
    }
    }


}
