package org.example;

import org.example.pages.RadioButtonPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class RadioButtonTests extends BaseTest{
    private WebDriver driver;
    private RadioButtonPage radioButtonPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/radio-button");

        radioButtonPage = new RadioButtonPage(driver);
    }

    @Test
    public void verifyYesRadioButtonCanBeSelectedTest() {
        radioButtonPage.selectYes();

        assertTrue(
                radioButtonPage.isYesSelected(),
                "The Yes radio button should be selected."
                        );

        assertTrue(
                radioButtonPage.getResultText().contains("Yes"),
                "The result should display Yes."
                        );
    }

    @Test
    public void verifyImpressiveRadioButtonCanBeSelectedTest() {
        radioButtonPage.selectImpressive();

        assertTrue(
                radioButtonPage.isImpressiveSelected(),
                "The Impressive radio button should be selected."
                        );

        assertTrue(
                radioButtonPage.getResultText().contains("Impressive"),
                "The result should display Impressive."
                        );
    }

    @Test
    public void verifySelectingImpressiveDeselectsYesTest() {
        radioButtonPage.selectYes();

        assertTrue(
                radioButtonPage.isYesSelected(),
                "The Yes radio button should initially be selected."
                        );

        radioButtonPage.selectImpressive();

        assertTrue(
                radioButtonPage.isImpressiveSelected(),
                "The Impressive radio button should be selected."
                        );

        assertFalse(
                radioButtonPage.isYesSelected(),
                "The Yes radio button should no longer be selected."
                        );
    }

    @Test
    public void verifyNoRadioButtonIsDisabledTest() {
        assertFalse(
                radioButtonPage.isNoEnabled(),
                "The No radio button should be disabled."
                        );
    }

    @Test
    public void verifySelectionResultUpdatesCorrectlyTest() {
        radioButtonPage.selectYes();

        assertEquals(
                "Yes",
                radioButtonPage.getResultText(),
                "The result should display Yes after selecting Yes."
        );

        radioButtonPage.selectImpressive();

        assertEquals(
                "Impressive",
                radioButtonPage.getResultText(),
                "The result should update to Impressive."
        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
