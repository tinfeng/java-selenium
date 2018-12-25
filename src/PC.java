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
        //配置并打开浏览器
        System.setProperty("webdriver.chrome.driver", "D:\\Documents\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        //设置浏览器大小
        //driver.manage().window().setSize(new Dimension(1280, 800));
        driver.manage().window().maximize();
        //测试地址
//        baseUrl = "http://139.159.241.7:9061";    //华为云
        baseUrl = "http://139.159.152.223:9061";  //3.6
//        baseUrl = "https://www.caichufang.com";  //线上
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        //进入首页
        driver.get(baseUrl + "/index.html");
        //添加cookie
        driver.manage().addCookie(new Cookie("SESSION", "11122fc1-ec76-4abf-876f-cac2398e7881"));
        //刷新页面进行登陆
        driver.navigate().refresh();
        //搜索关键字落叶
        /*driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='更多优惠等你'])[1]/following::input[1]")).sendKeys("落叶");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='更多优惠等你'])[1]/following::img[2]")).click();
        //按价格排序
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='销量'])[1]/following::span[1]")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/div[1]/label[1]/span[1]/span")).click();
        Thread.sleep(3000);
        //鼠标停留在图片上
        WebElement card = driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[3]/ul/li[1]/div/a/img"));
        Actions action = new Actions(driver);
        action.moveToElement(card).perform();
        Thread.sleep(2000);
        //将商品数量改为2
        driver.findElement(By.xpath("/html/body/div[1]/section/main/div/div[3]/ul/li[1]/div/div[4]/div/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[1]/section/main/div/div[3]/ul/li[1]/div/div[4]/div/div/input")).sendKeys("2");
        //将价格最低的商品加入购物车
        driver.findElement(By.id("addCart")).click();*/
        //进入购物车
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/header/div[2]/div[1]/div[1]/div/div[1]/div[2]/button")).click();
        //切换标签页
        Set<String> winHandels = driver.getWindowHandles(); // 得到当前窗口的set集合
        List<String> it = new ArrayList<String>(winHandels); // 将set集合存入list对象
        driver.switchTo().window(it.get(1)); // 切换到弹出的新窗口
        Thread.sleep(1000);
        //点击结算
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div/div[3]/button")).click();
        Thread.sleep(1000);
        WebElement Settlement = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[2]/div"));
        if (Settlement != null) {
            System.out.println(Settlement);
            driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[2]/div/div[2]/button[1]")).click();
            return;
        }
        driver.findElement(By.xpath("/html/body/div[1]/section/main/div/div/div[4]/div/button"));

        System.out.println("123");
        WebElement Order = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div"));
        if (Order != null) {
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='以下商家未满足最小配送金额，是否继续？'])[1]/following::button[1]")).click();
            System.out.println(Order);
            return;
        }
        System.out.println("312");
        driver.findElement(By.xpath("/html/body/div[1]/section/main/div/div[3]/button")).click();

        Thread.sleep(28000);
        WebElement flag = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/span"));
        if (flag != null) {
            System.out.println("未支付");
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/button[2]")).click();

        } else {
            System.out.println("下单成功,返回订单页面");
            driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div[2]/button[1]")).click();
        }
        Thread.sleep(10000);
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
