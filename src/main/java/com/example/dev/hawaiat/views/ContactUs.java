package com.example.dev.hawaiat.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.webServices.RetrofitWebService;
import com.example.dev.hawaiat.webServices.responses.ContactResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUs extends AppCompatActivity {
    private TextView phoneTextView;
    private TextView emailTextView;
    private ImageButton share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);

        share = (ImageButton) findViewById(R.id.share);

        phoneTextView = (TextView) findViewById(R.id.textPhone);
        emailTextView = (TextView) findViewById(R.id.textEmail);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareIt();
            }
        });

        loadData();

    }

    private void shareIt() {
//sharing implementation here
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AndroidSolved");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Now Learn Android with AndroidSolved clicke here to visit https://androidsolved.wordpress.com/ ");
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public void loadData() {
        RetrofitWebService.getService().getContactFun().enqueue(new Callback<ContactResponse>() {
            @Override
            public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                int status = response.body().getStatus();
                Toast.makeText(ContactUs.this, String.valueOf(status), Toast.LENGTH_SHORT).show();
                if (status == 200) {
                    Toast.makeText(ContactUs.this, "Success", Toast.LENGTH_SHORT).show();
                    emailTextView.setText(response.body().getEmail());

                    String[] phones = response.body().getPhones();


                    for (String phone1 : phones) {

                        phoneTextView.append(phone1 + "\n");

                    }

                }
            }

            @Override
            public void onFailure(Call<ContactResponse> call, Throwable t) {

            }
        });
    }
}

