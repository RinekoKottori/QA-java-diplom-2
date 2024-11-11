package order;

import io.qameta.allure.junit4.DisplayName;
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
    @DisplayName("Check is it possible to create order without accessToken")
    public void checkCreationOrderWithoutToken() {
        Response response = order.createNewOrderWithoutToken();
        checkOrder.checkCreateOrderWithoutAuthorization(response);
    }

    @Test
    @DisplayName("Check is it possible to create order with accessToken")
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
