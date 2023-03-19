package tests;
import org.testng.annotations.*;
import pages.HomePage;
import pages.SignInPage;
import pages.VideoGamesPage;

public class HomeTest extends BaseTests{
    public HomePage homePage = new HomePage();
    public SignInPage signinPage = new SignInPage();
    public VideoGamesPage videoGsPage = new VideoGamesPage();
    ;

    @Test(description="Open Amazon home page", priority = 1, groups = "initialize")
    public void OpenAmazonWebSite() {
        homePage.navigateToHomePage();
    }

    @Test(description="Login", priority = 2, groups = "initialize")
    public void Login() {
        homePage.signOutIfSignedIn();
        homePage.navigateToSignInPage();
        signinPage.enterUsername("tempamazon15@gmail.com");
        signinPage.clickContinueButton();
        signinPage.enterPassword("Temp@123");
        signinPage.clickSignInButton();
        homePage.navigateToHomePage();
    }
    @Test(description="HOME_PAGE: Navigating to the Video Games Page", priority = 3, groups = "initialize")
    public void NavigateToVideoGamesPageURL() {
        homePage.navigateToVideoGamesPageUsingURL();
    }
    @Test(description="HOME_PAGE: Navigating to the Video Games Page", priority = 4, groups = "initialize", enabled = false)
    public void NavigateToVideoGamesPage() {
        homePage.openAllLeftSideMenu();
        homePage.openSeeAllMenu();
        homePage.moveToAndChooseVG();
    }


}
