package com.example.moneygame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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

//                Toast.makeText(LoginActivity.this, "Email: " + email + " Password: " + password, Toast.LENGTH_LONG).show()
                LoginService loginService = new LoginService(LoginActivity.this);
                loginService.login(email, password, new LoginService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(LoginActivity.this, "Incorrect e-mail or password " + password, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(boolean loginSuccess) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });


            }
        });
    }
}