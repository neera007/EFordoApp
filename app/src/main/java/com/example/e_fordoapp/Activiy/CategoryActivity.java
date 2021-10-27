package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.e_fordoapp.Adapter.ProductCategoryAdapter;
import com.example.e_fordoapp.ApiConfig.ApiConfig;
import com.example.e_fordoapp.Model.ProductCategory;
import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Service.ProductCategoryService;
import com.example.e_fordoapp.Utility.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recycleView;
    Button btnBack;
    String strnCategoryID="";
    String strnCategoryName="";
    List<ProductCategory> productCategoryItemList= new ArrayList<>();
    private ProductCategoryService productCategoryService;
    String categoryCode;
    Utility utility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        utility = new Utility(this);
        recycleView = findViewById(R.id.recycleView);
        btnBack = findViewById(R.id.btnBack);


        btnBack.setOnClickListener(this);
        loadProductList(utility.getUserID(),utility.getPassword());
    }

    @Override
    public void onClick(View view) {
//        if(view==btnNext)
//        {
//            //todo pass value from activity to activity
//            Intent myIntent = new Intent(this, ProductInfoActivity.class);
//            myIntent.putExtra("categoryID", strnCategoryID);
//            myIntent.putExtra("categoryName", strnCategoryName);
//            startActivity(myIntent); // todo goto then next activity
//        }
        if(view==btnBack)
        {
            startActivity(new Intent(getApplicationContext(), CustomerActivity.class));
        }
    }

    private void loadProductList(final String user_id, final String password){  //item group
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        productCategoryService = ApiConfig.getApiClient().create(ProductCategoryService.class);
        Call<List<ProductCategory>> call = productCategoryService.getProductCategory(user_id,password);
        call.enqueue(new Callback<List<ProductCategory>>() {
            @Override

            public void onResponse(Call<List<ProductCategory>> call, Response<List<ProductCategory>> response) {

                //todo initializing the stockItemList
                if (response.body()==null){
                    return;
                }
                productCategoryItemList = new ArrayList<>(response.body());
                ProductCategoryAdapter adapter = new ProductCategoryAdapter(CategoryActivity.this, productCategoryItemList);

                //todo setting adapter to recyclerview
                recycleView.setAdapter(adapter);
                // Todo event assign when click to adapter
                adapter.setOnRecycleViewItemClickListener(new ProductCategoryAdapter.OnRecycleViewItemClickListener() {
                    @Override
                    public void onRecycleViewItemClick(int position) {
                        strnCategoryID= productCategoryItemList.get(position).getItemGroupID();
                        strnCategoryName= productCategoryItemList.get(position).getItemGroupName();
                       // String toast_msg= "Category : " + productCategoryItemList.get(position).getItemGroupName() + " is selected !" ;
                       // Toast.makeText(CategoryActivity.this,toast_msg, Toast.LENGTH_SHORT).show();

                        Intent myIntent = new Intent(CategoryActivity.this, ProductInfoActivity.class);
                        myIntent.putExtra("categoryID", strnCategoryID);
                        myIntent.putExtra("categoryName", strnCategoryName);
                        startActivity(myIntent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<ProductCategory>> call, Throwable t) {
                Toast.makeText(CategoryActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}