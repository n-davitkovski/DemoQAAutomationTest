package org.example;

import org.example.pages.LinksPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinksTests extends BaseTest{
    private WebDriver driver;
    private LinksPage linksPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/links");

        linksPage = new LinksPage(driver);
    }

    @Test
    public void verifyHomeLinkOpensInSameTabTest() {
        String originalHandle = driver.getWindowHandle();

        linksPage.clickHomeLink();

        assertEquals(
                2,
                driver.getWindowHandles().size(),
                "The Home link should open a new tab."
        );

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        assertTrue(
                driver.getCurrentUrl().contains("demoqa.com"),
                "The new tab should navigate to DemoQA."
                        );
    }

    @Test
    public void verifyCreatedLinkReturns201Test() {
        linksPage.clickCreatedLink();

        assertTrue(
                linksPage.getLinkResponse().contains("201"),
                "The Created link should return a 201 response."
                        );
    }

    @Test
    public void verifyNoContentLinkReturns204Test() {
        linksPage.clickNoContentLink();

        assertTrue(
                linksPage.getLinkResponse().contains("204"),
                "The No Content link should return a 204 response."
                        );
    }

    @Test
    public void verifyMovedLinkReturns301Test() {
        linksPage.clickMovedLink();

        assertTrue(
                linksPage.getLinkResponse().contains("301"),
                "The Moved link should return a 301 response."
                        );
    }

    @Test
    public void verifyBadRequestLinkReturns400Test() {
        linksPage.clickBadRequestLink();

        assertTrue(
                linksPage.getLinkResponse().contains("400"),
                "The Bad Request link should return a 400 response."
                        );
    }

    @Test
    public void verifyUnauthorizedLinkReturns401Test() {
        linksPage.clickUnauthorizedLink();

        assertTrue(
                linksPage.getLinkResponse().contains("401"),
                "The Unauthorized link should return a 401 response."
                        );
    }

    @Test
    public void verifyForbiddenLinkReturns403Test() {
        linksPage.clickForbiddenLink();

        assertTrue(
                linksPage.getLinkResponse().contains("403"),
                "The Forbidden link should return a 403 response."
                        );
    }

    @Test
    public void verifyInvalidUrlLinkReturns500Test() {
        linksPage.clickInvalidUrlLink();

        assertTrue(
                linksPage.getLinkResponse().contains("500"),
                "The Invalid URL link should return a 500 response."
                        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
