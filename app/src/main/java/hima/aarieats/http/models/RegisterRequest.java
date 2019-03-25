package hima.aarieats.http.models;

public class RegisterRequest {

    private String username;

    private String password;

    private String email;

    private String phno;

    public RegisterRequest(String userName,String password,String email,String phno) {
        this.username = userName;
        this.password = password;
        this.email = email;
        this.phno = phno;
    }
}
