package com.kingsmen.finsights.dao;

import java.util.Date;

public class Transaction {
    private String category;
    private Double amount;
    private Date date;
    private String merchant;

    public Transaction() {
        this.category = "";
        this.amount = 0.0;
        this.date = null;
        this.merchant = "";
    }

    public Transaction(String category, Double amount, Date date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(String category, Double amount, Date date, String merchant) {
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.merchant = merchant;
    }

    public Transaction(String category, int amount, Date date, String merchant) {
        this.category = category;
        this.amount = Double.valueOf(amount);
        this.date = date;
        this.merchant = merchant;
    }

    public Transaction(String category, Integer amount, Date date) {
        this.category = category;
        this.amount = Double.valueOf(amount);
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setAmount(Integer amount) {
        this.amount = Double.valueOf(amount);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }
}
