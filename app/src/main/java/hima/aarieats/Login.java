package hima.aarieats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hima.aarieats.http.HttpListner;
import hima.aarieats.http.api.ApiService;

public class Login extends AppCompatActivity {

    private EditText userName;

    private EditText password;

    private Button loginBtn;

    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goToMainActivity();
                if(userName.getText().toString().length() == 0 && password.getText().toString().length() == 0) {
                    Toast.makeText(Login.this,"Please enter user name and password",Toast.LENGTH_LONG).show();
                } else {
                    loginToServer(userName.getText().toString().trim(),password.getText().toString().trim());
                }
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            gotoRegisterActivity();

            }
        });
    }

    private void goToMainActivity(String username) {
        Intent goToMainActivity = new Intent(Login.this,MapsActivity.class);
        startActivity(goToMainActivity);
    }

    private  void loginToServer(final String username, String password) {
        ApiService apiService = ApiService.getInstance();
        apiService.login(username, password, new HttpListner() {
            @Override
            public void onSuccess(ResponseStatus status, String info) {
                if(status == HttpListner.ResponseStatus.LOGIN_SUCCESS) {
                    Toast.makeText(Login.this,"Login Success",Toast.LENGTH_LONG).show();
                    goToMainActivity(username);
                } else if(status == ResponseStatus.LOGIN_AUTHENTICATION_FAILURE) {
                    Toast.makeText(Login.this,"Invalid username or password",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(ResponseStatus status, String info) {
                Toast.makeText(Login.this,"Internal error",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void gotoRegisterActivity()
    {
        Intent gotoRegisterActivity = new Intent(Login.this,Register.class);
        startActivity(gotoRegisterActivity);
    }

}
