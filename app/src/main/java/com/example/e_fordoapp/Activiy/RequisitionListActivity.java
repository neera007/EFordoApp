package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.e_fordoapp.Adapter.ProductCategoryAdapter;
import com.example.e_fordoapp.Adapter.RequisitionHistoryAdapter;
import com.example.e_fordoapp.ApiConfig.ApiConfig;
import com.example.e_fordoapp.Model.Order;
import com.example.e_fordoapp.Model.ProductCategory;
import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Service.OrderService;
import com.example.e_fordoapp.Service.ProductCategoryService;
import com.example.e_fordoapp.Utility.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequisitionListActivity extends AppCompatActivity {
    private RecyclerView recycleView;
    List<Order> orderList= new ArrayList<>();
    String orderID="";
    String orderNumber="";
    String orderDate="";
    String orderAmount="";

    private OrderService orderService;
    Utility utility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_list);

        utility = new Utility(this);
        recycleView = findViewById(R.id.recycleView);
        loadOrderList(utility.getUserID(),utility.getPassword());
    }

    private void loadOrderList(final String userID, final String password) {
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        orderService = ApiConfig.getApiClient().create(OrderService.class);
        Call<List<Order>> call = orderService.getOrderHistory(userID,password);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {

                //todo initializing the stockItemList
                if (response.body()==null){
                    return;
                }
                orderList = new ArrayList<>(response.body());
                RequisitionHistoryAdapter adapter = new RequisitionHistoryAdapter(RequisitionListActivity.this, orderList);

                //todo setting adapter to recyclerview
                recycleView.setAdapter(adapter);

                adapter.setOnRecycleViewItemClickListener(new RequisitionHistoryAdapter.OnRecycleViewItemClickListener() {
                    @Override
                    public void onRecycleViewItemClick(int position) {
                        orderID= orderList.get(position).getOrderID();
                        orderNumber= orderList.get(position).getOrderNumber();
                        orderDate= orderList.get(position).getOrderDate();
                        orderAmount= orderList.get(position).getOrderAmount();

                        Intent myIntent = new Intent(RequisitionListActivity.this, RequisitionDetailsActivity.class);
                        myIntent.putExtra("orderID", orderID);
                        myIntent.putExtra("orderNumber", orderNumber);
                        myIntent.putExtra("orderDate", orderDate);
                        myIntent.putExtra("orderAmount", orderAmount);
                        startActivity(myIntent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(RequisitionListActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}