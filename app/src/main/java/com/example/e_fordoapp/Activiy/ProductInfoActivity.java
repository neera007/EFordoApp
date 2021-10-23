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

import com.example.e_fordoapp.Adapter.ProductCategoryAdapter;
import com.example.e_fordoapp.Adapter.ProductInfoAdapter;
import com.example.e_fordoapp.ApiConfig.ApiConfig;
import com.example.e_fordoapp.Model.Product;
import com.example.e_fordoapp.Model.ProductCategory;
import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Service.ProductCategoryService;
import com.example.e_fordoapp.Service.ProductService;
import com.example.e_fordoapp.Utility.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvTotalQty,tvCategoryName;
    private RecyclerView recycleView;
    private ProductService productService;
    Button btnNext,btnBack;
    String strnCategoryID="";
    String strnCategoryName="";
    List<Product> productItemList= new ArrayList<>();
    Utility utility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        utility = new Utility(this);
        //todo*** get and set values from audit activity
        Intent intent = getIntent();
        strnCategoryID = intent.getStringExtra("categoryID");
        strnCategoryName = intent.getStringExtra("categoryName");



        recycleView = findViewById(R.id.recycleView);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);
        tvCategoryName = findViewById(R.id.tvCategoryName);

        tvCategoryName.setText("Caterory : "+ strnCategoryName); //todo set Cat name in next activity


        loadProductList(utility.getUserID(),utility.getPassword(),strnCategoryID);


        btnNext.setOnClickListener((View.OnClickListener) this);
        btnBack.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnNext) {
            startActivity(new Intent(getApplicationContext(), ReviewActivity.class));
        }
        else if(view == btnBack) {
            startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
        }
    }

    private void loadProductList(final String user_id, final String password, final String itemGroup){  //item group

        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        utility.showLoading();
        productService = ApiConfig.getApiClient().create(ProductService.class);
        Call<List<Product>> call = productService.getProductByCategoryID(user_id,password,itemGroup);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                utility.hideLoading();
                //initializing the stockItemList
                if (response.body()==null){
                    return;
                }
                productItemList = new ArrayList<>(response.body());

                //todo creating recyclerview adapter
                ProductInfoAdapter adapter = new ProductInfoAdapter(ProductInfoActivity.this, productItemList);

                //todo setting adapter to recyclerview
                recycleView.setAdapter(adapter);

                // Todo event assign when click to adapter
                adapter.setOnRecycleViewItemClickListener(new ProductInfoAdapter.OnRecycleViewItemClickListener() {
                    @Override
                    public void onRecycleViewItemClick(int position) {
                        //utility.message(String.valueOf(busketItemList.get(position).getProductCode()));
                        //utility.calculateBusketAmount(0.00);
                        //tvTotalPrice.setText(String.format("%.2f", utility.getBusketAmount()));
                        //tvTotalQty.setText(String.format("%.2f", utility.getBusketTotalQty()));
//                        String noOfItems = "0";
//                        if (utility.getNoOfItems() > 1)
//                            tvNoOfItem.setText("(" + utility.getNoOfItems() + " items)");
//                        else
//                            tvNoOfItem.setText("(" + utility.getNoOfItems() + " item)");
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                utility.hideLoading();
                Toast.makeText(ProductInfoActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}