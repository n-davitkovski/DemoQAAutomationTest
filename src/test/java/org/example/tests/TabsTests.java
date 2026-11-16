package org.example.tests;

import org.example.BaseTest;
import org.example.pages.TabsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TabsTests extends BaseTest {
    private WebDriver driver;
    private TabsPage tabsPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/tabs");

        tabsPage = new TabsPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyWhatTabIsSelectedByDefaultTest() {
        assertTrue(
                tabsPage.isWhatTabSelected(),
                "The What tab should be selected by default."
                        );

        assertTrue(
                tabsPage.isWhatContentDisplayed(),
                "The What tab content should be displayed."
                        );
    }

    @Test
    public void verifyOriginTabCanBeSelectedTest() {
        tabsPage.clickOriginTab();

        assertTrue(
                tabsPage.isOriginTabSelected(),
                "The Origin tab should be selected."
                        );

        assertTrue(
                tabsPage.isOriginContentDisplayed(),
                "The Origin tab content should be displayed."
                        );
    }

    @Test
    public void verifyUseTabCanBeSelectedTest() {
        tabsPage.clickUseTab();

        assertTrue(
                tabsPage.isUseTabSelected(),
                "The Use tab should be selected."
                        );

        assertTrue(
                tabsPage.isUseContentDisplayed(),
                "The Use tab content should be displayed."
                        );
    }

    @Test
    public void verifyWhatTabDisplaysCorrectContentTest() {
        tabsPage.clickWhatTab();

        String content = tabsPage.getWhatTabContent();

        assertTrue(
                content.contains("Lorem Ipsum"),
                "The What tab should display its expected content."
                        );
    }

    @Test
    public void verifyOriginTabDisplaysCorrectContentTest() {
        tabsPage.clickOriginTab();

        String content = tabsPage.getOriginTabContent();

        assertTrue(
                content.contains("Contrary to popular belief"),
                "The Origin tab should display its expected content."
                        );
    }

    @Test
    public void verifyUseTabDisplaysCorrectContentTest() {
        tabsPage.clickUseTab();

        String content = tabsPage.getUseTabContent();

        assertTrue(
                content.contains("It is a long established fact"),
                "The Use tab should display its expected content."
                        );
    }

    @Test
    public void verifyOnlySelectedTabContentIsDisplayedTest() {
        tabsPage.clickOriginTab();

        assertTrue(tabsPage.isOriginContentDisplayed());

        assertFalse(
                tabsPage.isWhatContentDisplayed(),
                "The What tab content should be hidden when Origin is selected."
                        );

        assertFalse(
                tabsPage.isUseContentDisplayed(),
                "The Use tab content should be hidden when Origin is selected."
                        );
    }

    @Test
    public void verifyMoreTabIsDisabledTest() {
        assertTrue(
                tabsPage.isMoreTabDisabled(),
                "The More tab should be disabled."
                        );
    }

    @Test
    public void verifyDisabledMoreTabCannotBeSelectedTest() {
        tabsPage.clickWhatTab();

        assertTrue(tabsPage.isWhatTabSelected());

        assertFalse(
                tabsPage.clickMoreTab(),
                "The disabled More tab should not be selectable."
                        );

        assertTrue(
                tabsPage.isWhatTabSelected(),
                "The What tab should remain selected."
                        );
    }
}
