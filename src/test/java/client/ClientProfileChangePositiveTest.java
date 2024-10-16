package client;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.client.CheckClient;
import praktikum.client.Client;
import praktikum.client.ClientCredentials;
import praktikum.client.ClientShared;

import java.util.HashMap;

public class ClientProfileChangePositiveTest {
    ClientShared clientShared = new ClientShared();
    CheckClient checkClient = new CheckClient();
    Client client = Client.randomClient();
    HashMap<String, String> newName;
    HashMap<String, String> newEmail;
    HashMap<String, String> newProfileData;
    Response authorizationResponse;
    String token;

    @Before
    public void setUp() {
        Response clientResponse = clientShared.registrationNewClient(client);
        token = checkClient.getOkForRegisteredClient(clientResponse);
        authorizationResponse = clientShared.authorizationClient(ClientCredentials.fromClient(client));
    }

    @Test
    public void checkChangeDataInProfileAuthorizedClient() {
        newProfileData = clientShared.getNameAndEmailFromAuthorization(authorizationResponse);
        Response changeResponse = clientShared.changeClientDataWithAuthorization(token, newProfileData);
        checkClient.getOkForChangeInfo(changeResponse);
    }

    @Test
    public void checkChangeNameInProfileAuthorizedClient() {
        newName = clientShared.getNameFromAuthorization(authorizationResponse);
        Response changeResponse = clientShared.changeClientNameWithAuthorization(token, newName);
        checkClient.getOkForChangeInfo(changeResponse);
    }

    @Test
    public void checkChangeEmailInProfileAuthorizedClient() {
        newEmail = clientShared.getEmailFromAuthorization(authorizationResponse);
        Response changeResponse = clientShared.changeClientEmailWithAuthorization(token, newEmail);
        checkClient.getOkForChangeInfo(changeResponse);
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
