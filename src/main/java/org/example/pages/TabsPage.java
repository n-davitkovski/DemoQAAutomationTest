package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TabsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Tab buttons
    private final By whatTab = By.id("demo-tab-what");
    private final By originTab = By.id("demo-tab-origin");
    private final By useTab = By.id("demo-tab-use");
    private final By moreTab = By.id("demo-tab-more");

    // Tab content panels
    private final By whatContent = By.id("demo-tabpane-what");
    private final By originContent = By.id("demo-tabpane-origin");
    private final By useContent = By.id("demo-tabpane-use");

    public TabsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Click methods

    public void clickWhatTab() {
        wait.until(
                ExpectedConditions.elementToBeClickable(whatTab)
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(whatContent)
        );
    }

    public void clickOriginTab() {
        wait.until(
                ExpectedConditions.elementToBeClickable(originTab)
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(originContent)
        );
    }

    public void clickUseTab() {
        wait.until(
                ExpectedConditions.elementToBeClickable(useTab)
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(useContent)
        );
    }

    // Attempt to click the disabled More tab
    public boolean clickMoreTab() {
        WebElement tab = wait.until(
                ExpectedConditions.presenceOfElementLocated(moreTab)
        );

        if (!tab.isEnabled()
                || "true".equals(tab.getAttribute("aria-disabled"))) {
            return false;
        }

        tab.click();
        return true;
    }

    // Selected-state methods

    public boolean isWhatTabSelected() {
        return isTabSelected(whatTab);
    }

    public boolean isOriginTabSelected() {
        return isTabSelected(originTab);
    }

    public boolean isUseTabSelected() {
        return isTabSelected(useTab);
    }

    private boolean isTabSelected(By locator) {
        WebElement tab = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        return "true".equals(tab.getAttribute("aria-selected"));
    }

    // Content visibility methods

    public boolean isWhatContentDisplayed() {
        return isContentDisplayed(whatContent);
    }

    public boolean isOriginContentDisplayed() {
        return isContentDisplayed(originContent);
    }

    public boolean isUseContentDisplayed() {
        return isContentDisplayed(useContent);
    }

    private boolean isContentDisplayed(By locator) {
        for (WebElement element : driver.findElements(locator)) {
            if (element.isDisplayed()) {
                return true;
            }
        }

        return false;
    }

    // Content text methods

    public String getWhatTabContent() {
        return getContentText(whatContent);
    }

    public String getOriginTabContent() {
        return getContentText(originContent);
    }

    public String getUseTabContent() {
        return getContentText(useContent);
    }

    private String getContentText(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).getText().trim();
    }

    // Disabled-state method

    public boolean isMoreTabDisabled() {
        WebElement tab = wait.until(
                ExpectedConditions.presenceOfElementLocated(moreTab)
        );

        return !tab.isEnabled()
                || "true".equals(tab.getAttribute("aria-disabled"));
    }
}
