package praktikum.orders;

import java.util.HashMap;

//создание заказа POST https://stellarburgers.nomoreparties.site/api/orders
// и получение заказа кон-ого поль-ля GET https://stellarburgers.nomoreparties.site/api/orders
public class Order {
    private HashMap ingredients;

    public HashMap getIngredients() {
        return ingredients;
    }

    public void setIngredients(HashMap ingredients) {
        this.ingredients = ingredients;
    }

    public Order(HashMap ingredients) {
        this.ingredients = ingredients;
    }

    public Order (){

    }

}
