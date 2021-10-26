package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_fordoapp.Adapter.RequisitionHistoryAdapter;
import com.example.e_fordoapp.ApiConfig.ApiConfig;
import com.example.e_fordoapp.Model.Order;
import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Service.OrderService;
import com.example.e_fordoapp.Utility.Utility;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderListActivity extends AppCompatActivity implements View.OnClickListener{
    private OrderService orderService;
    private RecyclerView recycleView;
    EditText editTvOrderNumber;
    TextView tvDateFrom;
    Button btnBack,btnSearchOrderNumber,btnDateFrom;
    private DatePickerDialog.OnDateSetListener  mDateFromSetListener;
    List<Order> orderList= new ArrayList<>();
    String orderID="";
    String orderNumber="";
    String orderDate="";
    String orderAmount="";
    Utility utility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        utility = new Utility(this);

        recycleView = findViewById(R.id.recycleView);
        tvDateFrom=findViewById(R.id.tvDateFrom);
        editTvOrderNumber=findViewById(R.id.editTvOrderNumber);
        btnBack=findViewById(R.id.btnBack);
        btnSearchOrderNumber=findViewById(R.id.btnSearchOrderNumber);
        btnDateFrom=findViewById(R.id.btnDateFrom);

        Date date = new Date();
        CharSequence date_from  = DateFormat.format("dd/M/yyyy", date.getTime());
        tvDateFrom.setText(date_from);

        //todo ************ OnclickListener ***********
        btnBack.setOnClickListener(this);
        btnSearchOrderNumber.setOnClickListener(this);
        btnDateFrom.setOnClickListener(this);
        //tvDateFrom.setOnClickListener(this);

        loadOrderList(utility.getUserID(),utility.getPassword(),"0","0");

        mDateFromSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                DecimalFormat mFormat= new DecimalFormat("00");
                mFormat.format(Double.valueOf(year));

                String date = mFormat.format(Double.valueOf(day)) + "/" + mFormat.format(Double.valueOf(month)) + "/" + year;
                tvDateFrom.setText(date);
            }
        };
    }

    @Override
    public void onClick(View view) {
        if (view == btnBack ) {
            super.onBackPressed();
        }
        else if (view == btnSearchOrderNumber ) {
            // todo search button event
            String tvOrderNumber="0";
            if (editTvOrderNumber.getText().toString().length()>0)
                tvOrderNumber=editTvOrderNumber.getText().toString();
            loadOrderList(utility.getUserID(),utility.getPassword(),tvOrderNumber,tvDateFrom.getText().toString());
        }
        else if (view == btnDateFrom ) {
            showDatePicker();
        }

    }

    private void showDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        Date date = null;
        try {
            date = sdf. parse(tvDateFrom.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar. getInstance();
        cal.setTime(date);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(OrderListActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateFromSetListener, year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void loadOrderList(final String userID, final String password, String _orderNumber, String _orderDate) {
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        orderService = ApiConfig.getApiClient().create(OrderService.class);
        utility.showLoading();
        Call<List<Order>> call = orderService.getOrderHistory(userID,password,_orderNumber,_orderDate);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                utility.hideLoading();
                List<Order> orderListEmpty= new ArrayList<>();
                RequisitionHistoryAdapter adapter = new RequisitionHistoryAdapter(OrderListActivity.this, orderListEmpty);
                //todo initializing the stockItemList
                if (response.body()==null){
                    recycleView.setAdapter(adapter);
                    return;
                }
                orderList = new ArrayList<>(response.body());
                adapter = new RequisitionHistoryAdapter(OrderListActivity.this, orderList);

                //todo setting adapter to recyclerview
                recycleView.setAdapter(adapter);

                adapter.setOnRecycleViewItemClickListener(new RequisitionHistoryAdapter.OnRecycleViewItemClickListener() {
                    @Override
                    public void onRecycleViewItemClick(int position) {
                        orderID= orderList.get(position).getOrderID();
                        orderNumber= orderList.get(position).getOrderNumber();
                        orderDate= orderList.get(position).getOrderDate();
                        orderAmount= orderList.get(position).getOrderAmount();

                        Intent myIntent = new Intent(OrderListActivity.this, RequisitionDetailsActivity.class);
                        myIntent.putExtra("orderID", orderID);
                        myIntent.putExtra("orderNumber", orderNumber);
                        myIntent.putExtra("orderDate", orderDate);
                        myIntent.putExtra("orderAmount", orderAmount);
                        startActivity(myIntent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                utility.hideLoading();
                Toast.makeText(OrderListActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}