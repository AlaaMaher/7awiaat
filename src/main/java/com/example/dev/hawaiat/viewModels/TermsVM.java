package com.example.dev.hawaiat.viewModels;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.Toast;

import com.example.dev.hawaiat.BR;
import com.example.dev.hawaiat.models.TermsM;
import com.example.dev.hawaiat.webServices.RetrofitWebService;
import com.example.dev.hawaiat.webServices.request.PolicyTermsRequest;
import com.example.dev.hawaiat.webServices.responses.PolicyTermsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TermsVM extends BaseObservable {
    private TermsM termsM;
    private Context context;
    //

    public TermsVM(TermsM termsM, Context context) {
        this.termsM = termsM;
        this.context = context;
    }

    //
    @Bindable
    public String getMyTerms() {
        return termsM.getMyTerms();
    }

    public void setMyTerms(final String myTerms) {
        termsM.setMyTerms(myTerms);
        notifyPropertyChanged(BR.myTerms);


    }

    public void loadTerms(String lang) {
        PolicyTermsRequest policyTermsRequest = new PolicyTermsRequest(lang);
        RetrofitWebService.getService().getTermsFun(policyTermsRequest).enqueue(new Callback<PolicyTermsResponse>() {
            @Override
            public void onResponse(Call<PolicyTermsResponse> call, Response<PolicyTermsResponse> response) {
                int status = response.body().getStatus();
                Toast.makeText(context, String.valueOf(status), Toast.LENGTH_SHORT).show();
                if (status == 200) {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                    String terms = response.body().getTerms();
                    //termsM=new TermsM(terms);
                    setMyTerms(terms);
                }
            }

            @Override
            public void onFailure(Call<PolicyTermsResponse> call, Throwable t) {

            }
        });
    }
}
