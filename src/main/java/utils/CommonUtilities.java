package utils;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class CommonUtilities {

    private static int timeout = 10;
    public  static WebDriver driver;
    public CommonUtilities() { driver=DriverSetup(); }

    public static WebDriver DriverSetup() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }
    public static String baseUrl = "https://www.amazon.eg/-/en";


    public WebDriverWait wait;
    public Actions actions;
    public Select select;

    public void navigateToURL(String URL) {
        try {
            driver.navigate().to(URL);
        } catch (Exception e) {
            System.out.println("FAILURE: URL did not load: " + URL);
            throw new TestException("URL did not load");
        }
    }

    public void refreshPage() {
        try {
            driver.navigate().refresh();
        } catch (Exception e) {
            System.out.println("FAILURE: Could not refresh the page.");
            throw new TestException("Could not Could not refresh the page.");
        }
    }
    public String getElementText(By selector) {
        waitUntilElementIsDisplayedOnScreen(selector);
        try {
            return StringUtils.trim(driver.findElement(selector).getText());
        } catch (Exception e) {
            System.out.println(String.format("ERROR: Element [%s] does not exist - proceeding!",
                    selector));
        }
        return null;
    }
    public void waitUntilElementIsDisplayedOnScreen(By selector) {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        } catch (Exception e) {
            throw new NoSuchElementException(String.format("ERROR: The following element is not visible: [%s]!",
                    selector));
        }
    }
    public void click(By selector) {
        waitUntilElementIsDisplayedOnScreen(selector);
        WebElement element = getElement(selector);
        waitForElementToBeClickable(selector);
        try {
            element.click();
        } catch (Exception e) {
            throw new TestException(String.format("ERROR: The following element is not clickable: [%s]", selector));
        }
    }
    public void waitForElementToBeClickable(By selector) {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.elementToBeClickable(selector));
        } catch (Exception e) {
            throw new TestException("The following element is not clickable: " + selector);
        }
    }
    public WebElement getElement(By selector) {
        try {
            return driver.findElement(selector);
        } catch (Exception e) {
            System.out.println(String.format("ERROR: Element [%s] does not exist!",
                    selector));
        }
        return null;
    }

    public void waitForElementToBeVisible(By selector) {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.presenceOfElementLocated(selector));
        } catch (Exception e) {
            throw new NoSuchElementException(String.format("ERROR: The following element is not visible: [%s]!",
                    selector));
        }
    }

    public void sendKeys(By selector, String value) {
        WebElement element = getElement(selector);
        clearField(element);
        try {
            element.sendKeys(value);
        } catch (Exception e) {
            throw new TestException(String.format("ERROR: Error in sending [%s] to the following element: [%s]!",
                    value, selector.toString()));
        }
    }
    public void clearField(WebElement element) {
        try {
            element.clear();
        } catch (Exception e) {
            System.out.print(String.format("ERROR: The following element could not be cleared: [%s]!",
                    element.getText()));
        }
    }
    public void navigateBack() {
        try {
            driver.navigate().back();
        } catch (Exception e) {
            System.out.println("FAILURE: Could not navigate back to previous page.");
            throw new TestException("Could not navigate back to previous page.");
        }
    }
    public void moveTo(By selector) {
        WebElement element = driver.findElement(selector);
        actions = new Actions(driver);
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            actions.moveToElement(element).perform();
        } catch (Exception e) {
            throw new TestException(String.format("The following element does not exist: [%s]", element.toString()));
        }
    }
    public void selectDropdownElement(By element1selector, By element2selector) {
        WebElement element1 = driver.findElement(element1selector);
        WebElement element2 = driver.findElement(element2selector);
        actions = new Actions(driver);
        try {
            actions.dragAndDrop(element1,element2);
        } catch (Exception e) {
            throw new TestException(String.format("The actions.dragAndDrop didn't work.."));
        }
    }


    public void moveToThenClick(By selector) {
        WebElement element = driver.findElement(selector);
        actions = new Actions(driver);
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                    new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfElementLocated(selector)));
           ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            //actions.moveToElement(element).perform();
            //actions.click(element).perform();
        } catch (Exception e) {
            throw new TestException(String.format("The following element is not clickable: [%s]", element.toString()));
        }
    }
    public String getCurrentURL() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            throw new TestException(String.format("Exception Current URL is %s!",
                    driver.getCurrentUrl()));
        }
    }

    public List<WebElement> getElements(By Selector) {
        waitForElementToBeVisible(Selector);
        try {
            return driver.findElements(Selector);
        } catch (Exception e) {
            throw new NoSuchElementException(String.format("The following elements did not display: [%s] ", Selector.toString()));
        }
    }

    public List<String> getListOfElementTexts(By selector) {
        List<String> elementList = new ArrayList<String>();
        List<WebElement> elements = getElements(selector);

        for (WebElement element : elements) {
            if (element == null) {
                throw new TestException("Some elements in the list do not exist");
            }
            if (element.isDisplayed()) {
                elementList.add(element.getText().trim());
            }
        }
        return elementList;
    }
}
