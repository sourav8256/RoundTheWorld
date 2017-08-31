// How to get the current GPS location programmatically in Android

package com.example.sourav.roundtheworld;



import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.MobileAds;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class activity2 extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;
    String mprovider;
    double lati=0;
    double longi=0;
    double oplati=0;
    double oplongi=0;
    static final Integer LOCATION = 0x1;



    private static final String TAG = "MainActivity";

    private AdView mAdView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(this, "ca-app-pub-6249242764892548~6350611310");


        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);




        Button button = (Button)findViewById(R.id.mapview);
        button.setVisibility(View.GONE);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){


                if(longi>=0)
                {
                    oplongi=longi-180;
                }
                else
                {
                    oplongi=longi+180;
                }

                oplati=-lati;

                double latitude = oplati;
                double longitude = oplongi;
                String label = "ABC Label";
                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                startActivity(intent);


            }

        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        mprovider = locationManager.getBestProvider(criteria, false);

        if (mprovider != null && !mprovider.equals("")) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = locationManager.getLastKnownLocation(mprovider);
            locationManager.requestLocationUpdates(mprovider, 15000, 1, this);

            if (location != null)
                onLocationChanged(location);
            else
                Toast.makeText(getBaseContext(), "Please Make Sure GPS(Location service) Is ON", Toast.LENGTH_LONG).show();
            Toast.makeText(getBaseContext(), "Please Make Sure GPS(Location service) Is ON", Toast.LENGTH_LONG).show();
        }
    }




















    @Override
    public void onLocationChanged(Location location) {
        //TextView longitude = (TextView) findViewById(R.id.textView);
        //TextView latitude = (TextView) findViewById(R.id.textView1);
        TextView loading = (TextView) findViewById(R.id.loading);
        View b = findViewById(R.id.mapview);

        loading.setVisibility(View.GONE);
        b.setVisibility(View.VISIBLE);

        lati=location.getLatitude();
        longi=location.getLongitude();



        //longitude.setText("Current Longitude:" + location.getLongitude());
        //latitude.setText("Current Latitude:" + location.getLatitude());
    }







    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}