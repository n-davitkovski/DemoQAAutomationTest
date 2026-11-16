package org.example;

import org.example.pages.TextBoxPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;


public class TextBoxTests extends BaseTest {

    private WebDriver driver;
    private TextBoxPage textBoxPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/text-box");

        textBoxPage = new TextBoxPage(driver);
    }

    @Test
    public void verifyTextBoxSubmissionTest() {

        TextBoxPage textBoxPage =
                new TextBoxPage(driver);

        textBoxPage.enterFullName("Simona Stefanova");
        textBoxPage.enterEmail("simona@test.com");
        textBoxPage.enterCurrentAddress("10 Main Street");
        textBoxPage.enterPermanentAddress("25 Park Avenue");

        textBoxPage.clickSubmit();

        assertTrue(textBoxPage.isOutputDisplayed());
        assertTrue(textBoxPage.getOutputText().contains("Simona Stefanova"));
        assertTrue(textBoxPage.getOutputText().contains("simona@test.com"));
        assertTrue(textBoxPage.getOutputText().contains("10 Main Street"));
        assertTrue(textBoxPage.getOutputText().contains("25 Park Avenue"));
    }

    @Test
    public void verifyTextBoxSubmissionWithOnlyFullNameTest() {
        textBoxPage.enterFullName("Simona Stefanova");

        textBoxPage.clickSubmit();

        assertTrue(textBoxPage.isOutputDisplayed());
        assertTrue(textBoxPage.getOutputText().contains("Simona Stefanova"));
    }

    @Test
    public void verifyTextBoxSubmissionWithOnlyEmailTest() {
        textBoxPage.enterEmail("simona@test.com");

        textBoxPage.clickSubmit();

        assertTrue(textBoxPage.isOutputDisplayed());
        assertTrue(textBoxPage.getOutputText().contains("simona@test.com"));
    }

    @Test
    public void verifyTextBoxSubmissionWithOnlyCurrentAddressTest() {
        textBoxPage.enterCurrentAddress("10 Main Street");

        textBoxPage.clickSubmit();

        assertTrue(textBoxPage.isOutputDisplayed());
        assertTrue(textBoxPage.getOutputText().contains("10 Main Street"));
    }

    @Test
    public void verifyTextBoxSubmissionWithOnlyPermanentAddressTest() {
        textBoxPage.enterPermanentAddress("25 Park Avenue");

        textBoxPage.clickSubmit();

        assertTrue(textBoxPage.isOutputDisplayed());
        assertTrue(textBoxPage.getOutputText().contains("25 Park Avenue"));
    }

    @Test
    public void verifyTextBoxSubmissionWithEmptyFieldsTest() {
        textBoxPage.clickSubmit();

        assertFalse(textBoxPage.isOutputDisplayed());
    }

    @Test
    public void verifyTextBoxSubmissionWithInvalidEmailTest() {
        textBoxPage.enterFullName("Simona Stefanova");
        textBoxPage.enterEmail("invalid-email");
        textBoxPage.enterCurrentAddress("10 Main Street");
        textBoxPage.enterPermanentAddress("25 Park Avenue");

        textBoxPage.clickSubmit();

        // DemoQA may accept this value rather than reject it.
        // Verify the observed behavior before setting a validation expectation.
        assertTrue(
                textBoxPage.getOutputText().contains("invalid-email"),
                "The submitted output should contain the entered email if accepted."
                        );
    }

    @Test
    public void verifyTextBoxInputValuesBeforeSubmissionTest() {
        textBoxPage.enterFullName("Simona Stefanova");
        textBoxPage.enterEmail("simona@test.com");
        textBoxPage.enterCurrentAddress("10 Main Street");
        textBoxPage.enterPermanentAddress("25 Park Avenue");

        assertEquals(
                "Simona Stefanova",
                textBoxPage.getFullNameValue()
        );

        assertEquals(
                "simona@test.com",
                textBoxPage.getEmailValue()
        );

        assertEquals(
                "10 Main Street",
                textBoxPage.getCurrentAddressValue()
        );

        assertEquals(
                "25 Park Avenue",
                textBoxPage.getPermanentAddressValue()
        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
