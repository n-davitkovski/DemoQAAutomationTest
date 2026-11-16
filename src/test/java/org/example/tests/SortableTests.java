package org.example.tests;

import org.example.BaseTest;
import org.example.pages.SortablePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortableTests extends BaseTest{
    private WebDriver driver;
    private SortablePage sortablePage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/sortable");

        sortablePage = new SortablePage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifySortablePageIsDisplayedTest() {
        assertTrue(sortablePage.isPageDisplayed(),
                "Sortable page should be displayed");
    }

    @Test
    public void verifyListItemsAreDisplayedTest() {
        List<String> expectedItems = Arrays.asList(
                "One", "Two", "Three", "Four", "Five", "Six"
        );

        assertEquals(expectedItems, sortablePage.getListItems());
    }

    @Test
    public void verifyListItemsCanBeReorderedTest() {
        List<String> originalOrder = sortablePage.getListItems();

        sortablePage.dragListItem("One", "Four");

        List<String> updatedOrder = sortablePage.getListItems();

        assertNotEquals(originalOrder, updatedOrder,
                "List order should change after dragging an item");
    }

    @Test
    public void verifyListItemCanBeMovedToFirstPositionTest() {
        sortablePage.dragListItem("Six", "One");

        List<String> updatedOrder = sortablePage.getListItems();

        assertEquals("Six", updatedOrder.get(0),
                "Six should be moved to the first position");
    }

    @Test
    public void verifyGridItemsAreDisplayedTest() {
        List<String> expectedItems = Arrays.asList(
                "One", "Two", "Three",
                "Four", "Five", "Six",
                "Seven", "Eight", "Nine"
        );

        assertEquals(expectedItems, sortablePage.getGridItems());
    }

    @Test
    public void verifyGridItemsCanBeReorderedTest() {
        List<String> originalOrder = sortablePage.getGridItems();

        sortablePage.dragGridItem("One", "Five");

        List<String> updatedOrder = sortablePage.getGridItems();

        assertNotEquals(originalOrder, updatedOrder,
                "Grid order should change after dragging an item");
    }

    @Test
    public void verifyGridItemCanBeMovedToLastPositionTest() {
        sortablePage.dragGridItem("One", "Nine");

        List<String> updatedOrder = sortablePage.getGridItems();

        assertEquals("One", updatedOrder.get(8),
                "One should be moved to the last position");
    }
}
