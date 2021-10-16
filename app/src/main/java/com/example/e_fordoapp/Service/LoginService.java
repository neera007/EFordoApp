package com.example.e_fordoapp.Service;

import com.example.e_fordoapp.Model.UserInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginService {
    //http://192.168.0.26:9090/api/user/auth?userId=syeed&password=123
    @GET("user/auth")
    Call<UserInfo> getLoginUser(@Query("userId") String userId,
                                @Query("password") String password);
}
