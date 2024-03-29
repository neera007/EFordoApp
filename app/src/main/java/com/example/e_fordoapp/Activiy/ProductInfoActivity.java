package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_fordoapp.Adapter.BasketAdapter;
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
    private TextView tvReviewPushNotification,tvCartAmount;
    private ImageButton imgBtnReview;
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

        recycleView = findViewById(R.id.recycleView);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);
        tvCategoryName = findViewById(R.id.tvCategoryName);
        tvReviewPushNotification=findViewById(R.id.tvReviewPushNotification);
        tvCartAmount=findViewById(R.id.tvCartAmount);
        imgBtnReview=findViewById(R.id.imgBtnReview);

        //todo ************ OnclickListener ***********
        btnNext.setOnClickListener((View.OnClickListener) this);
        btnBack.setOnClickListener((View.OnClickListener) this);
        imgBtnReview.setOnClickListener(this);

        //todo*** get and set values
        Intent intent = getIntent();
        strnCategoryID = intent.getStringExtra("categoryID");
        strnCategoryName = intent.getStringExtra("categoryName");

        tvCategoryName.setText("Caterory : "+ strnCategoryName); //todo set Cat name in next activity

        // todo clear control
        tvCartAmount.setText("৳ 0");
        tvReviewPushNotification.setText("0");

        // todo load cart information
        loadCartSummary();

        loadProductList(utility.getUserID(),utility.getPassword(),strnCategoryID);

    }

    private void loadCartSummary() {
        List<Product> basketList= new ArrayList<>();
        basketList=utility.getBusketProduct();
        if (basketList.size()==0) {
            tvReviewPushNotification.setText("0");
            tvCartAmount.setText("৳ 0");
        }
        else {
            tvReviewPushNotification.setText(String.valueOf(basketList.size()));
            tvCartAmount.setText("৳ " +String.valueOf(utility.getBusketAmount()));
        }
    }

    @Override
    public void onClick(View view) {
        if(view == btnNext) {
            startActivity(new Intent(getApplicationContext(), BasketActivity.class));
        }
        else if(view == btnBack) {
            startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
        }
        else if (view == imgBtnReview ) {
            startActivity(new Intent(getApplicationContext(),BasketActivity.class));
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
                BasketAdapter adapter = new BasketAdapter(ProductInfoActivity.this, productItemList,true);

                //todo setting adapter to recyclerview
                recycleView.setAdapter(adapter);

                //tvOrderAmount.setText("৳: "+String.valueOf(utility.getBusketAmount()));

                // Todo event assign when click to adapter
                adapter.setOnRecycleViewItemClickListener(new BasketAdapter.OnRecycleViewItemClickListener() {
                    @Override
                    public void onRecycleViewItemClick(int position) {
                        //tvOrderAmount.setText("৳: "+String.valueOf(utility.getBusketAmount()));
                        loadCartSummary();
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