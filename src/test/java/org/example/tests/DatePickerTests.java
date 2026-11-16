package org.example.tests;

import org.example.BaseTest;
import org.example.pages.DatePickerPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatePickerTests extends BaseTest{
    private WebDriver driver;
    private DatePickerPage datePickerPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/date-picker");

        datePickerPage = new DatePickerPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifySelectDateFieldIsDisplayedTest() {
        assertTrue(
                datePickerPage.isSelectDateFieldDisplayed(),
                "The Select Date field should be displayed."
        );
    }

    @Test
    public void verifyDateAndTimeFieldIsDisplayedTest() {
        assertTrue(
                datePickerPage.isDateAndTimeFieldDisplayed(),
                "The Date And Time field should be displayed."
        );
    }

    @Test
    public void verifySelectDateCanBeChangedTest() {
        datePickerPage.enterDate("05/15/2025");

        assertEquals(
                "05/15/2025",
                datePickerPage.getSelectedDate(),
                "The selected date should be May 15, 2025."
        );
    }

    @Test
    public void verifyDateAndTimeCanBeChangedTest() {
        datePickerPage.enterDateAndTime("May 15, 2025 10:30 AM");

        assertEquals(
                "May 15, 2025 10:30 AM",
                datePickerPage.getSelectedDateAndTime(),
                "The selected date and time should match the entered value."
        );
    }

    @Test
    public void verifySelectDateFieldIsNotEmptyTest() {
        assertFalse(
                datePickerPage.getSelectedDate().isBlank(),
                "The Select Date field should contain a date by default."
                        );
    }

    @Test
    public void verifyDateAndTimeFieldIsNotEmptyTest() {
        assertFalse(
                datePickerPage.getSelectedDateAndTime().isBlank(),
                "The Date And Time field should contain a date and time by default."
                        );
    }
}
