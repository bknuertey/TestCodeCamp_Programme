package User;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FacebookTests {
    //import selenium webdriver
    private WebDriver driver;

    @BeforeClass   //functions before test commence
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");    //add chromedriver on which test will run
        driver = new ChromeDriver();    //launch a new browser instance
        driver.get("https://web.facebook.com/login/web/?_rdc=1&_rdr");    //input website url
        Thread.sleep(5000);    //wait for page to load
        driver.manage().window().maximize();    //maximize the page
        System.out.println(driver.getTitle());    //get page title
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void loginTest() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("email")).sendKeys("bnuerteytech@gmail.com");    //locate email filed
        driver.findElement(By.id("pass")).sendKeys("Quantumleap21_");    //locate password field
        driver.findElement(By.xpath("//*[@id=\"loginbutton\"]")).click();    //click on login button
        Thread.sleep(5000);    //wait to see user logged in
        if(driver.getCurrentUrl().contains("https://web.facebook.com")){    //test to confirm user logged in
            System.out.println("PASSED - User has successfully logged in");
        }else{
            System.out.println("FAILED - The user log in");
        }
        Thread.sleep(5000);    //waiting to see logged in page
    }
    @Test
    public void logoutTest() throws InterruptedException{
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        //click on drop=down button
        driver.findElement(By.cssSelector("div[aria-label=\"Account\"]")).click();
        //wait for 10 seconds
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        //click on log out button
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/div[4]/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[1]/div/div[3]/div/div[4]/div/div[1]/div[2]/div")).click();
        //test to confirm user logged out
        if(driver.getCurrentUrl().contains("https://web.facebook.com")){
            System.out.println("PASSED - User has successfully logged in");
        }else{
            System.out.println("FAILED - The user log in");
        }
        Thread.sleep(5000);    //waiting to see logged in page
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }
    public static void main(String[] args) throws InterruptedException{
        FacebookTests test = new FacebookTests();
        test.setUp();
    }
}
