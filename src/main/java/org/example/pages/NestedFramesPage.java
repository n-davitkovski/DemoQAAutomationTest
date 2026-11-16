package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NestedFramesPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By parentFrame = By.id("frame1");
    private final By childFrame = By.tagName("iframe");
    private final By parentBody = By.tagName("body");
    private final By childBody = By.tagName("body");

    public NestedFramesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getParentFrameText() {
        driver.switchTo().defaultContent();

        wait.until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(parentFrame)
        );

        try {
            WebElement body = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(parentBody)
            );

            return body.getText().trim();
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public String getChildFrameText() {
        driver.switchTo().defaultContent();

        wait.until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(parentFrame)
        );

        try {
            wait.until(
                    ExpectedConditions.frameToBeAvailableAndSwitchToIt(childFrame)
            );

            WebElement body = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(childBody)
            );

            return body.getText().trim();
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public boolean isParentFrameDisplayed() {
        driver.switchTo().defaultContent();

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(parentFrame)
        ).isDisplayed();
    }

    public boolean isChildFrameDisplayed() {
        driver.switchTo().defaultContent();

        wait.until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(parentFrame)
        );

        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(childFrame)
            ).isDisplayed();
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public boolean isChildFrameNestedInsideParent() {
        driver.switchTo().defaultContent();

        wait.until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(parentFrame)
        );

        try {
            return !driver.findElements(childFrame).isEmpty();
        } finally {
            driver.switchTo().defaultContent();
        }
    }
}
