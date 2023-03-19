package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.TestException;
import utils.CommonUtilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VideoGamesPage extends CommonUtilities {
    private WebDriver driver;
    private By videoGPageTitle = By.cssSelector("#nav-subnav > a.nav-a.nav-b > span");
    private By freeShipping = By.xpath("//*[@id=\"s-refinements\"]/div[2]/ul/li/span/a/div[1]");
    private By newCondition = By.xpath("//*[@id=\"p_n_condition-type/28071525031\"]/span/a");
    private By sortMenu = By.id("a-autoid-0-announce");
    private By highToLowSorting = By.id("s-result-sort-select_2");
    public By itemTitle = By.cssSelector("h2.a-size-mini.a-spacing-none.a-color-base.s-line-clamp-2");
    public By itemPrice = By.xpath("//div[@class=\"a-row a-size-base a-color-base\"]");
    private By cart = By.id("nav-cart");
    private By cartCount = By.cssSelector(".nav-right #nav-tools #nav-cart #nav-cart-count");

    private By nextPage = By.cssSelector("a.s-pagination-item.s-pagination-next.s-pagination-button.s-pagination-separator");

    public  List<String> products = new ArrayList<>();
    protected List<Integer> Prices;
    Map<Integer, String> myProductsMap = new HashMap< Integer, String>();

    public ItemPage itemPage = new ItemPage();

    public VideoGamesPage(){ }

    public void CheckSuccessfulNavigateToVGs() {
        System.out.println("VideoGamesPage: Check successful navigation");
        refreshPage();
        waitForElementToBeVisible(videoGPageTitle);
        String currentPageTitle = getElementText(videoGPageTitle);
        if (!currentPageTitle.contains("Video Games")) {
            throw new TestException("ERROR: The user didn't signed out!");
        }
        else
            System.out.println("VideoGamesPage: Video Games Page opened successfully");

    }
    public void AddFreeShippingFilter() {
        System.out.println("VideoGamesPage: Move to, and choose Free Shipping filter.");
        moveToThenClick(freeShipping);
    }
    public void AddNewConditionFilter() {
        System.out.println("VideoGamesPage: Move to, and choose New Condition filter.");
        moveToThenClick(newCondition);
    }

    public void SortFromHighToLow() {
        System.out.println("VideoGamesPage: Sort from high to low.");
        moveToThenClick(sortMenu);
        moveToThenClick(highToLowSorting);

    }
    public List<Integer>  GetAvailableDevicesPriceList(By selector) {
        System.out.println("VideoGamesPage: Get available devices price list.");
        List<String> itemPrices = getListOfElementTexts(selector);
        Prices = itemPrices.stream()
                .map(s -> Integer.parseInt((String) s.replaceAll("\\D+","").subSequence(0,5)))
                .collect(Collectors.toList());

        return Prices;
    }
    public List<String>  GetAvailableDevicesPriceTitle(By selector) {
        System.out.println("VideoGamesPage: Get available devices price list.");
        List<String> itemTitles = getListOfElementTexts(selector);
        return itemTitles;
    }

    public void AddItemsToCart(Map<Integer,String> productList) {

        for (int i :productList.keySet()){
            String numString = String.format("%,d", i);
            String xPathContains = String.format("//a/Span/Span[contains(text(),'%S')]/..", numString) ;
            By xpath = By.xpath(xPathContains);
            MoveToProduct(xpath);
            itemPage.clickAddToCart();
            MoveBack();
            MoveBack();

        }

    }
     public void ChooseItemsWithingRange() {
        List<String> itemsTitle =  GetAvailableDevicesPriceTitle(itemTitle);
        List<Integer> prices = GetAvailableDevicesPriceList(itemPrice);
         myProductsMap = GetItemsWithinPriceRange(prices,itemsTitle);
     }
    public Map<Integer,String> GetItemsWithinPriceRange(List<Integer> itemsPrice,List<String> itemsTitle) {
        System.out.println("VideoGamesPage: Check that the list contains 3 items with price less than 15K EGP.");
        List<Integer> last3Items = itemsPrice.subList(Math.max(itemsPrice.size()-3,0),itemsPrice.size());
        for (int i =0; i<itemsPrice.size(); i++){
            if(itemsPrice.get(i)<15000) {
                myProductsMap.put(itemsPrice.get(i), itemsTitle.get(i));
            }
        }
        Integer price = last3Items.get(0);
        if(myProductsMap.values().size()<3){
            MoveToNextResultPage();
            return myProductsMap;}

        else{
            return myProductsMap;
        }
    }

    public void MoveToNextResultPage() {
        System.out.println("VideoGamesPage: Move to next page to get items below 15K EGP!.");
        moveToThenClick(nextPage);
        ChooseItemsWithingRange();
    }
    public void MoveToProduct(By selector) {
        System.out.println("VideoGamesPage: Move to product page to add.");
        moveToThenClick(selector);

    }
    public void MoveBack() {
        System.out.println("VideoGamesPage: Move to back to the same products list.");
        navigateBack();

    }
    public String getNumberOfItemsInCart() {
        return getElementText(cartCount);
    }

    public void checkCartCount(int productsNum){
        click(cart);
    }


}
