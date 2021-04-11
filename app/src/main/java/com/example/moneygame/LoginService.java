package com.example.moneygame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

public class LoginService {

    public static final String URL_AUTH_LOGIN = "http://192.168.29.235:5000/auth/login";
    Context context;

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(boolean loginSuccess);
    }

    public LoginService(Context context) {
        this.context = context;
    }

    public void login(String email, String password, VolleyResponseListener volleyResponseListener) {

        final JSONObject loginJSON = new JSONObject();

        try {
            loginJSON.put("email", email);
            loginJSON.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, URL_AUTH_LOGIN, loginJSON,
                new Response.Listener<JSONObject>() {
                    private static final String TOKEN_ID = "token_pref";

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            @NonNull final String token = response.getString("jwtToken");
                            JWTSharedPref.setDefaults("jwt_token", token, context);
                            volleyResponseListener.onResponse(true);
//
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Error inside on response try catch", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                volleyResponseListener.onError("Incorrect email password or server error");
            }
        }
        );
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
