package com.example.dev.hawaiat.viewModels;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import com.example.dev.hawaiat.BR;
import com.example.dev.hawaiat.models.User;
import com.example.dev.hawaiat.views.Resetpassword;
import com.example.dev.hawaiat.webServices.RetrofitWebService;
import com.example.dev.hawaiat.webServices.request.LoginRequest;
import com.example.dev.hawaiat.webServices.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by medo on 15-Sep-17.
 */
//------- this is mine =============================================
public class LoginViewModel extends BaseObservable {

    private final ObservableField<User> mTaskObservable = new ObservableField<>();
    private Context ctx;
    private OnPassinData onPassinData;

    public LoginViewModel(Context ctx) {
        this.ctx = ctx;
        User user = new User();
        SharedPreferences sharedPreferences=ctx.getSharedPreferences("isChecked", Context.MODE_PRIVATE);

    if(sharedPreferences.getBoolean("isChec", false)) {
        user.setChecked(true);
        user.setPhoneNamber(sharedPreferences.getString("phone",""));
        user.setPassword(sharedPreferences.getString("pass",""));
    }
        mTaskObservable.set(user);

    }


    @Bindable
    public String getMhoneNumber() {
        return mTaskObservable.get().getPhoneNamber();
    }

    public void setMhoneNumber(String phonennNumber) {

        User user = mTaskObservable.get();
        user.setPhoneNamber(phonennNumber);
        notifyPropertyChanged(BR.mhoneNumber);

    }

    @Bindable
    public String getMassword() {
        vailidatePassword();
        return mTaskObservable.get().getPassword();
    }

    public void setMassword(String password) {

        User user = mTaskObservable.get();
        user.setPassword(password);
        notifyPropertyChanged(BR.massword);


    }

    @Bindable
    public boolean getChecked() {
        vailedatCheck();
        return mTaskObservable.get().isChecked();

    }

    public void setChecked(boolean checked) {

        User user = mTaskObservable.get();
        user.setChecked(checked);
        notifyPropertyChanged(BR.checked);


    }

    private void vailedatCheck() {

        User user = mTaskObservable.get();
        SharedPreferences sharedPreferences=ctx.getSharedPreferences("isChecked", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if (user.isChecked()) {
            Toast.makeText(ctx, " is checked ", Toast.LENGTH_SHORT).show();

            editor.putString("phone",getMhoneNumber());
            editor.putString("pass",getMassword());
            editor.putBoolean("isChec",true);
            editor.commit();


        }else{
            editor.putString("phone","");
            editor.putString("pass","");
            editor.putBoolean("isChec",false);
            editor.commit();
        }

    }

    public void onLogin(View v) {

        if (getMhoneNumber() == null) {
            Toast.makeText(ctx, " Please Enter phone number ", Toast.LENGTH_SHORT).show();
        }
       else if (getMassword() == null) {
            Toast.makeText(ctx, " Please Enter password ", Toast.LENGTH_SHORT).show();
        } else if (getMassword().length() <5) {
            Toast.makeText(ctx, " Password is too Short ", Toast.LENGTH_SHORT).show();
        }  else {
            //Intent intent = new Intent(ctx, HomeScreen.class);
            //ctx.startActivity(intent);

            login();


        }
    }

    private void login() {
        SharedPreferences sharedPreferences=ctx.getSharedPreferences("PhoneAndPaswword", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("phone",getMhoneNumber());
        editor.putString("pass",getMassword());
        editor.commit();

        final LoginRequest loginRequest=new LoginRequest();
        loginRequest.setPhone(getMhoneNumber());
        loginRequest.setPassword(getMassword());


        RetrofitWebService.getService().login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.body().getStatus()==200){


                    LoginResponse loginResponse=response.body();
                    ((OnPassinData) ctx).onPassingData(loginResponse);


                }else if(response.body().getStatus()==403){
                    Toast.makeText(ctx, " Invalid request, because of missing requirements. ", Toast.LENGTH_SHORT).show();
                }else if(response.body().getStatus()==404){
                    Toast.makeText(ctx, " Unknown error ", Toast.LENGTH_SHORT).show();
                }else if(response.body().getStatus()==405){
                    Toast.makeText(ctx, " Invalid phone or password ", Toast.LENGTH_SHORT).show();
                }else if(response.body().getStatus()==406){
                    Toast.makeText(ctx, " This phone is not verified ", Toast.LENGTH_SHORT).show();
                }else if(response.body().getStatus()==407){
                    Toast.makeText(ctx, " Non active acount ", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(ctx, " Connection failed "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
    public interface OnPassinData{

       public void onPassingData(LoginResponse loginResponse);

    }

    public void onForget(View v){

        Toast.makeText(ctx, " go to forget screen ", Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(ctx, Resetpassword.class);
        ctx.startActivity(intent);

    }

    private void vailidatePassword() {
        User user = mTaskObservable.get();
       // Toast.makeText(ctx, " hi password " + user.getPassword() + " email " + user.getPhoneNamber(), Toast.LENGTH_SHORT).show();

    }
}