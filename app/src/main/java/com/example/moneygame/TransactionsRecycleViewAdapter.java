package com.example.moneygame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransactionsRecycleViewAdapter extends RecyclerView.Adapter<TransactionsRecycleViewAdapter.TransactionsViewHolder> {

    Context context;
    List<AllTransactionsModel> allTransactionsModelList;

    public TransactionsRecycleViewAdapter(Context context, List<AllTransactionsModel> allTransactionsModelList) {
        this.context = context;
        this.allTransactionsModelList = allTransactionsModelList;
    }

    @NonNull
    @Override
    public TransactionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(context).inflate(R.layout.transactions_item, parent, false);
        TransactionsViewHolder transactionsViewHolder = new TransactionsViewHolder(view);

        return transactionsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsViewHolder holder, int position) {
        holder.stockName.setText(allTransactionsModelList.get(position).getStock_symbol());
        holder.quantity.setText(String.valueOf(allTransactionsModelList.get(position).getQuantity()));
        holder.transactionPrice.setText(String.valueOf(allTransactionsModelList.get(position).getEod_price()));
        holder.transactionDate.setText(String.valueOf(allTransactionsModelList.get(position).getTransaction_date()));
        holder.transactionType.setText(String.valueOf(allTransactionsModelList.get(position).getTransaction_type()));

    }

    @Override
    public int getItemCount() {
        return allTransactionsModelList.size();
    }

    public static class TransactionsViewHolder extends RecyclerView.ViewHolder {

        private TextView stockName;
        private TextView quantity;
        private TextView transactionPrice;
        private TextView transactionType;
        private TextView transactionDate;

        public TransactionsViewHolder(@NonNull View itemView) {
            super(itemView);

            stockName = (TextView) itemView.findViewById(R.id.ti_stock_symbol);
            quantity = (TextView) itemView.findViewById(R.id.ti_qty);
            transactionPrice = (TextView) itemView.findViewById(R.id.ti_price);
            transactionType = (TextView) itemView.findViewById(R.id.ti_transaction_type);
            transactionDate = (TextView) itemView.findViewById(R.id.ti_date);


        }
    }
}
