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
import com.example.dev.hawaiat.webServices.request.CompaniesRequest;
import com.example.dev.hawaiat.webServices.request.LoginRequest;
import com.example.dev.hawaiat.webServices.responses.CompaniesResponse;
import com.example.dev.hawaiat.webServices.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LoginViewModel.OnPassinData {


    // ========================== static images for view pagers================
    // private int images[] = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4};
    // private int images2[] = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4};
    LoginResponse loginResponse;

    private LoginViewModel loginViewModel;
    private ViewPager viewPager;
    private ViewPager viewPager2;

    private MyCustomPagerAdapter myCustomPagerAdapter;
    private MyCustomPagerAdapter myCustomPagerAdapter2;

    private int tab = 0;
    private int tab2 = 0;
    private SherdLanguageClass sherdLanguageClass;
    private Button tramimButton, nfyaitButton;
    private ImageView leftNav, rightNav, leftNav2, rightNav2;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        ////================================= for the web serviced intent =================================


        loginResponse = new LoginResponse();
        login();
        //     Intent intent = getIntent();
        //    final LoginResponse loginResponse = (LoginResponse) intent.getSerializableExtra("loginRes");

        try {
            Log.d("onResponse2", loginResponse.getUpperSlider().get(0));
            Log.d("onResponse2", loginResponse.getProfile().getApiToken());
            if (loginResponse.getProfile().getName() != null) {
                Log.d("onResponse3", loginResponse.getProfile().getName());
            }

            Log.d("Res", loginResponse.toString());
        } catch (Exception e) {

        }

        try {
            sherdLanguageClass = new SherdLanguageClass(getApplication());

            viewPager = (ViewPager) findViewById(R.id.pager);


            // viewPager.setAdapter(myCustomPagerAdapter);

            viewPager2 = (ViewPager) findViewById(R.id.pager2);

        } catch (Exception e) {

        }


        //================================================ swaping arrow =======================
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
                Toast.makeText(HomeScreen.this, "from img left", Toast.LENGTH_SHORT).show();
            } else {
                leftNav.setVisibility(View.VISIBLE);
                //  rightNav.setVisibility(View.VISIBLE);

            }
        } catch (Exception e) {
            Log.e("error in onclick ", e.getMessage());
        }


        try {
            // Images right navigatin
            rightNav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // leftNav.setVisibility(View.VISIBLE);
                    //  tab = viewPager.getCurrentItem();
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
                Toast.makeText(HomeScreen.this, "from img left", Toast.LENGTH_SHORT).show();
            } else {
                leftNav2.setVisibility(View.VISIBLE);
                //  rightNav.setVisibility(View.VISIBLE);

            }
        } catch (Exception e) {
            Log.e("error in onclick ", e.getMessage());
        }

        try {

            // Images right navigatin
            rightNav2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // leftNav.setVisibility(View.VISIBLE);
                    //  tab = viewPager.getCurrentItem();
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

                String companytype = "rest";
                Intent intent = new Intent(HomeScreen.this, ContainerRest.class);
                intent.putExtra("rest", companytype);
                startActivity(intent);
                Toast.makeText(HomeScreen.this, " hawait tramim", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(HomeScreen.this, Container_Detail.class));

                CompaniesRequest companiesRequest = new CompaniesRequest();
                companiesRequest.setApiToken(loginResponse.getProfile().getApiToken());
                companiesRequest.setContainerType("rest");

                CompanyApi(companiesRequest);


            }
        });

        nfyaitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = "trash";
                Intent intent = new Intent(getApplicationContext(), ContainerTrash.class);
                intent.putExtra("trash", type);
                Toast.makeText(HomeScreen.this, " hawait nfyat", Toast.LENGTH_SHORT).show();

                CompaniesRequest companiesRequest = new CompaniesRequest();
                companiesRequest.setApiToken(loginResponse.getProfile().getApiToken());
                companiesRequest.setContainerType("trash");

                CompanyApi(companiesRequest);

            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //=========================================== the side menue(navigation drawrer)========================================
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Find our drawer view
        //drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerview = navigationView.getHeaderView(0);

        ConstraintLayout header = (ConstraintLayout) headerview.findViewById(R.id.header);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeScreen.this, " Edit Profirle ", Toast.LENGTH_SHORT).show();
            }
        });

        // Tie DrawerLayout events to the ActionBarToggle
        drawer.addDrawerListener(toggle);
        initNavigationDrawer();
        toggle.syncState();
    }

    private void login() {
        SharedPreferences sharedPreferences = getSharedPreferences("EmailAndPaswword", Context.MODE_PRIVATE);

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

                    Toast.makeText(HomeScreen.this, " success ", Toast.LENGTH_SHORT).show();


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

    private void CompanyApi(CompaniesRequest companiesRequest) {
        RetrofitWebService.getService().getCompanies(companiesRequest).enqueue(new Callback<CompaniesResponse>() {
            @Override
            public void onResponse(Call<CompaniesResponse> call, Response<CompaniesResponse> response) {

                CompaniesResponse companiesResponse = response.body();

                if (response.body().getStatus() == 200) {
                    Toast.makeText(HomeScreen.this, "Success request", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 300) {
                    Toast.makeText(HomeScreen.this, "Success request, but no results found.", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 400) {
                    Toast.makeText(HomeScreen.this, "Invalid or undefined apiToken", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 403) {
                    Toast.makeText(HomeScreen.this, "Invalid request, because of missing requirements.", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 404) {
                    Toast.makeText(HomeScreen.this, "unknown error", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 406) {
                    Toast.makeText(HomeScreen.this, "Invalid containerType", Toast.LENGTH_SHORT).show();
                }

                Log.d("home", companiesResponse.getCompanies().get(0).getName());

            }

            @Override
            public void onFailure(Call<CompaniesResponse> call, Throwable t) {

                Toast.makeText(HomeScreen.this, " connection failed " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        //============================================= for mvvm of  header of navigation drawer ==========================

        NavHeaderBinding navHeaderBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.nav_header, navigationView, true);

        /*View headerView = LayoutInflater.from(this).inflate(R.layout.nav_header, navigationView, false);
        navigationView.addHeaderView(headerView);*/

        HeaderDrawerViewModel headerViewModel = new HeaderDrawerViewModel(this);

        headerViewModel.setImage("http://mirrors.creativecommons.org/presskit/icons/share.large.png");
        headerViewModel.setName("Mohamed Tony");

        navHeaderBinding.setHeaderViewModel(headerViewModel);


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

                        // startActivity(new Intent(HomeActivity.this,ContactUsActivity.class));
                        drawer.closeDrawers();
                        break;
                    case R.id.it_about:
                        // startActivity(new Intent(HomeActivity.this,AboutUsActivity.class));

                        Toast.makeText(getApplicationContext(), "About", Toast.LENGTH_SHORT).show();
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
    protected void onResume() {
        super.onResume();
        sherdLanguageClass.loadLocale();
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
