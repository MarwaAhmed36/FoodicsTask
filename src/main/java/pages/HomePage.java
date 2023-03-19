package pages;

import org.openqa.selenium.By;
import org.testng.TestException;
import utils.CommonUtilities;
public class HomePage extends CommonUtilities {
    private By allMenu = By.id("nav-hamburger-menu");
    private By signIn = By.id("nav-link-accountList");
    private By greetingName = By.id("nav-link-accountList-nav-line-1");
    private By homePageTitle = By.id("nav-hamburger-menu");
    private By cartItemsNumber = By.id("nav-cart-count");
    private By seeAllList = By.cssSelector("#hmenu-content > ul.hmenu.hmenu-visible > li:nth-child(15)");
    private By videoGames = By.cssSelector("#hmenu-content > ul.hmenu.hmenu-visible > ul > li:nth-child(11)");
    private By allVideoGames = By.cssSelector("#hmenu-content > ul.hmenu.hmenu-visible.hmenu-translateX > li:nth-child(3)");
    public static String videoGamesUrl = "https://www.amazon.eg/-/en/gp/browse.html?node=18022560031&ref_=nav_em_vg_all_0_2_16_2";
    public HomePage(){

    }

    public void navigateToHomePage() {
        System.out.println("Navigating to " + baseUrl + "...\n");
        navigateToURL(baseUrl);
        refreshPage();
    }
    public void navigateToSignInPage() {
        System.out.println("HOME_PAGE: Navigating to the Sign in Page...\n");
        moveToThenClick(signIn);

    }
    public void navigateToVideoGamesPageUsingURL() {
        String videoGamesURL = baseUrl+ "/gp/browse.html?node=18022560031&ref_=nav_em_vg_all_0_2_16_2";
        navigateToURL(videoGamesURL);

    }
    public void signOutIfSignedIn() {
        if(!getElementText(greetingName).contains("sign in")){
        String signOuturl = baseUrl+ "gp/flex/sign-out.html";
        navigateToURL(signOuturl);
        String currentUrl = getCurrentURL();
        if (!currentUrl.contains("sigin")) {
                throw new TestException("ERROR: The user didn't signed out!");
            }
        }
        else { System.out.println("No User Signed in  \n");
        }
    }

    public void openAllLeftSideMenu() {
        System.out.println("HOME_PAGE: Open the left side all menu.");
        click(allMenu);
    }
    public void openSeeAllMenu() {
        System.out.println("HOME_PAGE: Open See all menu.");
        click(seeAllList);
    }
    public void moveToAndChooseVG() {
        System.out.println("HOME_PAGE: Move to, and choose Video Games.");
        selectDropdownElement(seeAllList,videoGames);
        moveToThenClick(videoGames);
        System.out.println("HOME_PAGE: Move to, and choose All Video Games.");
        moveToThenClick(allVideoGames);
    }
}
