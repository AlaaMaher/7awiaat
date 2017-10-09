package com.example.dev.hawaiat.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.viewModels.LoginViewModel;
import com.example.dev.hawaiat.databinding.ActivityLoginScreenBinding;
import com.example.dev.hawaiat.webServices.responses.LoginResponse;

public class LoginScreen extends AppCompatActivity implements LoginViewModel.OnPassinData{

    public static String HAWAIT_SHARED_PREFRENSE="hawiat_shared";
    public static String API_TOKEN="apiToken";
    public static String LOGIN_RESPONSE="loginResponse";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_login_screen);

        ActivityLoginScreenBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_login_screen);
        binding.setLoginViewModel(new LoginViewModel(this));


    }

    @Override
    public void onPassingData(LoginResponse loginResponse) {

       // LoginResponse loginResponse1=new LoginResponse();
        SharedPreferences sharedPreferences=getSharedPreferences(HAWAIT_SHARED_PREFRENSE,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String s= sharedPreferences.getString(API_TOKEN,"");

        editor.putString(API_TOKEN,loginResponse.getProfile().getApiToken());
        editor.commit();

        Intent intent=new Intent(this,HomeScreen.class);
        intent.putExtra(LOGIN_RESPONSE,loginResponse);
        startActivity(intent);
    }

}
