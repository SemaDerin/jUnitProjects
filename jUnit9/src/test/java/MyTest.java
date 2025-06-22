import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class MyTest {
    static WebDriver driver;

    @BeforeClass
    public static void beforeTest(){
        WebDriverManager.chromedriver().setup();
        driver  = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


    }

    @Test
    public void compareTitle() {
        driver.get("https://google.com/");
        String expectedData = "Google";
        String actualData = driver.getTitle();
        Assert.assertEquals(expectedData, actualData);

    }

    @Test
    public void containsTest(){
        driver.get("https://facebook.com/");
        String expectedData = "Facebook";
        String actualData = driver.getTitle();
        Assert.assertTrue(actualData.contains(expectedData));


    }
    @AfterClass

    public static void tearDown(){
        driver.quit();
    }

}
