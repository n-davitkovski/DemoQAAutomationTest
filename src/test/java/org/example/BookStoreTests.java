package org.example;

import org.example.pages.BookStorePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookStoreTests extends BaseTest{
    private WebDriver driver;
    private BookStorePage bookStorePage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/books");

        bookStorePage = new BookStorePage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyBookStorePageDisplayedTest() {
        assertTrue(
                bookStorePage.isBookListDisplayed(),
                "The book list should be displayed."
                        );

        assertTrue(
                bookStorePage.getBooksCount() > 0,
                "The book list should contain books."
                        );
    }

    @Test
    public void verifySearchBookByTitleTest() {
        bookStorePage.searchBook("Git Pocket Guide");

        assertTrue(
                bookStorePage.isBookDisplayed("Git Pocket Guide"),
                "The search results should contain Git Pocket Guide."
                        );

        assertEquals(
                1,
                bookStorePage.getBooksCount(),
                "Only the matching book should be displayed."
        );
    }

    @Test
    public void verifySearchBookByAuthorTest() {
        bookStorePage.searchBook("Richard E. Silverman");

        assertTrue(
                bookStorePage.getBookTitles()
                                        .contains("Git Pocket Guide"),
                "The results should include a book by Richard E. Silverman."
                        );
    }

    @Test
    public void verifySearchBookWithNoMatchingResultsTest() {
        bookStorePage.searchBook("NonExistingBook12345");

        assertEquals(
                0,
                bookStorePage.getBooksCount(),
                "No books should be displayed when there are no matches."
        );
    }

    @Test
    public void verifyClearSearchTest() {
        bookStorePage.searchBook("Git Pocket Guide");

        assertEquals(
                1,
                bookStorePage.getBooksCount(),
                "The search should return one matching book."
        );

        bookStorePage.clearSearch();

        assertTrue(
                bookStorePage.getBooksCount() > 1,
                "Clearing the search should restore the book list."
                        );
    }

    @Test
    public void verifyBookDetailsPageOpensTest() {
        bookStorePage.clickBookByTitle("Git Pocket Guide");

        assertTrue(
                driver.getCurrentUrl().contains("/book"),
                "Clicking a book should open its details page."
                        );
    }

    @Test
    public void verifyLoginButtonNavigationTest() {
        bookStorePage.clickLoginButton();

        assertTrue(
                driver.getCurrentUrl().contains("/login"),
                "Clicking Login should open the login page."
                        );
    }

    @Test
    public void verifyProfileNavigationTest() {
        bookStorePage.clickProfileLink();

        assertTrue(
                driver.getCurrentUrl().contains("/profile"),
                "Clicking Profile should open the profile page."
                        );
    }
}
