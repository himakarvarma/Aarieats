package hima.aarieats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hima.aarieats.http.HttpListner;
import hima.aarieats.http.api.AariEatsApi;
import hima.aarieats.http.api.ApiService;

public class Register extends AppCompatActivity {


    private EditText username;

    private EditText password;

    private EditText number;

    private EditText address;

    private Button registerBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        number = findViewById(R.id.Number);
        address = findViewById(R.id.address);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().length() == 0 || password.getText().toString().length() == 0 || number.getText().toString().length() == 0 || address.getText().toString().length() == 0) {
                    Toast.makeText(Register.this,"Please enter user name and password",Toast.LENGTH_LONG).show();
                } else {
                    registerCallServer(username.getText().toString(), password.getText().toString());
                }
            }
        });

    }

    private void goToLoginActivity() {
        Intent goToLoginActivity = new Intent(Register.this, Login.class);
        startActivity(goToLoginActivity);
    }

    private void registerCallServer(String username,String password) {
        ApiService apiService = ApiService.getInstance();
        apiService.register(username, password, new HttpListner() {
            @Override
            public void onSuccess(ResponseStatus status, String info) {
                if(status == ResponseStatus.REGISTER_SUCCESS) {
                    Toast.makeText(Register.this,"Registration Success",Toast.LENGTH_SHORT).show();
                    goToLoginActivity();
                } else {
                    Toast.makeText(Register.this,"Internal Error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(ResponseStatus status, String info) {
                Toast.makeText(Register.this,"Internal Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
