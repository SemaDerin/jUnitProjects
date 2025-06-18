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

    /*
Sayfayı aç
Kullanıcı adını yanlış gir
Şifreyi doğru gir
Submit et
Error mesajını doğrula
*/
    static WebDriver driver;
    @BeforeClass
    public static void beforeTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void negatifeTest(){
        //login olma
        driver.get("https://practicetestautomation.com/practice-test-login/");
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("ssss");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("Password123");

        //Submit etme
        WebElement submit = driver.findElement(By.id("submit"));
        submit.click();

        //Error Mesajını doğrula
        WebElement error = driver.findElement(By.id("error"));
        String errorMessage = error.getText();
        String expectedMessage = "Your username is invalid!";
        assertEquals("Message not true",errorMessage,expectedMessage);




    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

}
