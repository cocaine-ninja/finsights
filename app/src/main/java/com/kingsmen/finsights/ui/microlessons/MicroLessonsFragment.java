package com.kingsmen.finsights.ui.microlessons;

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
import com.kingsmen.finsights.adapters.MicroLessonsAdapter;
import com.kingsmen.finsights.ui.gallery.GalleryViewModel;
import com.kingsmen.finsights.values.AccountList;

import java.util.ArrayList;
import java.util.List;

public class MicroLessonsFragment extends Fragment {
    private MicroLessonsViewModel microLessonsViewModel;

    private RecyclerView microLessonsRecyclerView;
    private RecyclerView.Adapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        microLessonsViewModel =
                ViewModelProviders.of(this).get(MicroLessonsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_microlessons, container, false);
        // final TextView textView = root.findViewById(R.id.text_gallery);
        microLessonsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                // textView.setText(s);
            }
        });

        microLessonsRecyclerView = root.findViewById(R.id.microlessonsRecyclerView);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        microLessonsRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new MicroLessonsAdapter(getContext());
        microLessonsRecyclerView.setAdapter(mAdapter);

        return root;
    }
}
