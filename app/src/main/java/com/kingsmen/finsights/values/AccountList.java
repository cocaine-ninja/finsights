package com.kingsmen.finsights.values;

import com.kingsmen.finsights.dao.Account;
import com.kingsmen.finsights.dao.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AccountList {
    private List<Account> accounts;

    public AccountList() {
        this.accounts = new ArrayList<>();
    }

    public AccountList(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void init() {
        Account account1 = new Account();
        account1.setAccountName("HSBC");
        account1.setAccountType("savings");
        account1.setAccountBalance(432123);
        account1.setAccountExpenditure(129321);
        account1.setLastUpdated(new Date());

        Account account2 = new Account();
        account2.setAccountName("HSBC");
        account2.setAccountType("credit");
        account2.setAccountBalance(150000);
        account2.setAccountExpenditure(37400);
        account2.setLastUpdated(new Date());

        Account account3 = new Account();
        account3.setAccountName("HSBC");
        account3.setAccountType("credit1");
        account3.setAccountBalance(350000);
        account3.setAccountExpenditure(123123);
        account3.setLastUpdated(new Date());

        Account account4 = new Account();
        account4.setAccountName("HSBC");
        account4.setAccountType("credit2");
        account4.setAccountBalance(200000);
        account4.setAccountExpenditure(1600);
        account4.setLastUpdated(new Date());

        Account account5 = new Account();
        account5.setAccountName("HSBC");
        account5.setAccountType("credit3");
        account5.setAccountBalance(150000);
        account5.setAccountExpenditure(0);
        account5.setLastUpdated(new Date());

        Account account6 = new Account();
        account6.setAccountName("HSBC");
        account6.setAccountType("savings2");
        account6.setAccountBalance(150000);
        account6.setAccountExpenditure(7400);
        account6.setLastUpdated(new Date());

        List<Account> a = Arrays.asList(account1, account2, account3, account4, account5, account6);
        this.accounts.addAll(0, a);
    }
}
