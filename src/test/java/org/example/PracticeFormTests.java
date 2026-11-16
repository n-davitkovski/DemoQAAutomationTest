package org.example;

import org.example.pages.PracticeFormPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PracticeFormTests extends BaseTest{
    private WebDriver driver;
    private WebDriverWait wait;
    private PracticeFormPage practiceFormPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/automation-practice-form");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        practiceFormPage = new PracticeFormPage(driver);
    }

    @Test
    public void verifyPracticeFormSubmissionWithValidDataTest() {
        // Enter personal information
        practiceFormPage.enterFirstName("Filip");
        practiceFormPage.enterLastName("Stefanov");
        practiceFormPage.enterEmail("filip.test@example.com");

        // Select gender and enter mobile number
        practiceFormPage.selectGender("Male");
        practiceFormPage.enterMobile("0712345678");

        // Enter date of birth
        practiceFormPage.enterDateOfBirth("15 Oct 1998");

        // Enter a subject and select a hobby
        practiceFormPage.enterSubject("Maths");
        practiceFormPage.selectHobby("Sports");

        // Enter address
        practiceFormPage.enterAddress("10 Main Street");

        // Select state and city
        practiceFormPage.selectState("NCR");
        practiceFormPage.selectCity("Delhi");

        // Submit the form
        practiceFormPage.submitForm();

        // Wait for the confirmation modal
        wait.until(d ->
                practiceFormPage.isConfirmationModalDisplayed()
        );

        // Verify the submission
        assertTrue(
                practiceFormPage.isConfirmationModalDisplayed(),
                "The confirmation modal should be displayed."
                        );

        String confirmation =
                practiceFormPage.getConfirmationTableText();

        assertTrue(confirmation.contains("Filip"));
        assertTrue(confirmation.contains("Stefanov"));
        assertTrue(confirmation.contains("filip.test@example.com"));
        assertTrue(confirmation.contains("Male"));
        assertTrue(confirmation.contains("0712345678"));
        assertTrue(confirmation.contains("15 October,1998"));
        assertTrue(confirmation.contains("Maths"));
        assertTrue(confirmation.contains("Sports"));
        assertTrue(confirmation.contains("10 Main Street"));
        assertTrue(confirmation.contains("NCR"));
        assertTrue(confirmation.contains("Delhi"));

        practiceFormPage.closeConfirmationModal();
    }

    @Test
    public void verifyPracticeFormRequiresFirstNameTest() {
        // Leave the first name empty
        practiceFormPage.enterLastName("Stefanov");
        practiceFormPage.selectGender("Male");
        practiceFormPage.enterMobile("0712345678");

        practiceFormPage.submitForm();

        assertFalse(
                practiceFormPage.isConfirmationModalDisplayed(),
                "The form should not submit when the first name is missing."
                        );
    }

    @Test
    public void verifyPracticeFormRequiresLastNameTest() {
        // Leave the last name empty
        practiceFormPage.enterFirstName("Filip");
        practiceFormPage.selectGender("Male");
        practiceFormPage.enterMobile("0712345678");

        practiceFormPage.submitForm();

        assertFalse(
                practiceFormPage.isConfirmationModalDisplayed(),
                "The form should not submit when the last name is missing."
                        );
    }

    @Test
    public void verifyPracticeFormRequiresGenderTest() {
        // Leave gender unselected
        practiceFormPage.enterFirstName("Filip");
        practiceFormPage.enterLastName("Stefanov");
        practiceFormPage.enterMobile("0712345678");

        practiceFormPage.submitForm();

        assertFalse(
                practiceFormPage.isConfirmationModalDisplayed(),
                "The form should not submit when gender is missing."
                        );
    }

    @Test
    public void verifyPracticeFormRequiresMobileNumberTest() {
        // Leave the mobile number empty
        practiceFormPage.enterFirstName("Filip");
        practiceFormPage.enterLastName("Stefanov");
        practiceFormPage.selectGender("Male");

        practiceFormPage.submitForm();

        assertFalse(
                practiceFormPage.isConfirmationModalDisplayed(),
                "The form should not submit when the mobile number is missing."
                        );
    }

    @Test
    public void verifyPracticeFormEmailFieldAcceptsValidEmailTest() {
        practiceFormPage.enterFirstName("Filip");
        practiceFormPage.enterLastName("Stefanov");
        practiceFormPage.enterEmail("filip.test@example.com");
        practiceFormPage.selectGender("Male");
        practiceFormPage.enterMobile("0712345678");

        practiceFormPage.submitForm();

        wait.until(d ->
                practiceFormPage.isConfirmationModalDisplayed()
        );

        assertTrue(
                practiceFormPage.getConfirmationTableText()
                        .contains("filip.test@example.com"),
                "The submitted email should appear in the confirmation."
        );

        practiceFormPage.closeConfirmationModal();
    }

    @Test
    public void verifyPracticeFormPictureUploadTest() {
        Path picturePath = Paths.get(
                "src",
                "test",
                "resources",
                "test-image.jpg"
        ).toAbsolutePath();

        assertTrue(
                picturePath.toFile().exists(),
                "The test image must exist at: " + picturePath
                        );

        practiceFormPage.uploadPicture(picturePath.toString());

        assertTrue(
                practiceFormPage.getUploadedPictureName()
                                        .contains("test-image.jpg"),
                "The selected picture filename should be displayed."
                        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
