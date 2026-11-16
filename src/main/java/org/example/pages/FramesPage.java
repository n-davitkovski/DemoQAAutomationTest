package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FramesPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstFrame = By.id("frame1");
    private final By secondFrame = By.id("frame2");
    private final By frameHeading = By.id("sampleHeading");

    public FramesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isFirstFrameDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstFrame)
        ).isDisplayed();
    }

    public boolean isSecondFrameDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(secondFrame)
        ).isDisplayed();
    }

    public String getFirstFrameText() {
        driver.switchTo().defaultContent();

        wait.until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(firstFrame)
        );

        try {
            WebElement heading = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(frameHeading)
            );

            return heading.getText();
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public String getSecondFrameText() {
        driver.switchTo().defaultContent();

        wait.until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(secondFrame)
        );

        try {
            WebElement heading = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(frameHeading)
            );

            return heading.getText();
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public String getFirstFrameHeading() {
        return getFirstFrameText();
    }

    public String getSecondFrameHeading() {
        return getSecondFrameText();
    }
}
