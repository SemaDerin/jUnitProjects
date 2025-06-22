import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyTest {

    WebDriver driver;

    @BeforeAll
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @RepeatedTest(10)
    public void productCount(){
        String baseUrl = "https://www.trendyol.com/butik/liste/1/kadin";
        driver.get(baseUrl);

        WebElement button = driver.findElement(By.xpath("//*[@id=\"browsing-gw-homepage\"]/div/div[1]/div[1]/div/article[1]/div/div/div/a[4]"));
        button.click();

        WebElement result = driver.findElement(By.xpath("//*[@id=\"search-app\"]/div/div/div/div[2]/div[1]/div[1]/div/h2"));
        String actualData = result.getText();

        Assertions.assertTrue(actualData.contains("100.000+"));
    }

    @AfterAll
    public void tearDown(){
        driver.quit();
    }
}
