package praktikum.orders;

import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import praktikum.Shared;
import praktikum.ingredients.IngredientsShared;

import java.util.List;

import static io.qameta.allure.model.Parameter.Mode.HIDDEN;

//методы для создания и др манипуляций с заказами
public class OrderShared extends Shared {

    @Step("Send POST request to /api/orders to create order without accessToken")
    @DisplayName("Create order without authorization with ingredient")
    public Response createNewOrderWithoutToken() {
        IngredientsShared ingredients = new IngredientsShared();
        Order order = ingredients.getRandomBurger();
        Object requestBody = order.getIngredients();
        return spec()
                .body(requestBody)
                .when()
                .post(CREATE_ORDER);
    }

    @Step("Send POST request to /api/orders to create order without accessToken and without ingredients")
    @DisplayName("Create order without authorization and without ingredients")
    public Response createNewOrderWithoutTokenAndIngredients() {
        Order order = new Order(null);
        Object requestBody = order.getIngredients();
        return spec()
                .body(requestBody)
                .when()
                .post(CREATE_ORDER);
    }

    @Step("Send POST request to /api/orders to create order without accessToken and wrong hash of ingredients")
    @DisplayName("Create order without authorization and wrong hash ingredients")
    public Response createNewOrderWithWrongHashIngredients() {
        List<String> ingredients = List.of("61c0c5a71d4582101bdaaa6d", "670fda549ed2898761b4e4d5c");
        Order order = new Order(ingredients);
        Object requestBody = order.getIngredients();
        return spec()
                .body(requestBody)
                .when()
                .post(CREATE_ORDER);
    }

    @Step("Send POST request to /api/orders  to create order with accessToken")
    @DisplayName("Create order with authorization and with ingredient")
    public Response createNewOrderWithToken(@Param(mode = HIDDEN)String token) {
        IngredientsShared ingredients = new IngredientsShared();
        Order order = ingredients.getRandomBurger();
        Object requestBody = order.getIngredients();
        return spec()
                .auth().oauth2(token)
                .body(requestBody)
                .when()
                .post(CREATE_ORDER);
    }

    @Step("Send POST request to /api/orders to create order with accessToken, but without ingredients")
    @DisplayName("Create order with authorization and without ingredients")
    public Response createNewOrderWithTokenAndWithoutIngredients() {
        Order order = new Order(null);
        Object requestBody = order.getIngredients();
        return spec()
                .body(requestBody)
                .when()
                .post(CREATE_ORDER);
    }

    @Step("Send POST request to /api/orders to create order with accessToken, but with wrong hashes of ingredients")
    @DisplayName("Create order with authorization and with wrong hash ingredients")
    public Response createNewOrderWithTokenAndWithWrongHashIngredients() {
        List<String> ingredients = List.of("61c0c5a71d4582101bdaaa6d", "670fda549ed2898761b4e4d5c");
        Order order = new Order(ingredients);
        Object requestBody = order.getIngredients();
        return spec()
                .body(requestBody)
                .when()
                .post(CREATE_ORDER);
    }

    @Step("Send GET request to /api/orders to get order list without accessToken")
    @DisplayName("Get orders without authorization")
    public Response getOrdersWithoutToken() {
        return spec()
                .when()
                .get(GET_ORDERS);
    }

    @Step("Send GET request to /api/orders to get order list with accessToken")
    @DisplayName("Get orders with authorization")
    public Response getOrdersWithToken(@Param(mode = HIDDEN)String token) {
            return spec()
                    .auth().oauth2(token)
                    .when()
                    .get(GET_ORDERS);
    }
}
