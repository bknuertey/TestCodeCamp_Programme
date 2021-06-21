package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;

public class SetupTests {
    //create a webdriver object
    private WebDriver driver;

    //create LoginPage Object
    protected LoginPage loginPage;

    //create DashboardPage Object
    protected DashboardPage dashboardPage;

    @BeforeTest
    public void setUp() {

        //setup chrome browser
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");

        //instantiate webdriver object
        driver = new ChromeDriver();

        //maximize browser window
        driver.manage().window().maximize();

        //launch application
        driver.get("https://opensource-demo.orangehrmlive.com");

        //instantiate login page after launching the browser
        loginPage = new LoginPage (driver);
    }
    @AfterTest
    public void closeBrowser() {

        //exit browser
        driver.quit();
    }
}
