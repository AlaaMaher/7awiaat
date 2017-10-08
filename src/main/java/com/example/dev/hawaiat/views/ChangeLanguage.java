package com.example.dev.hawaiat.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dev.hawaiat.R;

public class ChangeLanguage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static ArrayAdapter<CharSequence> adapter;
    private Spinner spinner;
    private SherdLanguageClass sherdLanguageClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);


        sherdLanguageClass = new SherdLanguageClass(getBaseContext());

        spinner = (Spinner) findViewById(R.id.spinner_language);
        // Create an ArrayAdapter using the string array and a default spinner layout
        spinner.setOnItemSelectedListener(this);


        adapter = ArrayAdapter.createFromResource(ChangeLanguage.this,
                R.array.spinnerItems, R.layout.spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        sherdLanguageClass.loadLocale();
        adapter.notifyDataSetChanged();


    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String item = adapterView.getItemAtPosition(i).toString();
        Intent intent;

        String lang = "";
        switch (i) {
            case 1:
                lang = "ar";
                Toast.makeText(this, " ar ", Toast.LENGTH_SHORT).show();
                sherdLanguageClass.changeLang(lang);
                adapter.notifyDataSetChanged();

                intent = new Intent(ChangeLanguage.this, HomeScreen.class);
                startActivity(intent);
                finish();
                break;
            case 2:
                lang = "en";
                Toast.makeText(this, " en ", Toast.LENGTH_SHORT).show();
                sherdLanguageClass.changeLang(lang);
                adapter.notifyDataSetChanged();

                intent = new Intent(ChangeLanguage.this, HomeScreen.class);
                startActivity(intent);
                finish();
                break;

            case 3:
                lang = "ur";
                Toast.makeText(this, " ur ", Toast.LENGTH_SHORT).show();
                sherdLanguageClass.changeLang(lang);
                adapter.notifyDataSetChanged();

                intent = new Intent(ChangeLanguage.this, HomeScreen.class);
                startActivity(intent);
                finish();
                break;
        }


    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        sherdLanguageClass.loadLocale();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sherdLanguageClass.loadLocale();
    }

}
