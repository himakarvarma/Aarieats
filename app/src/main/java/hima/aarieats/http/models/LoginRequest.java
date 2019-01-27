package hima.aarieats.http.models;

public class LoginRequest {

    private String username;

    private String password;

    public LoginRequest(String userName,String password) {
        this.username = userName;
        this.password = password;
    }
}
