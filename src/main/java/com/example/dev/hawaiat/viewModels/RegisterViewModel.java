package com.example.dev.hawaiat.viewModels;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import com.example.dev.hawaiat.BR;
import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.models.User;
import com.example.dev.hawaiat.views.VerifyPhone;
import com.example.dev.hawaiat.webServices.RetrofitWebService;
import com.example.dev.hawaiat.webServices.request.SignUp;
import com.example.dev.hawaiat.webServices.responses.StatusResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by medo on 13-Sep-17.
 */

public class RegisterViewModel extends BaseObservable {

    private final ObservableField<User> mTaskObservable = new ObservableField<>();
    private Context ctx;

    public RegisterViewModel(Context ctx) {
        this.ctx = ctx;
        User user = new User();
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

    }

    public void register(View v) {


        if (getMassword() == null) {
            Toast.makeText(ctx, " Please Enter password ", Toast.LENGTH_SHORT).show();
        } else if (getMassword().length() < 9) {
            Toast.makeText(ctx, " Password is too Short ", Toast.LENGTH_SHORT).show();
        } else if (getMhoneNumber() == null) {
            Toast.makeText(ctx, " Please Enter Phone Number ", Toast.LENGTH_SHORT).show();
        }else if(!getChecked()){
            Toast.makeText(ctx, " Please Accept Conditions ", Toast.LENGTH_SHORT).show();
        }
        else {
           registerF();
        }
    }

    private void registerF() {
        SignUp signUp=new SignUp();

        signUp.setPhone(getMhoneNumber());
        signUp.setPassword(getMassword());


        RetrofitWebService.getService().regesiter(signUp).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {



                if(response.body().getStatus()==200){
                    Toast.makeText(ctx,ctx.getResources().getString(R.string.regsuccess), Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(ctx, VerifyPhone.class);
                    intent.putExtra("phoneNumber",getMhoneNumber());
                    ctx.startActivity(intent);

                }else if(response.body().getStatus()==400){
                    Toast.makeText(ctx, ctx.getResources().getString(R.string.regbefore), Toast.LENGTH_SHORT).show();
                }else if(response.body().getStatus()==403){
                    Toast.makeText(ctx, ctx.getResources().getString(R.string.enterAll), Toast.LENGTH_SHORT).show();
                }else if(response.body().getStatus()==404){
                    Toast.makeText(ctx, " Unknown error ! ", Toast.LENGTH_SHORT).show();
                }else if(response.body().getStatus()==405){
                    Toast.makeText(ctx, ctx.getResources().getString(R.string.wrongPhone), Toast.LENGTH_SHORT).show();
                }else if(response.body().getStatus()==407){
                    Toast.makeText(ctx,  ctx.getResources().getString(R.string.wrongPassword), Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {

                Toast.makeText(ctx, " connection failed "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }

    public void vailidatePassword() {
        User user = mTaskObservable.get();
       // Toast.makeText(ctx, " hi password " + user.getPassword() + " email " + user.getPhoneNamber(), Toast.LENGTH_SHORT).show();
    }

}
