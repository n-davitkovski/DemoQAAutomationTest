package org.example.tests;

import org.example.BaseTest;
import org.example.pages.SelectMenuPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class SelectMenuTests extends BaseTest{
    private WebDriver driver;
    private SelectMenuPage selectMenuPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/select-menu");

        selectMenuPage = new SelectMenuPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifySelectMenuPageIsDisplayedTest() {
        assertTrue(selectMenuPage.isPageDisplayed(),
                "Select Menu page should be displayed");
    }

    @Test
    public void verifySelectValueOptionTest() {
        selectMenuPage.selectValue("Group 1, option 1");

        assertTrue(selectMenuPage.getSelectedValue().contains("Group 1, option 1"),
                "Selected value should be displayed");
    }

    @Test
    public void verifySelectOneOptionTest() {
        selectMenuPage.selectOne("Mrs.");

        assertTrue(selectMenuPage.getSelectedOne().contains("Mrs."),
                "Selected option should be displayed");
    }

    @Test
    public void verifyOldStyleSelectMenuTest() {
        selectMenuPage.selectOldStyleOption("Purple");

        assertEquals("Purple", selectMenuPage.getOldStyleSelectedOption());
    }

    @Test
    public void verifyMultipleOptionsCanBeSelectedTest() {
        selectMenuPage.selectMultipleColors("Red", "Blue");

        assertTrue(selectMenuPage.getSelectedMultipleColors().contains("Red"));
        assertTrue(selectMenuPage.getSelectedMultipleColors().contains("Blue"));
    }

    @Test
    public void verifyMultipleSelectedOptionsCanBeRemovedTest() {
        selectMenuPage.selectMultipleColors("Red", "Blue");

        selectMenuPage.removeMultipleColor("Red");

        assertFalse(selectMenuPage.getSelectedMultipleColors().contains("Red"));
        assertTrue(selectMenuPage.getSelectedMultipleColors().contains("Blue"));
    }

    @Test
    public void verifyStandardMultiSelectTest() {
        selectMenuPage.selectStandardMultipleOptions("Volvo", "Saab");

        assertTrue(selectMenuPage.getStandardSelectedOptions().contains("Volvo"));
        assertTrue(selectMenuPage.getStandardSelectedOptions().contains("Saab"));
    }

    @Test
    public void verifyStandardMultiSelectCanDeselectOptionTest() {
        selectMenuPage.selectStandardMultipleOptions("Volvo", "Saab");

        selectMenuPage.deselectStandardOption("Volvo");

        assertFalse(selectMenuPage.getStandardSelectedOptions().contains("Volvo"));
        assertTrue(selectMenuPage.getStandardSelectedOptions().contains("Saab"));
    }
}
