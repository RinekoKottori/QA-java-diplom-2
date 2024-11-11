package praktikum.orders;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

//проверки методов для заказов
public class CheckOrder {

    @Step("Verify that order could be created without authorization and status code 200")
    public void checkCreateOrderWithoutAuthorization(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_OK)
                .and()
                .body("success", equalTo(true))
                .body("$", hasKey("order"));
    }

    @Step("Verify that order could be created with authorization and status code 200")
    public void checkCreateOrderWithAuthorization(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_OK)
                .and()
                .body("success", equalTo(true))
                .body("order", hasKey("owner"));
    }

    @Step("Verify that order can't be created without authorization and without ingredients and status code is 400")
    public void checkCreateOrderWithoutAuthorizationAndIngredients(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Step("Verify that order can't be created with wrong hash ingredient and without authorization and status code is 500")
    public void checkCreateOrderWithoutAuthorizationAndWrongHashIngredients(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_INTERNAL_ERROR);
    }

    @Step("Verify that order can't be created without ingredients and status code is 400")
    public void checkCreateOrderWithAuthorizationAndWithoutIngredients(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Step("Verify that order can't be created with wrong hash of ingredient and status code is 500")
    public void checkCreateOrderWithAuthorizationAndWrongHashIngredients(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_INTERNAL_ERROR);
    }

    @Step("Verify that map can't be getting without authorization and status code is 401")
    public void checkIsOrderMapGettingWithoutToken(Response response) {
        response.then()
                .assertThat().statusCode(HTTP_UNAUTHORIZED)
                .and()
                .body("message", equalTo("You should be authorised"));
    }

    @Step("Get OK for get map of orders")
    public void checkIsOrderMapIsGettingWithToken(Response response) {
        response.then()
                .assertThat().statusCode(HTTP_OK)
                .and()
                .body("$", hasKey("orders"));
    }
}
