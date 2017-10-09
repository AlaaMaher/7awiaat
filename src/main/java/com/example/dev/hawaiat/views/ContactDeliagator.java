package com.example.dev.hawaiat.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.adapters.DeligatorRecyclerAdapter;

import java.util.ArrayList;

public class ContactDeliagator extends AppCompatActivity {


    DeligatorRecyclerAdapter myAdapter;
    RecyclerView myrcycler;
    ArrayList<String> phones = new ArrayList<String>();

    TextView mRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_deliagator);

        phones = (ArrayList<String>) getIntent().getSerializableExtra("phones");

        Toast.makeText(ContactDeliagator.this, " phone " + phones.get(0), Toast.LENGTH_SHORT).show();


        if (null != phones) {
            myrcycler = (RecyclerView) findViewById(R.id.mRecyclerView);
            myrcycler.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            myrcycler.setLayoutManager(layoutManager);
            myAdapter = new DeligatorRecyclerAdapter(ContactDeliagator.this, phones);
            myrcycler.setAdapter(myAdapter);


        }

        mRating = (TextView) findViewById(R.id.we_hope);
        mRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ContactDeliagator.this, " Rating Activty ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ContactDeliagator.this, RatingScreen.class));
            }
        });


    }
}
