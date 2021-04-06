package com.example.moneygame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

//                Toast.makeText(LoginActivity.this, "Email: " + email + " Password: " + password, Toast.LENGTH_LONG).show();
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                String url = "http://192.168.29.235:5000/auth/login";

                final JSONObject loginJSON = new JSONObject();

                try {
                    loginJSON.put("email", email);
                    loginJSON.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST, url, loginJSON,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    @NonNull final String token = response.getString("jwtToken");
                                    Toast.makeText(LoginActivity.this, "token: " + token, Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();

                                    Toast.makeText(LoginActivity.this, "Error inside on response try catch", Toast.LENGTH_LONG).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(LoginActivity.this, "Error from on error response", Toast.LENGTH_LONG).show();
                    }
                }
                );

                Volley.newRequestQueue(LoginActivity.this).add(jsonObjectRequest);


            }
        });
    }
}