package praktikum.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;


//проверки для пользователя
public class CheckClient {
    @Step("Verify that client is registrated and status code 200")
    public String getOkForRegistratedClient(Response response){
       String token = response.then()
                .assertThat()
                .statusCode(HTTP_OK)
                .and().body("success", equalTo(true))
                .and().extract().body().path("accessToken");
        return token.substring(7);
    }

    @Step("Verify that client deleted and status code 202")
    public void getAcceptedForDeletingClient(Response response){
        response.then()
                .assertThat()
                .statusCode(HTTP_ACCEPTED)
                .and().body("message", equalTo("User successfully removed"));
    }

    public void getForbiddenForRegistratedDoubleClient(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_FORBIDDEN)
                .and().body("success", equalTo(false));
    }

    public void getForbiddenForRegistratedClientWithoutPassword(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_FORBIDDEN)
                .and().body("success", equalTo(false));
    }

    public void getOkForAuthorizationClient(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_OK)
                .and().body("success", equalTo(true));
    }


    public void getUnauthorizedClient(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .and().body("success", equalTo(false));
    }

    public void getOkForChangeInfo(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_OK)
                .and().body("success", equalTo(true));

    }

    public void getUnauthorizedForChangeInfo(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .and().body("success", equalTo(false));
    }
}
