package com.example.dev.hawaiat.viewModels;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.views.LoginScreen;
import com.example.dev.hawaiat.webServices.RetrofitWebService;
import com.example.dev.hawaiat.webServices.request.ResendRequest;
import com.example.dev.hawaiat.webServices.request.VerifyRequest;
import com.example.dev.hawaiat.webServices.responses.StatusResponse;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by medo on 14-Sep-17.
 */

public class VerifyPhoneViewModel extends BaseObservable {

   private static EditText edit_pin1, edit_pin2, edit_pin3, edit_pin4, edit_pin5,edit_pin6;
    static TextView tv_resend;


    private static Context ctx;

    private String pin1, pin2, pin3, pin4, pin5,pin6;
    private String time;
    CountDownTimer  countDownTimer;
    String phone;

    public VerifyPhoneViewModel(Context context, String phoneNumber) {
        this.ctx = context;


       setTimee();
        pin1=null; pin2=null; pin3=null; pin4=null; pin5=null;pin6=null;
        phone=phoneNumber;
    }

    @BindingAdapter("app:editText")
    public static void bindImage(View view, String text) {
        if (view.getId() == R.id.pin1) {
            edit_pin1 = (EditText) view;
        } else if (view.getId() == R.id.pin2) {
            edit_pin2 = (EditText)view;
        } else if (view.getId() == R.id.pin3) {
            edit_pin3 =(EditText) view;
        } else if (view.getId() == R.id.pin4) {
            edit_pin4 = (EditText)view;
        } else if (view.getId() == R.id.pin5) {
            edit_pin5 = (EditText)view;
        }else if (view.getId() == R.id.pin6) {
            edit_pin6 = (EditText)view;
        }else if(view.getId()==R.id.tv_resend){
            tv_resend=(TextView)view;
        }
    }

    @Bindable
    public String getPin1() {

        return pin1;
    }

    @Bindable
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time=time;
        notifyPropertyChanged(BR.time);
    }


    public void setPin1(String pin1) {
        this.pin1 = pin1;

        if (pin1 != null) {
            edit_pin2.requestFocus();
        }
    }


    @Bindable
    public String getPin2() {
        return pin2;
    }

    public void setPin2(String pin2) {
        this.pin2 = pin2;

        if (pin1 != null && pin3 != null) {

         //   edit_pin3.requestFocus();

        } else {
           if (pin2 != null) {
                edit_pin3.requestFocus();
            } else {
                edit_pin1.requestFocus();
            }

        }
    }


    @Bindable
    public String getPin3() {
        return pin3;
    }

    public void setPin3(String pin3) {
        this.pin3 = pin3;
        if (pin2 != null && pin4 != null) {
            edit_pin4.requestFocus();

        } else {
            if (pin3 != null) {
                edit_pin4.requestFocus();
            } else {
                edit_pin2.requestFocus();
            }
        }
    }


    @Bindable
    public String getPin4() {
        return pin4;
    }

    public void setPin4(String pin4) {
        this.pin4 = pin4;
        if (pin3 != null && pin5 != null) {

        } else {
            if (pin4 != null) {
                edit_pin5.requestFocus();
            } else {

                edit_pin3.requestFocus();

            }
        }
    }

   @Bindable
    public String getPin5() {
        return pin5;
    }

    public void setPin5(String pin5) {
        this.pin5 = pin5;

        if (pin4 != null&& pin6!=null) {


        } else {
            if (pin5 != null) {
                edit_pin6.requestFocus();
            } else {

                edit_pin4.requestFocus();

            }

        }
    }
    @Bindable
    public String getPin6() {
        return pin6;
    }

    public void setPin6(String pin6) {
        this.pin6 = pin6;

        if (pin6 != null) {


        } else {

            edit_pin5.requestFocus();

        }
    }

    public void resendCode(View v) {
        TextView textView=(TextView)v;
        setTimee();


        //Retrofit for resend code

        resend();


    }

    private void resend() {

        ResendRequest resendRequest=new ResendRequest();
        resendRequest.setPhone(phone);

        RetrofitWebService.getService().resendCode(resendRequest).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {

                if(response.body().getStatus()==200){
                    Toast.makeText(ctx,ctx.getResources().getString(R.string.resendSuccess), Toast.LENGTH_SHORT).show();
            }else if(response.body().getStatus()==401){
                Toast.makeText(ctx, ctx.getResources().getString(R.string.unregPhone), Toast.LENGTH_SHORT).show();
            }else if(response.body().getStatus()==403){
                Toast.makeText(ctx, "Invalid request, because of missing requirements.", Toast.LENGTH_SHORT).show();
            }else if(response.body().getStatus()==404){
                Toast.makeText(ctx, "Unknown error ! ", Toast.LENGTH_SHORT).show();
            }else if(response.body().getStatus()==405){
                Toast.makeText(ctx,  ctx.getResources().getString(R.string.wrongPhone), Toast.LENGTH_SHORT).show();
            }else if(response.body().getStatus()==405) {

                    Toast.makeText(ctx,  ctx.getResources().getString(R.string.phoneAlreadyVerfied), Toast.LENGTH_SHORT).show();


                }




            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {

            }
        });

    }

    public void onLogin(View v) {

        countDownTimer.cancel();
        setTime("00:00");
        tv_resend.setEnabled(true);

        login();

    }

    private void login() {


        VerifyRequest verifyRequest=new VerifyRequest();
        String code=getPin1()+getPin2()+getPin3()+getPin4()+getPin5()+getPin6();
        verifyRequest.setCode(code);
        verifyRequest.setPhone(phone);

        RetrofitWebService.getService().verifyPhone(verifyRequest).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {

                if(response.body().getStatus()==200){
                    Toast.makeText(ctx,ctx.getResources().getString(R.string.phoneVerified), Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(ctx, LoginScreen.class);
                  //  intent.putExtra("phoneNumber",getMhoneNumber());
                    ctx.startActivity(intent);

                }else if(response.body().getStatus()==400){
                    Toast.makeText(ctx, ctx.getResources().getString(R.string.wrongCode), Toast.LENGTH_SHORT).show();
                }else if(response.body().getStatus()==401){
                    Toast.makeText(ctx," No registered user found.", Toast.LENGTH_SHORT).show();
                }else if(response.body().getStatus()==403){
                    Toast.makeText(ctx, "Invalid request, because of missing requirements.", Toast.LENGTH_SHORT).show();
                }else if(response.body().getStatus()==404){
                    Toast.makeText(ctx, "Unknown error ! ", Toast.LENGTH_SHORT).show();
                }else if(response.body().getStatus()==405){
                    Toast.makeText(ctx,  ctx.getResources().getString(R.string.wrongPhone), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {

            }
        });


    }




    public void setTimee(){

        Handler mainHandler = new Handler(Looper.getMainLooper());
        Runnable run = new Runnable() {

            @Override
            public void run() {
                int totalTimeCountInMilliseconds = 120000;
                countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 500) {

                    @Override
                    public void onTick(long leftTimeInMilliseconds) {
                        tv_resend.setEnabled(false);
                        long seconds = leftTimeInMilliseconds / 1000;

                        int sec = (int) seconds;
                        int minints = sec / 60;
                        int socnd = sec % 60;
                        String n = String.format(Locale.US, "%02d : %02d ", minints, socnd);
                        setTime(n);
                    }

                    @Override
                    public void onFinish() {
                        // this function will be called when the timecount is finished
                       tv_resend.setEnabled(true);

                    }

                }.start();

            }
        };
        mainHandler.post(run);



    }

}
