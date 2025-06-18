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
import static org.junit.Assert.assertTrue;

public class NewTest {

    /*
 Sayfayı aç
Kullanıcı adı gir
Şifre girSubmit butonuna tıkla
Yeni URL’nin doğru olduğunu doğrula
Sayfada "Congratulations" veya "successfully logged in" yazısını doğrula
"Log out" butonunun görünür olduğunu doğrula */

    static WebDriver driver;

    @BeforeClass
    public static void beforeTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


    }

    @Test
    public void loginTest(){

        driver.get("https://practicetestautomation.com/practice-test-login/");
        //Kullanıcı Adı ve Şifre Gir
        WebElement login = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        login.sendKeys("student");
        password.sendKeys("Password123");

        //Submit Butonuna Tıkla
        WebElement submit = driver.findElement(By.id("submit"));
        submit.click();

        // URL Doğrula
        String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
        String actualUrl = driver.getCurrentUrl();
        assertEquals("URL is incorrect", expectedUrl, actualUrl);

        // Sayfa yazısı doğrulama

        WebElement message = driver.findElement(By.xpath("//*[@id=\"loop-container\"]/div/article/div[2]/p[1]/strong"));
        String messageText = message.getText();
        assertTrue("Login success message not found", messageText.equals("Congratulations student. You successfully logged in!"));

        //Logut Buton Kontrolu
        WebElement logoutButton = driver.findElement(By.xpath("//*[@id=\"loop-container\"]/div/article/div[2]/div/div/div/a"));
        assertTrue("Log out button is not displayed", logoutButton.isDisplayed());



    }


    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
