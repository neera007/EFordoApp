package com.example.e_fordoapp.Service;

import com.example.e_fordoapp.Model.Customer;
import com.example.e_fordoapp.Model.CustomerAutoComplete;
import com.example.e_fordoapp.Model.ProductCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CustomerService {
    //http://172.29.35.5:2521/api/customerInfo/allCustomer?userId=syeed&password=123
    //http://103.58.95.39:801/api/customerInfo/allCustomer?userId=faysal&password=123
    @GET("api/customerInfo/allCustomer")
    Call<List<CustomerAutoComplete>> getAllCustomer(@Query("userId") String userId,
                                                    @Query("password") String password);

    //http://172.29.35.5:2521/api/customerInfo/customerByKeyword?userId=syeed&password=123&searchKeyword=25221
    //http://103.58.95.39:801/api/customerInfo/customerByKeyword?userId=faysal&password=123&searchKeyword=1
    @GET("api/customerInfo/customerByKeyword")
    Call<Customer> getCustomerByKeyword(@Query("userId") String userId,
                                                       @Query("password") String password,
                                                       @Query("searchKeyword") String searchKeyword);


}
