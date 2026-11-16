package org.example.api;

import org.openqa.selenium.devtools.v138.network.model.Response;

import static io.restassured.RestAssured.given;

public class BookStoreAPI {
    private static final String BASE_URL =
            "https://demoqa.com";

    private static final String BOOK_STORE_ENDPOINT =
            "/BookStore/v1";

    private static final String ACCOUNT_ENDPOINT =
            "/Account/v1";

    // -------------------------
    // Book Store API methods
    // -------------------------

    // Get all books
    public Response getAllBooks() {
        return (Response) given()
                .baseUri(BASE_URL)
                .when()
                .get(BOOK_STORE_ENDPOINT + "/Books");
    }

    // Get a book by ISBN
    public Response getBookByISBN(String isbn) {
        return (Response) given()
                .baseUri(BASE_URL)
                .queryParam("ISBN", isbn)
                .when()
                .get(BOOK_STORE_ENDPOINT + "/Book");
    }

    // Add a book to a user's collection
    public Response addBook(
            String userId,
            String isbn,
            String token) {

        return (Response) given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("""
                        {
                            "userId": "%s",
                            "collectionOfIsbns": [
                                {
                                    "isbn": "%s"
                                }
                            ]
                        }
                        """.formatted(userId, isbn))
                .when()
                .post(BOOK_STORE_ENDPOINT + "/Books");
    }

    // Delete a book from a user's collection
    public Response deleteBook(
            String userId,
            String isbn,
            String token) {

        return (Response) given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("""
                        {
                            "isbn": "%s",
                            "userId": "%s"
                        }
                        """.formatted(isbn, userId))
                .when()
                .delete(BOOK_STORE_ENDPOINT + "/Book");
    }

    // Delete all books from a user's collection
    public Response deleteAllBooks(
            String userId,
            String token) {

        return (Response) given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete(BOOK_STORE_ENDPOINT + "/Books");
    }

    // -------------------------
    // Account API methods
    // -------------------------

    // Create a user
    public Response createUser(
            String username,
            String password) {

        return (Response) given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body("""
                        {
                            "userName": "%s",
                            "password": "%s"
                        }
                        """.formatted(username, password))
                .when()
                .post(ACCOUNT_ENDPOINT + "/User");
    }

    // Generate an authentication token
    public Response generateToken(
            String username,
            String password) {

        return (Response) given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body("""
                        {
                            "userName": "%s",
                            "password": "%s"
                        }
                        """.formatted(username, password))
                .when()
                .post(ACCOUNT_ENDPOINT + "/GenerateToken");
    }

    // Check whether the credentials are authorized
    public Response authorizeUser(
            String username,
            String password) {

        return (Response) given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body("""
                        {
                            "userName": "%s",
                            "password": "%s"
                        }
                        """.formatted(username, password))
                .when()
                .post(ACCOUNT_ENDPOINT + "/Authorized");
    }

    // Get user details and collection
    public Response getUser(
            String userId,
            String token) {

        return (Response) given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(ACCOUNT_ENDPOINT + "/User/" + userId);
    }
}
