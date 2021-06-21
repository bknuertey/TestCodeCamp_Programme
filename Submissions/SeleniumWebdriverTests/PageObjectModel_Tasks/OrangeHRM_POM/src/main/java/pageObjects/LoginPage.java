package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    public LoginPage (WebDriver driver) {this.driver = driver;}

    private WebDriver driver;

    //using by.xpath locator to find elements
    private By username = By.xpath("//input[@name='txtUsername']");

    public void enterUsername(String uName) {

        //find the username element and enter the username
        driver.findElement(username).sendKeys(uName);
    }

    public void enterUserPassword(String pwd){

        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys(pwd);
    }

    public DashboardPage clickLoginBtn(){

        driver.findElement(By.xpath("//input[@name='Submit']")).click();
        return new DashboardPage(driver);
    }
}
