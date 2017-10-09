package com.example.dev.hawaiat.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.viewModels.RegisterViewModel;
import com.example.dev.hawaiat.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_register);

         ActivityRegisterBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_register);
        binding.setUserViewModel(new RegisterViewModel(this));



    }


}
