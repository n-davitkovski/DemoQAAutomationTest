package org.example.tests;

import org.example.BaseTest;
import org.example.pages.DraggablePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DraggableTests extends BaseTest{
    private WebDriver driver;
    private DraggablePage draggablePage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/dragabble");

        draggablePage = new DraggablePage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyDraggablePageIsDisplayedTest() {
        assertTrue(draggablePage.isPageDisplayed(),
                "Draggable page should be displayed");
    }

    // Simple

    @Test
    public void verifySimpleDraggableElementIsDisplayedTest() {
        assertTrue(draggablePage.isSimpleDraggableDisplayed(),
                "Simple draggable element should be displayed");
    }

    @Test
    public void verifySimpleDraggableElementCanBeMovedTest() {
        int initialX = draggablePage.getSimpleElementX();
        int initialY = draggablePage.getSimpleElementY();

        draggablePage.dragSimpleElement(100, 50);

        int updatedX = draggablePage.getSimpleElementX();
        int updatedY = draggablePage.getSimpleElementY();

        assertNotEquals(initialX, updatedX,
                "Element X position should change");

        assertNotEquals(initialY, updatedY,
                "Element Y position should change");
    }

    // Axis Restricted

    @Test
    public void verifyAxisRestrictedTabIsDisplayedTest() {
        assertTrue(draggablePage.isAxisRestrictedTabDisplayed());
    }

    @Test
    public void verifyOnlyHorizontalMovementIsAllowedTest() {
        draggablePage.clickAxisRestrictedTab();

        int initialX = draggablePage.getHorizontalElementX();
        int initialY = draggablePage.getHorizontalElementY();

        draggablePage.dragHorizontalElement(100, 100);

        int updatedX = draggablePage.getHorizontalElementX();
        int updatedY = draggablePage.getHorizontalElementY();

        assertNotEquals(initialX, updatedX,
                "Horizontal element should move on X axis");

        assertEquals(initialY, updatedY,
                "Horizontal element should not move on Y axis");
    }

    @Test
    public void verifyOnlyVerticalMovementIsAllowedTest() {
        draggablePage.clickAxisRestrictedTab();

        int initialX = draggablePage.getVerticalElementX();
        int initialY = draggablePage.getVerticalElementY();

        draggablePage.dragVerticalElement(100, 100);

        int updatedX = draggablePage.getVerticalElementX();
        int updatedY = draggablePage.getVerticalElementY();

        assertEquals(initialX, updatedX,
                "Vertical element should not move on X axis");

        assertNotEquals(initialY, updatedY,
                "Vertical element should move on Y axis");
    }

    // Container Restricted

    @Test
    public void verifyContainerRestrictedTabIsDisplayedTest() {
        assertTrue(draggablePage.isContainerRestrictedTabDisplayed());
    }

    @Test
    public void verifyElementCanBeDraggedWithinContainerTest() {
        draggablePage.clickContainerRestrictedTab();

        int initialX = draggablePage.getContainedElementX();
        int initialY = draggablePage.getContainedElementY();

        draggablePage.dragContainedElement(50, 50);

        int updatedX = draggablePage.getContainedElementX();
        int updatedY = draggablePage.getContainedElementY();

        assertTrue(initialX != updatedX || initialY != updatedY,
                "Element should move within its container");
    }

    // Cursor Style

    @Test
    public void verifyCursorStyleTabIsDisplayedTest() {
        assertTrue(draggablePage.isCursorStyleTabDisplayed());
    }

    @Test
    public void verifyElementCanBeDraggedUsingCenterCursorTest() {
        draggablePage.clickCursorStyleTab();

        int initialX = draggablePage.getCursorCenterElementX();

        draggablePage.dragCursorCenterElement(80, 50);

        int updatedX = draggablePage.getCursorCenterElementX();

        assertNotEquals(initialX, updatedX,
                "Element should move when dragged from its center");
    }

    @Test
    public void verifyElementCanBeDraggedUsingTopLeftCursorTest() {
        draggablePage.clickCursorStyleTab();

        int initialX = draggablePage.getCursorTopLeftElementX();

        draggablePage.dragCursorTopLeftElement(80, 50);

        int updatedX = draggablePage.getCursorTopLeftElementX();

        assertNotEquals(initialX, updatedX,
                "Element should move when dragged from its top-left area");
    }

    @Test
    public void verifyElementCanBeDraggedUsingBottomCursorTest() {
        draggablePage.clickCursorStyleTab();

        int initialX = draggablePage.getCursorBottomElementX();

        draggablePage.dragCursorBottomElement(80, 50);

        int updatedX = draggablePage.getCursorBottomElementX();

        assertNotEquals(initialX, updatedX,
                "Element should move when dragged from its bottom area");
    }
}
