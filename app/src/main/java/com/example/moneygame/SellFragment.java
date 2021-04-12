package com.example.moneygame;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class SellFragment extends Fragment {

    View view;
    AutoCompleteTextView stock_symbol_dropdown;
    private ArrayList<String> sell_stocks;
    public static final String URL_SELL_STOCKS = "http://192.168.29.235:5000/dashboard/sell-stocks";
    public static final String URL_SELL = "http://192.168.29.235:5000/transaction/sell";
    EditText quantity_et;
    Button sell;

    public String getToken() {
        String jwttoken = JWTSharedPref.getDefaults("jwt_token", getContext().getApplicationContext());
        return jwttoken;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.sell_fragment, container, false);
        stock_symbol_dropdown = (AutoCompleteTextView) view.findViewById(R.id.sell_stock_symbol_ac);

        ArrayAdapter<String> stocksAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, sell_stocks);
        stock_symbol_dropdown.setAdapter(stocksAdapter);
        quantity_et = (EditText) view.findViewById(R.id.sell_qty);
        sell = (Button) view.findViewById(R.id.sellBtn);

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String stock_symbol = stock_symbol_dropdown.getText().toString();
                int quantity = Integer.parseInt(quantity_et.getText().toString());

                final JSONObject sellJson = new JSONObject();

                try {
                    sellJson.put("stock_symbol", stock_symbol);
                    sellJson.put("qty", quantity);
                    sellJson.put("date", "2021-2-18");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                final StringRequest stringRequest = new StringRequest(
                        Request.Method.POST, URL_SELL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), "Selling Transaction Successful", Toast.LENGTH_SHORT).show();
                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        fr.replace(R.id.fragment_container, new PortfolioFragment());
                        fr.commit();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Buy transaction failed", Toast.LENGTH_LONG).show();
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

                    public byte[] getBody() {
                        return sellJson.toString().getBytes();
                    }

                    public String getBodyContentType() {
                        return "application/json";
                    }
                };
                MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
            }
        });


        return view;
    }

    private void fetchSellStocksData() {
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, URL_SELL_STOCKS, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject stock = (JSONObject) response.get(i);
                                String stock_symbol = stock.getString("stock_symbol");
                                sell_stocks.add(stock_symbol);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Cannot get user owned stocks " + error.toString(), Toast.LENGTH_LONG).show();
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
        MySingleton.getInstance(getActivity()).addToRequestQueue(jsonArrayRequest);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sell_stocks = new ArrayList<>();
        fetchSellStocksData();

    }

}
