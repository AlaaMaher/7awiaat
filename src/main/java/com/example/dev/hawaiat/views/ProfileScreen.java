package com.example.dev.hawaiat.views;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.databinding.ProfileScreenBinding;
import com.example.dev.hawaiat.models.Profile;
import com.example.dev.hawaiat.viewModels.ProfileVM;

public class ProfileScreen extends AppCompatActivity {
    //
    private static final int REQUEST_GALLERY_CODE = 200;
    ProfileScreenBinding binding;
    private Profile mUser = new Profile();
    private ProfileVM profileVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.profile_screen);


        binding = DataBindingUtil.setContentView(this, R.layout.profile_screen);
        profileVM = new ProfileVM(this, new Profile());
        binding.setProfileVM(profileVM);


        binding.editProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            final Uri imageUri = data.getData();

            mUser.userPhoto = getRealPathFromURI(this, imageUri);
            profileVM.setUserPhoto(mUser.userPhoto);
            binding.setProfileVM(profileVM);
        }
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
