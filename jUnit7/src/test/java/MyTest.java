import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
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
    public static void beforeTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @Test
    public void invalidElementStateException(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        WebElement edit1 = driver.findElement(By.id("edit_btn"));
        edit1.click();
        WebElement writeText = wait.until(ExpectedConditions.elementToBeClickable(xpath("//*[@id=\"row1\"]/input")));
        writeText.clear();
        writeText.sendKeys("Hamburger");


        WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath("//*[@id=\"save_btn\"]")));
        save.click();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath("//*[@id=\"confirmation\"]")));
        String actualMessage = message.getText();
        String expectedMessage = "Row 1 was saved";

        assertEquals("Text is not true",actualMessage ,expectedMessage);

        String actualInputValue = writeText.getAttribute("value");
        assertEquals("Input value is incorrect", "Hamburger", actualInputValue);




    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }




}
