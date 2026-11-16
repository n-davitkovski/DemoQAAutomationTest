package org.example.tests;

import org.example.BaseTest;
import org.example.pages.LoginPage;
import org.example.pages.ProfilePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


public class ProfileTests extends BaseTest{
    private WebDriver driver;
    private LoginPage loginPage;
    private ProfilePage profilePage;

    // Replace with your registered DemoQA account
    private final String username = "yourUsername";
    private final String password = "yourPassword";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.get("https://demoqa.com/login");

        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        // Login before accessing the Profile page
        loginPage.login(username, password);

        driver.get("https://demoqa.com/profile");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyProfilePageIsDisplayedTest() {
        assertTrue(profilePage.isProfilePageDisplayed(),
                "Profile page should be displayed.");
    }

    @Test
    public void verifyUsernameIsDisplayedTest() {
        assertEquals(username, profilePage.getUsername(),
                "Logged-in username should be displayed.");
    }

    @Test
    public void verifyBooksSectionIsDisplayedTest() {
        assertTrue(profilePage.isBooksSectionDisplayed(),
                "Books section should be displayed.");
    }

    @Test
    public void addBookToCollectionTest() {
        profilePage.clickGoToBookStore();

        profilePage.addFirstBookToCollection();

        profilePage.openProfilePage();

        assertTrue(profilePage.isBookDisplayedInCollection(),
                "Added book should be displayed in the collection.");
    }

    @Test
    public void deleteBookFromCollectionTest() {
        profilePage.clickGoToBookStore();

        profilePage.addFirstBookToCollection();

        profilePage.openProfilePage();

        assertTrue(profilePage.isBookDisplayedInCollection(),
                "Book should be present before deletion.");

        profilePage.deleteFirstBookFromCollection();
        profilePage.confirmDeletion();

        assertFalse(profilePage.isBookDisplayedInCollection(),
                "Book should be removed from the collection.");
    }

    @Test
    public void successfulLogoutTest() {
        profilePage.clickLogout();

        assertTrue(loginPage.isLoginFormDisplayed(),
                "Login page should be displayed after logout.");
    }

    @Test
    public void verifyProfileCannotBeAccessedWithoutLoginTest() {
        profilePage.clickLogout();

        driver.get("https://demoqa.com/profile");

        assertFalse(profilePage.isProfilePageDisplayed(),
                "Profile page should not be accessible without login.");
    }
}
