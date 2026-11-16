package org.example;

import org.example.pages.FramesPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FramesTests extends BaseTest{
    private WebDriver driver;
    private FramesPage framesPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/frames");

        framesPage = new FramesPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyFirstFrameDisplaysCorrectTextTest() {
        String actualText = framesPage.getFirstFrameText();

        assertEquals(
                "This is a sample page",
                actualText,
                "The first frame should display the expected text."
        );
    }

    @Test
    public void verifySecondFrameDisplaysCorrectTextTest() {
        String actualText = framesPage.getSecondFrameText();

        assertEquals(
                "This is a sample page",
                actualText,
                "The second frame should display the expected text."
        );
    }

    @Test
    public void verifyFirstFrameHasCorrectHeadingTest() {
        String actualHeading = framesPage.getFirstFrameHeading();

        assertEquals(
                "This is a sample page",
                actualHeading,
                "The first frame heading should match the expected text."
        );
    }

    @Test
    public void verifySecondFrameHasCorrectHeadingTest() {
        String actualHeading = framesPage.getSecondFrameHeading();

        assertEquals(
                "This is a sample page",
                actualHeading,
                "The second frame heading should match the expected text."
        );
    }

    @Test
    public void verifyPageContainsBothFramesTest() {
        assertTrue(
                framesPage.isFirstFrameDisplayed(),
                "The first frame should be displayed."
                        );

        assertTrue(
                framesPage.isSecondFrameDisplayed(),
                "The second frame should be displayed."
                        );
    }
}
