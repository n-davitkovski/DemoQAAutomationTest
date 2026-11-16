package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccordianPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Accordion headings
    private final By firstSectionHeading = By.id("section1Heading");
    private final By secondSectionHeading = By.id("section2Heading");
    private final By thirdSectionHeading = By.id("section3Heading");

    // Accordion content
    private final By firstSectionContent = By.id("section1Content");
    private final By secondSectionContent = By.id("section2Content");
    private final By thirdSectionContent = By.id("section3Content");

    public AccordianPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Click methods

    public void expandFirstSection() {
        if (!isFirstSectionExpanded()) {
            wait.until(
                    ExpectedConditions.elementToBeClickable(firstSectionHeading)
            ).click();
        }
    }

    public void collapseFirstSection() {
        if (isFirstSectionExpanded()) {
            wait.until(
                    ExpectedConditions.elementToBeClickable(firstSectionHeading)
            ).click();
        }
    }

    public void expandSecondSection() {
        if (!isSecondSectionExpanded()) {
            wait.until(
                    ExpectedConditions.elementToBeClickable(secondSectionHeading)
            ).click();
        }
    }

    public void expandThirdSection() {
        if (!isThirdSectionExpanded()) {
            wait.until(
                    ExpectedConditions.elementToBeClickable(thirdSectionHeading)
            ).click();
        }
    }

    // Visibility and expanded-state methods

    public boolean isFirstSectionExpanded() {
        return isSectionExpanded(firstSectionContent);
    }

    public boolean isSecondSectionExpanded() {
        return isSectionExpanded(secondSectionContent);
    }

    public boolean isThirdSectionExpanded() {
        return isSectionExpanded(thirdSectionContent);
    }

    private boolean isSectionExpanded(By contentLocator) {
        WebElement content = wait.until(
                ExpectedConditions.presenceOfElementLocated(contentLocator)
        );

        return content.isDisplayed()
                && "true".equals(content.getAttribute("aria-hidden")) == false;
    }

    // Content methods

    public String getFirstSectionContent() {
        return getContent(firstSectionContent);
    }

    public String getSecondSectionContent() {
        return getContent(secondSectionContent);
    }

    public String getThirdSectionContent() {
        return getContent(thirdSectionContent);
    }

    private String getContent(By contentLocator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(contentLocator)
        ).getText().trim();
    }
}
