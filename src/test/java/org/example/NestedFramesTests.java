package org.example;

import org.example.pages.NestedFramesPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NestedFramesTests extends BaseTest{
    private WebDriver driver;
    private NestedFramesPage nestedFramesPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/nestedframes");

        nestedFramesPage = new NestedFramesPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyParentFrameDisplaysCorrectTextTest() {
        String actualText = nestedFramesPage.getParentFrameText();

        assertEquals(
                "Parent frame",
                actualText,
                "The parent frame should display the expected text."
        );
    }

    @Test
    public void verifyChildFrameDisplaysCorrectTextTest() {
        String actualText = nestedFramesPage.getChildFrameText();

        assertEquals(
                "Child Iframe",
                actualText,
                "The child frame should display the expected text."
        );
    }

    @Test
    public void verifyParentFrameIsDisplayedTest() {
        assertTrue(
                nestedFramesPage.isParentFrameDisplayed(),
                "The parent frame should be displayed."
                        );
    }

    @Test
    public void verifyChildFrameIsDisplayedTest() {
        assertTrue(
                nestedFramesPage.isChildFrameDisplayed(),
                "The child frame should be displayed."
                        );
    }

    @Test
    public void verifyChildFrameIsNestedInsideParentFrameTest() {
        assertTrue(
                nestedFramesPage.isChildFrameNestedInsideParent(),
                "The child frame should be nested inside the parent frame."
                        );
    }
}
