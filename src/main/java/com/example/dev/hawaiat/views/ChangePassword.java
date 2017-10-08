package com.example.dev.hawaiat.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.databinding.ChangePasswordBinding;
import com.example.dev.hawaiat.viewModels.ChangePasswordVM;

public class ChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChangePasswordBinding binding = DataBindingUtil.setContentView(this, R.layout.change_password);
        ChangePasswordVM changePasswordVM = new ChangePasswordVM(this);
        binding.setChangePasswordVM(changePasswordVM);
    }
}
