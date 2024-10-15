package praktikum.client;

import org.apache.commons.lang3.RandomStringUtils;

public class Client {
    private String email;
    private String password;
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client() {

    }

    public Client(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Client(String email, String name) {
        this.email = email;
        this.name = name;
    }

    //создать уникального пользователя;
    //создать пользователя, который уже зарегистрирован;
    public static Client randomClient() {
        return new Client("Alexander" + RandomStringUtils.randomAlphanumeric(1, 3) + "@ya.com", "pushnoy" + RandomStringUtils.randomAlphanumeric(1, 5), "yavasneznau");
    }

    //создать пользователя и не заполнить одно из обязательных полей.
    public static Client withOutPassword() {
        return new Client("Alexander" + RandomStringUtils.randomAlphanumeric(1, 3) + "@ya.com", null, "yavasneznau");
    }

}
