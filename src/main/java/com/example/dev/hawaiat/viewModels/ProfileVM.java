package com.example.dev.hawaiat.viewModels;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dev.hawaiat.BR;
import com.example.dev.hawaiat.models.Data;
import com.example.dev.hawaiat.models.Profile;
import com.example.dev.hawaiat.webServices.RetrofitWebService;
import com.example.dev.hawaiat.webServices.responses.UpdateInfoResponse;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class ProfileVM extends BaseObservable {

    private Context context;
    private Profile profile;


    public ProfileVM(Context context, Profile profile) {
        this.context = context;
        this.profile = profile;
    }

    @BindingAdapter("app:image")
    public static void setImageViewResource(ImageView photo, String url) {
        // Picasso.with(photo.getContext()).load(url).into(photo);
        Bitmap bitmap = BitmapFactory.decodeFile(url);
        photo.setImageBitmap(bitmap);
    }

    @Bindable
    public String getUserName() {
        return profile.userName;
    }

    public void setUserName(String userName) {
        profile.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getUserPhone() {

        return profile.userPhone;
    }

    public void setUserPhone(String userPhone) {
        profile.userPhone = userPhone;
        notifyPropertyChanged(BR.userPhone);
    }

    @Bindable
    public String getUserPhoto() {
        return profile.userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        profile.userPhoto = userPhoto;
        notifyPropertyChanged(BR.userPhoto);
    }

    public void onSavedClicked(View view) {

        MultipartBody.Part fileToUpload = null;
        if (getUserPhoto() != null && getUserPhoto().length() > 0) {
            File file = new File(getUserPhoto());
            RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            fileToUpload = MultipartBody.Part.createFormData("photo", file.getName(), mFile);
        }

        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), getUserName());
        RequestBody apiToken = RequestBody.create(MediaType.parse("text/plain"), "4EN93W8g842d6fYsiBw6xQJIpnGw3MwCna2dClHT0TirD0eG7LbzkfmKJSk0otni4X49Ll");
        RequestBody phone = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(getUserPhone()));

        RetrofitWebService.getService().getUpdateInfoFun(fileToUpload, apiToken, name, phone).enqueue(new Callback<UpdateInfoResponse>() {
            @Override
            public void onResponse(Call<UpdateInfoResponse> call, Response<UpdateInfoResponse> response) {
                if (response.body().getStatus() == 200) {
                    Toast.makeText(context, "Success request, and the info has been updated", Toast.LENGTH_SHORT).show();
                    Data.get(context).setUser(profile);
                } else if (response.body().getStatus() == 300) {
                    Toast.makeText(context, "Success request, and new photo returned.", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 400) {
                    Toast.makeText(context, "Non registered apiToken", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 403) {
                    Toast.makeText(context, "Missing required parameter", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 404) {
                    Toast.makeText(context, "Unknown error", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 405) {
                    Toast.makeText(context, "Invalid apiToken", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 406) {
                    Toast.makeText(context, "Invalid Phone", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 407) {
                    Toast.makeText(context, "Invalid Name", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 408) {
                    Toast.makeText(context, "Invalid Photo", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus() == 409) {
                    Toast.makeText(context, "This phone is already registered.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateInfoResponse> call, Throwable t) {
                Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show();
            }
        });

    }
}





