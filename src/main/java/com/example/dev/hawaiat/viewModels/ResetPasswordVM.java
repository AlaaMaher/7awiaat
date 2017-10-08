package com.example.dev.hawaiat.viewModels;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;

import com.example.dev.hawaiat.BR;
import com.example.dev.hawaiat.models.ResetPasswordM;
import com.example.dev.hawaiat.webServices.RetrofitWebService;
import com.example.dev.hawaiat.webServices.request.ResetPasswordRequest;
import com.example.dev.hawaiat.webServices.responses.ResetPasswordResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordVM extends BaseObservable {
    private Context context;
    private ResetPasswordM resetPasswordM;

    public ResetPasswordVM(Context context, ResetPasswordM resetPasswordM) {
        this.context = context;
        this.resetPasswordM = resetPasswordM;
    }

    @Bindable
    public String getEditReset() {
        return resetPasswordM.getEditReset();
    }

    public void setEditReset(String editReset) {
        resetPasswordM.setEditReset(editReset);
        notifyPropertyChanged(BR.editReset);

    }

    public void onSendClicked(View v) {

        String phone = resetPasswordM.getEditReset();
        int number = Integer.parseInt(phone);
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest(number);

        RetrofitWebService.getService().getResetPasswordFun(resetPasswordRequest).enqueue(new Callback<ResetPasswordResponse>() {
            @Override
            public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                Toast.makeText(context, "Status" + response.body().getStatus(), Toast.LENGTH_SHORT).show();

                /*Intent intent = new Intent(context.getApplicationContext(),LoginScreen.class);
                Toast.makeText(context, "Go to Login Screen", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);*/

            }

            @Override
            public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {

            }
        });


    }


}
