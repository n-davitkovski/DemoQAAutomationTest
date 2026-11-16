package org.example.tests;

import org.example.BaseTest;
import org.example.pages.ResizablePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResizableTests extends BaseTest{
    private WebDriver driver;
    private ResizablePage resizablePage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/resizable");

        resizablePage = new ResizablePage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyResizablePageIsDisplayedTest() {
        assertTrue(resizablePage.isPageDisplayed(),
                "Resizable page should be displayed");
    }

    @Test
    public void verifyResizableBoxIsDisplayedTest() {
        assertTrue(resizablePage.isResizableBoxDisplayed(),
                "Resizable box should be displayed");
    }

    @Test
    public void verifyResizableBoxCanBeResizedTest() {
        int initialWidth = resizablePage.getResizableBoxWidth();
        int initialHeight = resizablePage.getResizableBoxHeight();

        resizablePage.resizeBox(150, 100);

        int updatedWidth = resizablePage.getResizableBoxWidth();
        int updatedHeight = resizablePage.getResizableBoxHeight();

        assertTrue(updatedWidth > initialWidth,
                "Box width should increase after resizing");

        assertTrue(updatedHeight > initialHeight,
                "Box height should increase after resizing");
    }

    @Test
    public void verifyResizableBoxMaximumDimensionsTest() {
        resizablePage.resizeBox(1000, 1000);

        int width = resizablePage.getResizableBoxWidth();
        int height = resizablePage.getResizableBoxHeight();

        assertTrue(width <= 500,
                "Resizable box width should not exceed 500px");

        assertTrue(height <= 300,
                "Resizable box height should not exceed 300px");
    }

    @Test
    public void verifySecondResizableBoxIsDisplayedTest() {
        assertTrue(resizablePage.isSecondResizableBoxDisplayed(),
                "Second resizable box should be displayed");
    }

    @Test
    public void verifySecondResizableBoxCanBeResizedTest() {
        int initialWidth = resizablePage.getSecondBoxWidth();
        int initialHeight = resizablePage.getSecondBoxHeight();

        resizablePage.resizeSecondBox(100, 80);

        int updatedWidth = resizablePage.getSecondBoxWidth();
        int updatedHeight = resizablePage.getSecondBoxHeight();

        assertTrue(updatedWidth > initialWidth,
                "Second box width should increase");

        assertTrue(updatedHeight > initialHeight,
                "Second box height should increase");
    }

    @Test
    public void verifySecondResizableBoxMinimumDimensionsTest() {
        resizablePage.resizeSecondBox(-1000, -1000);

        int width = resizablePage.getSecondBoxWidth();
        int height = resizablePage.getSecondBoxHeight();

        assertTrue(width >= 150,
                "Second box width should not be less than 150px");

        assertTrue(height >= 150,
                "Second box height should not be less than 150px");
    }

    @Test
    public void verifySecondResizableBoxMaximumDimensionsTest() {
        resizablePage.resizeSecondBox(1000, 1000);

        int width = resizablePage.getSecondBoxWidth();
        int height = resizablePage.getSecondBoxHeight();

        assertTrue(width <= 500,
                "Second box width should not exceed 500px");

        assertTrue(height <= 300,
                "Second box height should not exceed 300px");
    }
}
