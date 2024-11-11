package praktikum;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Shared {
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    public static final String CREATE_REGISTRATION_CLIENT = "/api/auth/register";
    public static final String AUTHORIZATION_CLIENT = "/api/auth/login";
    public static final String ACTIONS_CLIENT = "/api/auth/user";
    public static final String CREATE_ORDER = "/api/orders";
    public static final String GET_ORDERS = "/api/orders";

    public RequestSpecification spec() {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI);
    }
}

