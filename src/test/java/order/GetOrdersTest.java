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

public class GetOrdersTest {
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
    @DisplayName("Check is it possible to get a list of orders")
    public void checkIsOrderMapIsGetting() {
        Response response = order.getOrdersWithToken(token);
        checkOrder.checkIsOrderMapIsGettingWithToken(response);
    }

    @Test
    @DisplayName("Check is it possible to get a list of orders without accessToken ")
    public void checkGetOrdersWithoutToken() {
        Response response = order.getOrdersWithoutToken();
        checkOrder.checkIsOrderMapGettingWithoutToken(response);
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
