package org.example;

import org.example.pages.BrokenLinksImagesPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrokenLinksImagesTests extends BaseTest{
    private WebDriver driver;
    private BrokenLinksImagesPage brokenLinksImagesPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/broken");

        brokenLinksImagesPage = new BrokenLinksImagesPage(driver);
    }

    @Test
    public void verifyValidImageLoadsCorrectlyTest() {
        assertTrue(
                brokenLinksImagesPage.isValidImageLoaded(),
                "The valid image should load successfully."
        );
    }

    @Test
    public void verifyBrokenImageDoesNotLoadTest() {
        assertFalse(
                brokenLinksImagesPage.isBrokenImageLoaded(),
                "The broken image should fail to load."
        );
    }

    @Test
    public void verifyValidLinkHasCorrectDestinationTest() {
        String href = brokenLinksImagesPage.getValidLinkHref();

        assertNotNull(href, "The valid link should have a destination.");
        assertTrue(
                href.contains("demoqa.com"),
                "The valid link should point to DemoQA."
        );
    }

    @Test
    public void verifyBrokenLinkHasDestinationTest() {
        String href = brokenLinksImagesPage.getBrokenLinkHref();

        assertNotNull(href, "The broken link should have a destination.");
        assertFalse(
                href.isBlank(),
                "The broken link should have a non-empty destination."
        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
