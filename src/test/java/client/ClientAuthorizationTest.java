package client;

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
    public void setUp(){
       Response clientResponse = clientShared.registrationNewClient(client);
       token = checkClient.getOkForRegistratedClient(clientResponse);
    }

    @Test
    public void checkAuthorizationClient(){
        Response authorizationResponse = clientShared.authorizationClient(token, ClientCredentials.fromClient(client));
        checkClient.getOkForAuthorizationClient(authorizationResponse);
    }

    @Test
    public void checkAuthorizationClientWithIncorrectData(){
        Response firstAuthorizationResponse = clientShared.authorizationClient(token, ClientCredentials.fromClient(client));
        checkClient.getOkForAuthorizationClient(firstAuthorizationResponse);
        Response secondAuthorizationResponse = clientShared.authorizationClient(token, new ClientCredentials("Babochka3r@ya.com", "bubu7"));
        checkClient.getUnauthorizedClient(secondAuthorizationResponse);
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
