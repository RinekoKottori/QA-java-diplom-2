package praktikum.client;

//DELETE https://stellarburgers.nomoreparties.site/api/auth/user 202 Accepted

public class DeleteClient {
    private String token; //токен для манипуляций с профилем пользователя

    public String getAuthorizationFieldForToken() {
        return token;
    }

    public void setAuthorizationFieldForToken(String token) {
        this.token = token;

    }

    public DeleteClient(String token) {
        this.token = token;
    }

    public DeleteClient(){

    }
}
