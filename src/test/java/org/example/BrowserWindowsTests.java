package org.example;

import org.example.pages.BrowserWindowsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BrowserWindowsTests extends BaseTest{
    private WebDriver driver;
    private WebDriverWait wait;
    private BrowserWindowsPage browserWindowsPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demoqa.com/browser-windows");

        browserWindowsPage = new BrowserWindowsPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyNewTabOpensTest() {

        String originalWindow = driver.getWindowHandle();

        browserWindowsPage.clickNewTab();

        wait.until(d -> d.getWindowHandles().size() == 2);

        Set<String> windowHandles = driver.getWindowHandles();

        assertEquals(
                2,
                windowHandles.size(),
                "A new tab should open."
        );

        // Switch to the new tab
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        assertEquals(
                "This is a sample page",
                browserWindowsPage.getSamplePageHeading(),
                "The new tab should display the expected heading."
        );

        // Return to the original window
        driver.switchTo().window(originalWindow);

        assertTrue(
                driver.getCurrentUrl().contains("browser-windows"),
                "The original browser window should remain open."
                        );
    }

    @Test
    public void verifyNewWindowOpensTest() {

        String originalWindow = driver.getWindowHandle();

        browserWindowsPage.clickNewWindow();

        wait.until(d -> d.getWindowHandles().size() == 2);

        Set<String> windowHandles = driver.getWindowHandles();

        assertEquals(
                2,
                windowHandles.size(),
                "A new browser window should open."
        );

        // Switch to the new window
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        assertEquals(
                "This is a sample page",
                browserWindowsPage.getSamplePageHeading(),
                "The new window should display the expected heading."
        );

        // Return to the original window
        driver.switchTo().window(originalWindow);

        assertTrue(
                driver.getCurrentUrl().contains("browser-windows"),
                "The original browser window should remain open."
                        );
    }

    @Test
    public void verifyNewWindowMessageOpensTest() {

        String originalWindow = driver.getWindowHandle();

        browserWindowsPage.clickNewWindowMessage();

        wait.until(d -> d.getWindowHandles().size() == 2);

        Set<String> windowHandles = driver.getWindowHandles();

        assertEquals(
                2,
                windowHandles.size(),
                "A new window should open."
        );

        // Switch to the new window
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        wait.until(d -> !d.getPageSource().isBlank());

        String pageSource = driver.getPageSource();

        assertTrue(
                pageSource.contains("Knowledge increases by sharing"),
                "The new window should display the expected message."
                        );

        // Return to the original window
        driver.switchTo().window(originalWindow);

        assertTrue(
                driver.getCurrentUrl().contains("browser-windows"),
                "The original browser window should remain open."
                        );
    }
}
