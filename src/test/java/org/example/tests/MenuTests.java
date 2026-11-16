package org.example.tests;

import org.example.BaseTest;
import org.example.pages.MenuPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuTests extends BaseTest{
    private WebDriver driver;
    private MenuPage menuPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/menu");

        menuPage = new MenuPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyMenuPageIsDisplayedTest() {
        assertTrue(menuPage.isPageDisplayed(),
                "Menu page should be displayed");
    }

    @Test
    public void verifyMainMenuItemsAreDisplayedTest() {
        assertTrue(menuPage.isMainMenuItemDisplayed("Main Item 1"));
        assertTrue(menuPage.isMainMenuItemDisplayed("Main Item 2"));
        assertTrue(menuPage.isMainMenuItemDisplayed("Main Item 3"));
    }

    @Test
    public void verifyMainItem2SubmenuIsDisplayedTest() {
        menuPage.hoverOverMainItem2();

        assertTrue(menuPage.isSubMenuItemDisplayed("Sub Item"));
        assertTrue(menuPage.isSubMenuItemDisplayed("Sub Item 2"));
        assertTrue(menuPage.isSubMenuItemDisplayed("Sub Item 3"));
    }

    @Test
    public void verifySubItem2NestedMenuIsDisplayedTest() {
        menuPage.hoverOverMainItem2();
        menuPage.hoverOverSubItem2();

        assertTrue(menuPage.isNestedSubMenuItemDisplayed("SUB SUB LIST"));
    }

    @Test
    public void verifyNestedSubMenuItemsAreDisplayedTest() {
        menuPage.hoverOverMainItem2();
        menuPage.hoverOverSubItem2();

        assertTrue(menuPage.isNestedSubMenuItemDisplayed("Sub Sub Item 1"));
        assertTrue(menuPage.isNestedSubMenuItemDisplayed("Sub Sub Item 2"));
    }

    @Test
    public void verifySubmenuDisappearsAfterMouseMovesAwayTest() {
        menuPage.hoverOverMainItem2();

        assertTrue(menuPage.isSubMenuItemDisplayed("Sub Item"));

        menuPage.moveMouseAway();

        assertFalse(menuPage.isSubMenuItemDisplayed("Sub Item"));
    }
}
