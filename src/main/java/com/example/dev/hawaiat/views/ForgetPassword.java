package com.example.dev.hawaiat.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.ViewModel.ForgetPasswordVM;
import com.example.dev.hawaiat.databinding.ForgetPasswordBinding;

public class ForgetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ForgetPasswordBinding binding = DataBindingUtil.setContentView(this, R.layout.forget_password);
        ForgetPasswordVM forgetPasswordVM = new ForgetPasswordVM(getApplicationContext());
        binding.setForgetPasswordVM(forgetPasswordVM);

    }
}
