package com.example.dev.hawaiat.viewModels;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;

import com.example.dev.hawaiat.BR;
import com.example.dev.hawaiat.webServices.RetrofitWebService;
import com.example.dev.hawaiat.webServices.request.AboutUsRequest;
import com.example.dev.hawaiat.webServices.responses.AboutUsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmed on 26/09/17.
 */

public class AboutUsVM extends BaseObservable {

    private Context context;
    private String textAbout;


    public AboutUsVM(Context context) {

        this.context = context;

    }

    @Bindable
    public String getTextAbout() {
        return this.textAbout;
    }

    public void setTextAbout(final String textAbout) {
        this.textAbout = textAbout;
        notifyPropertyChanged(BR.textAbout);

    }

    public void onShare(View v) {
        shareIt();
    }

    public void loadAboutUs(String lang) {

        AboutUsRequest aboutUsRequest = new AboutUsRequest(lang);
        RetrofitWebService.getService().getAboutUsFun(aboutUsRequest).enqueue(new Callback<AboutUsResponse>() {
            @Override
            public void onResponse(Call<AboutUsResponse> call, Response<AboutUsResponse> response) {
                int status = response.body().getStatus();
                Toast.makeText(context, String.valueOf(status), Toast.LENGTH_SHORT).show();
                if (status == 200) {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                    String text = response.body().getText();
                    //termsM=new TermsM(terms);
                    setTextAbout(text);
                }
            }

            @Override
            public void onFailure(Call<AboutUsResponse> call, Throwable t) {

            }
        });
    }

    private void shareIt() {
//sharing implementation here
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AndroidSolved");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Now Learn Android with AndroidSolved clicke here to visit https://androidsolved.wordpress.com/ ");
        context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }


}
