package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.CommonUtilities;

public class BaseTests {

    public static WebDriver driver;
    @BeforeTest(alwaysRun = true)
    public void setUp() {
        driver = CommonUtilities.DriverSetup();
    }


    @AfterTest(alwaysRun = true)
    public void tearDown() throws Exception {
       // driver.close();
       // driver.quit();
    }
}