// BaseTest.java
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import org.apache.commons.io.FileUtils;


public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private URL url;

    protected void initializeDriver() {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "/Users/vinay/Documents/CultsportMweb/chromedriver");

        // Create ChromeOptions instance
        ChromeOptions options = new ChromeOptions();

        // Set platform name and Android device details
        options.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        options.setCapability(MobileCapabilityType.DEVICE_NAME, "N000TA1183962301141");
        options.setCapability(MobileCapabilityType.BROWSER_VERSION, "106");

        // Set Appium server address
        try {
            url = new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Initialize AndroidDriver instance
        driver = new AndroidDriver(url, options);

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    // Method to scroll down the page until the element is clickable
    protected WebElement scrollUntilElementClickable(By locator) {
        WebElement element = null;
        int maxScrollAttempts = 10;
        int scrollAttempts = 0;

        while (element == null && scrollAttempts < maxScrollAttempts) {
            // Scroll down the page
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
            scrollAttempts++;

            // Check if the element is clickable
            try {
                element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            } catch (Exception e) {
                // Element not found yet, continue scrolling
            }
        }
        return element;
    }
    protected void handlePopup() {
        // Check if the popup is present
        try {
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-class")));
            if (popup.isDisplayed()) {
                // Close the popup
                WebElement closeButton = popup.findElement(By.className("close-button-class"));
                closeButton.click();
            }
        } catch (NoSuchElementException | TimeoutException e) {
            // Popup not found or not displayed, continue with the test
        }
    }

    protected WebElement clickOnDeliveryAvailability(String pincode) throws InterruptedException {
        scrollUntilElementClickable(By.xpath("//*[contains(text(), 'Check delivery availability')]"));
        WebElement pincodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("style-prefix-ux5z5z")));
        pincodeInput.click();
        pincodeInput.clear();
        pincodeInput.sendKeys(pincode);
        WebElement pincodeCheck = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Check delivery availability')]")));
        Thread.sleep(2000);
        pincodeCheck.click();
        pincodeCheck.click();
        return pincodeCheck;
    }

    protected WebElement clickOnFirstAvailableSize() {
        WebElement sizeContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("style-prefix-d97jto")));
        List<WebElement> sizeElements = sizeContainer.findElements(By.className("style-prefix-4eq6st"));
        for (WebElement sizeElement : sizeElements) {
            if (!sizeElement.getAttribute("class").contains("out-of-stock")) {
                sizeElement.click();
                return sizeElement;
            }
        }
        return null;
    }
