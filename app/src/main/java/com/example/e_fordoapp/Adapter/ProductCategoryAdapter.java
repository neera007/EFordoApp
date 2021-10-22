package com.example.e_fordoapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_fordoapp.ApiConfig.ApiConfig;
import com.example.e_fordoapp.Model.Product;
import com.example.e_fordoapp.Model.ProductCategory;
import com.example.e_fordoapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.MyViewHolder> {
    //this context we will use to inflate the layout
    private final Context mCtx;


    //we are storing all the products in a list
    private final List<ProductCategory> productCategory;

    //getting the context and product list with constructor
    public ProductCategoryAdapter(Context mCtx, List<ProductCategory> productCategory) {
        this.mCtx = mCtx;
        this.productCategory = productCategory;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_rec_product_category, null);
        return new MyViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return productCategory.size();
    }


    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(ProductCategoryAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ProductCategory productCategoryItem = productCategory.get(position);
        holder.tvProductName.setText(productCategoryItem.getItemGroupName());
        // Todo define Card View  click function---------------------
        holder.cardViewCatagoryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecycleViewItemClick(position);
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewCatagoryList;
        TextView tvProductName;

        public MyViewHolder(final View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            cardViewCatagoryList = itemView.findViewById(R.id.cardViewCatagoryList);
        }
    }

    ProductCategoryAdapter.OnRecycleViewItemClickListener listener;
    public void setOnRecycleViewItemClickListener(ProductCategoryAdapter.OnRecycleViewItemClickListener listener) {
        this.listener = listener;
    }
    public interface OnRecycleViewItemClickListener {
        void onRecycleViewItemClick(int position);
    }
}
