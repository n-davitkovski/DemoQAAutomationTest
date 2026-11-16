package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MenuPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By pageTitle = By.className("main-header");

    private By mainItem1 = By.xpath("//a[text()='Main Item 1']");
    private By mainItem2 = By.xpath("//a[text()='Main Item 2']");
    private By mainItem3 = By.xpath("//a[text()='Main Item 3']");

    private By subItem = By.xpath("//a[text()='Sub Item']");
    private By subItem2 = By.xpath("//a[text()='Sub Item 2']");
    private By subItem3 = By.xpath("//a[text()='Sub Item 3']");

    private By nestedMenu = By.xpath("//a[text()='SUB SUB LIST']");
    private By nestedSubItem1 = By.xpath("//a[text()='Sub Sub Item 1']");
    private By nestedSubItem2 = By.xpath("//a[text()='Sub Sub Item 2']");

    public MenuPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
    }

    public boolean isPageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText().equals("Menu");
    }

    public boolean isMainMenuItemDisplayed(String itemName) {
        By item = By.xpath("//a[text()='" + itemName + "']");

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(item)
        ).isDisplayed();
    }

    public void hoverOverMainItem2() {
        actions.moveToElement(
                wait.until(ExpectedConditions.visibilityOfElementLocated(mainItem2))
        ).perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(subItem));
    }

    public void hoverOverSubItem2() {
        actions.moveToElement(
                wait.until(ExpectedConditions.visibilityOfElementLocated(subItem2))
        ).perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(nestedMenu));
    }

    public boolean isSubMenuItemDisplayed(String itemName) {
        By item = By.xpath("//a[text()='" + itemName + "']");

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(item)
        ).isDisplayed();
    }

    public boolean isNestedSubMenuItemDisplayed(String itemName) {
        By item = By.xpath("//a[text()='" + itemName + "']");

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(item)
        ).isDisplayed();
    }

    public void moveMouseAway() {
        actions.moveToElement(
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle))
        ).perform();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(subItem));
    }
}
