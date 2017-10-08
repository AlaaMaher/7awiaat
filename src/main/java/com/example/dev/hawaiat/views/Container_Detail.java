package com.example.dev.hawaiat.views;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.adapters.CompanyRecyclerAdapter;
import com.example.dev.hawaiat.adapters.MyCustomPagerAdapter;
import com.example.dev.hawaiat.webServices.RetrofitWebService;
import com.example.dev.hawaiat.webServices.request.CompanyProfileRequest;
import com.example.dev.hawaiat.webServices.responses.CompanyProfileResponse;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Container_Detail extends AppCompatActivity {


    private static final int PERMISSION_REQUEST_CODE = 1;

    RecyclerView myrcycler;
    CompanyRecyclerAdapter myAdapter;
    ViewPager viewPager3;
    List<String> images2 = new ArrayList<>();
    MyCustomPagerAdapter myCustomPagerAdapter;
    GPSUserLocation gpsUserLocation;
    int tab = 2;


    ImageView leftNav, rightNav;
    private String lat, longtit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container__detail);


        //============================ web services ==========================

        getCompanyProfile();


        //=================== recyeler for containers============================
        List<String> images = new ArrayList<String>();
        List<String> capacity = new ArrayList<String>();
        List<String> cost = new ArrayList<String>();

        images.add("https://thumb7.shutterstock.com/display_pic_with_logo/608644/214476049/stock-photo-industrial-crane-loading-containers-in-a-cargo-freight-ship-214476049.jpg");
        images.add("https://thumb7.shutterstock.com/display_pic_with_logo/608644/214476049/stock-photo-industrial-crane-loading-containers-in-a-cargo-freight-ship-214476049.jpg");
        images.add("https://thumb7.shutterstock.com/display_pic_with_logo/608644/214476049/stock-photo-industrial-crane-loading-containers-in-a-cargo-freight-ship-214476049.jpg");

        capacity.add(" 200 ");
        capacity.add(" 30 ");
        capacity.add(" 20 ");

        cost.add("400");
        cost.add("400");
        cost.add("400");


        myrcycler = (RecyclerView) findViewById(R.id.container_detail_recycler);
        myrcycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myrcycler.setLayoutManager(layoutManager);
        myAdapter = new CompanyRecyclerAdapter(Container_Detail.this, images, capacity, cost);
        myrcycler.setAdapter(myAdapter);


        viewPager3 = (ViewPager) findViewById(R.id.pager3);
        images2.add("https://thumb7.shutterstock.com/display_pic_with_logo/608644/214476049/stock-photo-industrial-crane-loading-containers-in-a-cargo-freight-ship-214476049.jpg");
        images2.add("https://thumb7.shutterstock.com/display_pic_with_logo/608644/214476049/stock-photo-industrial-crane-loading-containers-in-a-cargo-freight-ship-214476049.jpg");
        images2.add("https://thumb7.shutterstock.com/display_pic_with_logo/608644/214476049/stock-photo-industrial-crane-loading-containers-in-a-cargo-freight-ship-214476049.jpg");


        myCustomPagerAdapter = new MyCustomPagerAdapter(Container_Detail.this, images2);
        viewPager3.setAdapter(myCustomPagerAdapter);

        // viewPager.setAdapter(myCustomPagerAdapter);
        PageIndicatorView indicator = (PageIndicatorView) findViewById(R.id.pageIndicatorView);
        indicator.setViewPager(viewPager3);
        indicator.setAnimationType(AnimationType.SCALE);
        indicator.setRadius(10);
        indicator.setPadding(30);
        indicator.setSelection(tab);

        leftNav = (ImageView) findViewById(R.id.imgageleft3);
        rightNav = (ImageView) findViewById(R.id.imageright3);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.rating_bar);

        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#FA7300"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.parseColor("#FA7300"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(Color.parseColor("#FA7300"), PorterDuff.Mode.SRC_ATOP);


        // Images left navigation
        leftNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tab = viewPager3.getCurrentItem();

                rightNav.setVisibility(View.INVISIBLE);
                if (tab > 0) {
                    tab--;
                    viewPager3.setCurrentItem(tab);
                } else if (tab == 0) {

                    viewPager3.setCurrentItem(tab);
                }

                myCustomPagerAdapter.setLeftIconViability(leftNav, tab);
                myCustomPagerAdapter.setRightIconViability(rightNav, tab);
            }
        });

        if (tab == 0) {

            leftNav.setVisibility(View.GONE);
            Toast.makeText(Container_Detail.this, "from img left", Toast.LENGTH_SHORT).show();
        } else {
            leftNav.setVisibility(View.VISIBLE);
            //  rightNav.setVisibility(View.VISIBLE);

        }

        // Images right navigatin
        rightNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // leftNav.setVisibility(View.VISIBLE);
                //  tab = viewPager.getCurrentItem();
                tab++;
                viewPager3.setCurrentItem(tab);
                myCustomPagerAdapter.setLeftIconViability(leftNav, tab);
                myCustomPagerAdapter.setRightIconViability(rightNav, tab);

            }
        });


        if (checkPermission()) {
            Toast.makeText(getApplicationContext(), "Permission already granted. ", Toast.LENGTH_SHORT).show();


        } else {

            if (!checkPermission()) {

                requestPermission();

            } else {

                Toast.makeText(getApplicationContext(), "Permission already granted. ", Toast.LENGTH_SHORT).show();


            }

        }


        ///======================================== for currnet location for user======================================

        gpsUserLocation = new GPSUserLocation(Container_Detail.this);

        // check if GPS enabled
        if (gpsUserLocation.canGetLocation()) {

            double latitude = gpsUserLocation.getLatitude();
            double longitude = gpsUserLocation.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gpsUserLocation.showSettingsAlert();
        }
