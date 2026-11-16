package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProgressBarPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By progressBar = By.cssSelector(
            "#progressBar [role='progressbar']"
    );

    private final By startStopButton = By.id("startStopButton");
    private final By resetButton = By.id("resetButton");

    public ProgressBarPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Check whether the progress bar is displayed
    public boolean isProgressBarDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(progressBar)
        ).isDisplayed();
    }

    // Get the current progress value
    public int getProgressValue() {
        WebElement bar = wait.until(
                ExpectedConditions.presenceOfElementLocated(progressBar)
        );

        return Integer.parseInt(
                bar.getAttribute("aria-valuenow")
        );
    }

    // Start the progress bar
    public void clickStartButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(startStopButton)
        ).click();

        wait.until(
                driver -> "Stop".equalsIgnoreCase(
                        driver.findElement(startStopButton).getText().trim()
                )
        );
    }

    // Stop the progress bar
    public void clickStopButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(startStopButton)
        ).click();

        wait.until(
                driver -> "Start".equalsIgnoreCase(
                        driver.findElement(startStopButton).getText().trim()
                )
        );
    }

    // Wait until the progress increases above zero
    public boolean waitForProgressToIncrease() {
        try {
            wait.until(driver -> getProgressValue() > 0);
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    // Wait until the progress bar reaches 100%
    public void waitForProgressToComplete() {
        wait.until(driver -> getProgressValue() == 100);
    }

    // Reset the progress bar
    public void clickResetButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(resetButton)
        ).click();

        wait.until(driver -> getProgressValue() == 0);
    }
}
