package com.example.dev.hawaiat.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.webServices.RetrofitWebService;
import com.example.dev.hawaiat.webServices.request.RatingRequest;
import com.example.dev.hawaiat.webServices.responses.StatusResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingScreen extends AppCompatActivity {

    Button Rating_button;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_screen);


        Rating_button = (Button) findViewById(R.id.Rating_button);
        ratingBar = (RatingBar) findViewById(R.id.rating_bar);

        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#FA7300"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.parseColor("#FA7300"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(Color.parseColor("#FA7300"), PorterDuff.Mode.SRC_ATOP);


        Rating_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(RatingScreen.this, " go to container Screen ", Toast.LENGTH_SHORT).show();


                rateWebService();


            }
        });
    }

    private void rateWebService() {
        SharedPreferences sharedPreferences = getSharedPreferences(LoginScreen.HAWAIT_SHARED_PREFRENSE, MODE_PRIVATE);
        String api_Token = (String) sharedPreferences.getString(LoginScreen.API_TOKEN, "");

        SharedPreferences sharedPreferences2 = getSharedPreferences(Company_Detail.CompanyIDDherd, MODE_PRIVATE);
        String company_id=Integer.toString( sharedPreferences2.getInt("CompanyID",0));
       // String company_id = (String) sharedPreferences2.getString("CompanyID", "");


        RatingRequest ratingRequest = new RatingRequest();
        ratingRequest.setRate(Float.toString(ratingBar.getRating()));
        ratingRequest.setApiToken(api_Token);
        ratingRequest.setCompanyID(company_id);


        RetrofitWebService.getService().rateCompany(ratingRequest).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {

                if (response.body().getStatus() == 200) {
                    Toast.makeText(RatingScreen.this, getString(R.string.loginSuc), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RatingScreen.this,HomeScreen.class));
                } else if (response.body().getStatus() == 400) {
                    Toast.makeText(RatingScreen.this, "Undefined token", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 403) {
                    Toast.makeText(RatingScreen.this, "Missing requirements.", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 405) {
                    Toast.makeText(RatingScreen.this, "Invalid token", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 406) {
                    Toast.makeText(RatingScreen.this, "Invalid companyID or undefined company", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 407) {
                    Toast.makeText(RatingScreen.this, getString(R.string.invalidRate), Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 408) {
                    Toast.makeText(RatingScreen.this, "The user did not request an order from this company", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {

            }
        });

    }
}
