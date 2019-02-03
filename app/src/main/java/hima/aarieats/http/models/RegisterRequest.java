package hima.aarieats.http.models;

public class RegisterRequest {

    private String username;

    private String password;

    public RegisterRequest(String userName,String password) {
        this.username = userName;
        this.password = password;
    }
}
