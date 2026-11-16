package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserWindowsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By newTabButton = By.id("tabButton");
    private final By newWindowButton = By.id("windowButton");
    private final By newWindowMessageButton =
            By.id("messageWindowButton");

    private final By samplePageHeading =
            By.id("sampleHeading");

    public BrowserWindowsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickNewTab() {
        wait.until(
                ExpectedConditions.elementToBeClickable(newTabButton)
        ).click();
    }

    public void clickNewWindow() {
        wait.until(
                ExpectedConditions.elementToBeClickable(newWindowButton)
        ).click();
    }

    public void clickNewWindowMessage() {
        wait.until(
                ExpectedConditions.elementToBeClickable(newWindowMessageButton)
        ).click();
    }

    public String getSamplePageHeading() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        samplePageHeading
                )
        ).getText();
    }
}
