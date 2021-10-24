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
import com.example.e_fordoapp.Adapter.ProductInfoAdapter;
import com.example.e_fordoapp.ApiConfig.ApiConfig;
import com.example.e_fordoapp.Model.Customer;
import com.example.e_fordoapp.Model.Order;
import com.example.e_fordoapp.Model.Product;
import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Service.CustomerService;
import com.example.e_fordoapp.Service.OrderService;
import com.example.e_fordoapp.Service.ProductService;
import com.example.e_fordoapp.Utility.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity implements View.OnClickListener{

    private OrderService orderService;
    private RecyclerView recycleView;
    TextView tvOrderAmount;
    Button btnNext,btnBack;
    List<Product> productList= new ArrayList<>();
    Utility utility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        utility = new Utility(this);

        recycleView = findViewById(R.id.recycleView);
        tvOrderAmount=findViewById(R.id.tvOrderAmount);
        btnNext = findViewById(R.id.btnNext);
        btnBack=findViewById(R.id.btnBack);

        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        loadBasketProduct();
    }
    @Override
    public void onClick(View view) {
        if (view==btnBack)
        {
            super.onBackPressed();
        }
        else if(view == btnNext) {
            // Todo Save Order
            Order order=new Order();
            Customer customer=utility.getCustomer();
            List<Product> basketProduct=utility.getBusketProduct();

            if (basketProduct.size()==0){
                utility.message("No data found for save");
                return;
            }
            if (customer==null){
                utility.message("No customer found for save");
                return;
            }
            order.setAccountID(customer.getAccountID());
            order.setUserID(utility.getUserID());
            order.setPassword(utility.getPassword());
            order.setOrderDetailInfos(utility.getBusketProduct());
            saveOrder(order);
        }
    }

    private void saveOrder(Order order) {
        orderService = ApiConfig.getApiClient().create(OrderService.class);
        Call<Order> call = orderService.saveInvoice(order);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful())
                {
                    String success= response.body().getSuccess();
                    if (success.equals("true")) {
                        // if saved--------------------------------------
                        final String orderNumber = response.body().getOrderNumber();
                        //todo after saved go to success page
                        Intent intent = new Intent(ReviewActivity.this, InvoiceActivity.class);
                        intent.putExtra("extra_orderNumber", orderNumber);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        // if not saved-----------------------------------------
                        utility.message(response.body().getResponseText());
                    }
                }
            }
            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                utility.message("Transaction error."+t.getMessage());
            }
        });
    }

    private void loadBasketProduct(){
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        productList=utility.getBusketProduct();

        //todo creating recyclerview adapter
        BasketAdapter adapter = new BasketAdapter(ReviewActivity.this, productList);

        //todo setting adapter to recyclerview
        recycleView.setAdapter(adapter);

        //todo load total amount
        tvOrderAmount.setText("৳: "+String.valueOf(utility.getBusketAmount()));

        // Todo event assign when click to adapter
        adapter.setOnRecycleViewItemClickListener(new BasketAdapter.OnRecycleViewItemClickListener() {
            @Override
            public void onRecycleViewItemClick(int position) {
                tvOrderAmount.setText("৳: "+String.valueOf(utility.getBusketAmount()));
            }
        });

    }
}