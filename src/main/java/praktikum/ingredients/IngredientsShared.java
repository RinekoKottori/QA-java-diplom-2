package praktikum.ingredients;

import io.restassured.response.Response;
import praktikum.Shared;
import praktikum.orders.Order;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

//Создание случайного набора ингредиентов с 1 булкой, 1 начинкой, 1 соусом

public class IngredientsShared extends Shared {
    private final Random random = new Random();

    public List<HashMap<String, Object>> getIngredients() {
      Response response = spec()
                .when()
                .get("/api/ingredients");
        return response.then().extract().body().path("data");
    }

    public String getRandomIngredientsByType(List<HashMap<String, Object>> ingredients, String type) {
        List<HashMap<String, Object>> ingredientsByType =
                ingredients.stream().filter(ingredient -> ingredient.get("type").equals(type)).collect(Collectors.toList());
        return (String) ingredientsByType.get(random.nextInt(ingredientsByType.size())).get("_id");
    }

    public Order getRandomBurger() {
        List<HashMap<String, Object>> ingredients = getIngredients();
        List<String> randomIngredientsId = new ArrayList<>();
        String bunId = getRandomIngredientsByType(ingredients, "bun");
        String mainId = getRandomIngredientsByType(ingredients, "main");
        String sauceId = getRandomIngredientsByType(ingredients, "sauce");

        randomIngredientsId.add(bunId);
        randomIngredientsId.add(mainId);
        randomIngredientsId.add(sauceId);
        return new Order(randomIngredientsId);
    }

}
