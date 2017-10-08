package com.example.dev.hawaiat.webServices;

import com.example.dev.hawaiat.webServices.request.AboutUsRequest;
import com.example.dev.hawaiat.webServices.request.ChangePasswordRequest;
import com.example.dev.hawaiat.webServices.request.CompaniesRequest;
import com.example.dev.hawaiat.webServices.request.CompanyProfileRequest;
import com.example.dev.hawaiat.webServices.request.GetCompaniesRequest;
import com.example.dev.hawaiat.webServices.request.LogRequest;
import com.example.dev.hawaiat.webServices.request.LoginRequest;
import com.example.dev.hawaiat.webServices.request.PolicyTermsRequest;
import com.example.dev.hawaiat.webServices.request.RatingRequest;
import com.example.dev.hawaiat.webServices.request.ResendRequest;
import com.example.dev.hawaiat.webServices.request.ResetPasswordRequest;
import com.example.dev.hawaiat.webServices.request.SignUp;
import com.example.dev.hawaiat.webServices.request.UpdatePasswordRequest;
import com.example.dev.hawaiat.webServices.request.VerifyRequest;
import com.example.dev.hawaiat.webServices.responses.AboutUsResponse;
import com.example.dev.hawaiat.webServices.responses.ChangePasswordResponse;
import com.example.dev.hawaiat.webServices.responses.CompaniesResponse;
import com.example.dev.hawaiat.webServices.responses.CompanyProfileResponse;
import com.example.dev.hawaiat.webServices.responses.ContactResponse;
import com.example.dev.hawaiat.webServices.responses.GetCompaniesResponse;
import com.example.dev.hawaiat.webServices.responses.LoginResponse;
import com.example.dev.hawaiat.webServices.responses.PolicyTermsResponse;
import com.example.dev.hawaiat.webServices.responses.ResetPasswordResponse;
import com.example.dev.hawaiat.webServices.responses.StatusResponse;
import com.example.dev.hawaiat.webServices.responses.UpdateInfoResponse;
import com.example.dev.hawaiat.webServices.responses.UpdatePasswordResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitService {

    @POST("signup")
    Call<StatusResponse> regesiter(@Body SignUp signUp);

    @POST("verifyPhone")
    Call<StatusResponse> verifyPhone(@Body VerifyRequest VerifyRequest);

    @POST("resendCode")
    Call<StatusResponse> resendCode(@Body ResendRequest resendRequest);

    @POST("changePassword")
    Call<ChangePasswordResponse> getChangePasswordFun(@Body ChangePasswordRequest changePasswordRequest);

    @POST("resetPassword")
    Call<ResetPasswordResponse> getResetPasswordFun(@Body ResetPasswordRequest resetPasswordRequest);
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("aboutUs")
    Call<AboutUsResponse> getAboutUsFun(@Body AboutUsRequest aboutUsRequest);

    @POST("getCompanies")
    Call<GetCompaniesResponse> getCompaniesfun(@Body GetCompaniesRequest getCompaniesRequest);

    @POST("updatePassword")
    Call<UpdatePasswordResponse> getUpdatePasswordFun(@Body UpdatePasswordRequest updatePasswordRequest);

    @POST("rateCompany")
    Call<StatusResponse> rateCompany(@Body RatingRequest ratingRequest);

    @POST("getCompanies")
    Call<CompaniesResponse> getCompanies(@Body CompaniesRequest companiesRequest);

    @Multipart
    @POST("updateInfo")
    Call<UpdateInfoResponse> getUpdateInfoFun(@Part MultipartBody.Part photo, @Part("apiToken") RequestBody apiToken, @Part("name") RequestBody name, @Part("phone") RequestBody phone);

    @POST("log")
    Call<StatusResponse> getLog(@Body LogRequest logRequest);

    @POST("companyProfile")
    Call<CompanyProfileResponse> getCompanyProfile(@Body CompanyProfileRequest companyProfileRequest);

    @POST("contact")
    Call<ContactResponse> getContactFun();

    @POST("getPolicyTerms")
    Call<PolicyTermsResponse> getTermsFun(@Body PolicyTermsRequest policyTermsRequest);
}