package com.example.e_fordoapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.e_fordoapp.Model.ProductCategory;
import com.example.e_fordoapp.R;

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

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //getting the shelf stock of specified position
        ProductCategory productCategoryItem = productCategory.get(position);
        holder.tvProductName.setText(productCategoryItem.getRootItemGroupID());
//        holder.tvShelf.setText(productCategoryItem.getItemGroupName());
//        holder.tvStockQty.setText(productCategoryItem.getDescription());
//        holder.tvStockQty.setText(productCategoryItem.getActivity());
    }

    @Override
    public int getItemCount() {
        return productCategory.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvProductName, tvShelf,tvStockQty;

        public MyViewHolder(final View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
        }
    }
}
