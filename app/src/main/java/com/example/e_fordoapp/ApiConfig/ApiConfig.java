package com.example.e_fordoapp.ApiConfig;

import com.example.e_fordoapp.Model.Setting;
import com.example.e_fordoapp.Utility.Utility;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {
    private static Retrofit retrofit;
//    Utility utility=new Utility();
//    Setting setting=utility.getSetting();
//    String BASE_URL_NEW="http://"+setting.getBaseIP()+"/";

    // todo local
    //private static final String BASE_URL = "http://172.29.19.17:9090/api/";
    // todo live
    //private static final String BASE_URL = "http://172.29.19.17:9090/api/";
    private static final String BASE_URL = "http://103.58.95.39:801/";

    public static Retrofit getApiClient() {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
