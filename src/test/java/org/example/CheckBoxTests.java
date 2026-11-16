package org.example;

import org.example.pages.CheckBoxPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckBoxTests extends BaseTest{
    private WebDriver driver;
    private CheckBoxPage checkBoxPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/checkbox");

        checkBoxPage = new CheckBoxPage(driver);
    }

    @Test
    public void verifyHomeCheckboxCanBeExpandedTest() {
        checkBoxPage.expandHome();

        assertTrue(
                checkBoxPage.isHomeExpanded(),
                "The Home checkbox section should be expanded."
        );
    }

    @Test
    public void verifyHomeCheckboxCanBeSelectedTest() {
        checkBoxPage.expandHome();
        checkBoxPage.selectHome();

        assertTrue(
                checkBoxPage.isHomeSelected(),
                "The Home checkbox should be selected."
        );
    }

    @Test
    public void verifyHomeCheckboxCanBeDeselectedTest() {
        checkBoxPage.expandHome();
        checkBoxPage.selectHome();

        assertTrue(
                checkBoxPage.isHomeSelected(),
                "The Home checkbox should initially be selected."
        );

        checkBoxPage.selectHome();

        assertFalse(
                checkBoxPage.isHomeSelected(),
                "The Home checkbox should be deselected after clicking it again."
                        );
    }

    @Test
    public void verifySelectedCheckboxAppearsInResultsTest() {
        checkBoxPage.expandHome();
        checkBoxPage.selectCheckbox("Desktop");

        assertTrue(
                checkBoxPage.getResultsText().toLowerCase().contains("desktop"),
                "The selected Desktop checkbox should appear in the results."
        );
    }

    @Test
    public void verifyMultipleCheckboxesCanBeSelectedTest() {
        checkBoxPage.expandHome();
        checkBoxPage.selectCheckbox("Desktop");
        checkBoxPage.selectCheckbox("Documents");

        String results = checkBoxPage.getResultsText().toLowerCase();

        assertTrue(
                results.contains("desktop"),
                "Desktop should appear in the results."
        );

        assertTrue(
                results.contains("documents"),
                "Documents should appear in the results."
        );
    }

    @Test
    public void verifySelectingHomeSelectsChildCheckboxesTest() {
        checkBoxPage.expandHome();
        checkBoxPage.selectHome();

        String results = checkBoxPage.getResultsText().toLowerCase();

        assertTrue(
                results.contains("home"),
                "Home should appear in the results."
        );

        assertTrue(
                results.contains("desktop"),
                "Desktop should appear in the results."
        );

        assertTrue(
                results.contains("documents"),
                "Documents should appear in the results."
        );

        assertTrue(
                results.contains("downloads"),
                "Downloads should appear in the results."
        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
