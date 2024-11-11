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

public class ClientAuthorizationTest {
    ClientShared clientShared = new ClientShared();
    CheckClient checkClient = new CheckClient();
    String token;
    Client client = Client.randomClient();

    @Before
    public void setUp() {
        Response clientResponse = clientShared.registrationNewClient(client);
        token = checkClient.getOkForRegisteredClient(clientResponse);
    }

    @Test
    @DisplayName("Check authorization client")
    public void checkAuthorizationClient() {
        Response authorizationResponse = clientShared.authorizationClient(ClientCredentials.fromClient(client));
        checkClient.getOkForAuthorizationClient(authorizationResponse);
    }

    @Test
    @DisplayName("Check authorization client with incorrect email and password")
    public void checkAuthorizationClientWithIncorrectData() {
        Response firstAuthorizationResponse = clientShared.authorizationClient(ClientCredentials.fromClient(client));
        checkClient.getOkForAuthorizationClient(firstAuthorizationResponse);
        Response secondAuthorizationResponse = clientShared.authorizationClient(new ClientCredentials("Babochka3r@ya.com", "bubu7"));
        checkClient.getUnauthorizedClient(secondAuthorizationResponse);
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
