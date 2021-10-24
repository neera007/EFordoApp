package com.example.e_fordoapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_fordoapp.Model.Order;
import com.example.e_fordoapp.Model.ProductCategory;
import com.example.e_fordoapp.R;

import java.util.List;

public class RequisitionHistoryAdapter extends RecyclerView.Adapter<RequisitionHistoryAdapter.MyViewHolder> {
    //this context we will use to inflate the layout
    private final Context mCtx;


    //we are storing all the products in a list
    private final List<Order> order;

    //getting the context and product list with constructor
    public RequisitionHistoryAdapter(Context mCtx, List<Order> order) {
        this.mCtx = mCtx;
        this.order = order;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_rec_requsition_list, null);
        return new MyViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Order orderItem = order.get(position);
        holder.tvOrderNumber.setText(orderItem.getOrderNumber());
        holder.tvOrderDate.setText(orderItem.getOrderDate());
        holder.tvAccountName.setText(orderItem.getAccountName());
        holder.tvStatus.setText(orderItem.getStatus());
        holder.tvOrderAmount.setText("৳: "+orderItem.getOrderAmount());
        // Todo define Card View  click function---------------------
        holder.cardViewOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecycleViewItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return order.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderNumber, tvOrderDate,tvAccountName,tvStatus,tvOrderAmount;
        CardView cardViewOrderList;

        public MyViewHolder(final View itemView) {
            super(itemView);
            tvOrderNumber = itemView.findViewById(R.id.tvOrderNumber);
            tvOrderDate = itemView.findViewById(R.id.tvOrderDate);
            tvAccountName = itemView.findViewById(R.id.tvAccountName);
            tvOrderDate = itemView.findViewById(R.id.tvOrderDate);
            tvAccountName = itemView.findViewById(R.id.tvAccountName);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvOrderAmount = itemView.findViewById(R.id.tvOrderAmount);
            cardViewOrderList= itemView.findViewById(R.id.cardViewOrderList);
        }
    }

    RequisitionHistoryAdapter.OnRecycleViewItemClickListener listener;
    public void setOnRecycleViewItemClickListener(RequisitionHistoryAdapter.OnRecycleViewItemClickListener listener) {
        this.listener = listener;
    }
    public interface OnRecycleViewItemClickListener {
        void onRecycleViewItemClick(int position);
    }

}
