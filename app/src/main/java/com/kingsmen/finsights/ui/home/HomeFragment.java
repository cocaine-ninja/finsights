package com.kingsmen.finsights.ui.home;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.kingsmen.finsights.R;
import com.kingsmen.finsights.service.location.LocationService;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private HomeViewModel homeViewModel;

    LocationService mService;
    boolean mBound = false;

    TextView mLatitudeTextView;
    TextView mLongitudeTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        mLatitudeTextView = (TextView) getActivity().findViewById(R.id.latitude_textview);
        mLongitudeTextView = (TextView) getActivity().findViewById(R.id.longitude_textview);

        // start and bind location service
        Intent intent = new Intent(getActivity(), LocationService.class);
        getActivity().startService(intent);

        getActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE);

        return root;
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            LocationService.LocationBinder binder = (LocationService.LocationBinder) service;
            mService = binder.getService();
            mBound = true;

            mLatitudeTextView.setText(mService.getmLatitude());
            mLongitudeTextView.setText(mService.getmLongitude());
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}