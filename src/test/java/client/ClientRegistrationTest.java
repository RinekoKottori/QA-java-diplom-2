package client;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import praktikum.client.CheckClient;
import praktikum.client.Client;
import praktikum.client.ClientShared;

public class ClientRegistrationTest {
    ClientShared clientShared = new ClientShared();
    CheckClient checkClient = new CheckClient();
    String token;

    @Test
    @DisplayName("Check is new client can be created")
    public void checkRegistrationNewClient() {
        var client = Client.randomClient();
        Response createResponse = clientShared.registrationNewClient(client);
        token = checkClient.getOkForRegisteredClient(createResponse);
    }

    @Test
    @DisplayName("Check is client can be created twice")
    public void checkRegistrationTwoSameClients() {
        var client = Client.randomClient();
        Response firstCreateResponse = clientShared.registrationNewClient(client);
        token = checkClient.getOkForRegisteredClient(firstCreateResponse);
        Response secondCreateResponse = clientShared.registrationNewClient(client);
        checkClient.getForbiddenForRegisteredDoubleClient(secondCreateResponse);
    }

    @Test
    @DisplayName("Check is new client can be created without password")
    public void checkRegistrationClientWithoutPassword() {
        var client = Client.withOutPassword();
        Response response = clientShared.registrationNewClient(client);
        checkClient.getForbiddenForRegisteredClientWithoutPassword(response);
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