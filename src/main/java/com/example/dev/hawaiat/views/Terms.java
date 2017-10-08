package com.example.dev.hawaiat.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.databinding.ActivityTermsBinding;
import com.example.dev.hawaiat.models.TermsM;
import com.example.dev.hawaiat.viewModels.TermsVM;


public class Terms extends AppCompatActivity {
    //
    // private TextView myTerms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_terms);


        ActivityTermsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_terms);
        TermsVM termsVM = new TermsVM(new TermsM(), this);
        termsVM.loadTerms("en");
        binding.setTermsVM(termsVM);



     /*   PolicyTermsRequest policyTermsRequest=new PolicyTermsRequest("en");
        RetrofitWebService.getService().getTermsFun(policyTermsRequest).enqueue(new Callback<PolicyTermsResponse>() {
            @Override
            public void onResponse(Call<PolicyTermsResponse> call, Response<PolicyTermsResponse> response) {

                int status=response.body().getStatus();
                if (status==200){
                    Toast.makeText(Terms.this, "Success", Toast.LENGTH_SHORT).show();
                    String terms=response.body().getTerms();
                    myTerms.setText(terms);
                }
            }

            @Override
            public void onFailure(Call<PolicyTermsResponse> call, Throwable t) {
                Toast.makeText(Terms.this, "Internet Conection Fail", Toast.LENGTH_SHORT).show();

            }
        });*/

    }
}
