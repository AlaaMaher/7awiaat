package com.example.dev.hawaiat.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.views.LoginScreen;
import com.example.dev.hawaiat.webServices.RetrofitWebService;
import com.example.dev.hawaiat.webServices.request.LogRequest;
import com.example.dev.hawaiat.webServices.responses.StatusResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by medo on 29-Aug-17.
 */

public class DeligatorRecyclerAdapter extends RecyclerView.Adapter<DeligatorRecyclerAdapter.MyViewHolder> {

    private ArrayList<String> phoneNumber;

    private Activity ctx;

    public DeligatorRecyclerAdapter(Activity ctx, ArrayList<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.ctx = ctx;

    }

    @Override
    public DeligatorRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycelr_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeligatorRecyclerAdapter.MyViewHolder holder, int position) {


        holder.mTextView.setText(phoneNumber.get(position));


    }

    @Override
    public int getItemCount() {
        return phoneNumber.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        ImageView mImagView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_phoneNumber);
            mImagView = (ImageView) itemView.findViewById(R.id.phone_call);


            mImagView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posion = getAdapterPosition();
                    String phoneNumge = phoneNumber.get(posion);
                    log();
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + phoneNumge));
                    if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    ctx.startActivity(callIntent);


                }

            });
        }

        private void log() {
            LogRequest logRequest = new LogRequest();
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(LoginScreen.API_TOKEN_SHARED, ctx.MODE_PRIVATE);
            String api_Token = sharedPreferences.getString(LoginScreen.API_TOKEN, "");
            logRequest.setApiToken(api_Token);

            //TODO please change the company id to a variable
            logRequest.setCompanyID("1");
            logRequest.setType("call");

            RetrofitWebService.getService().getLog(logRequest).enqueue(new Callback<StatusResponse>() {
                @Override
                public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {

                    if (response.body().getStatus() == 200) {
                        Toast.makeText(ctx, "Success Request", Toast.LENGTH_SHORT).show();
                    } else if (response.body().getStatus() == 400) {
                        Toast.makeText(ctx, "Undefined token", Toast.LENGTH_SHORT).show();
                    } else if (response.body().getStatus() == 403) {
                        Toast.makeText(ctx, "Missing requirements.", Toast.LENGTH_SHORT).show();
                    } else if (response.body().getStatus() == 404) {
                        Toast.makeText(ctx, "Unknown error", Toast.LENGTH_SHORT).show();
                    } else if (response.body().getStatus() == 405) {
                        Toast.makeText(ctx, "Invalid token", Toast.LENGTH_SHORT).show();
                    } else if (response.body().getStatus() == 406) {
                        Toast.makeText(ctx, "Invalid companyID or undefined company", Toast.LENGTH_SHORT).show();
                    } else if (response.body().getStatus() == 407) {
                        Toast.makeText(ctx, "Invalid type", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<StatusResponse> call, Throwable t) {

                }
            });


        }


        public void onClick(View view) {

            int posion = getAdapterPosition();
            String phoneNumge = phoneNumber.get(posion);

/*            Intent intent=new Intent(ctx,QuestionActivity.class);
            intent.putExtra("name",modelItem.getmText());*/
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // ctx.startActivity(intent);

        }
    }
}