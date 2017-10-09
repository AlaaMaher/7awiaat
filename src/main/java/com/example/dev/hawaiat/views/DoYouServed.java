package com.example.dev.hawaiat.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.dev.hawaiat.R;

public class DoYouServed extends AppCompatActivity {

    Button yes_Button;
    Button no_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_you_served);

        yes_Button = (Button) findViewById(R.id.served_yes);
        no_Button = (Button) findViewById(R.id.served_no);

        yes_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(DoYouServed.this, RatingScreen.class);
                startActivity(i1);
            }
        });

        no_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               startActivity(new Intent(DoYouServed.this,HomeScreen.class));

            }
        });

    }
}
