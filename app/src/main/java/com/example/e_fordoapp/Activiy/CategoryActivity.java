package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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
    private TextView tvTotalQty;
    private RecyclerView recycleView;
    String UserID,Password;

    List<ProductCategory> productCategoryItemList= new ArrayList<>();
    private ProductCategoryService productCategoryService;
    String categoryCode;
    Utility utility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recycleView = findViewById(R.id.recycleView);
        UserID="faysal";
        Password="123";
        loadCategoryLit(UserID,Password);
    }

    @Override
    public void onClick(View view) {

    }

    private void loadCategoryLit(final String user_id, final String password){  //item group
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
       // utility.showLoading();
        productCategoryService = ApiConfig.getApiClient().create(ProductCategoryService.class);
        Call<List<ProductCategory>> call = productCategoryService.getProductCategory(user_id,password);
        call.enqueue(new Callback<List<ProductCategory>>() {
            @Override
            public void onResponse(Call<List<ProductCategory>> call, Response<List<ProductCategory>> response) {
                utility.hideLoading();
                if (response.body()==null)
                {
                    return;
                }

                productCategoryItemList = new ArrayList<>(response.body());

                //creating recyclerview adapter
                ProductCategoryAdapter adapter = new ProductCategoryAdapter(CategoryActivity.this, productCategoryItemList);

                //setting adapter to recyclerview
                recycleView.setAdapter(adapter);

                //set total qty
//                int stockQty=0;
//                for (int i = 0; i < productCategoryItemList.size(); i++) {
//                    stockQty=stockQty+Integer.valueOf(productCategoryItemList.get(i).getStockQty());
//                }
//                tvTotalQty.setText(String.valueOf(stockQty));
            }

            @Override
            public void onFailure(Call<List<ProductCategory>> call, Throwable t) {
                //progressBar.setVisibility(View.INVISIBLE);
              //  utility.hideLoading();
                Toast.makeText(CategoryActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}