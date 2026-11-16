package org.example;

import org.example.pages.WebTablesPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class WebTablesTests extends BaseTest{
    private WebDriver driver;
    private WebTablesPage webTablesPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/webtables");

        webTablesPage = new WebTablesPage(driver);
    }

    @Test
    public void verifyDefaultRecordsAreDisplayedTest() {
        assertTrue(
                webTablesPage.getRecordsCount() > 0,
                "At least one record should be displayed in the table."
                        );
    }

    @Test
    public void verifyNewRecordCanBeAddedTest() {
        String firstName = "Nikola";
        String lastName = "Petrov";
        String email = "nikola.petrov@test.com";
        String age = "25";
        String salary = "3500";
        String department = "QA";

        int initialCount = webTablesPage.getRecordsCount();

        webTablesPage.clickAddButton();
        webTablesPage.enterFirstName(firstName);
        webTablesPage.enterLastName(lastName);
        webTablesPage.enterEmail(email);
        webTablesPage.enterAge(Integer.parseInt(age));
        webTablesPage.enterSalary(Integer.parseInt(salary));
        webTablesPage.enterDepartment(department);
        webTablesPage.clickSubmit();

        assertEquals(
                initialCount + 1,
                webTablesPage.getRecordsCount(),
                "The number of records should increase by one."
        );

        assertTrue(
                webTablesPage.isRecordDisplayed(email),
                "The new record should appear in the table."
                        );

        assertTrue(
                webTablesPage.getRecordText(email).contains(firstName),
                "The record should contain the correct first name."
                        );
    }

    @Test
    public void verifyRecordCanBeSearchedTest() {
        webTablesPage.searchRecord("Alden");

        assertTrue(
                webTablesPage.isRecordDisplayed("Alden"),
                "The matching record should be displayed."
                        );
    }

    @Test
    public void verifySearchReturnsNoResultsForUnknownRecordTest() {
        webTablesPage.searchRecord("UnknownPerson123");

        assertEquals(
                0,
                webTablesPage.getRecordsCount(),
                "No records should be displayed for an unmatched search."
        );
    }

    @Test
    public void verifyRecordCanBeEditedTest() {
        String email = "cierra@example.com";
        String updatedFirstName = "CierraUpdated";

        webTablesPage.clickEditRecord(email);
        webTablesPage.clearFirstName();
        webTablesPage.enterFirstName(updatedFirstName);
        webTablesPage.clickSubmit();

        assertTrue(
                webTablesPage.isRecordDisplayed(email),
                "The edited record should remain in the table."
                        );

        assertTrue(
                webTablesPage.getRecordText(email).contains(updatedFirstName),
                "The first name should be updated."
                        );
    }

    @Test
    public void verifyRecordCanBeDeletedTest() {
        String email = "alden@example.com";

        int initialCount = webTablesPage.getRecordsCount();

        assertTrue(
                webTablesPage.isRecordDisplayed(email),
                "The record should exist before deletion."
                        );

        webTablesPage.deleteRecord(email);

        assertFalse(
                webTablesPage.isRecordDisplayed(email),
                "The deleted record should no longer appear."
                        );

        assertEquals(
                initialCount - 1,
                webTablesPage.getRecordsCount(),
                "The number of records should decrease by one."
        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
