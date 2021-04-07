package com.example.moneygame;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import static android.content.ContentValues.TAG;

public class PortfolioFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        PortfolioService portfolioService = new PortfolioService(getActivity());
        portfolioService.getUserPortfolio(new PortfolioService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(List<PortfolioModel> portfolioModelList) {
                Toast.makeText(getActivity(), portfolioModelList.toString(), Toast.LENGTH_LONG).show();
            }
        });

        return inflater.inflate(R.layout.portfolio_fragment, container, false);
    }
}
