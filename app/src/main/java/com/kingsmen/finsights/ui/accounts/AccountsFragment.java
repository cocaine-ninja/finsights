package com.kingsmen.finsights.ui.accounts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kingsmen.finsights.R;
import com.kingsmen.finsights.adapters.AccountsAdapter;
import com.kingsmen.finsights.adapters.TransactionAdapter;
import com.kingsmen.finsights.values.AccountList;

public class AccountsFragment extends Fragment {
    private AccountsViewModel accountsViewModel;

    private RecyclerView accountsRecyclerView;
    private RecyclerView.Adapter mAdapter;

    AccountList accountList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountsViewModel =
                ViewModelProviders.of(this).get(AccountsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_accounts, container, false);
        // final TextView textView = root.findViewById(R.id.text_gallery);
        accountsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                // textView.setText(s);
            }
        });

        accountsRecyclerView = root.findViewById(R.id.accountsRecyclerView);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        accountsRecyclerView.setLayoutManager(layoutManager);


        accountList = new AccountList();
        accountList.init();

        mAdapter = new AccountsAdapter(getContext(), accountList.getAccounts());
        accountsRecyclerView.setAdapter(mAdapter);

        return root;
    }
}
