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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    static Button textLogin;
    static Button textReg;
    static TextView textView;
    static ArrayAdapter<CharSequence> adapter;
    Spinner spinner;
    SherdLanguageClass sherdLanguageClass;


/*    private static final String LOCALE_PREFRENCE="locale_prefrence";
    private static final String LOCALE_KEYVALUE="Saved_locale";
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor editor;
    private static Locale mLocale;*/

    public static void updateTexts() {

        adapter.notifyDataSetChanged();
        textView.setText(R.string.SinUpas);
        textLogin.setText(R.string.Login);
        textReg.setText(R.string.Registration);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        spinner.setOnItemSelectedListener(this);


        adapter = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.spinnerItems, R.layout.spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        sherdLanguageClass = new SherdLanguageClass(getBaseContext());


        initView();
        //    loadLocale();

        // updateTexts();

        sherdLanguageClass.loadLocale();
        //////
        adapter.notifyDataSetChanged();
        //updateTexts();


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {


        super.onConfigurationChanged(newConfig);
        //updateTexts();
    }

    private void initView() {
        //  mSharedPreferences=getSharedPreferences(LOCALE_PREFRENCE, Context.MODE_PRIVATE);
        // editor=mSharedPreferences.edit();

        textLogin=(Button)findViewById(R.id.login);
        textReg=(Button)findViewById(R.id.Registeration);
        textView=(TextView)findViewById(R.id.textView);
    }


/*    public void loadLocale() {
        String language = mSharedPreferences.getString(LOCALE_KEYVALUE, "");
        changeLang(language);

        //////
        adapter.notifyDataSetChanged();
        updateTexts();

    }*/


   /* public void changeLang(final String lang) {

        Handler mainHandler = new Handler(Looper.getMainLooper());

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {


                if (lang.equalsIgnoreCase(""))
                    return;
                mLocale=new Locale(lang);
                saveLocale(lang);
                Locale.setDefault(mLocale);
                Configuration config=new Configuration();
                config.locale=mLocale;
                getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

                adapter.notifyDataSetChanged();
                updateTexts();

            } // This is your code
        };
        mainHandler.post(myRunnable);



    }*/

    public void loginOrReg(final View v) {


        Handler mainHandler = new Handler(Looper.getMainLooper());

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {


                if (v == findViewById(R.id.login)) {

                    Intent intent = new Intent(MainActivity.this, LoginScreen.class);
                    startActivity(intent);


                } else if (v == findViewById(R.id.Registeration)) {
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }

            } // This is your code
        };
        mainHandler.post(myRunnable);



    }

/*    private void saveLocale(String lang) {
        editor.putString(LOCALE_KEYVALUE,lang);
        editor.commit();

    }*/

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String item=adapterView.getItemAtPosition(i).toString();

        String lang="";
        switch (i){
            case  1:
                lang="ar";
                Toast.makeText(this, " ar ", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                lang="en";
                Toast.makeText(this, " en ", Toast.LENGTH_SHORT).show();
                break;

            case 3:
               lang="ur";
                Toast.makeText(this, " ur ", Toast.LENGTH_SHORT).show();
                break;
        }

        sherdLanguageClass.changeLang(lang);
        adapter.notifyDataSetChanged();
        // updateTexts();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
