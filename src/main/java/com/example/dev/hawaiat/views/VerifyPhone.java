package com.example.dev.hawaiat.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.databinding.ActivityVerifyPhoneBinding;
import com.example.dev.hawaiat.viewModels.VerifyPhoneViewModel;


public class VerifyPhone extends AppCompatActivity {

    ActivityVerifyPhoneBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_verify_phone);

       String phoneNumber= getIntent().getStringExtra("phoneNumber");


         binding= DataBindingUtil.setContentView(this,R.layout.activity_verify_phone);
        binding.setVerfiyPhone(new VerifyPhoneViewModel(this,phoneNumber));



    }


}
