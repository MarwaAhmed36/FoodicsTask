package pages;

import org.openqa.selenium.*;
import utils.CommonUtilities;

public class SignInPage extends CommonUtilities {

    private final By Username = By.id("ap_email");
    private final By Continue = By.id("continue");
    private final By Password = By.cssSelector("#ap_password");

    private final By SignInGButton = By.cssSelector("#signInSubmit");

    public void enterUsername(String userName){
        System.out.println("SIGNING_PAGE: Entering username: " + userName);
        waitForElementToBeVisible(Username);
        sendKeys(Username, userName);
    }
    public void clickContinueButton(){
        System.out.println("SIGNING_PAGE: Clicking the Continue button.\n");
        click(Continue);
    }
    public void enterPassword(String password){
        System.out.println("SIGNING_PAGE: Entering password.");
        waitForElementToBeVisible(Password);
        sendKeys(Password, password);
    }

    public void clickSignInButton(){
        System.out.println("SIGNING_PAGE: Clicking the Sign in button.\n");
        click(SignInGButton);
    }
}
