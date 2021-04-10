package com.example.moneygame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class PortfolioRecycleViewAdapter extends RecyclerView.Adapter<PortfolioRecycleViewAdapter.PortfolioViewHolder> {

    Context context;
    List<PortfolioModel> portfolioModelList;

    public PortfolioRecycleViewAdapter(Context context, List<PortfolioModel> portfolioModelList) {
        this.context = context;
        this.portfolioModelList = portfolioModelList;
    }

    @NonNull
    @Override
    public PortfolioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(context).inflate(R.layout.portfolio_item, parent, false);
        PortfolioViewHolder portfolioViewHolder = new PortfolioViewHolder(view);

        return portfolioViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PortfolioViewHolder holder, int position) {
        holder.stockName.setText(portfolioModelList.get(position).getStock_symbol());
        holder.quantity.setText(String.valueOf(portfolioModelList.get(position).getQuantity()));
        holder.currentPrice.setText(String.valueOf(portfolioModelList.get(position).getEod_price()));

    }

    @Override
    public int getItemCount() {
        return portfolioModelList.size();
    }

    public static class PortfolioViewHolder extends RecyclerView.ViewHolder {

        private TextView stockName;
        private TextView quantity;
        private TextView currentPrice;

        public PortfolioViewHolder(@NonNull View itemView) {
            super(itemView);

            stockName = (TextView) itemView.findViewById(R.id.stock_name_portfolio_item);
            quantity = (TextView) itemView.findViewById(R.id.qty_portfolio_item);
            currentPrice = (TextView) itemView.findViewById(R.id.curr_price_portfolio_item);
        }
    }
}
