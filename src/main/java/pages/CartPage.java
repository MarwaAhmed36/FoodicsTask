package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.TestException;
import utils.CommonUtilities;

import java.util.List;

public class CartPage extends CommonUtilities {
    private WebDriver driver;
    private By cartIcon = By.id("nav-cart-count-container");
    private  By delete= By.cssSelector("input[value='Delete']");
    private  By totalPrice = By.cssSelector("[class='a-size-medium a-color-price sc-price sc-white-space-nowrap sc-price-sign']");
    private By cartItemsNumber = By.id("nav-cart-count");
    private By proceedToBuy = By.id("sc-buy-box-ptc-button > span > input");
    private By enterAddressPhoneNumber  = By.id("address-ui-widgets-enterAddressPhoneNumber");
    private By addressFullName = By.id("address-ui-widgets-enterAddressFullName");
    private By buildName = By.id("address-ui-widgets-enter-building-name-or-number");
    private By apartment = By.id("#address-ui-widgets-enter-floor-or-apartment-number");
    private By streetName = By.id("address-ui-widgets-enterAddressLine1");
    private By addressCity = By.id("address-ui-widgets-enterAddressCity");
    private By addressDistrictOrCounty = By.id("address-ui-widgets-enterAddressDistrictOrCounty");
    private By addressStateOrRegion = By.id("address-ui-widgets-enterAddressStateOrRegion");
    private By addAddressButton = By.id("address-ui-widgets-form-submit-button-announce");
    private By useThisAddress = By.id("shipToThisAddressButton-announce");
    private By COD = By.xpath("input#pp-VnBmtg-71");
    public HomePage homePage = new HomePage();


    public CartPage(){ }

    public void VerifyProductsAddedSuccessfully() {
        System.out.println("Check that the products are added to Cart.");
        click(cartIcon);
    }

    public void AddAddress() {
        System.out.println("Add delivery Address.");
        moveToThenClick(proceedToBuy);
        sendKeys(addressFullName,"Taalat Harb");
        sendKeys(enterAddressPhoneNumber,"1111111111");
        sendKeys(buildName,"1");
        sendKeys(apartment,"1");
        sendKeys(addressStateOrRegion,"1");
        sendKeys(addressCity,"1");
        sendKeys(addressDistrictOrCounty,"1");
        sendKeys(streetName,"1");
        moveToThenClick(addAddressButton);

    }
    public void ChooseAddress() {
        System.out.println("Choose delivery Address.");
        click(cartIcon);
        moveToThenClick(proceedToBuy);
        //moveToThenClick(useThisAddress);

    }
    public void ChoseCODAsPaymentMethod() {
        System.out.println("Choose Cash on delivery as payment method.");
        click(COD);
    }
    public void VerifyTotalPaymentAmount(Double ExpectedTotal) {
        System.out.println("Check the total payment amount with the delivery fees.");
        Double expectedTotal = ExpectedTotal;
        Double actualTotal = Double.parseDouble(getCartSubtotal());
        if (!actualTotal.equals(expectedTotal)) {
            throw new TestException("ERROR in Cart Page: The actual payment amount is '" +
                    actualTotal + "', while expected '" + expectedTotal + "'!");
        }
    }
    public String getCartSubtotal() {
        return getElementText(totalPrice);
    }

    public String getCartItemsCount() {
        return getElementText(cartItemsNumber);
    }


    public boolean CartIsEmpty() {
        System.out.println("Check the Cart is empty, and clear it if not!!");
        homePage.navigateToHomePage();
        String itemsInCart = getCartItemsCount();
        return itemsInCart.equals("0");
    }
    public void deleteAllItemsInCart(){
        List<WebElement> deleteButtons = getElements(delete);
        for ( WebElement button : deleteButtons ){
            button.click();
        }
    }
}
