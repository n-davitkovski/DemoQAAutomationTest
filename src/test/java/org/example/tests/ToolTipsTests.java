package org.example.tests;

import org.example.BaseTest;
import org.example.pages.ToolTipsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class ToolTipsTests extends BaseTest{
    private WebDriver driver;
    private ToolTipsPage toolTipsPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/tool-tips");

        toolTipsPage = new ToolTipsPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyToolTipsPageIsDisplayedTest() {
        assertTrue(toolTipsPage.isPageDisplayed(),
                "Tool Tips page should be displayed");
    }

    @Test
    public void verifyButtonTooltipTest() {
        toolTipsPage.hoverOverButton();

        assertEquals("You hovered over the Button",
                toolTipsPage.getTooltipText());
    }

    @Test
    public void verifyTextFieldTooltipTest() {
        toolTipsPage.hoverOverTextField();

        assertEquals("You hovered over the text field",
                toolTipsPage.getTooltipText());
    }

    @Test
    public void verifyContraryLinkTooltipTest() {
        toolTipsPage.hoverOverContraryLink();

        assertEquals("You hovered over the Contrary",
                toolTipsPage.getTooltipText());
    }

    @Test
    public void verifySectionLinkTooltipTest() {
        toolTipsPage.hoverOverSectionLink();

        assertEquals("You hovered over the 1.10.32",
                toolTipsPage.getTooltipText());
    }

    @Test
    public void verifyTooltipDisappearsAfterMouseMovesAwayTest() {
        toolTipsPage.hoverOverButton();

        assertTrue(toolTipsPage.isTooltipDisplayed(),
                "Tooltip should be displayed after hovering");

        toolTipsPage.moveMouseAway();

        assertFalse(toolTipsPage.isTooltipDisplayed(),
                "Tooltip should disappear after moving the mouse away");
    }
}
