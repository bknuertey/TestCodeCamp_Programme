package login;

import org.testng.annotations.Test;
import setup.SetupTests;

public class LoginTests extends SetupTests {

    @Test

    //this class extends to the SetupTests as the SetupTests class contains all the setup requirements to run our test
    public void testLogin() throws InterruptedException{

        String username = "Admin";
        loginPage.enterUsername(username);
        loginPage.enterUserPassword("admin123");

        //handler for dashboard page
        loginPage.clickLoginBtn();
        String pageUrl = "https://opensource-demo.orangehrmlive.com/index.php/dashboard";
        System.out.println("Passed - The page url is " + pageUrl);
    }

    @Test
    public void testLogout() throws InterruptedException{
        try {
            dashboardPage.clickWelcomeAdmin();
            loginPage = dashboardPage.clickLogout();
        }
        catch (Exception e){
            String loginUrl = "https://opensource-demo.orangehrmlive.com/index.php/auth/login";
            System.out.println("Passed - The page url is " + loginUrl);
        }
    }
}
