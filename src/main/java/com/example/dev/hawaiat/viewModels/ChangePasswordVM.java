package com.example.dev.hawaiat.viewModels;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;

import com.example.dev.hawaiat.BR;
import com.example.dev.hawaiat.webServices.RetrofitWebService;
import com.example.dev.hawaiat.webServices.request.ChangePasswordRequest;
import com.example.dev.hawaiat.webServices.responses.ChangePasswordResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmed on 24/09/17.
 */

public class ChangePasswordVM extends BaseObservable {

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    private String apiToken = "4EN93W8g842d6fYsiBw6xQJIpnGw3MwCna2dClHT0TirD0eG7LbzkfmKJSk0otni4X49Ll";
    private String errorConfirmPassword;

    private Context context;

    public ChangePasswordVM(Context context) {
        this.context = context;
    }

    public static boolean isPasswordMatching(final String password, final String confirmPassword) {

        Pattern pattern = Pattern.compile(password, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(confirmPassword);

        if (!matcher.matches()) {

            return false;
        } else {
            return true;
        }

    }

    @Bindable
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        notifyPropertyChanged(BR.oldPassword);


    }

    @Bindable
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        notifyPropertyChanged(BR.newPassword);

    }

    @Bindable
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        isPasswordMatching(newPassword, confirmPassword);
        notifyPropertyChanged(BR.confirmPassword);
        notifyPropertyChanged(BR.errorConfirmPassword);
    }

    @Bindable
    public String getErrorConfirmPassword() {
        if (getConfirmPassword() == null) {
            return "Please Enter pass agin";
        } else if (!isPasswordMatching(getNewPassword(), getConfirmPassword())) {
            return "Pass doesn't match";
        } else {
            return null;
        }

    }

    public void onSavedClick(View v) {

        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(apiToken, oldPassword, newPassword);
        RetrofitWebService.getService().getChangePasswordFun(changePasswordRequest).enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                Toast.makeText(context, "Status" + response.body().getStatus(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {

            }
        });

    }
}


