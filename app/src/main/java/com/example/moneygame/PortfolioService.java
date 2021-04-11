package com.example.moneygame;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.example.moneygame.UserInfoService.BASE_URL;


public class PortfolioService {

    public static final String URL_PORTFOLIO = BASE_URL + "/dashboard/portfolio";
    Context context;

    public PortfolioService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(List<PortfolioModel> portfolioModelList);
    }

    public String getToken() {
        String jwttoken = JWTSharedPref.getDefaults("jwt_token", context.getApplicationContext());
        return jwttoken;
    }

    public void getUserPortfolio(PortfolioService.VolleyResponseListener volleyResponseListener) {

        List<PortfolioModel> portfolioModelList = new ArrayList<>();

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, URL_PORTFOLIO, null,
                new Response.Listener<JSONArray>() {


                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONArray response) {


                        for (int i = 0; i < response.length(); i++) {
                            try {
                                PortfolioModel portfolioModel = new PortfolioModel();
                                JSONObject stock = (JSONObject) response.get(i);
                                portfolioModel.setStock_symbol(stock.getString("stock_symbol"));
                                portfolioModel.setQuantity(Integer.parseInt(stock.getString("qty")));
                                portfolioModel.setEod_price(Double.parseDouble(stock.getString("eod_price")));
                                portfolioModelList.add(portfolioModel);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        volleyResponseListener.onResponse(portfolioModelList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                volleyResponseListener.onError(error.toString());
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

        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }
}
