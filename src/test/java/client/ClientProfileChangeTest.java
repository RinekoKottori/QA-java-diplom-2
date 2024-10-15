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

public class ClientProfileChangeTest {
    ClientShared clientShared = new ClientShared();
    CheckClient checkClient = new CheckClient();
    Client client = Client.randomClient();
    String token;
    HashMap<String, String> newProfileData;

    @Before
    public void setUp() {
        Response clientResponse = clientShared.registrationNewClient(client);
        token = checkClient.getOkForRegistratedClient(clientResponse);
        Response authorizationResponse = clientShared.authorizationClient(token, ClientCredentials.fromClient(client));
        newProfileData = clientShared.getNameAndEmailFromAuthorization(authorizationResponse);
    }

    @Test
    public void checkChangeDataInProfileAuthorizedClient() {
        Response changeResponse = clientShared.changeClientWithAuthorization(token, newProfileData);
        checkClient.getOkForChangeInfo(changeResponse);
    }

    @Test
    public void checkChangeDataInProfileNonAuthorizedClient() {
        Response changeResponse = clientShared.changeClientWithOutAuthorization(newProfileData);
        checkClient.getUnauthorizedForChangeInfo(changeResponse);
    }

    @After
    public void tearDown() {
        ClientShared deleteClient = new ClientShared();
        CheckClient isClientdeleted = new CheckClient();
        if (token != null) {
            Response deletedClient = deleteClient.deleteClient(token);
            isClientdeleted.getAcceptedForDeletingClient(deletedClient);
        }
    }
}
