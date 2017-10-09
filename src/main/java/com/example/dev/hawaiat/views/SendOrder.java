package com.example.dev.hawaiat.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.webServices.RetrofitWebService;
import com.example.dev.hawaiat.webServices.request.LogRequest;
import com.example.dev.hawaiat.webServices.responses.StatusResponse;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.dev.hawaiat.views.Company_Detail.CompanyIDDherd;

public class SendOrder extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    TextView tvPlaceDetails;
    TextView container_size;
    EditText editText_location,contianer_reguired;
    ImageView daysTop, daysBelow, hourTop, hourBelow;
    int days = 7, hours = 47;
    TextView dayText, hourText;
    Button send_button;
    private GoogleApiClient mGoogleApiClient;
    private int PLACE_PICKER_REQUEST = 1;
    private ImageView imagePicker;
   private String phoneNumber,containerZise,containerCost,containerColor,containerType;
    StringBuilder stBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_order);


        //============================= get the company phone ==================================
        phoneNumber=(String) getIntent().getStringExtra("PhoneNumber");
        containerCost=(String) getIntent().getStringExtra("ContainerCost");
        containerColor=(String) getIntent().getStringExtra("ContainerColor");
        containerType=(String) getIntent().getStringExtra("ContainerType");




        imagePicker = (ImageView) findViewById(R.id.place_picker_image);
        editText_location = (EditText) findViewById(R.id.editText_location);
        daysTop = (ImageView) findViewById(R.id.days_top);
        daysBelow = (ImageView) findViewById(R.id.days_down);
        hourTop = (ImageView) findViewById(R.id.hours_top);
        hourBelow = (ImageView) findViewById(R.id.hours_down);
        dayText = (TextView) findViewById(R.id.days_text);
        hourText = (TextView) findViewById(R.id.hours_text);
        send_button = (Button) findViewById(R.id.send_order);
        container_size = (TextView) findViewById(R.id.container_size);
        contianer_reguired=(EditText)findViewById(R.id.contianer_reguired);


        containerZise= (String) getIntent().getStringExtra("ContainerCapacity");
        container_size.setText(getString(R.string.container_capacity) + " " + containerZise + " " + getString(R.string.yard));


        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                log();

            }
        });


        daysTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                days++;
                dayText.setText(Integer.toString(days));
            }
        });


        daysBelow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (days > 0) {
                    days--;
                }
                dayText.setText(Integer.toString(days));
            }
        });


        hourTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hours++;
                hourText.setText(Integer.toString(hours));
            }
        });

        hourBelow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (hours > 0) {
                    hours--;
                }
                hourText.setText(Integer.toString(hours));
            }
        });

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();


        imagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {


                    startActivityForResult(builder.build(SendOrder.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }


            }
        });


    }

    private void log() {
        LogRequest logRequest = new LogRequest();
        SharedPreferences sharedPreferences = getSharedPreferences(LoginScreen.HAWAIT_SHARED_PREFRENSE, MODE_PRIVATE);
        String api_Token = (String) sharedPreferences.getString(LoginScreen.API_TOKEN, "");

        Toast.makeText(this, " api token " + api_Token, Toast.LENGTH_SHORT).show();

        logRequest.setApiToken(api_Token);

        //TODO please change the company id to a variable
        SharedPreferences sharedPreferences2 = getSharedPreferences(CompanyIDDherd, MODE_PRIVATE);


      String id= Integer.toString( sharedPreferences2.getInt("CompanyID",0));
        logRequest.setCompanyID(id);

        logRequest.setType("order");

        RetrofitWebService.getService().getLog(logRequest).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {

                if (response.body().getStatus() == 200) {
                    Toast.makeText(SendOrder.this, "Success Request", Toast.LENGTH_SHORT).show();

                    sendMessage();
                   // sendSMSMessage();
                    //Intent intent = new Intent(SendOrder.this, DoYouServed.class);
                    //startActivity(intent);

                } else if (response.body().getStatus() == 400) {
                    Toast.makeText(SendOrder.this, "Undefined token", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 403) {
                    Toast.makeText(SendOrder.this, "Missing requirements.", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 404) {
                    Toast.makeText(SendOrder.this, "Unknown error", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 405) {
                    Toast.makeText(SendOrder.this, "Invalid token", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 406) {
                    Toast.makeText(SendOrder.this, "Invalid companyID or undefined company", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 407) {
                    Toast.makeText(SendOrder.this, "Invalid type", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {

            }
        });


    }

    private void sendMessage() {

        Intent[] intent=new Intent[2];

/*        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", new String("01234"));
        smsIntent.putExtra("sms_body", "Test ");
glh
        startActivity(smsIntent);*/

        String containerNum=contianer_reguired.getText().toString();


        intent[0]=new Intent(SendOrder.this,DoYouServed.class);

        intent[1]= new Intent(Intent.ACTION_VIEW); //phoneNumber,containerZise,containerCost,containerColor,containerType;
        intent[1].setData(Uri.parse("smsto:"));
        intent[1].setType("vnd.android-dir/mms-sms");
        intent[1].putExtra("address", new String(phoneNumber));
        intent[1].putExtra("sms_body", "Container Detail : \n"+"\n \n Container Size : "+containerZise+"\n Container Cost : "+
                containerCost+"\n"+" Continer Color : "+containerColor+"\n"+" Contianer Type :"+containerType+"\n Number of Containiars :"+containerNum+"\n"+
                "\n \n Delivery Time : After "+days+" Days "+hours+" Hours "
                +"\n \n \n Client Details : "+" Location : "+stBuilder+" \n ");



        startActivities(intent);



/*        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("01090885823", null, "hi form hawait", null, null);
        Toast.makeText(getApplicationContext(), "SMS sent.",
                Toast.LENGTH_LONG).show();*/


/*       Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("smsto:" + "01090885823"));
        intent.putExtra("sms_body", "hi");
        startActivity(intent);*/

    }

   /* protected void sendSMSMessage() {


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("01090885823", null, "hi from hawiat", null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }*/

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "fialed ", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                 stBuilder = new StringBuilder();
                String placename = String.format("%s", place.getName());
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);
                String address = String.format("%s", place.getAddress());
                stBuilder.append("Name: ");
                stBuilder.append(placename);
                stBuilder.append("\n");
                stBuilder.append("Latitude: ");
                stBuilder.append(latitude);
                stBuilder.append("\n");
                stBuilder.append("Logitude: ");
                stBuilder.append(longitude);
                stBuilder.append("\n");
                stBuilder.append("Address: ");
                stBuilder.append(address);
                editText_location.setText(address);
            }
        }
    }

    public static class Containers1 extends AppCompatActivity {


        ArrayList<ContainerSizes> containerSizesArrayList = new ArrayList<>();
        private RecyclerView recyclerView;
        private ContainersAdapter mAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.container_rest);

            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            ContainerSizes containerSizes;


            //(String companyname,String logo,RatingBar rate,String percentage)
            containerSizes = new ContainerSizes(" AlFatah Company For Renting Containers ", "https://uploud.wikimedia.org/wikipedia/en/0/0d/Simpsons_FamilyPicture.png", .1f, "60%");
            containerSizesArrayList.add(containerSizes);

            containerSizes = new ContainerSizes(" AlFatah Company For Renting Containers ", "https://uploud.wikimedia.org/wikipedia/en/0/0d/Simpsons_FamilyPicture.png", .1f, "80%");
            containerSizesArrayList.add(containerSizes);

            containerSizes = new ContainerSizes(" AlFatah Company For Renting Containers ", "https://uploud.wikimedia.org/wikipedia/en/0/0d/Simpsons_FamilyPicture.png", .1f, "40%");
            containerSizesArrayList.add(containerSizes);

            containerSizes = new ContainerSizes(" AlFatah Company For Renting Containers ", "https://uploud.wikimedia.org/wikipedia/en/0/0d/Simpsons_FamilyPicture.png", .1f, "30%");
            containerSizesArrayList.add(containerSizes);

            //String img="https://i.imgur.com/tGbaZCY.jpg";
            //containerSizes=new ContainerSizes(" two",img,.4f,"70%");

            //containerSizesArrayList.add(containerSizes);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);

            ContainersAdapter adapter = new ContainersAdapter(this, containerSizesArrayList);
            recyclerView.setAdapter(adapter);


        }
    }

    /**
     * Created by ahmed on 30/08/17.
     */

    public static class ContainersAdapter extends RecyclerView.Adapter<ContainersAdapter.MyViewHolder> {

        private Context mContext;
        private List<ContainerSizes> containerSizesList;

        public ContainersAdapter(Context mContext, List<ContainerSizes> containerSizesList) {

            this.containerSizesList = containerSizesList;
            this.mContext = mContext;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {


            holder.companyname.setText(containerSizesList.get(position).getCompanyname());
            holder.percentage.setText(containerSizesList.get(position).getPercentage());
            holder.rate.setRating(containerSizesList.get(position).getRate());


            Picasso.with(mContext).load(R.drawable.logo1).resize(93, 60).into(holder.logo);


        }

        @Override
        public int getItemCount() {
            return containerSizesList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView companyname, percentage;
            private RatingBar rate;
            private ImageView logo;

            public MyViewHolder(View view) {
                super(view);
                companyname = (TextView) view.findViewById(R.id.companyname);
                percentage = (TextView) view.findViewById(R.id.percentage);
                logo = (ImageView) view.findViewById(R.id.logo);
                rate = (RatingBar) view.findViewById(R.id.rate);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, Company_Detail.class);
                        mContext.startActivity(intent);

                    }
                });

            }
        }
    }

    /**
     * Created by ahmed on 30/08/17.
     */

    public static class ContainerSizes {

        private String companyname;
        private String logo;
        private float rate;
        private String percentage;

        public ContainerSizes() {

        }

        public ContainerSizes(String companyname, String logo, float rate, String percentage) {
            this.companyname = companyname;
            this.logo = logo;
            this.rate = rate;
            this.percentage = percentage;
        }

        public String getCompanyname() {
            return companyname;
        }

        public void setCompanyname(String companyname) {
            this.companyname = companyname;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public float getRate() {
            return rate;
        }

        public void setRate(float rate) {
            this.rate = rate;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

    }

}

