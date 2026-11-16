package org.example;

import org.example.pages.AccordianPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccordianTests extends BaseTest{
    private WebDriver driver;
    private AccordianPage accordianPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/accordian");

        accordianPage = new AccordianPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyFirstSectionIsExpandedByDefaultTest() {
        assertTrue(
                accordianPage.isFirstSectionExpanded(),
                "The first accordion section should be expanded by default."
                        );
    }

    @Test
    public void verifyFirstSectionCanBeCollapsedTest() {
        accordianPage.collapseFirstSection();

        assertFalse(
                accordianPage.isFirstSectionExpanded(),
                "The first accordion section should collapse when clicked."
                        );
    }

    @Test
    public void verifyFirstSectionExpandsTest() {
        accordianPage.expandFirstSection();

        assertTrue(
                accordianPage.isFirstSectionExpanded(),
                "The first accordion section should be expanded."
                        );
    }

    @Test
    public void verifySecondSectionExpandsTest() {
        accordianPage.expandSecondSection();

        assertTrue(
                accordianPage.isSecondSectionExpanded(),
                "The second accordion section should be expanded."
                        );
    }

    @Test
    public void verifyThirdSectionExpandsTest() {
        accordianPage.expandThirdSection();

        assertTrue(
                accordianPage.isThirdSectionExpanded(),
                "The third accordion section should be expanded."
                        );
    }

    @Test
    public void verifySecondSectionDisplaysContentTest() {
        accordianPage.expandSecondSection();

        String content = accordianPage.getSecondSectionContent();

        assertFalse(
                content.isBlank(),
                "The second accordion section should display content."
                        );
    }

    @Test
    public void verifyThirdSectionDisplaysContentTest() {
        accordianPage.expandThirdSection();

        String content = accordianPage.getThirdSectionContent();

        assertFalse(
                content.isBlank(),
                "The third accordion section should display content."
                        );
    }

    @Test
    public void verifyOnlyOneSectionIsExpandedAtATimeTest() {
        accordianPage.expandSecondSection();

        assertTrue(
                accordianPage.isSecondSectionExpanded(),
                "The second section should be expanded."
                        );

        assertFalse(
                accordianPage.isFirstSectionExpanded(),
                "The first section should collapse when the second expands."
                        );

        accordianPage.expandThirdSection();

        assertTrue(
                accordianPage.isThirdSectionExpanded(),
                "The third section should be expanded."
                        );

        assertFalse(
                accordianPage.isSecondSectionExpanded(),
                "The second section should collapse when the third expands."
                        );
    }
}
