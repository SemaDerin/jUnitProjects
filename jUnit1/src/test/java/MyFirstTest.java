import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.Scanner;

public class MyFirstTest {
    static WebDriver driver;

    @BeforeClass
    public static void beforeTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void myFirstTest(){
        driver.get("https://www.facebook.com/");
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void mySecondTest(){
        driver.get("https://www.google.com/");
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    @AfterClass
    public static void finishTest(){
        driver.quit();
    }
}

