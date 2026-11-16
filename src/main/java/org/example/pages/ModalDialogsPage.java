package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ModalDialogsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Buttons
    private final By smallModalButton = By.id("showSmallModal");
    private final By largeModalButton = By.id("showLargeModal");

    // Small modal
    private final By smallModal = By.id("example-modal-sizes-title-sm");
    private final By smallModalTitle = By.id("example-modal-sizes-title-sm");
    private final By smallModalContent = By.className("modal-body");
    private final By smallModalCloseButton =
            By.cssSelector("#closeSmallModal");

    // Large modal
    private final By largeModal = By.id("example-modal-sizes-title-lg");
    private final By largeModalTitle = By.id("example-modal-sizes-title-lg");
    private final By largeModalContent = By.className("modal-body");
    private final By largeModalCloseButton =
            By.cssSelector("#closeLargeModal");

    public ModalDialogsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Small modal methods

    public void clickSmallModalButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(smallModalButton)
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(smallModal)
        );
    }

    public boolean isSmallModalDisplayed() {
        return driver.findElements(smallModal).stream()
                .anyMatch(element -> element.isDisplayed());
    }

    public String getSmallModalTitle() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(smallModalTitle)
        ).getText();
    }

    public String getSmallModalContent() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(smallModalContent)
        ).getText();
    }

    public void clickSmallModalCloseButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(smallModalCloseButton)
        ).click();

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(smallModal)
        );
    }

    // Large modal methods

    public void clickLargeModalButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(largeModalButton)
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(largeModal)
        );
    }

    public boolean isLargeModalDisplayed() {
        return driver.findElements(largeModal).stream()
                .anyMatch(element -> element.isDisplayed());
    }

    public String getLargeModalTitle() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(largeModalTitle)
        ).getText();
    }

    public String getLargeModalContent() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(largeModalContent)
        ).getText();
    }

    public void clickLargeModalCloseButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(largeModalCloseButton)
        ).click();

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(largeModal)
        );
    }
}
