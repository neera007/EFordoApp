package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.e_fordoapp.Adapter.BasketAdapter;
import com.example.e_fordoapp.Model.Product;
import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class InvoiceActivity extends AppCompatActivity implements View.OnClickListener{
    Utility utility;
    private TextView tvSummaryTitle,tvOrderAmount;
    private RecyclerView recycleView;
    private Button btnGoToHome,btnPrintInvoice;
    List<Product> busketItemList = new ArrayList<>();
    String orderNumber;

/*    private int portType = 0;
    private String logicalName = "SPP-R310";
    private static BixolonPrinter bxlPrinter = null;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        utility = new Utility(this);
        btnGoToHome = this.findViewById(R.id.btnGoToHome);
        btnPrintInvoice=this.findViewById(R.id.btnPrintInvoice);

        //todo textview
        tvSummaryTitle = findViewById(R.id.tvSummaryTitle);
        tvOrderAmount=findViewById(R.id.tvOrderAmount);

        recycleView = findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);

        btnGoToHome.setOnClickListener(this);
        btnPrintInvoice.setOnClickListener(this);

        loadBasketData();

    }

    @Override
    public void onClick(View view) {
        if(view == btnGoToHome) {
            Intent intent = new Intent(InvoiceActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }
        else if(view == btnPrintInvoice) {
            utility.message("Connect the printer");
        }
    }

    private void loadBasketData() {
        //todo get & set invoice number
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            orderNumber = extras.getString("extra_orderNumber");
        }
        tvSummaryTitle.setText("SUMMARY (Order Number: "+orderNumber+")");
        busketItemList=utility.getBusketProduct();

        //todo creating recyclerview adapter
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        BasketAdapter adapter = new BasketAdapter(InvoiceActivity.this, busketItemList,false);
        recycleView.setAdapter(adapter);

        //todo load total amount
        tvOrderAmount.setText("৳: "+String.valueOf(utility.getBusketAmount()));

        // Todo event assign when click to adapter
        adapter.setOnRecycleViewItemClickListener(new BasketAdapter.OnRecycleViewItemClickListener() {
            @Override
            public void onRecycleViewItemClick(int position) {

            }
        });

        utility.clearBusket();
        utility.clearCustomer();
    }


}