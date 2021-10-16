package com.example.e_fordoapp.Service;
import com.example.e_fordoapp.Model.ProductCategory;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductService {
    //http://172.29.35.5:2521/api/itemInfo/allitem?userId=syeed&password=123
    @GET("itemInfo/allitem")
    Call<List<ProductCategory>> getAllProduct(@Query("userId") String userId,
                                              @Query("password") String password);

    //http://172.29.35.5:2521/api/itemInfo/itembygroup?userId=syeed&password=123&itemGroup=1
    @GET("itemInfo/itembygroup")
    Call<List<ProductCategory>> getProductByCategoryID(@Query("userId") String userId,
                                                        @Query("password") String password,
                                                        @Query("itemGroup") String itemGroup);
}
