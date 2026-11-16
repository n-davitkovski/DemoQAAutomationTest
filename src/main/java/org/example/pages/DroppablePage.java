package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DroppablePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By pageTitle = By.className("main-header");

    // Simple
    private By simpleDraggable = By.id("draggable");
    private By simpleDropTarget = By.id("droppable");

    // Accept
    private By acceptTab = By.id("droppableExample-tab-accept");
    private By acceptableElement = By.id("acceptable");
    private By notAcceptableElement = By.id("notAcceptable");
    private By acceptDropTarget = By.id("acceptDropContainer");

    // Prevent Propagation
    private By preventPropagationTab =
            By.id("droppableExample-tab-preventPropogation");

    private By propagationDraggable = By.id("dragBox");
    private By outerDropTarget = By.id("notGreedyDropBox");
    private By innerDropTarget = By.id("notGreedyInnerDropBox");

    // Revert Draggable
    private By revertDraggableTab =
            By.id("droppableExample-tab-revertable");

    private By revertDraggable = By.id("revertable");
    private By notRevertDraggable = By.id("notRevertable");

    private By revertDropTarget = By.id("revertableDropContainer");

    public DroppablePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
    }

    // General

    public boolean isPageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText().equals("Droppable");
    }

    private WebElement getElement(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    private void dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement source = getElement(sourceLocator);
        WebElement target = getElement(targetLocator);

        actions.dragAndDrop(source, target).perform();
    }

    private int getX(By locator) {
        return getElement(locator).getLocation().getX();
    }

    private int getY(By locator) {
        return getElement(locator).getLocation().getY();
    }

    // Simple

    public boolean isSimpleDraggableDisplayed() {
        return getElement(simpleDraggable).isDisplayed();
    }

    public boolean isSimpleDropTargetDisplayed() {
        return getElement(simpleDropTarget).isDisplayed();
    }

    public void dragSimpleElementToTarget() {
        dragAndDrop(simpleDraggable, simpleDropTarget);

        wait.until(
                ExpectedConditions.textToBe(simpleDropTarget, "Dropped!")
        );
    }

    public String getSimpleDropTargetText() {
        return getElement(simpleDropTarget).getText();
    }

    public boolean isSimpleDropTargetHighlighted() {
        return getElement(simpleDropTarget)
                .getAttribute("class").contains("ui-state-highlight");
    }

    // Accept

    public boolean isAcceptTabDisplayed() {
        return getElement(acceptTab).isDisplayed();
    }

    public void clickAcceptTab() {
        getElement(acceptTab).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(acceptableElement)
        );
    }

    public void dragAcceptableElementToTarget() {
        dragAndDrop(acceptableElement, acceptDropTarget);

        wait.until(
                ExpectedConditions.textToBe(acceptDropTarget, "Dropped!")
        );
    }

    public void dragNotAcceptableElementToTarget() {
        dragAndDrop(notAcceptableElement, acceptDropTarget);
    }

    public String getAcceptDropTargetText() {
        return getElement(acceptDropTarget).getText();
    }

    // Prevent Propagation

    public boolean isPreventPropagationTabDisplayed() {
        return getElement(preventPropagationTab).isDisplayed();
    }

    public void clickPreventPropagationTab() {
        getElement(preventPropagationTab).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(propagationDraggable)
        );
    }

    public void dragElementToOuterContainer() {
        dragAndDrop(propagationDraggable, outerDropTarget);
    }

    public void dragElementToInnerContainer() {
        WebElement source = getElement(propagationDraggable);
        WebElement target = getElement(innerDropTarget);

        actions.dragAndDrop(source, target).perform();

        wait.until(
                ExpectedConditions.textToBe(innerDropTarget, "Dropped!")
        );
    }

    public String getOuterDropTargetText() {
        return getElement(outerDropTarget).getText();
    }

    public String getInnerDropTargetText() {
        return getElement(innerDropTarget).getText();
    }

    // Revert Draggable

    public boolean isRevertDraggableTabDisplayed() {
        return getElement(revertDraggableTab).isDisplayed();
    }

    public void clickRevertDraggableTab() {
        getElement(revertDraggableTab).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(revertDraggable)
        );
    }

    public int getRevertDraggableX() {
        return getX(revertDraggable);
    }

    public int getRevertDraggableY() {
        return getY(revertDraggable);
    }

    public int getNotRevertDraggableX() {
        return getX(notRevertDraggable);
    }

    public int getNotRevertDraggableY() {
        return getY(notRevertDraggable);
    }

    public void dragRevertDraggableOutsideTarget() {
        dragOutsideTarget(revertDraggable);
    }

    public void dragNotRevertDraggableOutsideTarget() {
        dragOutsideTarget(notRevertDraggable);
    }

    private void dragOutsideTarget(By locator) {
        WebElement element = getElement(locator);

        actions.clickAndHold(element)
                .moveByOffset(250, 150)
                .release()
                .perform();
    }

    public void waitForRevertDraggableToReturn() {
        wait.until(driver ->
                getElement(revertDraggable).getLocation().getX() >= 0
        );

        wait.until(driver ->
                getElement(revertDraggable).getLocation().getY() >= 0
        );
    }

    public boolean hasNotRevertDraggableMoved(int initialX, int initialY) {
        Point currentPosition = getElement(notRevertDraggable).getLocation();

        return currentPosition.getX() != initialX
                || currentPosition.getY() != initialY;
    }
}
