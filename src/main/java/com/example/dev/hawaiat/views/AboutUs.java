package com.example.dev.hawaiat.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.databinding.AboutUsBinding;
import com.example.dev.hawaiat.viewModels.AboutUsVM;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.about_us);


        AboutUsBinding binding = DataBindingUtil.setContentView(this, R.layout.about_us);
        AboutUsVM aboutUsVM = new AboutUsVM(this);
        aboutUsVM.loadAboutUs("en");
        binding.setAboutUsVM(aboutUsVM);
    }
}
