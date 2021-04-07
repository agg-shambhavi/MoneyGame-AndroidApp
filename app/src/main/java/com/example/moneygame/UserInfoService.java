package com.example.moneygame;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class UserInfoService {

    public static final String BASE_URL = "http://192.168.29.235:5000";
    public static final String URL_USERINFO = BASE_URL + "/dashboard/userinfo";
    Context context;

    public UserInfoService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(UserInfoModel userInfo);
    }

    public String getToken() {
        String jwttoken = JWTSharedPref.getDefaults("jwt_token", context.getApplicationContext());
        return jwttoken;
    }

    public void getUserInfo(UserInfoService.VolleyResponseListener volleyResponseListener) {
        UserInfoModel userInfo = null;

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, URL_USERINFO, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            UserInfoModel userInfoModel = new UserInfoModel(
                                    response.getString("first_name"),
                                    response.getString("last_name"),
                                    response.getString("user_email"),
                                    response.getString("registration_date")

                            );
                            volleyResponseListener.onResponse(userInfoModel);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                volleyResponseListener.onError("Incorrect email password or server error");
            }
        }
        ) {
            @NonNull
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                final Map<String, String> params = new HashMap<>();
                params.put("token", getToken());
                return params;
            }
        };

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
