package Konga;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class KongaTests
{
    private WebDriver driver;

    //functions before test commence
    @BeforeClass
    public void Konga() throws InterruptedException
    {
        //add chromedriver on which test will run
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");

        //launch a new browser instance
        driver = new ChromeDriver();

        //input website url
        driver.get("https://www.konga.com/account/login?return_url=/");
        Thread.sleep(5000);

        //maximize the page
        driver.manage().window().maximize();
        Thread.sleep(5000);
    }

    @Test
    public void LoginTest() throws InterruptedException
    {
        //initialize all variables
        By categoryElement = By.className("ef511_2c5_i");
        By subCategory = By.xpath(".//a[contains(text(),'Macbooks')]");

        //categories are numbered, where 0 = All Categories and 7 = Wines
        int categoryNumber = 1;

        //enter user details and login
        driver.findElement(By.id("username")).sendKeys("muyiwadeyemi@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Dummypass17");
        driver.findElement(By.xpath("/html/body/div[1]/div/section/div[2]/section/main/div/div/div/div[1]/form/div[3]/button")).click();
        Thread.sleep(5000);

        //hover action
        Actions ac = new Actions(driver);

        //hover on Computers and Accessories category
        WebElement selectCategory = driver.findElements(categoryElement).get(categoryNumber);
        ac.moveToElement(selectCategory).perform();
        Thread.sleep(20000);

        //click on macbook section
        driver.findElement(subCategory).click();
        Thread.sleep(20000);
    }

    @Test
    public void AddToCartTest() throws InterruptedException
    {

        //initialize all variables
        By addToCart = By.xpath("(//button[@type='submit'])[5]");
        By myCart = By.xpath("//section[@id='app-content-wrapper']/div[2]/nav/div[2]/div/div/a[2]/span");
        By checkout = By.xpath("//section[@id='app-content-wrapper']/div[3]/section/section/aside/div[3]/div/div[2]/button");

        //add macbook to cart
        driver.findElement(addToCart).click();
        Thread.sleep(5000);

        //click on my cart to see contents
        driver.findElement(myCart).click();
        Thread.sleep(5000);

        //click to go to checkout page
        driver.findElement(checkout).click();
        Thread.sleep(30000);
    }

    @Test
    public void CheckOutTest() throws InterruptedException
    {

        //initialize all variables
        By pickUpLocation = By.xpath("/html/body/div[1]/div/section/div[2]/section/main/form/div/div/section[1]/div[2]/div/div[1]/div/div[2]/form/div/div/a");
        By data_address = By.className("_881fb_F6tni _3d909_3vE1z");
        By choosePickUpLocation = By.xpath("//button[@name='selectPickupLocation']");
        By confirmPickUpLocation = By.xpath("//a[contains(text(),'Use this Address')]");
        By paymentMethod = By.xpath("//button[@name='selectPaymentMethod']");
        By continueToPayment = By.xpath("//button[@name='placeOrder']");
        By checkout_iFrame = By.id("kpg-frame-component");
        By card = By.cssSelector(".Card");
        By cardField = By.id("card-number");
        By dateField = By.id("expiry");
        By cvvField = By.id("cvv");
        By completePayment = By.xpath("//button[@id='validateCardForm']");
        By errorMsg = By.id("error-message");

        //check location picker
        if (pickUpLocation != data_address) {
            driver.findElement(pickUpLocation).click();
            Thread.sleep(2000);

            //select a pickup location
            driver.findElement(choosePickUpLocation).click();
            Thread.sleep(5000);

            //click to use chosen location
            driver.findElement(confirmPickUpLocation).click();
            Thread.sleep(2000);

            //click to pay before delivery
            driver.findElement(paymentMethod).click();
            Thread.sleep(5000);
        }

        //click to pay before delivery
        driver.findElement(paymentMethod).click();
        Thread.sleep(5000);

        //confirm to pay
        driver.findElement(continueToPayment).click();
        Thread.sleep(30000);

        //switch to payment details modal
        WebElement checkoutFrame = driver.findElement(checkout_iFrame);
        driver.switchTo().frame(checkoutFrame);

        //choose to pay with card
        driver.findElement(card).click();
        Thread.sleep(1000);

        //enter payment details and click Pay Now
        driver.findElement(cardField).sendKeys("4860 3990 0298 8761");
        driver.findElement(dateField).sendKeys("01/21");
        driver.findElement(cvvField).sendKeys("145");
        Thread.sleep(2000);

        driver.findElement(completePayment).click();
        Thread.sleep(20000);

        //print out error message after using wrong card details
         System.out.println(driver.findElement(errorMsg).getText());

        //close modal
        driver.findElement(By.xpath("//aside[@onclick='UIHelper.returnToMerchantPage()']")).click();
        Thread.sleep(1000);
    }

    @AfterTest
    public void closeBrowser()
    {
        driver.quit();
    }
}
