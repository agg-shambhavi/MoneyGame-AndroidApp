package com.example.moneygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        EditText emailField = findViewById(R.id.LoginScreenEmailEditText);
        EditText passwordField = findViewById(R.id.LoginScreenPasswordEditText);
        Button loginBtn = findViewById(R.id.LoginScreenBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                Toast.makeText(LoginActivity.this, "Email: " + email + " Password: " + password, Toast.LENGTH_LONG).show();
            }
        });
    }
}