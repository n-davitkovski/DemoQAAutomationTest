package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ResizablePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By pageTitle = By.className("main-header");

    // First resizable box
    private By resizableBox = By.id("resizable");
    private By firstResizeHandle = By.cssSelector(
            "#resizable .react-resizable-handle"
    );

    // Second resizable box
    private By secondResizableBox = By.id("resizableBoxWithRestriction");
    private By secondResizeHandle = By.cssSelector(
            "#resizableBoxWithRestriction .react-resizable-handle"
    );

    public ResizablePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
    }

    public boolean isPageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText().equals("Resizable");
    }

    // First resizable box

    public boolean isResizableBoxDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(resizableBox)
        ).isDisplayed();
    }

    public int getResizableBoxWidth() {
        WebElement box = wait.until(
                ExpectedConditions.visibilityOfElementLocated(resizableBox)
        );

        return box.getSize().getWidth();
    }

    public int getResizableBoxHeight() {
        WebElement box = wait.until(
                ExpectedConditions.visibilityOfElementLocated(resizableBox)
        );

        return box.getSize().getHeight();
    }

    public void resizeBox(int xOffset, int yOffset) {
        WebElement handle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstResizeHandle)
        );

        actions.clickAndHold(handle)
                .moveByOffset(xOffset, yOffset)
                .release()
                .perform();
    }

    // Second resizable box

    public boolean isSecondResizableBoxDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(secondResizableBox)
        ).isDisplayed();
    }

    public int getSecondBoxWidth() {
        WebElement box = wait.until(
                ExpectedConditions.visibilityOfElementLocated(secondResizableBox)
        );

        return box.getSize().getWidth();
    }

    public int getSecondBoxHeight() {
        WebElement box = wait.until(
                ExpectedConditions.visibilityOfElementLocated(secondResizableBox)
        );

        return box.getSize().getHeight();
    }

    public void resizeSecondBox(int xOffset, int yOffset) {
        WebElement handle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(secondResizeHandle)
        );

        actions.clickAndHold(handle)
                .moveByOffset(xOffset, yOffset)
                .release()
                .perform();
    }
}
