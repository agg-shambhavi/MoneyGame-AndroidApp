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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class PortfolioFragment extends Fragment {

    View view;
    private RecyclerView recyclerViewPortfolio;
    private List<PortfolioModel> portfolioModelListlocal;
    PortfolioRecycleViewAdapter portfolioRecycleViewAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.portfolio_fragment, container, false);
        recyclerViewPortfolio = (RecyclerView) view.findViewById(R.id.portfolio_recycleView);
        portfolioModelListlocal = new ArrayList<>();

        recyclerViewPortfolio.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewPortfolio.setHasFixedSize(true);
        portfolioRecycleViewAdapter = new PortfolioRecycleViewAdapter(getContext(), portfolioModelListlocal);
        recyclerViewPortfolio.setAdapter(portfolioRecycleViewAdapter);
        fetchData();
        return view;
    }

    private void fetchData() {
        PortfolioService portfolioService = new PortfolioService(getActivity());
        portfolioService.getUserPortfolio(new PortfolioService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(List<PortfolioModel> portfolioModelList) {
                portfolioModelListlocal.addAll(portfolioModelList);
                portfolioRecycleViewAdapter.notifyDataSetChanged();

            }
        });
    }

}
