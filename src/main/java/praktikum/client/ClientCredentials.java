package praktikum.client;

//авторизация пользователя POST https://stellarburgers.nomoreparties.site/api/auth/login

public class ClientCredentials {
    private String email;
    private String password;

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

    public ClientCredentials() {

    }

    //логин под существующим пользователем, логин с неверным логином и паролем.
    public ClientCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static ClientCredentials fromClient(Client client) {
        return new ClientCredentials(client.getEmail(), client.getPassword());

    }
}
