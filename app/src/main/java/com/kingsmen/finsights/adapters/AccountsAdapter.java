package com.kingsmen.finsights.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kingsmen.finsights.R;
import com.kingsmen.finsights.dao.Account;
import com.kingsmen.finsights.dao.Transaction;

import java.util.List;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.ViewHolder> {
    private List<Account> accounts;
    private LayoutInflater mInflater;
    private AccountsAdapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    public AccountsAdapter(Context context, List<Account> accounts) {
        this.mInflater = LayoutInflater.from(context);
        this.accounts = accounts;
    }

    @NonNull
    @Override
    public AccountsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.account_recycler_view_item, parent, false);
        return new AccountsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountsAdapter.ViewHolder holder, int position) {
        Account a = this.accounts.get(position);
        String accountType = a.getAccountType();
        String accountBalance = String.valueOf(a.getAccountBalance());
        String accountExpenditure = String.valueOf(a.getAccountExpenditure());

        holder.accountTypeTextView.setText(accountType);
        holder.accountBalanceTextView.setText(accountBalance);
        holder.accountExpenditureTextView.setText(accountExpenditure);
    }

    @Override
    public int getItemCount() {
        return this.accounts.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView accountTypeTextView;
        TextView accountBalanceTextView;
        TextView accountExpenditureTextView;

        ViewHolder(View itemView) {
            super(itemView);
            accountTypeTextView = itemView.findViewById(R.id.accountTypeTextView);
            accountBalanceTextView = itemView.findViewById(R.id.accountBalanceTextView);
            accountExpenditureTextView = itemView.findViewById(R.id.accountExpenditureTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
