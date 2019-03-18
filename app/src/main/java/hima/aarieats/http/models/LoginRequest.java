package hima.aarieats.http.models;

public class LoginRequest {

    private String email;

    private String password;

    public LoginRequest(String userName,String password) {
        this.email = userName;
        this.password = password;
    }
}
