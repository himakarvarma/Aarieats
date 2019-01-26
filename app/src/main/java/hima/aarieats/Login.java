package hima.aarieats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                goToMainActivity();
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

    private void goToMainActivity() {
        Intent goToMainActivity = new Intent(Login.this,MainActivity.class);
        startActivity(goToMainActivity);


    }
    private void gotoRegisterActivity()
    {
        Intent gotoRegisterActivity = new Intent(Login.this,Register.class);
        startActivity(gotoRegisterActivity);

    }

}
