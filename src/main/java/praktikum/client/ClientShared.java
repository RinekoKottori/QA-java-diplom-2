package praktikum.client;

import io.qameta.allure.Description;
import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import praktikum.Shared;

import java.util.HashMap;

import static io.qameta.allure.model.Parameter.Mode.HIDDEN;

//методы для авторизации и создания, и др.
public class ClientShared extends Shared {

    @Step("Send POST request to api/auth/register to create new client")
    @DisplayName("Register a client")
    public Response registrationNewClient(Client client) {
        return spec()
                .body(client)
                .when()
                .post(CREATE_REGISTRATION_CLIENT);
    }

    @Step("Send POST request to api/auth/login to login client")
    @DisplayName("Authorize a client")
    public Response authorizationClient(ClientCredentials clientCredentials) {
        return spec()
                .when()
                .body(clientCredentials)
                .post(AUTHORIZATION_CLIENT);
    }

    @Step("Send DELETE request to api/auth/user to delete client")
    @DisplayName("Delete a client")
    public Response deleteClient(@Param(mode = HIDDEN)String token) {
        return spec()
                .auth().oauth2(token)
                .when()
                .delete(ACTIONS_CLIENT);
    }

    @Step("Send POST request to api/auth/user to get name and email from client profile")
    @DisplayName("Get name and email from client profile")
    public HashMap<String, String> getNameAndEmailFromAuthorization(Response response) {
        String email = response.then()
                .extract().body().path("user.email");
        String name = response.then()
                .extract().body().path("user.name");
        HashMap<String, String> nameAndEmail = new HashMap<>();
        nameAndEmail.put("name", name);
        nameAndEmail.put("email", email);
        return nameAndEmail;
    }

    @Step("Send POST request to api/auth/user to get name")
    @DisplayName("Get name from client profile")
    public HashMap<String, String> getNameFromAuthorization(Response response) {
        String name = response.then()
                .extract().body().path("name");
        HashMap<String, String> newName = new HashMap<>();
        newName.put("name", name);
        return newName;
    }

    @Step("Send POST request to api/auth/user to get email from client profile")
    @DisplayName("Get email from client profile")
    public HashMap<String, String> getEmailFromAuthorization(Response response) {
        String email = response.then()
                .extract().body().path("email");
        HashMap<String, String> newEmail = new HashMap<>();
        newEmail.put("email", email);
        return newEmail;
    }

    @Step("Send PATCH request to api/auth/user to change name and email in client profile")
    @DisplayName("Change name and email in the profile of the registered client")
    public Response changeClientDataWithAuthorization(@Param(mode = HIDDEN)String token, HashMap<String, String> newProfileData) {
        newProfileData.put("email", "bubusya@ya.com");
        newProfileData.put("name", "Vorobushek");
        return spec()
                .auth().oauth2(token)
                .and()
                .body(newProfileData)
                .when()
                .patch(ACTIONS_CLIENT);
    }

    @Step("Send POST request to api/auth/user  to change name in client profile")
    @DisplayName("Change name in the profile of the registered client")
    public Response changeClientNameWithAuthorization(@Param(mode = HIDDEN)String token, HashMap<String, String> name) {
        name.put("name", "karakul");
        return spec()
                .auth().oauth2(token)
                .and()
                .body(name)
                .when()
                .patch(ACTIONS_CLIENT);
    }

    @Step("Send POST request to api/auth/user  to change email in client profile")
    @DisplayName("Change email in the profile of the registered client")
    public Response changeClientEmailWithAuthorization(@Param(mode = HIDDEN)String token, HashMap<String, String> email) {
        email.put("email", "boradavochnik@ya.com");
        return spec()
                .auth().oauth2(token)
                .and()
                .body(email)
                .when()
                .patch(ACTIONS_CLIENT);
    }

    @Step("Send PATCH request to api/auth/user to change name and email in client profile without accessToken")
    @DisplayName("Change name and email in the profile of an unregistered client")
    public Response changeClientDataWithOutAuthorization(HashMap<String, String> newProfileData) {
        newProfileData.put("email", "koko@ya.com");
        newProfileData.put("name", "Burka");
        return spec()
                .body(newProfileData)
                .when()
                .patch(ACTIONS_CLIENT);
    }

    @Step("Send PATCH request to api/auth/user to change name in client profile without accessToken")
    @DisplayName("Change name in the profile of an unregistered client")
    public Response changeClientNameWithOutAuthorization(HashMap<String, String> name) {
        name.put("name", "Alyosha");
        return spec()
                .body(name)
                .when()
                .patch(ACTIONS_CLIENT);
    }

    @Step("Send PATCH request to api/auth/user to change email in client profile without accessToken")
    @DisplayName("Change email in the profile of an unregistered client")
    public Response changeClientEmailWithOutAuthorization(HashMap<String, String> email) {
        email.put("email", "buterbrod@ya.com");
        return spec()
                .body(email)
                .when()
                .patch(ACTIONS_CLIENT);
    }

}
