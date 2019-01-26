package hima.aarieats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {


    private EditText username;

    private EditText password;

    private EditText Number;

    private EditText address;

    private Button registerBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Number = findViewById(R.id.Number);
        address = findViewById(R.id.address);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginActivity();

            }
        });

    }

    private void goToLoginActivity() {
        Intent goToLoginActivity = new Intent(Register.this, Login.class);
        startActivity(goToLoginActivity);
    }
}
