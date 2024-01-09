package comLuma.pageObjects;

import comLuma.testCases.BaseClass;
import comLuma.utilities.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.function.Function;

public class LoginPage extends BaseClass {

    //Initiate the driver in the constructor
    // rdriver = remote driver ldriver = local driver
    public LoginPage(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);

    }

    //Locators
    @FindBy(xpath = "//div[@class='panel header']//a[contains(text(),'Sign In')]")
    WebElement signInHomeButton;
    @FindBy(id = "email")
    WebElement emailField;
    @FindBy(id = "pass")
    WebElement passwordField;
    @FindBy(xpath = "//fieldset[@class='fieldset login']//span[contains(text(),'Sign In')]")
    WebElement signInButton;
    @FindBy(xpath = "//a[@class='action remind']//span[contains(text(),'Forgot Your Password?')]")
    WebElement forgotPasswordButton;
    @FindBy(xpath = "//a[@class='action create primary']//span[contains(text(),'Create an Account')]")
    WebElement createAccountButton;
    @FindBy(id = "email-error")
    WebElement invalidEmailAddress;

    @FindBy(css="body > div.page-wrapper > header > div.panel.wrapper > div > ul > li.customer-welcome > span")
    WebElement customerWelcomeDropdown;

    @FindBy(css="body > div.page-wrapper > header > div.panel.wrapper > div > ul > li.customer-welcome.active > div > ul > li.authorization-link > a")
    WebElement signOutButton;
    @FindBy(xpath = "//div[@class='page-wrapper']//header//div[@class='panel wrapper']//ul//li[contains(@class, 'greet')]/span")
    WebElement pageTitleAfterLogin;


    By pageTitleAfterLoginBy = By.xpath("//div[@class='page-wrapper']//header//div[@class='panel wrapper']//ul//li[contains(@class, 'greet')]/span");
    WebDriver ldriver;

    WaitUtils waitUtils = new WaitUtils(driver);


    public void goToLoginPage() {
        signInHomeButton.click();

    }

    public void setEmailAddress(String text) {

        emailField.clear();
        emailField.sendKeys(text);
    }

    public void setPassword_field(String text) {
        passwordField.clear();
        passwordField.sendKeys(text);

    }

    public void signInButton() {
        signInButton.click();

    }

    public void forgotYourPassword() {

        forgotPasswordButton.click();

    }

    public void createAccountButton() {

        createAccountButton.click();

    }

    public boolean invalidEmailErrorMessage() {

        boolean displayErrorInvalidEmail = invalidEmailAddress.isDisplayed();
        return displayErrorInvalidEmail;
    }
    public String getInvalidEmailErrorMessage() {
        try {
            return invalidEmailAddress.getText();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            // Return null if the element is not found or not visible
            return null;
        }
    }

    public String getSuccessMessage() {
        waitUtils.waitForElementVisible(pageTitleAfterLoginBy);
        waitUtils.waitForTextToChange(pageTitleAfterLogin, "Click “Write for us” link in the footer to submit a guest post");


        String displayErrorInvalidEmail = pageTitleAfterLogin.getText();
        return displayErrorInvalidEmail;
    }

    public void signOut() {
    	customerWelcomeDropdown.click();
    	signOutButton.click();
    }
}
