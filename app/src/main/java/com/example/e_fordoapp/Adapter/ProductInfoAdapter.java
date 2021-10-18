package com.example.e_fordoapp.Adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.e_fordoapp.Model.Product;
import com.example.e_fordoapp.Model.ProductCategory;
import com.example.e_fordoapp.R;

import java.util.List;

public class ProductInfoAdapter extends RecyclerView.Adapter<ProductInfoAdapter.MyViewHolder> {
    //this context we will use to inflate the layout
    private final Context mCtx;


    //we are storing all the products in a list
    private final List<Product> product;

    //getting the context and product list with constructor
    public ProductInfoAdapter(Context mCtx, List<Product> product) {
        this.mCtx = mCtx;
        this.product = product;
    }

    @Override
    public ProductInfoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_rec_product_info, null);
        return new ProductInfoAdapter.MyViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(ProductInfoAdapter.MyViewHolder holder, int position) {
        //getting the shelf stock of specified position
        Product ProductItem = product.get(position);
        holder.tvProductCode.setText(ProductItem.getItemCode());
        holder.tvProductName.setText(ProductItem.getItemName());
        holder.tvUnit.setText(ProductItem.getUnit());
        holder.tvPrice.setText(ProductItem.getPrice());
      //  holder.tvProductName.setText(ProductItem.getMaxQty());
    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvProductName, tvProductCode,tvUnit,tvPrice;

        public MyViewHolder(final View itemView) {
            super(itemView);
            tvProductCode = itemView.findViewById(R.id.tvProductCode);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvUnit = itemView.findViewById(R.id.tvUnit);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}

