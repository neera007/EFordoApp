package com.example.e_fordoapp.Service;

import com.example.e_fordoapp.Model.Order;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderService {

    //Invoice Save
    @POST("api/order/save")
    Call<Order> saveInvoice(@Body Order order);


}
