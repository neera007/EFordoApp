package com.example.e_fordoapp.Service;

import com.example.e_fordoapp.Model.Order;
import com.example.e_fordoapp.Model.Product;
import com.example.e_fordoapp.Model.ProductCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderService {

    //Invoice Save
    @POST("api/order/save")
    Call<Order> saveInvoice(@Body Order order);

    //Get order history
    @GET("api/order/orderHistory")
    Call<List<Order>> getOrderHistory(@Query("userId") String userId,
                                      @Query("password") String password,
                                      @Query("orderNumber") String orderNumber,
                                      @Query("orderDate") String orderDate);
    //Get order history
    @GET("api/order/orderDetails")
    Call<List<Product>> getorderDetails(@Query("userId") String userId,
                                        @Query("password") String password,
                                        @Query("orderID") String orderID);

}
