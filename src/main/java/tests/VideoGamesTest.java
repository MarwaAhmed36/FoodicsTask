package tests;

import org.testng.annotations.Test;
import pages.VideoGamesPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoGamesTest extends BaseTests{

    public VideoGamesPage videoGsPage = new VideoGamesPage();
    protected Map<Integer,String> Products  = new HashMap< Integer, String>();
    List<String> productsTitles;

    @Test(description="Checking video games page opened successfully", priority = 5, dependsOnGroups = "initialize",groups = "AddProduct")
    public void CheckSuccessfulNavigateToVGs() {
        videoGsPage.CheckSuccessfulNavigateToVGs();
    }

        @Test(description="Adding FreeShipping, and New Condition Filters", priority = 6, dependsOnGroups = "initialize",groups = "AddProduct")
        public void AddingFilters() {
            videoGsPage.AddFreeShippingFilter();
            videoGsPage.AddNewConditionFilter();
        }

    @Test(description="Sorting from high to low", priority = 7, dependsOnGroups = "initialize",groups = "AddProduct")
    public void ItemsSorting() {
        videoGsPage.SortFromHighToLow();
    }
    @Test(description="Getting 3 items below 15K Egp", priority = 8, dependsOnGroups = "initialize",groups = "AddProduct")
    public void Validate3ItemsExistBelowRange() {
        List<String> itemsTitle = videoGsPage.GetAvailableDevicesPriceTitle(videoGsPage.itemTitle);
        List<Integer> prices = videoGsPage.GetAvailableDevicesPriceList(videoGsPage.itemPrice);
        Products = videoGsPage.GetItemsWithinPriceRange(prices,itemsTitle);
    }
    @Test(description="Adding 3 items below 15K Egp to Cart", priority = 9, dependsOnGroups = "initialize",groups = "AddProduct")
    public void AddItemsToCart() {
        videoGsPage.AddItemsToCart(Products);
    }


}
