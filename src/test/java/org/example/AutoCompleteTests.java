package org.example;

import org.example.pages.AutoCompletePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class AutoCompleteTests extends BaseTest{
    private WebDriver driver;
    private AutoCompletePage autoCompletePage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/auto-complete");

        autoCompletePage = new AutoCompletePage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifySingleColorCanBeSelectedTest() {
        autoCompletePage.selectSingleColor("Red");

        assertEquals(
                "Red",
                autoCompletePage.getSelectedSingleColor(),
                "The selected color should be Red."
        );
    }

    @Test
    public void verifySingleColorCanBeChangedTest() {
        autoCompletePage.selectSingleColor("Red");
        autoCompletePage.selectSingleColor("Blue");

        assertEquals(
                "Blue",
                autoCompletePage.getSelectedSingleColor(),
                "The selected color should change to Blue."
        );
    }

    @Test
    public void verifyMultipleColorsCanBeSelectedTest() {
        autoCompletePage.selectMultipleColor("Red");
        autoCompletePage.selectMultipleColor("Blue");

        assertTrue(
                autoCompletePage.getSelectedMultipleColors().contains("Red"),
                "Red should be selected."
                        );

        assertTrue(
                autoCompletePage.getSelectedMultipleColors().contains("Blue"),
                "Blue should be selected."
                        );
    }

    @Test
    public void verifyMultipleColorsCanBeSelectedInAnyOrderTest() {
        autoCompletePage.selectMultipleColor("Green");
        autoCompletePage.selectMultipleColor("Red");

        assertEquals(
                2,
                autoCompletePage.getSelectedMultipleColors().size(),
                "Two colors should be selected."
        );

        assertTrue(
                autoCompletePage.getSelectedMultipleColors().contains("Green")
                                        && autoCompletePage.getSelectedMultipleColors().contains("Red"),
                "The selected colors should be Green and Red."
                        );
    }

    @Test
    public void verifyMultipleColorsCanBeRemovedTest() {
        autoCompletePage.selectMultipleColor("Red");
        autoCompletePage.selectMultipleColor("Blue");

        autoCompletePage.removeMultipleColor("Red");

        assertFalse(
                autoCompletePage.getSelectedMultipleColors().contains("Red"),
                "Red should be removed from the selected colors."
                        );

        assertTrue(
                autoCompletePage.getSelectedMultipleColors().contains("Blue"),
                "Blue should remain selected."
                        );
    }

    @Test
    public void verifySingleColorInputCanBeClearedTest() {
        autoCompletePage.selectSingleColor("Red");
        autoCompletePage.clearSingleColor();

        assertEquals(
                "",
                autoCompletePage.getSelectedSingleColor(),
                "The single-color selection should be cleared."
        );
    }

    @Test
    public void verifyMultipleColorInputCanBeClearedTest() {
        autoCompletePage.selectMultipleColor("Red");
        autoCompletePage.selectMultipleColor("Blue");

        autoCompletePage.clearMultipleColors();

        assertTrue(
                autoCompletePage.getSelectedMultipleColors().isEmpty(),
                "All selected colors should be removed."
                        );
    }
}
