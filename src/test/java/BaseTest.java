// BaseTest.java
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


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

//    @AfterMethod
//    public void tearDown() {
//        // Close the browser
//        if (driver != null) {
//            driver.quit();
//        }
//    }


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

}
