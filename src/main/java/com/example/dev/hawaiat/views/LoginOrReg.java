package com.example.dev.hawaiat.views;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dev.hawaiat.R;

import java.util.ArrayList;

public class LoginOrReg extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static ArrayAdapter<CharSequence> adapter;
    private static Spinner spinner;
    private static Button textLogin;
    private static Button textReg;
    private static TextView textView;
    private SherdLanguageClass sherdLanguageClass;


    private ArrayList<String> items;

    public static void updateTexts() {


        textView.setText(R.string.SinUpas);
        textLogin.setText(R.string.Login);
        textReg.setText(R.string.Registration);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        spinner.setOnItemSelectedListener(this);


        adapter = ArrayAdapter.createFromResource(LoginOrReg.this,
                R.array.spinnerItems, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        sherdLanguageClass = new SherdLanguageClass(getBaseContext());
        sherdLanguageClass.loadLocale();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {


        super.onConfigurationChanged(newConfig);
        //updateTexts();
        items = new ArrayList<>();
        items.add(getResources().getString(R.string.spinner_Title));
        items.add(getResources().getString(R.string.Arabic));
        items.add(getResources().getString(R.string.English));
        items.add(getResources().getString(R.string.Urdu));


        adapter=new ArrayAdapter<CharSequence>(this,R.layout.spinner_item,getResources().getStringArray(R.array.spinnerItems));
        spinner.setAdapter(adapter);
    }

    private void initView() {
        textLogin = (Button) findViewById(R.id.login);
        textReg = (Button) findViewById(R.id.Registeration);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void loginOrReg(final View v) {


        Handler mainHandler = new Handler(Looper.getMainLooper());
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                if (v == findViewById(R.id.login)) {

                    Intent intent = new Intent(LoginOrReg.this, LoginScreen.class);
                    startActivity(intent);


                } else if (v == findViewById(R.id.Registeration)) {
                    Intent intent = new Intent(LoginOrReg.this, RegisterActivity.class);
                    startActivity(intent);
                }

            } // This is your code
        };
        mainHandler.post(myRunnable);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String item = adapterView.getItemAtPosition(i).toString();

        String lang = "";
        switch (i) {
            case 1:
                lang = "ar";
                Toast.makeText(this, " ar ", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                finish();
                startActivity(getIntent());
                break;
            case 2:
                lang = "en";
                Toast.makeText(this, " en ", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                finish();
                startActivity(getIntent());
                break;

            case 3:
                lang = "ur";
                Toast.makeText(this, " ur ", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                finish();
                startActivity(getIntent());
                break;
        }

        sherdLanguageClass.changeLang(lang);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        adapter.notifyDataSetChanged();
    }
}
