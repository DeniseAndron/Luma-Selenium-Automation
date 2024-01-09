package comLuma.testCases;



import comLuma.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class TC_LoginTest extends BaseClass {

    //Listeners are components in TestNG that keep track of test execution and helps to perform actions at multiple stages of test execution
    //1. We are login in with valid email and Password in the app
    @Test
    public void test_01_ValidLogin() {
        LoginPage loginObj = new LoginPage(driver);

        String validEmail = "denise@gmail.com";
        String validPassword = "test123";



        //We are getting validEmail and valid Password from the confing properties file
        loginObj.goToLoginPage();
        loginObj.setEmailAddress(validEmail);
        logger.info("Set Email");

        loginObj.setPassword_field(validPassword);
        logger.info("Set Password");
        loginObj.signInButton();
    }
    //2. Login with invalid password and email, check for error message

    @Test
    public void test_02_invalidData() throws IOException {
        LoginPage loginObj = new LoginPage(driver);
        String expectedErrorMessage = "Please enter a valid email address (Ex: johndoe@domain.com).";




        loginObj.goToLoginPage();
        loginObj.setEmailAddress(("invalidEmail"));
        loginObj.signInButton();

        // Assertion
        if (expectedErrorMessage.equals(loginObj.invalidEmailErrorMessage())) {
            // Assertion passed, log confirmation using a logger
            logger.info("Assertion passed: Invalid email error message is displayed as expected.");
        } else {
            // Assertion failed, capture screenshot and log failure
            captureScreen(driver,"test_02_invalidData");
            logger.info("Assertion failed: Invalid email error message is not displayed as expected.");

        }


        loginObj.setPassword_field(("invalidPassword"));
        loginObj.signInButton();
    }

}
