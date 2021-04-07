package com.example.moneygame;

public class PortfolioModel {

    private String stock_symbol;
    private int quantity;
    private Double eod_price;

    public PortfolioModel(String stock_symbol, int quantity, Double eod_price) {
        this.stock_symbol = stock_symbol;
        this.quantity = quantity;
        this.eod_price = eod_price;
    }

    public PortfolioModel() {
    }

    @Override
    public String toString() {
        return "PortfolioModel{" +
                "stock_symbol='" + stock_symbol + '\'' +
                ", quantity=" + quantity +
                ", eod_price=" + eod_price +
                '}';
    }

    public String getStock_symbol() {
        return stock_symbol;
    }

    public void setStock_symbol(String stock_symbol) {
        this.stock_symbol = stock_symbol;
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

}
