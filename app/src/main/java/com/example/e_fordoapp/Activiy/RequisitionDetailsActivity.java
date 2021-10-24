package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_fordoapp.Adapter.BasketAdapter;
import com.example.e_fordoapp.ApiConfig.ApiConfig;
import com.example.e_fordoapp.Model.Order;
import com.example.e_fordoapp.Model.Product;
import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Service.OrderService;
import com.example.e_fordoapp.Service.ProductService;
import com.example.e_fordoapp.Utility.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequisitionDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private OrderService orderService;
    List<Product> productItemList= new ArrayList<>();
    private TextView tvSummaryTitle,tvOrderAmount;
    private RecyclerView recycleView;
    private Button btnBack;

    Utility utility;
    String orderID="";
    String orderNumber="";
    String orderDate="";
    String orderAmount="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_details);

        utility = new Utility(this);
        btnBack = this.findViewById(R.id.btnBack);
        tvSummaryTitle = findViewById(R.id.tvSummaryTitle);
        tvOrderAmount=findViewById(R.id.tvOrderAmount);

        // todo get data from previous page
        Intent intent = getIntent();
        orderID = intent.getStringExtra("orderID");
        orderNumber = intent.getStringExtra("orderNumber");
        orderDate = intent.getStringExtra("orderDate");
        orderAmount = intent.getStringExtra("orderAmount");

        recycleView = findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);

        btnBack.setOnClickListener(this);

        tvSummaryTitle.setText("SUMMARY (Order Number: "+orderNumber+")");
        tvOrderAmount.setText("৳: "+String.valueOf(orderAmount));

        loadOrderDetails(orderID);
    }

    private void loadOrderDetails(String orderID) {
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        utility.showLoading();

        orderService = ApiConfig.getApiClient().create(OrderService.class);
        Call<List<Product>> call = orderService.getorderDetails(utility.getUserID(),utility.getPassword(),orderID);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                utility.hideLoading();
                if (response.body()==null){
                    return;
                }
                productItemList = new ArrayList<>(response.body());

                //todo creating recyclerview adapter
                BasketAdapter adapter = new BasketAdapter(RequisitionDetailsActivity.this, productItemList,false);

                //todo setting adapter to recyclerview
                recycleView.setAdapter(adapter);

                // Todo event assign when click to adapter
                adapter.setOnRecycleViewItemClickListener(new BasketAdapter.OnRecycleViewItemClickListener() {
                    @Override
                    public void onRecycleViewItemClick(int position) {
                        //tvOrderAmount.setText("৳: "+String.valueOf(utility.getBusketAmount()));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                utility.hideLoading();
                Toast.makeText(RequisitionDetailsActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view==btnBack)
        {
            super.onBackPressed();
        }
    }
}