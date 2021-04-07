package com.example.moneygame;

public class AllTransactionsModel {

    private String stock_symbol;
    private String stock_name;
    private int quantity;
    private Double eod_price;
    private String transaction_date;
    private String transaction_type;

    public AllTransactionsModel(String stock_symbol, String stock_name, int quantity, Double eod_price, String transaction_date, String transaction_type) {
        this.stock_symbol = stock_symbol;
        this.stock_name = stock_name;
        this.quantity = quantity;
        this.eod_price = eod_price;
        this.transaction_date = transaction_date;
        this.transaction_type = transaction_type;
    }

    public AllTransactionsModel() {
    }

    @Override
    public String toString() {
        return "AllTransactionsModel{" +
                "stock_symbol='" + stock_symbol + '\'' +
                ", stock_name='" + stock_name + '\'' +
                ", quantity=" + quantity +
                ", eod_price=" + eod_price +
                ", transaction_date='" + transaction_date + '\'' +
                ", transaction_type='" + transaction_type + '\'' +
                '}';
    }

    public String getStock_symbol() {
        return stock_symbol;
    }

    public void setStock_symbol(String stock_symbol) {
        this.stock_symbol = stock_symbol;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getEod_price() {
        return eod_price;
    }

    public void setEod_price(Double eod_price) {
        this.eod_price = eod_price;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }
}
