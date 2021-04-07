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

public class TransactionsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        AllTransactionsService allTransactionsService = new AllTransactionsService(getActivity());
        allTransactionsService.getUserTransactions(new AllTransactionsService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(List<AllTransactionsModel> transactionsModelList) {
                Toast.makeText(getActivity(), transactionsModelList.toString(), Toast.LENGTH_LONG).show();
                Log.d(TAG, transactionsModelList.toString());
            }
        });
        return inflater.inflate(R.layout.transactions_fragment, container, false);
    }
}
