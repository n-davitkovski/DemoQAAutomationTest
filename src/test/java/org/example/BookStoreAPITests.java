package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookStoreAPITests {
    private static final String BASE_URL =
            "https://demoqa.com/BookStore/v1";

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void verifyGetAllBooksReturnsSuccessTest() {

        Response response = RestAssured
                .given()
                .when()
                .get("/Books");

        assertEquals(
                200,
                response.getStatusCode(),
                "The API should return HTTP 200."
        );

        assertNotNull(
                response.jsonPath().getList("books").toString(),
                "The response should contain a books list."
        );

        assertTrue(
                response.jsonPath().getList("books").size() > 0,
                "The books list should contain at least one book."
                        );
    }

    @Test
    public void verifyGetAllBooksReturnsBookDetailsTest() {

        Response response = RestAssured
                .given()
                .when()
                .get("/Books");

        assertEquals(
                200,
                response.getStatusCode()
        );

        assertTrue(
                response.jsonPath().getList("books.isbn").size() > 0,
                "Each returned book should have an ISBN."
                        );

        assertTrue(
                response.jsonPath().getList("books.title").size() > 0,
                "The response should contain book titles."
                        );

        assertTrue(
                response.jsonPath().getList("books.author").size() > 0,
                "The response should contain book authors."
                        );

        assertTrue(
                response.jsonPath().getList("books.publisher").size() > 0,
                "The response should contain publisher information."
                        );
    }

    @Test
    public void verifyGetBookByISBNReturnsCorrectBookTest() {

        String isbn = "9781449325862";

        Response response = RestAssured
                .given()
                .queryParam("ISBN", isbn)
                .when()
                .get("/Book");

        assertEquals(
                200,
                response.getStatusCode(),
                "The API should return HTTP 200 for a valid ISBN."
        );

        assertEquals(
                isbn,
                response.jsonPath().getString("isbn"),
                "The returned ISBN should match the requested ISBN."
        );

        assertEquals(
                "Git Pocket Guide",
                response.jsonPath().getString("title"),
                "The API should return the correct book title."
        );

        assertEquals(
                "Richard E. Silverman",
                response.jsonPath().getString("author"),
                "The API should return the correct author."
        );
    }

    @Test
    public void verifyGetBookByISBNReturnsBookDetailsTest() {

        String isbn = "9781449325862";

        Response response = RestAssured
                .given()
                .queryParam("ISBN", isbn)
                .when()
                .get("/Book");

        assertEquals(200, response.getStatusCode());

        assertNotNull(
                response.jsonPath().getString("title"),
                "The book title should not be null."
        );

        assertNotNull(
                response.jsonPath().getString("author"),
                "The book author should not be null."
        );

        assertNotNull(
                response.jsonPath().getString("publisher"),
                "The book publisher should not be null."
        );

        assertTrue(
                response.jsonPath().getInt("pages") > 0,
                "The book should have a positive page count."
                        );
    }

    @Test
    public void verifyGetAllBooksResponseContentTypeTest() {

        Response response = RestAssured
                .given()
                .when()
                .get("/Books");

        assertEquals(
                200,
                response.getStatusCode()
        );

        assertTrue(
                response.getContentType().contains("application/json"),
                "The API should return a JSON response."
                        );
    }
}
