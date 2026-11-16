package org.example.tests;

import org.example.BaseTest;
import org.example.pages.SelectablePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SelectableTests extends BaseTest{
    private WebDriver driver;
    private SelectablePage selectablePage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/selectable");

        selectablePage = new SelectablePage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifySelectablePageIsDisplayedTest() {
        assertTrue(selectablePage.isPageDisplayed(),
                "Selectable page should be displayed");
    }

    @Test
    public void verifyListItemsAreDisplayedTest() {
        List<String> expectedItems = Arrays.asList(
                "Cras justo odio",
                "Dapibus ac facilisis in",
                "Morbi leo risus",
                "Porta ac consectetur ac"
        );

        assertEquals(expectedItems, selectablePage.getListItems());
    }

    @Test
    public void verifyListItemCanBeSelectedTest() {
        selectablePage.selectListItem("Cras justo odio");

        assertTrue(selectablePage.isListItemSelected("Cras justo odio"),
                "List item should be selected");
    }

    @Test
    public void verifyMultipleListItemsCanBeSelectedTest() {
        selectablePage.selectListItem("Cras justo odio");
        selectablePage.selectListItem("Dapibus ac facilisis in");

        assertTrue(selectablePage.isListItemSelected("Cras justo odio"));
        assertTrue(selectablePage.isListItemSelected("Dapibus ac facilisis in"));
    }

    @Test
    public void verifySelectedListItemCanBeDeselectedTest() {
        selectablePage.selectListItem("Cras justo odio");

        assertTrue(selectablePage.isListItemSelected("Cras justo odio"));

        selectablePage.selectListItem("Cras justo odio");

        assertFalse(selectablePage.isListItemSelected("Cras justo odio"),
                "List item should be deselected after clicking it again");
    }

    @Test
    public void verifySelectingAnotherListItemDoesNotDeselectPreviousItemTest() {
        selectablePage.selectListItem("Cras justo odio");
        selectablePage.selectListItem("Dapibus ac facilisis in");

        assertTrue(selectablePage.isListItemSelected("Cras justo odio"));
        assertTrue(selectablePage.isListItemSelected("Dapibus ac facilisis in"));
    }

    @Test
    public void verifyGridItemsAreDisplayedTest() {
        selectablePage.clickGridTab();

        List<String> expectedItems = Arrays.asList(
                "One", "Two", "Three",
                "Four", "Five", "Six",
                "Seven", "Eight", "Nine"
        );

        assertEquals(expectedItems, selectablePage.getGridItems());
    }

    @Test
    public void verifyGridItemCanBeSelectedTest() {
        selectablePage.clickGridTab();
        selectablePage.selectGridItem("One");

        assertTrue(selectablePage.isGridItemSelected("One"),
                "Grid item should be selected");
    }

    @Test
    public void verifyMultipleGridItemsCanBeSelectedTest() {
        selectablePage.clickGridTab();

        selectablePage.selectGridItem("One");
        selectablePage.selectGridItem("Five");
        selectablePage.selectGridItem("Nine");

        assertTrue(selectablePage.isGridItemSelected("One"));
        assertTrue(selectablePage.isGridItemSelected("Five"));
        assertTrue(selectablePage.isGridItemSelected("Nine"));
    }

    @Test
    public void verifySelectedGridItemCanBeDeselectedTest() {
        selectablePage.clickGridTab();

        selectablePage.selectGridItem("One");

        assertTrue(selectablePage.isGridItemSelected("One"));

        selectablePage.selectGridItem("One");

        assertFalse(selectablePage.isGridItemSelected("One"));
    }

    @Test
    public void verifySelectingAnotherGridItemDoesNotDeselectPreviousItemTest() {
        selectablePage.clickGridTab();

        selectablePage.selectGridItem("One");
        selectablePage.selectGridItem("Two");

        assertTrue(selectablePage.isGridItemSelected("One"));
        assertTrue(selectablePage.isGridItemSelected("Two"));
    }
}
