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
    public static void beforeClass(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void noSuchElementException(){


        String baseUrl = "https://practicetestautomation.com/practice-test-exceptions/";
        driver.get(baseUrl);

        WebElement addButton = driver.findElement(By.id("add_btn"));
        addButton.click();
        // Add işleminden sonra mesajın görünmesini bekle
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));



        WebElement message2 = driver.findElement(By.id("confirmation"));
        String expectedMessage = "Row 2 was added";
        String actualMessage = message2.getText();

        assertEquals("Error mesage is not found",expectedMessage,actualMessage);


    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

}
