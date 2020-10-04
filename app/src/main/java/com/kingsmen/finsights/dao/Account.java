package com.kingsmen.finsights.dao;

import java.util.Date;

public class Account {
    private String accountName;
    private String accountType;
    private int accountBalance;
    private int accountExpenditure;
    private Date lastUpdated;

    public Account() {
        this.accountName = "";
        this.accountType = "";
        this.accountBalance = 0;
        this.accountExpenditure = 0;
        this.lastUpdated = null;
    }

    public Account(String accountName, String accountType, int accountBalance, int accountExpenditure, Date lastUpdated) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.accountExpenditure = accountExpenditure;
        this.lastUpdated = lastUpdated;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getAccountExpenditure() {
        return accountExpenditure;
    }

    public void setAccountExpenditure(int accountExpenditure) {
        this.accountExpenditure = accountExpenditure;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
