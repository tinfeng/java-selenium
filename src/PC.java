import java.util.*;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.Set;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.*;

public class PC {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "D:\\JAVA\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 800));
        baseUrl = "http://139.159.241.7:9041";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        driver.get(baseUrl + "/index.html");
        driver.manage().addCookie(new Cookie("SESSION", "492c6f73-205c-4547-a281-6fd61be5aa44"));
        driver.navigate().refresh();
        
        driver.findElement(By.text("???????")).click();
        driver.findElement(By.text("?????งา?")).click();
        
        driver.switchTo().frame(1);
        
        driver.findElement(By.text("??????").click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div[2]/div[2]/div/input")).sendKeys("123321");
        driver.findElement(By.xpath(".//*[normalize-space(text()) and normalize-space(.)='??????'])[1]/following::button[1]")).click;
        
        driver.findElement(By.text("??????")).click();
        driver.findElement(By.xpath(".//*[normalize-space(text()) and normalize-space(.)='?????????'])[1]/following::button[1]")).click();
        
        driver.findElement(By.text("???????")).click();
        
    }

    @After
    public void tearDown() throws Exception {
        //driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
