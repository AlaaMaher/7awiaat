package com.example.dev.hawaiat.webServices;

import android.util.Log;

import com.example.dev.hawaiat.webServices.responses.StatusResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitWebService {
    private static final String TAG = RetrofitWebService.class.getSimpleName();
    private static final Map<String, RetrofitService> mServices = new HashMap<>();
    private static final String url = "https://haweyat.magdsoft.com/api/";

    private RetrofitWebService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .writeTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mServices.put(url, retrofit.create(RetrofitService.class));
    }

    public static RetrofitService getService() {
        if (null == mServices.get(url)) {
            new RetrofitWebService();
        }
        return mServices.get(url);
    }

    public static <T> T getBody(Callback<?> callback, Call call, Response<?> response) {
        StatusResponse r = (StatusResponse) response.body();
        if (null != r && ("done".equals(r.status) || "200".equals(r.status) || 200 == r.status)) {
            return (T) r;
        }
        Log.d(TAG, response.toString());
        callback.onFailure(call, new Throwable("Body is null or statusToasts not \"done\""));
        return null;
    }

    public static void log(Throwable t) {
        Log.e(TAG, null != t.getMessage() ? t.getMessage() : t.toString());
    }
}
