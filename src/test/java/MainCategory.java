// MenCategoryTest.java
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

public class MainCategory extends BaseTest {

    @BeforeMethod
    public void setUp() {
        initializeDriver();
    }

    @Test
    public void MainCategory() throws InterruptedException {

            driver.get("https://cultsport.com/");

            driver.findElement(By.xpath("//img[@alt='womens-day-men']")).click();

            WebElement MTshirt = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Joggers")));
            wait.until(ExpectedConditions.visibilityOf(MTshirt));
            MTshirt.click();

            WebElement MTshirts = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '₹ 500 - ₹ 999')]")));
            Assert.assertTrue(MTshirts.isDisplayed(), "Men's Joggers element is displayed");

            driver.navigate().back();
            driver.navigate().back();

            Thread.sleep(2000);

            WebElement element = driver.findElement(By.xpath("//img[@alt='womens-day-women']"));
            element.click();

            WebElement Tshirt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'T-shirts')]")));
            Tshirt.click();

            WebElement WTshirt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '₹ 500 - ₹ 999')]")));
            Assert.assertTrue(WTshirt.isDisplayed(), "Women's T-Shirts element is displayed");


    }
}
