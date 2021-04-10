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

public class TransactionsFragment extends Fragment {

    View view;
    private RecyclerView recyclerViewTransactions;
    private List<AllTransactionsModel> allTransactionsModelList;
    TransactionsRecycleViewAdapter transactionsRecycleViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.transactions_fragment, container, false);
        recyclerViewTransactions = (RecyclerView) view.findViewById(R.id.transactionScreen_recycleView);
        allTransactionsModelList = new ArrayList<>();
        recyclerViewTransactions.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewTransactions.setHasFixedSize(true);
        transactionsRecycleViewAdapter = new TransactionsRecycleViewAdapter(getContext(), allTransactionsModelList);
        recyclerViewTransactions.setAdapter(transactionsRecycleViewAdapter);
        fetchTransactionData();

        return view;
    }

    private void fetchTransactionData() {
        AllTransactionsService allTransactionsService = new AllTransactionsService(getActivity());
        allTransactionsService.getUserTransactions(new AllTransactionsService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(List<AllTransactionsModel> transactionsModelList) {

                allTransactionsModelList.addAll(transactionsModelList);
                transactionsRecycleViewAdapter.notifyDataSetChanged();

            }
        });
    }
}
