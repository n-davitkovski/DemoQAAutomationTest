package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DraggablePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By pageTitle = By.className("main-header");

    // Tabs
    private By axisRestrictedTab = By.id("draggableExample-tab-axisRestriction");
    private By containerRestrictedTab = By.id("draggableExample-tab-containerRestriction");
    private By cursorStyleTab = By.id("draggableExample-tab-cursorStyle");

    // Simple
    private By simpleElement = By.id("dragBox");

    // Axis Restricted
    private By horizontalElement = By.id("restrictedX");
    private By verticalElement = By.id("restrictedY");

    // Container Restricted
    private By containedElement = By.id("containmentWrapper");

    // Cursor Style
    private By cursorCenterElement = By.id("cursorCenter");
    private By cursorTopLeftElement = By.id("cursorTopLeft");
    private By cursorBottomElement = By.id("cursorBottom");

    public DraggablePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
    }

    public boolean isPageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText().equals("Dragabble");
    }

    // Common methods

    private WebElement getElement(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    private int getX(By locator) {
        return getElement(locator).getLocation().getX();
    }

    private int getY(By locator) {
        return getElement(locator).getLocation().getY();
    }

    private void dragElement(By locator, int xOffset, int yOffset) {
        WebElement element = getElement(locator);

        actions.clickAndHold(element)
                .moveByOffset(xOffset, yOffset)
                .release()
                .perform();
    }

    // Simple

    public boolean isSimpleDraggableDisplayed() {
        return getElement(simpleElement).isDisplayed();
    }

    public int getSimpleElementX() {
        return getX(simpleElement);
    }

    public int getSimpleElementY() {
        return getY(simpleElement);
    }

    public void dragSimpleElement(int xOffset, int yOffset) {
        dragElement(simpleElement, xOffset, yOffset);
    }

    // Axis Restricted

    public boolean isAxisRestrictedTabDisplayed() {
        return getElement(axisRestrictedTab).isDisplayed();
    }

    public void clickAxisRestrictedTab() {
        getElement(axisRestrictedTab).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(horizontalElement)
        );
    }

    public int getHorizontalElementX() {
        return getX(horizontalElement);
    }

    public int getHorizontalElementY() {
        return getY(horizontalElement);
    }

    public void dragHorizontalElement(int xOffset, int yOffset) {
        dragElement(horizontalElement, xOffset, yOffset);
    }

    public int getVerticalElementX() {
        return getX(verticalElement);
    }

    public int getVerticalElementY() {
        return getY(verticalElement);
    }

    public void dragVerticalElement(int xOffset, int yOffset) {
        dragElement(verticalElement, xOffset, yOffset);
    }

    // Container Restricted

    public boolean isContainerRestrictedTabDisplayed() {
        return getElement(containerRestrictedTab).isDisplayed();
    }

    public void clickContainerRestrictedTab() {
        getElement(containerRestrictedTab).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(containedElement)
        );
    }

    public int getContainedElementX() {
        return getX(containedElement);
    }

    public int getContainedElementY() {
        return getY(containedElement);
    }

    public void dragContainedElement(int xOffset, int yOffset) {
        dragElement(containedElement, xOffset, yOffset);
    }

    // Cursor Style

    public boolean isCursorStyleTabDisplayed() {
        return getElement(cursorStyleTab).isDisplayed();
    }

    public void clickCursorStyleTab() {
        getElement(cursorStyleTab).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(cursorCenterElement)
        );
    }

    public int getCursorCenterElementX() {
        return getX(cursorCenterElement);
    }

    public void dragCursorCenterElement(int xOffset, int yOffset) {
        dragElement(cursorCenterElement, xOffset, yOffset);
    }

    public int getCursorTopLeftElementX() {
        return getX(cursorTopLeftElement);
    }

    public void dragCursorTopLeftElement(int xOffset, int yOffset) {
        dragElement(cursorTopLeftElement, xOffset, yOffset);
    }

    public int getCursorBottomElementX() {
        return getX(cursorBottomElement);
    }

    public void dragCursorBottomElement(int xOffset, int yOffset) {
        dragElement(cursorBottomElement, xOffset, yOffset);
    }
}
