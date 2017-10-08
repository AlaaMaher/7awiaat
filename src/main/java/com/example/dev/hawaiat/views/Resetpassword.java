package com.example.dev.hawaiat.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.databinding.ResetPasswordBinding;
import com.example.dev.hawaiat.models.ResetPasswordM;
import com.example.dev.hawaiat.viewModels.ResetPasswordVM;

public class Resetpassword extends AppCompatActivity {
    //private Button butReset;
    //private EditText editReset;
    //private  int number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ResetPasswordBinding binding = DataBindingUtil.setContentView(this, R.layout.reset_password);
        ResetPasswordVM resetPasswordVM = new ResetPasswordVM(this, new ResetPasswordM());
        binding.setResetPasswordVM(resetPasswordVM);



      /*  setContentView(R.layout.reset_password);
        butReset = findViewById(R.id.butReset);
        editReset = (EditText) findViewById(R.id.editReset);

        butReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Resetpassword.this, "OnClick`", Toast.LENGTH_SHORT).show();
                String numberText = editReset.getText().toString();
                number = Integer.parseInt(numberText);


                ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest(number);

                RetrofitWebService.getService().getResetPasswordFun(resetPasswordRequest).enqueue(new Callback<ResetPasswordResponse>() {
                    @Override
                    public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                        Toast.makeText(Resetpassword.this, "Status"+response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {

                    }
                });
            }

        });*/
    }
}
