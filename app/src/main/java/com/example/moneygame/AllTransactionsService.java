package com.example.moneygame;

import android.content.Context;
import android.os.Build;
import android.util.Log;

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

public class AllTransactionsService {
    public static final String URL_TRANSACTIONS = BASE_URL + "/transaction/all";
    Context context;

    public AllTransactionsService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(List<AllTransactionsModel> transactionsModelList);
    }

    public String getToken() {
        String jwttoken = JWTSharedPref.getDefaults("jwt_token", context.getApplicationContext());
        return jwttoken;
    }

    public void getUserTransactions(AllTransactionsService.VolleyResponseListener volleyResponseListener) {

        List<AllTransactionsModel> allTransactionsModelList = new ArrayList<>();

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, URL_TRANSACTIONS, null,
                new Response.Listener<JSONArray>() {


                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {

                            try {
                                AllTransactionsModel allTransactionsModel = new AllTransactionsModel();
                                JSONObject stock = (JSONObject) response.get(i);
                                allTransactionsModel.setStock_symbol(stock.getString("stock_symbol"));
                                allTransactionsModel.setStock_name(stock.getString("stock_name"));
                                allTransactionsModel.setTransaction_date(stock.getString("transaction_date"));
                                allTransactionsModel.setTransaction_type(stock.getString("transaction_type"));
                                allTransactionsModel.setQuantity(Integer.parseInt(stock.getString("abs")));
                                allTransactionsModel.setEod_price(Double.parseDouble(stock.getString("eod_price")));
                                Log.d(TAG, allTransactionsModel.toString() + "heloooooooooooo");
                                allTransactionsModelList.add(allTransactionsModel);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        volleyResponseListener.onResponse(allTransactionsModelList);


//                        volleyResponseListener.onResponse(response.length());

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
