package tests;

import org.testng.annotations.Test;
import pages.CartPage;
import utils.CommonUtilities;

public class CartTest extends CommonUtilities {
    public CartPage cartPage = new CartPage();

    @Test(description="Check that the products are added to Cart", priority = 10, dependsOnGroups = "AddProduct")
    public void VerifyAddedItemsToCart() {
        cartPage.VerifyProductsAddedSuccessfully();
    }

    @Test(description="Add delivery Address.", priority = 11, dependsOnGroups = "AddProduct", enabled = false)
    public void AddDeliveryAddress() {
        cartPage.AddAddress();
    }
    @Test(description="Choose already Added delivery Address.", priority = 11, dependsOnGroups = "AddProduct")
    public void ChooseDeliveryAddress() {
        cartPage.ChooseAddress();
    }

    @Test(description="Choose Cash on delivery as payment method.", priority = 12, dependsOnGroups = "AddProduct")
    public void ChoseCODAsPaymentMethod() {
        cartPage.ChoseCODAsPaymentMethod();
    }

    @Test(description="Check that the total payment amount is correct", priority = 13, dependsOnGroups = "AddProduct", enabled = false)
    public void VerifyTotalPayment() {
        //cartPage.VerifyTotalPaymentAmount();
    }
    @Test(description="Delete All items from cart.", priority = 15, dependsOnGroups = "AddProduct")
    public void CheckCartIsEmpty() {

        if(!cartPage.CartIsEmpty()){
        cartPage.deleteAllItemsInCart();}
    }


}
