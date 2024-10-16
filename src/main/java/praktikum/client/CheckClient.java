package praktikum.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;


//проверки для пользователя
public class CheckClient {
    @Step("Verify that client is registered and status code 200")
    public String getOkForRegisteredClient(Response response) {
        String token = response.then()
                .assertThat()
                .statusCode(HTTP_OK)
                .and().body("success", equalTo(true))
                .and().extract().body().path("accessToken");
        return token.substring(7);
    }

    @Step("Verify that client can't register twice and status code 403")
    public void getForbiddenForRegisteredDoubleClient(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_FORBIDDEN)
                .and().body("message", equalTo("User already exists"));
    }

    @Step("Verify that client can't register without a password and status code 403")
    public void getForbiddenForRegisteredClientWithoutPassword(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_FORBIDDEN)
                .and().body("message", equalTo("Email, password and name are required fields"));
    }

    @Step("Verify that client authorized and status code 200")
    public void getOkForAuthorizationClient(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_OK)
                .and().body("success", equalTo(true));
    }

    @Step("Verify that client can't authorize with wrong password and email, status code 401")
    public void getUnauthorizedClient(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .and().body("message", equalTo("email or password are incorrect"));
    }

    @Step("Verify that client deleted and status code 202")
    public void getAcceptedForDeletedClient(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_ACCEPTED)
                .and().body("message", equalTo("User successfully removed"));
    }

    @Step("Verify that client could change data in profile with token and status code 200")
    public void getOkForChangeInfo(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_OK)
                .and().body("success", equalTo(true));
    }

    @Step("Verify that client can't change data in profile without token and status code 401")
    public void getUnauthorizedForChangeInfo(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .and().body("message", equalTo("You should be authorised"));
    }
}
