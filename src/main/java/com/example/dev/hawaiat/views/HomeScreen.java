package com.example.dev.hawaiat.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.adapters.MyCustomPagerAdapter;
import com.example.dev.hawaiat.databinding.NavHeaderBinding;
import com.example.dev.hawaiat.viewModels.HeaderDrawerViewModel;
import com.example.dev.hawaiat.viewModels.LoginViewModel;
import com.example.dev.hawaiat.webServices.RetrofitWebService;
import com.example.dev.hawaiat.webServices.request.LoginRequest;
import com.example.dev.hawaiat.webServices.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LoginViewModel.OnPassinData {


    // ========================== static images for view pagers================
    // private int images[] = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4};
    // private int images2[] = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4};
    private LoginResponse loginResponse;
    private LoginViewModel loginViewModel;
    private ViewPager viewPager;
    private ViewPager viewPager2;
    private MyCustomPagerAdapter myCustomPagerAdapter;
    private MyCustomPagerAdapter myCustomPagerAdapter2;
    private int tab = 0;
    private int tab2 = 0;
    //private SherdLanguageClass sherdLanguageClass;
    private Button tramimButton, nfyaitButton;
    private ImageView leftNav, rightNav, leftNav2, rightNav2;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    public static String API_TOKEN_SHARED = "apiToken_shared";
    public static String API_TOKEN = "apiToken";

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);



        //=================================== for the header of the side menue============================

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        ////================================= for the web serviced intent =================================


        //=================================== for the home screen web services============================
        loginResponse = new LoginResponse();
        login();

        //=================================== for the upper slider and lower slider=======================
        try {
            viewPager = (ViewPager) findViewById(R.id.pager);
            viewPager2 = (ViewPager) findViewById(R.id.pager2);
        } catch (Exception e) {

        }


        //================================================ swaping arrow for slider=======================
        leftNav = (ImageView) findViewById(R.id.imgageleft);
        rightNav = (ImageView) findViewById(R.id.imageright);
        leftNav2 = (ImageView) findViewById(R.id.imgageleft2);
        rightNav2 = (ImageView) findViewById(R.id.imageright2);


        try {

            // Images left navigation
            leftNav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tab = viewPager.getCurrentItem();

                    rightNav.setVisibility(View.INVISIBLE);
                    if (tab > 0) {
                        tab--;
                        viewPager.setCurrentItem(tab);
                    } else if (tab == 0) {

                        viewPager.setCurrentItem(tab);
                    }

                    myCustomPagerAdapter.setLeftIconViability(leftNav, tab);
                    myCustomPagerAdapter.setRightIconViability(rightNav, tab);
                }
            });
        } catch (Exception e) {
            Log.e("error in onclick ", e.getMessage());
        }
        try {
            if (tab == 0) {
                leftNav.setVisibility(View.GONE);
            } else {
                leftNav.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            Log.e("error in onclick ", e.getMessage());
        }


        try {
            // Images right navigatin
            rightNav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tab++;
                    viewPager.setCurrentItem(tab);
                    myCustomPagerAdapter.setLeftIconViability(leftNav, tab);
                    myCustomPagerAdapter.setRightIconViability(rightNav, tab);
                }
            });
        } catch (Exception e) {
            Log.e("error in onclick ", e.getMessage());
        }


        //======================================== for the second view pager=====================================================

        try {
            // Images left navigation
            leftNav2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tab2 = viewPager2.getCurrentItem();
                    rightNav2.setVisibility(View.INVISIBLE);
                    if (tab2 > 0) {
                        tab2--;
                        viewPager2.setCurrentItem(tab2);
                    } else if (tab2 == 0) {
                        viewPager2.setCurrentItem(tab2);
                    }
                    myCustomPagerAdapter2.setLeftIconViability(leftNav2, tab2);
                    myCustomPagerAdapter2.setRightIconViability(rightNav2, tab2);
                }
            });
        } catch (Exception e) {
            Log.e("error in onclick ", e.getMessage());
        }
        try {
            if (tab2 == 0) {
                leftNav2.setVisibility(View.GONE);
            } else {
                leftNav2.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            Log.e("error in onclick ", e.getMessage());
        }

        try {
            // Images right navigatin
            rightNav2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tab2++;
                    viewPager2.setCurrentItem(tab2);
                    myCustomPagerAdapter2.setLeftIconViability(leftNav2, tab2);
                    myCustomPagerAdapter2.setRightIconViability(rightNav2, tab2);

                }
            });
        } catch (Exception e) {
            Log.e("error in onclick ", e.getMessage());
        }


        //=============================================== clicks on buttons of home screen=======================================
        tramimButton = (Button) findViewById(R.id.hawait_tarmim);
        nfyaitButton = (Button) findViewById(R.id.hawait_nfayat);


        tramimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this, ContainerRest.class));
            }
        });

        nfyaitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this, ContainerTrash.class));
            }
        });



        ///================================= for the toolbar================================================================

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //=========================================== the side menue(navigation drawrer)========================================
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Find our drawer view
        //drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        navigationView.setNavigationItemSelectedListener(this);
        View headerview = navigationView.getHeaderView(0);
        ConstraintLayout header = (ConstraintLayout) headerview.findViewById(R.id.header);

        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeScreen.this, " Edit Profirle ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeScreen.this, ProfileScreen.class));
            }
        });

        // Tie DrawerLayout events to the ActionBarToggle
        drawer.addDrawerListener(toggle);
        initNavigationDrawer();
        toggle.syncState();
    }
    private void login() {


        SharedPreferences sharedPreferences = getSharedPreferences("PhoneAndPaswword", Context.MODE_PRIVATE);
        String phone = sharedPreferences.getString("phone", "");
        String pass = sharedPreferences.getString("pass", "");

        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPhone(phone);
        loginRequest.setPassword(pass);

        RetrofitWebService.getService().login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.body().getStatus() == 200) {

                    loginResponse = response.body();
                    myCustomPagerAdapter = new MyCustomPagerAdapter(HomeScreen.this, loginResponse.getUpperSlider());
                    viewPager.setAdapter(myCustomPagerAdapter);
                    myCustomPagerAdapter2 = new MyCustomPagerAdapter(HomeScreen.this, loginResponse.getLowerSlider());
                    viewPager2.setAdapter(myCustomPagerAdapter2);


                    saveApiToken();

                } else if (response.body().getStatus() == 403) {
                    Toast.makeText(HomeScreen.this, " Invalid request, because of missing requirements. ", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 404) {
                    Toast.makeText(HomeScreen.this, " Unknown error ", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 405) {
                    Toast.makeText(HomeScreen.this, " Invalid phone or password ", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 406) {
                    Toast.makeText(HomeScreen.this, " This phone is not verified ", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 407) {
                    Toast.makeText(HomeScreen.this, " Non active acount ", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(HomeScreen.this, " Connection failed " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void saveApiToken() {

        SharedPreferences sharedPreferences = getSharedPreferences(API_TOKEN_SHARED, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String s = sharedPreferences.getString(API_TOKEN, "");

        editor.putString(API_TOKEN, loginResponse.getProfile().getApiToken());
        editor.commit();





        //============================================= for mvvm of  header of navigation drawer ==========================

        NavHeaderBinding navHeaderBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.nav_header, navigationView, true);

        /*View headerView = LayoutInflater.from(this).inflate(R.layout.nav_header, navigationView, false);
        navigationView.addHeaderView(headerView);*/

        HeaderDrawerViewModel headerViewModel = new HeaderDrawerViewModel(this);

        //   headerViewModel.setImage("http://mirrors.creativecommons.org/presskit/icons/share.large.png");
        //   headerViewModel.setName("Mohamed Tony");
        try {
            if (loginResponse.getProfile().getName() != null) {
                headerViewModel.setName(loginResponse.getProfile().getName());
            }
            if (loginResponse.getProfile().getPhoto() != null) {
                headerViewModel.setImage(loginResponse.getProfile().getPhoto());
            }
            navHeaderBinding.setHeaderViewModel(headerViewModel);
        }catch (Exception e){
            Log.e(" Error ",e.getMessage());
        }




    }


    //========================for the side menue(navigation drawer))========================================================================================
    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void initNavigationDrawer() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {
                    case R.id.it_language:

                        Toast.makeText(getApplicationContext(), "Language", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeScreen.this, ChangeLanguage.class));

                        drawer.closeDrawers();
                        break;
                    case R.id.it_contact:
                        Toast.makeText(getApplicationContext(), "Contact Us", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeScreen.this, ContactUs.class));
                        drawer.closeDrawers();
                        break;
                    case R.id.it_about:
                        Toast.makeText(getApplicationContext(), "About", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeScreen.this, AboutUs.class));
                        drawer.closeDrawers();
                        break;

                    case R.id.it_logout:
                        startActivity(new Intent(HomeScreen.this, LoginScreen.class));
                        finish();
                }
                return true;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPassingData(LoginResponse loginResponse) {
        // Toast.makeText(this, " status "+String.valueOf(loginResponse.getStatus()), Toast.LENGTH_SHORT).show();
        Log.d("onResponse1", loginResponse.getUpperSlider().get(0));

    }
}
