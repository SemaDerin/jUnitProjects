import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MyTest {


    static WebDriver driver;

    @BeforeClass
    public static void beforeClass(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


    }

    @Test
    public void negativePasswordTest(){
        driver.get("https://practicetestautomation.com/practice-test-login/");
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("student");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("Password123ss");
        WebElement submit = driver.findElement(By.id("submit"));
        submit.click();

        WebElement error = driver.findElement(By.id("error"));
        String errorMessage = error.getText();
        String expectedText = "Your password is invalid!";

        assertEquals("Error mesage is not found",errorMessage,expectedText);



    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

}
