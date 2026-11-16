package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SelectablePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By pageTitle = By.className("main-header");

    private By listTab = By.id("demo-tab-list");
    private By gridTab = By.id("demo-tab-grid");

    private By listItems = By.cssSelector(
            "#demo-tabpane-list li"
    );

    private By gridItems = By.cssSelector(
            "#demo-tabpane-grid li"
    );

    public SelectablePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public boolean isPageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText().equals("Selectable");
    }

    // List

    public List<String> getListItems() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(listTab)
        );

        List<WebElement> elements = driver.findElements(listItems);
        List<String> itemNames = new ArrayList<>();

        for (WebElement element : elements) {
            itemNames.add(element.getText());
        }

        return itemNames;
    }

    public void selectListItem(String itemName) {
        WebElement item = findItem(listItems, itemName);
        item.click();
    }

    public boolean isListItemSelected(String itemName) {
        WebElement item = findItem(listItems, itemName);

        return item.getAttribute("class").contains("active");
    }

    // Grid

    public void clickGridTab() {
        wait.until(
                ExpectedConditions.elementToBeClickable(gridTab)
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(gridItems)
        );
    }

    public List<String> getGridItems() {
        List<WebElement> elements = driver.findElements(gridItems);
        List<String> itemNames = new ArrayList<>();

        for (WebElement element : elements) {
            itemNames.add(element.getText());
        }

        return itemNames;
    }

    public void selectGridItem(String itemName) {
        WebElement item = findItem(gridItems, itemName);
        item.click();
    }

    public boolean isGridItemSelected(String itemName) {
        WebElement item = findItem(gridItems, itemName);

        return item.getAttribute("class").contains("active");
    }

    // Common method

    private WebElement findItem(By locator, String itemName) {
        List<WebElement> elements = driver.findElements(locator);

        for (WebElement element : elements) {
            if (element.getText().equals(itemName)) {
                return element;
            }
        }

        throw new RuntimeException("Item not found: " + itemName);
    }
}
