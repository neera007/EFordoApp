package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.e_fordoapp.Adapter.BasketAdapter;
import com.example.e_fordoapp.Adapter.ProductInfoAdapter;
import com.example.e_fordoapp.ApiConfig.ApiConfig;
import com.example.e_fordoapp.Model.Product;
import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Service.ProductService;
import com.example.e_fordoapp.Utility.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recycleView;
    Button btnNext;
    List<Product> productList= new ArrayList<>();
    Utility utility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        utility = new Utility(this);
        recycleView = findViewById(R.id.recycleView);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener((View.OnClickListener) this);
        loadBasketProduct();
    }

    @Override
    public void onClick(View view) {
        if(view == btnNext) {
            startActivity(new Intent(getApplicationContext(), InvoiceActivity.class));
        }
    }

    private void loadBasketProduct(){
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        productList=utility.getBusketProduct();

        //todo creating recyclerview adapter
        BasketAdapter adapter = new BasketAdapter(ReviewActivity.this, productList);

        //todo setting adapter to recyclerview
        recycleView.setAdapter(adapter);

        // Todo event assign when click to adapter
        adapter.setOnRecycleViewItemClickListener(new BasketAdapter.OnRecycleViewItemClickListener() {
            @Override
            public void onRecycleViewItemClick(int position) {

            }
        });

    }
}