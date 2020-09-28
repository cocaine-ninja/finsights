package com.kingsmen.finsights.ui.home;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.kingsmen.finsights.R;
import com.kingsmen.finsights.adapters.NewsAdapter;
import com.kingsmen.finsights.dao.Offer;
import com.kingsmen.finsights.values.CountrySpecificOffers;
import com.kingsmen.finsights.adapters.OfferAdapter;
import com.kingsmen.finsights.values.NewsList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private static final String TAG = "HomeFragment";
    private HomeViewModel homeViewModel;

    private LocationManager locationManager;
    private Location mLocation;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private long UPDATE_INTERVAL = 2 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */

    private RecyclerView offersRecyclerView;
    private RecyclerView newsRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mNewsAdapter;
    private RecyclerView.LayoutManager layoutManager;


    Double mLatitude;
    Double mLongitude;
    String mCountry;

    CountrySpecificOffers offers;
    NewsList newsList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        offersRecyclerView = root.findViewById(R.id.offersRecyclerView);
        newsRecyclerView = root.findViewById(R.id.newsRecyclerView);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        offersRecyclerView.setLayoutManager(horizontalLayoutManager);
        LinearLayoutManager horizontalLayoutManager2
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        newsRecyclerView.setLayoutManager(horizontalLayoutManager2);

        // checkLocation();

//        mLatitudeTextView = (TextView) root.findViewById(R.id.latitude_textview);
//        mLongitudeTextView = (TextView) root.findViewById(R.id.longitude_textview);
//        mCountryTextView = (TextView) root.findViewById(R.id.country_textView);
//        mOffersTextView = (TextView) root.findViewById(R.id.offers_textView);
        // start and bind location service
//        Intent intent = new Intent(getActivity(), LocationService.class);
//        getActivity().startService(intent);
//        getActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE);

        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();

        offers = new CountrySpecificOffers();
        offers.init();

        newsList = new NewsList();
        newsList.init();

        return root;
    }

    private boolean checkLocation() {
        Log.d(TAG, "checkLocation");
        if(!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private boolean isLocationEnabled() {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    protected void startLocationUpdates() {
        // Create the location request
        Log.d(TAG, "startLocationUpdates");
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);
        // Request location updates
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Log.d(TAG, "isGoogleApiClientConnected = " + mGoogleApiClient.isConnected());
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                mLocationRequest, this);
        Log.d("reque", "--->>>>");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "onConnected");
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startLocationUpdates();
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(mLocation == null){
            startLocationUpdates();
        }
        if (mLocation != null) {
            mLatitude = mLocation.getLatitude();
            mLongitude = mLocation.getLongitude();

            Log.d(TAG, "Lat = " + String.valueOf(mLatitude));
            Log.d(TAG, "Long = " + String.valueOf(mLongitude));

//            mLatitudeTextView.setText("Lat = " + String.valueOf(mLocation.getLatitude()));
//            mLongitudeTextView.setText("Long = " + String.valueOf(mLocation.getLongitude()));

            mCountry = getAddress(mLatitude, mLongitude);
            Log.d(TAG, "Country = " + String.valueOf(mCountry));
//            mCountryTextView.setText("Country = " + mCountry);

            List<Offer> countryOffers = offers.getOffers().get(mCountry);
            String s = "";
            s = s + "Offers for this country are\n";
            for (Offer o: countryOffers) {
                s = s + "Offer type: " + o.getType() + "\n";
                s = s + "Offer description: " + o.getDescription() + "\n";
            }
            Log.d(TAG, s);
            mAdapter = new OfferAdapter(getContext(), countryOffers);
            offersRecyclerView.setAdapter(mAdapter);

            mNewsAdapter = new NewsAdapter(getContext(), newsList.getNewsList());
            newsRecyclerView.setAdapter(mNewsAdapter);
//            mOffersTextView.setText(s);
        } else {
            Toast.makeText(getContext(), "Location not Detected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection Suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed. Error: " + connectionResult.getErrorCode());
    }

    @Override
    public void onLocationChanged(Location location) {
        String msg = "Updated Location: " +
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());
        // mLatitudeTextView.setText(String.valueOf(location.getLatitude()));
        // mLongitudeTextView.setText(String.valueOf(location.getLongitude() ));
        // Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

        // You can now create a LatLng Object for use with maps
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
    }

    public String getAddress(double lat, double lng) {
        Geocoder gcd = new Geocoder(getContext(), Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(lat, lng, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses.size() > 0) {
            String countryName = addresses.get(0).getCountryName();
            return countryName;
        }
        return null;
    }

}