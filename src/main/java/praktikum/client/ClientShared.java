package praktikum.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import praktikum.Shared;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

//методы для авторизации и создания и др.
public class ClientShared extends Shared {

    @Step("Send POST request to api/auth/register")
    public Response registrationNewClient(Client client){
                return given().log().all()
                        .spec(speca())
                        .body(client)
                        .when()
                        .post(CREATE_REGISTRATION_CLIENT);
    }

    @Step("Send POST request to api/auth/login")
    public Response authorizationClient(String token, ClientCredentials clientCredentials){
        return given().log().all()
                .spec(speca())
                .auth().oauth2(token)
                .when()
                .body(clientCredentials)
                .post(AUTHORIZATION_CLIENT);
    }

    @Step("Send DELETE request to api/auth/user")
    public Response deleteClient(String token){
            return given().log().all()
                    .spec(speca())
                    .auth().oauth2(token)
                    .when()
                    .delete(ACTIONS_CLIENT);
    }

    @Step("Send PATCH request to api/auth/user")
    public Response changeClientWithAuthorization(String token, HashMap<String, String> newProfileData){
        newProfileData.put("email", "bubusya@ya.com");
        newProfileData.put("name", "Vorobushek");
        return given().log().all()
                .spec(speca())
                .auth().oauth2(token)
                .and()
                .body(newProfileData)
                .when()
                .patch(ACTIONS_CLIENT);
    }

    @Step("Send PATCH request to api/auth/user")
    public Response changeClientWithOutAuthorization(HashMap<String, String> newProfileData){
        newProfileData.put("email", "koko@ya.com");
        newProfileData.put("name", "Burka");
        return given().log().all()
                .spec(speca())
                .body(newProfileData)
                .when()
                .patch(ACTIONS_CLIENT);
    }

    public HashMap<String, String> getNameAndEmailFromAuthorization(Response response) {
        String email = response.then()
                .extract().body().path("user.email");
        String name = response.then()
                .extract().body().path("user.name");
        System.out.println("Extracted email: " + email);
        System.out.println("Extracted name: " + name);
        HashMap<String, String> nameAndEmail = new HashMap<>();
        nameAndEmail.put("name", name);
        nameAndEmail.put("email", email);
        System.out.println("HashMap data: " + nameAndEmail);
        return nameAndEmail;
    }
}
