
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Checkout extends BaseTest {

    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() {
        // Path to your CSV file
        String csvFile = "/Users/vinay/Documents/CultsportMweb/src/test/TestData/testData.csv";
        List<Object[]> testData = new ArrayList<>();

        // Read data from CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean headerSkipped = false;
            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; // Skip header row
                }
                String[] data = line.split(",");
                testData.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert list of arrays to array of arrays
        Object[][] testDataArray = new Object[testData.size()][];
        for (int i = 0; i < testData.size(); i++) {
            testDataArray[i] = testData.get(i);
        }
        return testDataArray;
    }



    @Test(dataProvider = "checkoutData")
    public void guestCheckoutTest(String email, String password) {
        try {
            driver.get("https://cultsport.com/mens-sportswear");
            scrollUntilElementClickable(By.xpath("//div[contains(@class, 'style-prefix-wlzv28') and contains(text(), 'cult')]")).click();
            scrollUntilElementClickable(By.xpath("//*[contains(text(), 'Select Size')]"));
            clickOnFirstAvailableSize();

            WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("style-prefix-br5nx7")));
            addToCartButton.click();

            WebElement goToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("style-prefix-r6n4c1")));
            goToCartButton.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Existing User?')]"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Use another email')]"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[class='style-prefix-1l39b9m'][placeholder='Enter email address']"))).sendKeys(email);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'CONTINUE')]"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("style-prefix-18e1pgr"))).sendKeys(password);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'button_text style-prefix-imjule')]"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'style-prefix-33rlv')]"))).isDisplayed();

            while (true) {
                List<WebElement> elements = driver.findElements(By.className("style-prefix-1lcp925"));
                if(elements.isEmpty()){
                    break;
                }
               wait.until(ExpectedConditions.elementToBeClickable(elements.get(0))).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'style-prefix-15j5lrk')]"))).click();
            }
        } catch (Exception e) {
            captureScreenshot("guestCheckoutTest");
            System.out.println("Exception occurred during guestCheckoutTest: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }


}
