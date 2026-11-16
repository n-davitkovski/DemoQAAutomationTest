package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortablePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By pageTitle = By.className("main-header");

    private By listTab = By.id("demo-tab-list");
    private By gridTab = By.id("demo-tab-grid");

    private By listItems = By.cssSelector("#demo-tabpane-list .list-group-item");
    private By gridItems = By.cssSelector("#demo-tabpane-grid .list-group-item");

    public SortablePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
    }

    public boolean isPageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText().equals("Sortable");
    }

    // List

    public List<String> getListItems() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(listTab));

        List<WebElement> elements = driver.findElements(listItems);
        List<String> itemNames = new ArrayList<>();

        for (WebElement element : elements) {
            itemNames.add(element.getText());
        }

        return itemNames;
    }

    public void dragListItem(String sourceText, String targetText) {
        WebElement source = findItem(listItems, sourceText);
        WebElement target = findItem(listItems, targetText);

        actions.clickAndHold(source)
                .moveToElement(target)
                .release()
                .perform();

        wait.until(driver ->
                !getListItems().equals(
                        List.of("One", "Two", "Three", "Four", "Five", "Six")
                )
        );
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
        clickGridTab();

        List<WebElement> elements = driver.findElements(gridItems);
        List<String> itemNames = new ArrayList<>();

        for (WebElement element : elements) {
            itemNames.add(element.getText());
        }

        return itemNames;
    }

    public void dragGridItem(String sourceText, String targetText) {
        clickGridTab();

        WebElement source = findItem(gridItems, sourceText);
        WebElement target = findItem(gridItems, targetText);

        actions.clickAndHold(source)
                .moveToElement(target)
                .release()
                .perform();

        wait.until(driver ->
                !getGridItems().equals(
                        List.of(
                                "One", "Two", "Three",
                                "Four", "Five", "Six",
                                "Seven", "Eight", "Nine"
                        )
                )
        );
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
