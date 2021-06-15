package User;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DeydamTests {
    //import selenium webdriver
    private WebDriver driver;

    @BeforeClass   //functions before test commence
        public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");    //add chromedriver on which test will run
        driver = new ChromeDriver();    //launch a new browser instance
        driver.get("https://dev.d2rxvhrryr2bkn.amplifyapp.com/login");    //input website url
        Thread.sleep(5000);    //wait for page to load
        driver.manage().window().maximize();    //maximize the page
        System.out.println(driver.getTitle());    //get page title
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
        public void loginTest() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("username")).sendKeys("kwamebright");    //locate username filed
        driver.findElement(By.id("password")).sendKeys("Overleap21_");    //locate password field
        driver.findElement(By.xpath("/html/body/div/div/div/div/main/div/div[2]/div/div/div/div[2]/div/div/form/button")).click();    //click on login button
        Thread.sleep(5000);    //wait to see user logged in
        if(driver.getCurrentUrl().contains("https://dev.d2rxvhrryr2bkn.amplifyapp.com/app/feed")){    //test to confirm user logged in
            System.out.println("PASSED - User has successfully logged in");
        }else{
            System.out.println("FAILED - The user log in");
        }
        Thread.sleep(5000);    //waiting to see logged in page
    }
    @Test
        public void logoutTest() throws InterruptedException{
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[1]/div[1]/div/div[2]/div[3]/button/p")).click();    //click on arrow button that has logout menu on the list
        Thread.sleep(5000);    //wait to ensure the drop-down list is displayed
        driver.findElement(By.linkText("Log Out")).click();    //click on logout button
        if(driver.getCurrentUrl().contains("https://dev.d2rxvhrryr2bkn.amplifyapp.com/login")){    //test to confirm user logged out
            System.out.println("PASSED - User has successfully logged out");
        }else{
            System.out.println("FAILED - The user is still logged in");
        }
        Thread.sleep(5000);    //waiting to see logged out page
    }
    @AfterTest
        public void tearDown(){
        driver.quit();
    }
    public static void main(String[] args) throws InterruptedException{
        DeydamTests test = new DeydamTests();
        test.setUp();
    }
}
