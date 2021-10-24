package com.example.e_fordoapp.Adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.e_fordoapp.ApiConfig.ApiConfig;
import com.example.e_fordoapp.Model.Product;
import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Utility.Utility;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.MyViewHolder> {
    //this context we will use to inflate the layout
    private final Context mCtx;
    Utility utility;

    //we are storing all the products in a list
    private final List<Product> productList;

    //getting the context and product list with constructor
    public BasketAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
        utility = new Utility(mCtx);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_rec_busket_product_info, null);
        return new MyViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product productItem = productList.get(position);
        double salesAmount=0.00;
        salesAmount=Double.valueOf(productItem.getItemQty())*Double.valueOf(productItem.getPrice());

        holder.tvProductCode.setText(productItem.getItemCode());
        holder.tvProductName.setText(productItem.getItemName());
        holder.tvUnit.setText(productItem.getUnit());
        holder.tvPrice.setText(productItem.getPrice());
        holder.tvItemQty.setText(String.valueOf(productItem.getItemQty()));
        holder.tvItemQtyBelow.setText(String.valueOf(productItem.getItemQty()));
        holder.tvTotalAmount.setText(String.format("%.2f", salesAmount));
        if (productItem.getItemImageName().isEmpty()==false) {
            String ImageURL = ApiConfig.getApiClient().baseUrl()+"/images/" + productItem.getItemImageName();
            Picasso.get().load(ImageURL).into(holder.imageView);
            Picasso.get().load(ImageURL).resize(60, 60);
        }

        // Todo define minus button function---------------------
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // calculate qty and sales amount
                double salesAmount=0.00;
                Integer Qty=0;
                if (productItem.getItemQty()>0)
                    Qty=productItem.getItemQty()-1;//decrease 1 qty from the current qty

                productItem.setItemQty(Qty);//update the qty
                salesAmount=Double.valueOf(Qty)*Double.valueOf(productItem.getPrice());//calculate sales amount

                // update the holder and notify the adapter about change
                holder.tvItemQty.setText(String.valueOf(Qty));
                //holder.tvAmount.setText(String.format("%.2f", salesAmount));
                notifyItemRangeChanged(position,getItemCount());

                //update shared preferances
                utility.setBusketProduct(productItem);

                listener.onRecycleViewItemClick(position);
            }
        });

        // Todo define plus button function---------------------
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // calculate qty and sales amount
                double salesAmount=0.00;
                Integer Qty=productItem.getItemQty()+1;//increase 1 qty with the current qty

                //Stock Validation added
//                if (Integer.parseInt(productItem.getDisplayQty())<Qty)
//                    Qty=productItem.getQty();

                productItem.setItemQty(Qty);//update the qty
                //salesAmount=Double.valueOf(Qty)*Double.valueOf(productItem.getPrice());//calculate sales amount

                // update the holder and notify the adapter about change
                holder.tvItemQty.setText(String.valueOf(Qty));
                //holder.tvAmount.setText(String.format("%.2f", salesAmount));
                notifyItemRangeChanged(position,getItemCount());

                //update shared preferances
                utility.setBusketProduct(productItem);

                listener.onRecycleViewItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName, tvProductCode,tvUnit,tvPrice,tvItemQty,tvItemQtyBelow,tvTotalAmount;
        Button btnMinus,btnPlus;
        ImageView imageView;

        public MyViewHolder(final View itemView) {
            super(itemView);
            tvProductCode = itemView.findViewById(R.id.tvProductCode);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvUnit = itemView.findViewById(R.id.tvUnit);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imageView=itemView.findViewById(R.id.imageView);
            tvItemQty=itemView.findViewById(R.id.tvItemQty);
            tvTotalAmount = itemView.findViewById(R.id.tvTotalAmount);
            tvItemQtyBelow=itemView.findViewById(R.id.tvItemQtyBelow);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);
        }
    }

    OnRecycleViewItemClickListener listener;
    public void setOnRecycleViewItemClickListener(OnRecycleViewItemClickListener listener) {
        this.listener = listener;
    }
    public interface OnRecycleViewItemClickListener {
        void onRecycleViewItemClick(int position);
    }
}

