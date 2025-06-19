import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
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

public class MyTest {

    static WebDriver driver;

    @BeforeClass
    public static void beforeTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void staleElementReferenceException(){

        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        WebElement addButton = driver.findElement(By.id("add_btn"));
        addButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        WebElement textMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
        assertEquals("Row 2 was added", textMessage.getText());
        assertEquals("Message should be displayed", true, textMessage.isDisplayed());

        boolean isMessageGone = wait
                .withTimeout(Duration.ofSeconds(15))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("confirmation")));

        assertEquals("Message should disappear after 3 seconds", true, isMessageGone);









    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
