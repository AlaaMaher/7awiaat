package com.example.dev.hawaiat.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.dev.hawaiat.R;

import java.util.HashMap;
import java.util.Map;

public class TestActivity extends AppCompatActivity {
    private final Map<String, Class> mActivities = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_testing);


        // Add ur activities here.
        mActivities.put("MainActivity", MainActivity.class);
        mActivities.put("ContainersActivity", ContainerTrash.class);
        mActivities.put("RegisterActivity", RegisterActivity.class);
        mActivities.put("SplashActivity", SplashActivity.class);
        mActivities.put("ForgetPassword", ForgetPassword.class);
        mActivities.put("Terms", Terms.class);
        mActivities.put("Reset", Resetpassword.class);
        mActivities.put("Profile", ProfileScreen.class);
        mActivities.put("ChangePassword", ChangePassword.class);
        mActivities.put("About Us", AboutUs.class);
        mActivities.put("Contact Us", ContactUs.class);


        for (final Map.Entry<String, Class> activity : mActivities.entrySet()) {
            Button button = new Button(this);
            button.setText(activity.getKey());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(TestActivity.this, activity.getValue()));
                }
            });
            linearLayout.addView(button);
        }
    }
}
