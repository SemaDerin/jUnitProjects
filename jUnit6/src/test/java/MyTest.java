import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.xpath;

public class MyTest {

    static WebDriver driver;

    @BeforeClass
    public static void beforeTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void elementNotInteractableException() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement add1 = driver.findElement(By.id("add_btn"));
        add1.click();

        // Yeni input alanının görünmesini bekle
        WebElement newElement = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath("//*[@id=\"row2\"]/input")));
        newElement.sendKeys("Hamburger");

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(xpath("/html/body/div/div/section/section/div/div[3]/div/button[1]")));
        saveButton.click();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath("/html/body/div/div/section/section/div/div[4]")));
        String actualMessage = message.getText();
        String expectedMessage = "Row 2 was saved";
        assertEquals("Message not verifiy", actualMessage, expectedMessage);
    }
}
