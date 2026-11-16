package org.example.tests;

import org.example.BaseTest;
import org.example.pages.SliderPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SliderTests extends BaseTest{
    private WebDriver driver;
    private SliderPage sliderPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/slider");

        sliderPage = new SliderPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifySliderIsDisplayedTest() {
        assertTrue(
                sliderPage.isSliderDisplayed(),
                "The slider should be displayed."
        );
    }

    @Test
    public void verifySliderHasDefaultValueTest() {
        assertEquals(
                "25",
                sliderPage.getSliderValue(),
                "The slider should have a default value of 25."
        );
    }

    @Test
    public void verifySliderCanMoveToMinimumValueTest() {
        sliderPage.setSliderValue(0);

        assertEquals(
                "0",
                sliderPage.getSliderValue(),
                "The slider should move to the minimum value of 0."
        );
    }

    @Test
    public void verifySliderCanMoveToMaximumValueTest() {
        sliderPage.setSliderValue(100);

        assertEquals(
                "100",
                sliderPage.getSliderValue(),
                "The slider should move to the maximum value of 100."
        );
    }

    @Test
    public void verifySliderValueCanBeChangedTest() {
        sliderPage.setSliderValue(50);

        assertEquals(
                "50",
                sliderPage.getSliderValue(),
                "The slider value should change to 50."
        );
    }

    @Test
    public void verifySliderValueStaysWithinAllowedRangeTest() {
        sliderPage.setSliderValue(75);

        int actualValue = Integer.parseInt(sliderPage.getSliderValue());

        assertTrue(
                actualValue >= 0 && actualValue <= 100,
                "The slider value should remain between 0 and 100."
        );
    }
}
