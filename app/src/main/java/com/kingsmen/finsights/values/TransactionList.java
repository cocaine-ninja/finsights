package com.kingsmen.finsights.values;

import com.kingsmen.finsights.dao.Transaction;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionList {
    private List<Transaction> transactions;

    public TransactionList() {
        this.transactions = new ArrayList<>();
    }

    public TransactionList(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void init() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Transaction tf1 = new Transaction("Food", 150, sdf.parse("29/09/2020"), "Swiggy");
        Transaction tf2 = new Transaction("Food", 400, sdf.parse("28/09/2020"));
        Transaction tf3 = new Transaction("Food", 300, sdf.parse("27/09/2020"));
        Transaction tf4 = new Transaction("Food", 50, sdf.parse("26/09/2020"));
        Transaction tf5 = new Transaction("Food", 204, sdf.parse("25/09/2020"));
        Transaction tf6 = new Transaction("Food", 170, sdf.parse("24/09/2020"));

        Transaction tt1 = new Transaction("Transport", 50, sdf.parse("29/09/2020"));
        Transaction tt2 = new Transaction("Transport", 400, sdf.parse("28/09/2020"));
        Transaction tt3 = new Transaction("Transport", 7532, sdf.parse("27/09/2020"));

        Transaction ts1 = new Transaction("Shopping", 2303, sdf.parse("29/09/2020"));
        Transaction ts2 = new Transaction("Shopping", 1723, sdf.parse("27/09/2020"));

        Transaction th1 = new Transaction("Household", 384, sdf.parse("29/09/2020"));
        Transaction th2 = new Transaction("Household", 1219, sdf.parse("28/09/2020"));
        Transaction th3 = new Transaction("Household", 123, sdf.parse("27/09/2020"));
        Transaction th4 = new Transaction("Household", 4278, sdf.parse("28/09/2020"));

        List<Transaction> tl = Arrays.asList(tf1, tf2, tf3, tf4, tf5, tf6, tt1, tt2, tt3, ts1, ts2, th1, th2, th3, th4);
        transactions.addAll(0, tl);
    }

    public Map<String, Double> getAggregateByCategory() {
        Map<String, Double> map = new HashMap<>();
        for (Transaction t: this.transactions) {
            String category = t.getCategory();
            if (map.containsKey(category)) {
                Double previousAmt = map.get(category);
                map.put(category, previousAmt + t.getAmount());
            } else {
                map.put(category, t.getAmount());
            }
        }

        return map;
    }
}
