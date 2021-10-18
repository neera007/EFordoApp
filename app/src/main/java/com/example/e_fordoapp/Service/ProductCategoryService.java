package com.example.e_fordoapp.Service;
import com.example.e_fordoapp.Model.ProductCategory;
import com.example.e_fordoapp.Model.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductCategoryService {
    //http://172.29.35.5:2521/api/itemgroup/all?userId=syeed&password=123
    //http://103.58.95.39:801/api/itemgroup/all?userId=faysal&password=123
    @GET("itemgroup/all")
    Call<List<ProductCategory>> getProductCategory(@Query("userId") String userId,
                                             @Query("password") String password);


}
