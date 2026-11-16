package org.example.tests;

import org.example.BaseTest;
import org.example.pages.ProgressBarPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProgressBarTests extends BaseTest{
    private WebDriver driver;
    private ProgressBarPage progressBarPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/progress-bar");

        progressBarPage = new ProgressBarPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyProgressBarIsDisplayedTest() {
        assertTrue(
                progressBarPage.isProgressBarDisplayed(),
                "The progress bar should be displayed."
                        );
    }

    @Test
    public void verifyProgressBarHasDefaultValueTest() {
        assertEquals(
                0,
                progressBarPage.getProgressValue(),
                "The progress bar should initially have a value of 0%."
        );
    }

    @Test
    public void verifyProgressBarStartsTest() {
        progressBarPage.clickStartButton();

        assertTrue(
                progressBarPage.waitForProgressToIncrease(),
                "The progress bar should start increasing."
                        );
    }

    @Test
    public void verifyProgressBarCanBeStoppedTest() {
        progressBarPage.clickStartButton();

        assertTrue(
                progressBarPage.waitForProgressToIncrease(),
                "The progress bar should begin progressing."
                        );

        progressBarPage.clickStopButton();

        int stoppedValue = progressBarPage.getProgressValue();

        assertTrue(
                stoppedValue > 0 && stoppedValue < 100,
                "The progress bar should stop before reaching 100%."
                        );
    }

    @Test
    public void verifyProgressBarReaches100PercentTest() {
        progressBarPage.clickStartButton();

        progressBarPage.waitForProgressToComplete();

        assertEquals(
                100,
                progressBarPage.getProgressValue(),
                "The progress bar should reach 100%."
        );
    }

    @Test
    public void verifyProgressBarCanBeResetTest() {
        progressBarPage.clickStartButton();

        progressBarPage.waitForProgressToComplete();

        progressBarPage.clickResetButton();

        assertEquals(
                0,
                progressBarPage.getProgressValue(),
                "The progress bar should return to 0% after reset."
        );
    }
}
