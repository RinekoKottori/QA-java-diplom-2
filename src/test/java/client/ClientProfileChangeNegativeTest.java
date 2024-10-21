package client;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.client.CheckClient;
import praktikum.client.Client;
import praktikum.client.ClientCredentials;
import praktikum.client.ClientShared;

import java.util.HashMap;

public class ClientProfileChangeNegativeTest {
    ClientShared clientShared = new ClientShared();
    CheckClient checkClient = new CheckClient();
    Response authorizationResponse;
    HashMap<String, String> newName;
    HashMap<String, String> newEmail;
    HashMap<String, String> newProfileData;
    String token;

    @Before
    public void setUp() {
        Client client = Client.randomClient();
        Response clientResponse = clientShared.registrationNewClient(client);
        token = checkClient.getOkForRegisteredClient(clientResponse);
        authorizationResponse = clientShared.authorizationClient(ClientCredentials.fromClient(client));
    }

    @Test
    @DisplayName("Check is user without authorization can change email and name in profile")
    public void checkChangeDataInProfileNonAuthorizedClient() {
        newProfileData = clientShared.getNameAndEmailFromAuthorization(authorizationResponse);
        Response changeResponse = clientShared.changeClientDataWithOutAuthorization(newProfileData);
        checkClient.getUnauthorizedForChangeInfo(changeResponse);
    }

    @Test
    @DisplayName("Check is user without authorization can change name in profile")
    public void checkChangeNameInProfileNonAuthorizedClient() {
        newName = clientShared.getNameFromAuthorization(authorizationResponse);
        Response changeResponse = clientShared.changeClientNameWithOutAuthorization(newName);
        checkClient.getUnauthorizedForChangeInfo(changeResponse);
    }

    @Test
    @DisplayName("Check is user without authorization can change email in profile")
    public void checkChangeEmailInProfileNonAuthorizedClient() {
        newEmail = clientShared.getEmailFromAuthorization(authorizationResponse);
        Response changeResponse = clientShared.changeClientEmailWithOutAuthorization(newEmail);
        checkClient.getUnauthorizedForChangeInfo(changeResponse);
    }

    @After
    public void tearDown() {
        ClientShared deleteClient = new ClientShared();
        CheckClient isClientDeleted = new CheckClient();
        if (token != null) {
            Response deletedClient = deleteClient.deleteClient(token);
            isClientDeleted.getAcceptedForDeletedClient(deletedClient);
        }
    }
}
