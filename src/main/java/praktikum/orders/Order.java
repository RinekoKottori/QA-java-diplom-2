package praktikum.orders;

import java.util.HashMap;
import java.util.List;

//Хранение интерфейса ингредиентов и преобразование в нужный формат при передаче в тело запроса
public class Order {
    private HashMap<String, List<String>> ingredients;

    public Order(List<String> ingredients) {
        this.ingredients = new HashMap<>();
        this.ingredients.put("ingredients", ingredients); // сохраняем ингредиенты в нужном формате
    }

    public HashMap<String, List<String>> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = new HashMap<>();
        this.ingredients.put("ingredients", ingredients);
    }

    public Order() {

    }
}
