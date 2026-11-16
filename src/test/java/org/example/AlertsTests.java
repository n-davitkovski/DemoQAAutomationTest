package org.example;

import org.example.pages.AlertsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlertsTests extends BaseTest{
    private WebDriver driver;
    private AlertsPage alertsPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/alerts");

        alertsPage = new AlertsPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifySimpleAlertTest() {

        alertsPage.clickAlertButton();

        assertTrue(
                alertsPage.isAlertPresent(),
                "An alert should appear after clicking the alert button."
                        );

        assertEquals(
                "You clicked a button",
                alertsPage.getAlertText(),
                "The alert should display the expected message."
        );

        alertsPage.acceptAlert();

        assertFalse(
                alertsPage.isAlertPresent(),
                "The alert should close after clicking OK."
        );
    }

    @Test
    public void verifyDelayedAlertTest() {

        alertsPage.clickDelayedAlertButton();

        assertTrue(
                alertsPage.waitForAlert(),
                "An alert should appear after the delay."
                        );

        assertEquals(
                "This alert appeared after 5 seconds",
                alertsPage.getAlertText(),
                "The delayed alert should display the expected message."
        );

        alertsPage.acceptAlert();
    }

    @Test
    public void verifyConfirmationAlertAcceptTest() {

        alertsPage.clickConfirmationAlertButton();

        assertTrue(
                alertsPage.isAlertPresent(),
                "The confirmation alert should appear."
                        );

        assertEquals(
                "Do you confirm action?",
                alertsPage.getAlertText(),
                "The confirmation alert should display the expected message."
        );

        alertsPage.acceptAlert();

        assertEquals(
                "You selected Ok",
                alertsPage.getConfirmationResult(),
                "The result should indicate that OK was selected."
        );
    }

    @Test
    public void verifyConfirmationAlertDismissTest() {

        alertsPage.clickConfirmationAlertButton();

        assertTrue(
                alertsPage.isAlertPresent(),
                "The confirmation alert should appear."
                        );

        alertsPage.dismissAlert();

        assertEquals(
                "You selected Cancel",
                alertsPage.getConfirmationResult(),
                "The result should indicate that Cancel was selected."
        );
    }

    @Test
    public void verifyPromptAlertTest() {

        String inputText = "DemoQA Automation Test";

        alertsPage.clickPromptAlertButton();

        assertTrue(
                alertsPage.isAlertPresent(),
                "The prompt alert should appear."
                        );

        assertEquals(
                "Please enter your name",
                alertsPage.getAlertText(),
                "The prompt should ask the user to enter a name."
        );

        alertsPage.enterTextInAlert(inputText);
        alertsPage.acceptAlert();

        assertEquals(
                "You entered " + inputText,
                alertsPage.getPromptResult(),
                "The result should display the entered text."
        );
    }
}
