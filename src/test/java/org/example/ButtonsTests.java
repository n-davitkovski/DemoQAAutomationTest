package org.example;

import org.example.pages.ButtonsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ButtonsTests extends BaseTest{
    private WebDriver driver;
    private ButtonsPage buttonsPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/buttons");

        buttonsPage = new ButtonsPage(driver);
    }

    @Test
    public void verifyDoubleClickButtonTest() {
        buttonsPage.doubleClickButton();

        assertTrue(
                buttonsPage.isDoubleClickMessageDisplayed(),
                "The double-click confirmation message should be displayed."
                        );

        assertEquals(
                "You have done a double click",
                buttonsPage.getDoubleClickMessage(),
                "The double-click confirmation message should be correct."
        );
    }

    @Test
    public void verifyRightClickButtonTest() {
        buttonsPage.rightClickButton();

        assertTrue(
                buttonsPage.isRightClickMessageDisplayed(),
                "The right-click confirmation message should be displayed."
                        );

        assertEquals(
                "You have done a right click",
                buttonsPage.getRightClickMessage(),
                "The right-click confirmation message should be correct."
        );
    }

    @Test
    public void verifyDynamicClickButtonTest() {
        buttonsPage.clickDynamicButton();

        assertTrue(
                buttonsPage.isDynamicClickMessageDisplayed(),
                "The dynamic-click confirmation message should be displayed."
                        );

        assertEquals(
                "You have done a dynamic click",
                buttonsPage.getDynamicClickMessage(),
                "The dynamic-click confirmation message should be correct."
        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
