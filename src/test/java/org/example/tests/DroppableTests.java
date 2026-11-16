package org.example.tests;

import org.example.BaseTest;
import org.example.pages.DroppablePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DroppableTests extends BaseTest{
    private WebDriver driver;
    private DroppablePage droppablePage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/droppable");

        droppablePage = new DroppablePage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // General

    @Test
    public void verifyDroppablePageIsDisplayedTest() {
        assertTrue(droppablePage.isPageDisplayed(),
                "Droppable page should be displayed");
    }

    // Simple

    @Test
    public void verifySimpleDraggableElementIsDisplayedTest() {
        assertTrue(droppablePage.isSimpleDraggableDisplayed());
    }

    @Test
    public void verifySimpleDropTargetIsDisplayedTest() {
        assertTrue(droppablePage.isSimpleDropTargetDisplayed());
    }

    @Test
    public void verifyElementCanBeDroppedIntoTargetTest() {
        droppablePage.dragSimpleElementToTarget();

        assertEquals("Dropped!",
                droppablePage.getSimpleDropTargetText());

        assertTrue(droppablePage.isSimpleDropTargetHighlighted());
    }

    // Accept

    @Test
    public void verifyAcceptTabIsDisplayedTest() {
        assertTrue(droppablePage.isAcceptTabDisplayed());
    }

    @Test
    public void verifyAcceptableElementCanBeDroppedTest() {
        droppablePage.clickAcceptTab();
        droppablePage.dragAcceptableElementToTarget();

        assertEquals("Dropped!",
                droppablePage.getAcceptDropTargetText());
    }

    @Test
    public void verifyNotAcceptableElementCannotBeDroppedTest() {
        droppablePage.clickAcceptTab();

        String initialText = droppablePage.getAcceptDropTargetText();

        droppablePage.dragNotAcceptableElementToTarget();

        assertEquals(initialText,
                droppablePage.getAcceptDropTargetText(),
                "Drop target text should not change for a non-acceptable element");
    }

    // Prevent Propagation

    @Test
    public void verifyPreventPropagationTabIsDisplayedTest() {
        assertTrue(droppablePage.isPreventPropagationTabDisplayed());
    }

    @Test
    public void verifyElementCanBeDroppedIntoOuterContainerTest() {
        droppablePage.clickPreventPropagationTab();
        droppablePage.dragElementToOuterContainer();

        assertEquals("Dropped!",
                droppablePage.getOuterDropTargetText());
    }

    @Test
    public void verifyElementCanBeDroppedIntoInnerContainerTest() {
        droppablePage.clickPreventPropagationTab();
        droppablePage.dragElementToInnerContainer();

        assertEquals("Dropped!",
                droppablePage.getInnerDropTargetText());
    }

    @Test
    public void verifyInnerDropDoesNotTriggerOuterDropTest() {
        droppablePage.clickPreventPropagationTab();
        droppablePage.dragElementToInnerContainer();

        assertEquals("Dropped!",
                droppablePage.getInnerDropTargetText());

        assertEquals("Outer droppable",
                droppablePage.getOuterDropTargetText(),
                "Dropping into the inner container should not trigger the outer drop");
    }

    // Revert Draggable

    @Test
    public void verifyRevertDraggableTabIsDisplayedTest() {
        assertTrue(droppablePage.isRevertDraggableTabDisplayed());
    }

    @Test
    public void verifyRevertDraggableReturnsToOriginalPositionTest() {
        droppablePage.clickRevertDraggableTab();

        int initialX = droppablePage.getRevertDraggableX();
        int initialY = droppablePage.getRevertDraggableY();

        droppablePage.dragRevertDraggableOutsideTarget();

        droppablePage.waitForRevertDraggableToReturn();

        assertEquals(initialX, droppablePage.getRevertDraggableX());
        assertEquals(initialY, droppablePage.getRevertDraggableY());
    }

    @Test
    public void verifyNotRevertDraggableStaysInNewPositionTest() {
        droppablePage.clickRevertDraggableTab();

        int initialX = droppablePage.getNotRevertDraggableX();
        int initialY = droppablePage.getNotRevertDraggableY();

        droppablePage.dragNotRevertDraggableOutsideTarget();

        assertTrue(droppablePage.hasNotRevertDraggableMoved(
                                        initialX, initialY),
                "Non-reverting draggable should stay in its new position");
    }
}
