package org.example;

import org.example.pages.DynamicPropertiesPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicPropertiesTests extends BaseTest{
    private WebDriver driver;
    private WebDriverWait wait;
    private DynamicPropertiesPage dynamicPropertiesPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/dynamic-properties");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        dynamicPropertiesPage = new DynamicPropertiesPage(driver);
    }

    @Test
    public void verifyButtonBecomesEnabledAfterDelayTest() {
        // Verify that the button is initially disabled.
        assertFalse(
                dynamicPropertiesPage.isEnableAfterButtonEnabled(),
                "The Enable After button should initially be disabled."
                        );

        // Wait until the button becomes enabled.
        wait.until(d -> dynamicPropertiesPage.isEnableAfterButtonEnabled());

        assertTrue(
                dynamicPropertiesPage.isEnableAfterButtonEnabled(),
                "The Enable After button should become enabled."
                        );
    }

    @Test
    public void verifyButtonTextColorChangesAfterDelayTest() {
        // Get the initial text color.
        String initialColor =
                dynamicPropertiesPage.getColorChangeButtonColor();

        // Wait until the text color changes.
        wait.until(d ->
                !dynamicPropertiesPage.getColorChangeButtonColor()
                        .equals(initialColor)
        );

        String changedColor =
                dynamicPropertiesPage.getColorChangeButtonColor();

        assertNotEquals(
                initialColor,
                changedColor,
                "The button text color should change after the delay."
        );

        assertEquals(
                "rgba(220, 53, 69, 1)",
                changedColor,
                "The button text should change to red."
        );
    }

    @Test
    public void verifyButtonBecomesVisibleAfterDelayTest() {
        // Verify that the button is initially hidden.
        assertFalse(
                dynamicPropertiesPage.isVisibleAfterButtonDisplayed(),
                "The Visible After button should initially be hidden."
                        );

        // Wait until the button becomes visible.
        wait.until(d -> dynamicPropertiesPage.isVisibleAfterButtonDisplayed());

        assertTrue(
                dynamicPropertiesPage.isVisibleAfterButtonDisplayed(),
                "The Visible After button should become visible."
                        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
