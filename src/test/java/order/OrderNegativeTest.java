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

public class OrderNegativeTest {
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
    public void checkCreationOrderWithoutTokenAndIngredients() {
        Response response = order.createNewOrderWithoutTokenAndIngredients();
        checkOrder.checkCreateOrderWithoutAuthorizationAndIngredients(response);
    }

    @Test
    public void checkCreationOrderWithoutTokenAndWrongIngredientsHash() {
        Response response = order.createNewOrderWithWrongHashIngredients();
        checkOrder.checkCreateOrderWithoutAuthorizationAndWrongHashIngredients(response);
    }

    @Test
    public void checkCreationOrderWithTokenAndWithoutIngredients() {
        Response response = order.createNewOrderWithTokenAndWithoutIngredients();
        checkOrder.checkCreateOrderWithAuthorizationAndWithoutIngredients(response);
    }

    @Test
    public void checkCreationOrderWithTokenAndWrongIngredientsHash() {
        Response response = order.createNewOrderWithTokenAndWithWrongHashIngredients();
        checkOrder.checkCreateOrderWithAuthorizationAndWrongHashIngredients(response);
    }

    @Test
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