/*            if(gpsUserLocation.canGetLocation()){

                double latitude = gpsUserLocation.getLatitude();
                double longitude = gpsUserLocation.getLongitude();

                // \n is for new line
                Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            }*//*
        }*/


        //getLocation();




   /*     if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            // here to request the missing permissions, and then overriding



            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
*/


    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(Container_Detail.this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            Toast.makeText(this, "GPS permission allows us to access location data. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(Container_Detail.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);

        } else {

            ActivityCompat.requestPermissions(Container_Detail.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;

        } else {

            return false;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "frommm GPS permission allows us to access location data. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(this, "Permission Denied, You cannot access location data.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //gpsUserLocation=new GPSUserLocation(Container_Detail.this);
        // check if GPS enabled
        //  if(gpsUserLocation.canGetLocation()){
/*
        double latitude = gpsUserLocation.getLatitude();
        double longitude = gpsUserLocation.getLongitude();

        // \n is for new line
        Toast.makeText(getApplicationContext(), "Your Location from on resume is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
    }*/
        gpsUserLocation = new GPSUserLocation(Container_Detail.this);

        // check if GPS enabled
        if (gpsUserLocation.canGetLocation()) {

            double latitude = gpsUserLocation.getLatitude();
            double longitude = gpsUserLocation.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gpsUserLocation.showSettingsAlert();
        }


    }


    private void getCompanyProfile() {
        CompanyProfileRequest companyProfileRequest = new CompanyProfileRequest();

        SharedPreferences sharedPreferences = getSharedPreferences(LoginScreen.API_TOKEN_SHARED, MODE_PRIVATE);
        String api_Token = (String) sharedPreferences.getString(LoginScreen.API_TOKEN, "");

        companyProfileRequest.setApiToken(api_Token);

        //TODO please get the correct company id
        companyProfileRequest.setCompanyID("1");


        //TODO(2) i'm get confused about this
        if (lat == null || longtit == null) {
            // Toast.makeText(this, " please open gps : ", Toast.LENGTH_SHORT).show();
        } else {
            companyProfileRequest.setLatitude(lat);
            companyProfileRequest.setLongitude(longtit);

            RetrofitWebService.getService().getCompanyProfile(companyProfileRequest).enqueue(new Callback<CompanyProfileResponse>() {
                @Override
                public void onResponse(Call<CompanyProfileResponse> call, Response<CompanyProfileResponse> response) {

                    if (response.body().getStatus() == 200) {
                        Toast.makeText(Container_Detail.this, " Success request ", Toast.LENGTH_SHORT).show();
                    } else if (response.body().getStatus() == 400) {
                        Toast.makeText(Container_Detail.this, " Invalid apiToken or undefined ", Toast.LENGTH_SHORT).show();
                    } else if (response.body().getStatus() == 401) {
                        Toast.makeText(Container_Detail.this, " Invalid companyID or undefined ", Toast.LENGTH_SHORT).show();
                    } else if (response.body().getStatus() == 402) {
                        Toast.makeText(Container_Detail.this, " Invalid containerType ", Toast.LENGTH_SHORT).show();
                    } else if (response.body().getStatus() == 403) {
                        Toast.makeText(Container_Detail.this, " Missing requirements ", Toast.LENGTH_SHORT).show();
                    } else if (response.body().getStatus() == 404) {
                        Toast.makeText(Container_Detail.this, " Invalid Longitude or Latitude ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CompanyProfileResponse> call, Throwable t) {

                }
            });

        }


    }
/*
    public void SendOrder(View v) {
        startActivity(new Intent(Container_Detail.this, SendOrder.class));
    }*/

    public void contact(View v) {
        startActivity(new Intent(Container_Detail.this, ContactDeliagator.class));
    }

    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            // Retrieving Latitude
            lat = Double.toString(location.getLatitude());
            // Retrieving getLongitude
            longtit = Double.toString(location.getLongitude());

            Log.d("location = ", " long " + longtit + " lat " + lat);
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
}
