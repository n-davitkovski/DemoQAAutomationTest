package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ToolTipsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By pageTitle = By.className("main-header");

    private By button = By.id("toolTipButton");
    private By textField = By.id("toolTipTextField");
    private By contraryLink = By.id("texToolTopContainer");
    private By sectionLink = By.id("sectionToolTip");

    private By tooltip = By.className("tooltip-inner");

    public ToolTipsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
    }

    public boolean isPageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText().equals("Tool Tips");
    }

    public void hoverOverButton() {
        actions.moveToElement(
                wait.until(ExpectedConditions.visibilityOfElementLocated(button))
        ).perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(tooltip));
    }

    public void hoverOverTextField() {
        actions.moveToElement(
                wait.until(ExpectedConditions.visibilityOfElementLocated(textField))
        ).perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(tooltip));
    }

    public void hoverOverContraryLink() {
        actions.moveToElement(
                wait.until(ExpectedConditions.visibilityOfElementLocated(contraryLink))
        ).perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(tooltip));
    }

    public void hoverOverSectionLink() {
        actions.moveToElement(
                wait.until(ExpectedConditions.visibilityOfElementLocated(sectionLink))
        ).perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(tooltip));
    }

    public String getTooltipText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(tooltip)
        ).getText();
    }

    public boolean isTooltipDisplayed() {
        return !driver.findElements(tooltip).isEmpty()
                && driver.findElement(tooltip).isDisplayed();
    }

    public void moveMouseAway() {
        actions.moveToElement(
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle))
        ).perform();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(tooltip));
    }
}
