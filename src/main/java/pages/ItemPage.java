package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.TestException;
import utils.CommonUtilities;

public class ItemPage extends CommonUtilities {
    private WebDriver driver;

    private final By productTitle = By.id("productTitle");
     private final By price = By.cssSelector(".a-size-medium.a-color-price.offer-price.a-text-normal");
    private By addToCart = By.id("add-to-cart-button");

    public void checkProductTitle(String expectedTitle){
        String actualTitle = getProductTitle();
        System.out.println("ItemPage: Verifying that the product title is '" + actualTitle + "'");
        if (!expectedTitle.equals(actualTitle)){
            throw new TestException("ERROR: ItemPage: Product Title is ['" + actualTitle + "']. Expected ['" + expectedTitle + "'].");
        }
    }

    public String getProductTitle(){
        return getElementText(productTitle);
    }
    public String getPrice(){
        return getElementText(price);
    }

    public void clickAddToCart(){
        System.out.println("ItemPage: Clicking on [ADD_TO_CART] button. \n");
        click(addToCart);
    }
}
