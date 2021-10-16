package com.example.e_fordoapp.Service;

import com.example.e_fordoapp.Model.ProductCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CustomerService {

    //http://172.29.35.5:2521/api/customerInfo/allCustomer?userId=syeed&password=123
    @GET("customerInfo/allCustomer")
    Call<List<ProductCategory>> getAllCustomer(@Query("userId") String userId,
                                              @Query("password") String password);

    //http://172.29.35.5:2521/api/customerInfo/customerByKeyword?userId=syeed&password=123&searchKeyword=25221
    @GET("customerInfo/customerByKeyword")
    Call<List<ProductCategory>> getCustomerByKeyword(@Query("userId") String userId,
                                                       @Query("password") String password,
                                                       @Query("searchKeyword") String searchKeyword);


}
