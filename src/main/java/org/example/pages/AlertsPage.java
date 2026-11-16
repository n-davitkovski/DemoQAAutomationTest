package org.example.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By alertButton = By.id("alertButton");
    private final By delayedAlertButton = By.id("timerAlertButton");
    private final By confirmationAlertButton = By.id("confirmButton");
    private final By promptAlertButton = By.id("promptButton");

    private final By confirmationResult = By.id("confirmResult");
    private final By promptResult = By.id("promptResult");

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAlertButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(alertButton)
        ).click();
    }

    // Click the delayed alert button
    public void clickDelayedAlertButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(delayedAlertButton)
        ).click();
    }

    // Click the confirmation alert button
    public void clickConfirmationAlertButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        confirmationAlertButton
                )
        ).click();
    }

    // Click the prompt alert button
    public void clickPromptAlertButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(promptAlertButton)
        ).click();
    }

    // Check whether an alert is present without waiting
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    // Wait for an alert to appear
    public boolean waitForAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    // Get alert text
    public String getAlertText() {
        Alert alert = wait.until(
                ExpectedConditions.alertIsPresent()
        );

        return alert.getText();
    }

    // Accept an alert by clicking OK
    public void acceptAlert() {
        wait.until(
                ExpectedConditions.alertIsPresent()
        ).accept();
    }

    // Dismiss an alert by clicking Cancel
    public void dismissAlert() {
        wait.until(
                ExpectedConditions.alertIsPresent()
        ).dismiss();
    }

    // Enter text into a prompt alert
    public void enterTextInAlert(String text) {
        wait.until(
                ExpectedConditions.alertIsPresent()
        ).sendKeys(text);
    }

    // Get confirmation alert result
    public String getConfirmationResult() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        confirmationResult
                )
        ).getText();
    }

    // Get prompt alert result
    public String getPromptResult() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        promptResult
                )
        ).getText();
    }
}
