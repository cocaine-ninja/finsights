package com.kingsmen.finsights.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kingsmen.finsights.R;
import com.kingsmen.finsights.dao.Transaction;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private List<Transaction> transactions;
    private LayoutInflater mInflater;
    private TransactionAdapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    public TransactionAdapter(Context context, List<Transaction> transactions) {
        this.mInflater = LayoutInflater.from(context);
        this.transactions = transactions;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.transactions_recycler_view_item, parent, false);
        return new TransactionAdapter.ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        Transaction t = this.transactions.get(position);
        String transactionCategory = t.getCategory();
        String transactionAmount = String.valueOf(t.getAmount());
        String transactionDate = String.valueOf(t.getDate());
        holder.transactionCategoryTextView.setText(transactionCategory);
        holder.transactionAmountTextView.setText(transactionAmount);
        holder.transactionDateTextView.setText(transactionDate);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return transactions.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView transactionCategoryTextView;
        TextView transactionAmountTextView;
        TextView transactionDateTextView;

        ViewHolder(View itemView) {
            super(itemView);
            transactionCategoryTextView = itemView.findViewById(R.id.transactionCategoryTextView);
            transactionAmountTextView = itemView.findViewById(R.id.transactionAmountTextView);
            transactionDateTextView = itemView.findViewById(R.id.transactionDateTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    public void setClickListener(TransactionAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
