package praktikum;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;//константы
public class Shared {
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    public static final String CREATE_REGISTRATION_CLIENT = "/api/auth/register";
    public static final String AUTHORIZATION_CLIENT = "/api/auth/login";
    public static final String LOGOUT_CLIENT = "/api/auth/logout";
    public static final String ACTIONS_CLIENT = "/api/auth/user";
    public static final String REFRESH_TOKEN = "/api/auth/token";
    public static final String CREATE_ORDER = "/api/orders";

    public RequestSpecification speca() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URI)
                .build();
    }
}

