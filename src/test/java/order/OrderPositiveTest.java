package order;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.client.CheckClient;
import praktikum.client.Client;
import praktikum.client.ClientShared;
import praktikum.orders.CheckOrder;
import praktikum.orders.OrderShared;

public class OrderPositiveTest {
    OrderShared order = new OrderShared();
    CheckOrder checkOrder = new CheckOrder();
    Client client = Client.randomClient();
    String token;

    @Before
    public void setUp() {
        ClientShared clientShared = new ClientShared();
        CheckClient checkClient = new CheckClient();
        Response clientResponse = clientShared.registrationNewClient(client);
        token = checkClient.getOkForRegisteredClient(clientResponse);
    }

    @Test
    public void checkCreationOrderWithoutToken() {
        Response response = order.createNewOrderWithoutToken();
        checkOrder.checkCreateOrderWithoutAuthorization(response);
    }

    @Test
    public void checkCreationOrderWithToken() {
        Response response = order.createNewOrderWithToken(token);
        checkOrder.checkCreateOrderWithAuthorization(response);
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
