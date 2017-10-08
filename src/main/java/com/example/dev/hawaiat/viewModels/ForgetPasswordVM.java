package com.example.dev.hawaiat.ViewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.example.dev.hawaiat.webServices.RetrofitWebService;
import com.example.dev.hawaiat.webServices.request.UpdatePasswordRequest;
import com.example.dev.hawaiat.webServices.responses.UpdatePasswordResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.Types.NULL;

/**
 * Created by ahmed on 13/09/17.
 */

public class ForgetPasswordVM extends BaseObservable {

    private String userPassword;
    private String errorPassword;
    private String confirmPassword;
    private Context mContext;
    private int tmpToken = NULL;
    private String errorMatch;

    public ForgetPasswordVM(Context mContext) {
        this.mContext = mContext;
    }

    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        pattern = Pattern.compile(PASSWORD_PATTERN);

        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isPasswordMatching(final String password, final String confirmPassword) {

        Pattern pattern = Pattern.compile(password, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(confirmPassword);

        if (!matcher.matches()) {

            return false;
        } else {
            return true;
        }
/*
        if (password.equals(confirmPassword)) {

            return "true";
        } else {
            return "Not equal";
        }*/
    }

    @Bindable
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        isValidPassword(userPassword);
        notifyPropertyChanged(BR.userPassword);
        notifyPropertyChanged(BR.errorPassword);

    }

    @Bindable
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {


        this.confirmPassword = confirmPassword;
        isPasswordMatching(userPassword, confirmPassword);

        notifyPropertyChanged(BR.confirmPassword);
        notifyPropertyChanged(BR.errorMatch);

    }

    @Bindable
    public String getErrorPassword() {
        if (getUserPassword() == null) {
            return "Please Enter";
        } else if (getUserPassword().length() < 8) {
            return "Too Short..!!";
        } else if (!isValidPassword(getUserPassword())) {
            return "Your Password Must be combination of \n Small,Captital & Special Characters";
        } else {
            return null;
        }
    }

    @Bindable
    public String getErrorMatch() {
        if (getConfirmPassword() == null) {
            return "Please Enter pass again";
        } else if (!isPasswordMatching(getUserPassword(), getConfirmPassword())) {
            return "Pass doesn't match";
        } else {
            return null;
        }
    }


    public void onSaveClicked(View v) {
        UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest(tmpToken, Integer.parseInt(userPassword));
        RetrofitWebService.getService().getUpdatePasswordFun(updatePasswordRequest).enqueue(new Callback<UpdatePasswordResponse>() {
            @Override
            public void onResponse(Call<UpdatePasswordResponse> call, Response<UpdatePasswordResponse> response) {
                Toast.makeText(mContext, "Status" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(mContext.getApplicationContext(),LoginScreen.class);
//                Toast.makeText(mContext, "Go to Login Screen", Toast.LENGTH_SHORT).show();
//                mContext.startActivity(intent);

            }

            @Override
            public void onFailure(Call<UpdatePasswordResponse> call, Throwable t) {

            }
        });


    }


}



