import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.fail;

public class supplier {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        //配置并打开浏览器
        System.setProperty("webdriver.chrome.driver", "D:\\Documents\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        //设置浏览器大小
        driver.manage().window().setSize(new Dimension(1280, 800));
        //测试地址
//        baseUrl = "http://139.159.241.7:9041";    //华为云
        baseUrl = "http://139.159.152.223:9041";  //3.6
//        baseUrl = "https://store.caichufang.com";  //线上
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //进入首页
        driver.get(baseUrl + "/login.html");
        //添加cookie
        driver.manage().addCookie(new Cookie("SESSION", "3202b86b-c3b9-4938-bcad-925af2bf9759"));
        Thread.sleep(2000);
        //刷新页面进行登陆
        driver.get(baseUrl + "/index.html");
        //driver.navigate().refresh();
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        //进入订单列表
        driver.findElement(By.linkText("销售管理")).click();
        Thread.sleep(500);
        driver.findElement(By.linkText("订单列表")).click();
        Thread.sleep(2000);
        driver.switchTo().frame(1);     //切换frame

        //确认订单
        driver.findElement(By.linkText("确认订单")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("html/body/div[5]/div[2]/div/div[3]/button[1]")).click();

        //确认发货
        driver.findElement(By.linkText("确认发货")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div[2]/div[2]/div/input")).sendKeys("123321");
        Thread.sleep(500);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='快递单号'])[1]/following::button[1]")).click();

        //确认收货
        driver.findElement(By.linkText("确认收货")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"confirm-goods\"]/div[2]/div/div[3]/button[1]")).click();

        //申请退款
        driver.findElement(By.linkText("申请退款")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"applyRefund\"]/div[2]/div/div[2]/div[1]/div[3]/div[2]/textarea")).sendKeys("退款");
        String nub = driver.findElement(By.xpath("//*[@id=\"goodsList\"]/tr/td[3]")).getText();
        driver.findElement(By.xpath("//*[@id=\"goodsList\"]/tr/td[5]/input")).sendKeys(nub);
        driver.findElement(By.xpath("//*[@id=\"applyRefund\"]/div[2]/div/div[3]/button[1]")).click();

        driver.switchTo().defaultContent();     //跳出frame
        driver.findElement(By.linkText("售后订单")).click();
        Thread.sleep(2000);
        driver.switchTo().frame(2);     //进入售后订单frame

        //同意退款
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr[1]/td[10]/div/a[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"update-status\"]/div[2]/div/div[3]/button[1]")).click();

        //确认收货
        driver.findElement(By.linkText("确认收货")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"update-status\"]/div[2]/div/div[3]/button[1]")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
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

