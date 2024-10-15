package client;

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
    public void checkRegistrationNewClient() {
        var client = Client.randomClient();
        Response createResponse = clientShared.registrationNewClient(client);
        token = checkClient.getOkForRegistratedClient(createResponse);
    }

    @Test
    public void checkRationTwoSameClients(){
        var client = Client.randomClient();
        Response firstCreateResponse = clientShared.registrationNewClient(client);
        token = checkClient.getOkForRegistratedClient(firstCreateResponse);
        Response secondCreateResponse = clientShared.registrationNewClient(client);
        checkClient.getForbiddenForRegistratedDoubleClient(secondCreateResponse);
    }

    @Test
    public void checkRegistrationClientWithoutPassword(){
        var client = Client.withOutPassword();
        Response response = clientShared.registrationNewClient(client);
        checkClient.getForbiddenForRegistratedClientWithoutPassword(response);
    }

    @After
    public void tearDown() {
        ClientShared deleteClient = new ClientShared();
        CheckClient isClientdeleted = new CheckClient();
        if (token != null) {
        Response deletedClient =  deleteClient.deleteClient(token);
        isClientdeleted.getAcceptedForDeletingClient(deletedClient);
        }
    }
}