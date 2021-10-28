package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.e_fordoapp.Adapter.BasketAdapter;
import com.example.e_fordoapp.Model.BluetoothConn;
import com.example.e_fordoapp.Model.Customer;
import com.example.e_fordoapp.Model.Order;
import com.example.e_fordoapp.Model.Product;
import com.example.e_fordoapp.PrinterControl.BixolonPrinter;
import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class OrderCheckoutActivity extends AppCompatActivity implements View.OnClickListener{
    Utility utility;
    private TextView tvSummaryTitle,tvOrderAmount;
    private RecyclerView recycleView;
    private Button btnGoToHome,btnPrintInvoice,btnMakeConnection;
    List<Product> busketItemList = new ArrayList<>();
    String orderNumber;


//    private String logicalName = "";   //74:F0:7D:EF:86:A7
//    private String address = "";  //SPP-R310

    private String logicalName = "74:F0:7D:EF:86:A7";
    private String address = "SPP-R310 ";

    private static BixolonPrinter bxlPrinter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_checkout);

        utility = new Utility(this);
        btnGoToHome = this.findViewById(R.id.btnGoToHome);
        btnPrintInvoice=this.findViewById(R.id.btnPrintInvoice);
        btnMakeConnection=this.findViewById(R.id.btnMakeConnection);

        //todo textview
        tvSummaryTitle = findViewById(R.id.tvSummaryTitle);
        tvOrderAmount=findViewById(R.id.tvOrderAmount);

        recycleView = findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);

        btnGoToHome.setOnClickListener(this);
        btnPrintInvoice.setOnClickListener(this);
        btnMakeConnection.setOnClickListener(this);
        loadBasketData();
        bxlPrinter = new BixolonPrinter(getApplicationContext());
        //todo get utility shared value ----------------------------
        BluetoothConn bluetoothConn =utility.getBluetoothConn();
        logicalName =  bluetoothConn.getLogicalName();
        address =   bluetoothConn.getAddress();
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//       // utility.message(logicalName + "  " + address);
//      //  Toast.makeText(OrderCheckoutActivity.this, "GOT :" +address + "  " + logicalName, Toast.LENGTH_SHORT).show();
//    }
    @Override
    public void onClick(View view) {
        if(view == btnGoToHome) {
            Intent intent = new Intent(OrderCheckoutActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }
        if(view == btnMakeConnection) {
            //todo
            if (getPrinterInstance().printerOpen(0, logicalName, address, true)) {
               utility.message("Connected Successfully");
            } else {
                Toast.makeText(getApplicationContext(), "Connection failed", Toast.LENGTH_LONG).show();
                startActivity(new Intent(OrderCheckoutActivity.this, BlutoothConnectionctivity.class));
            }
        }

        else if(view == btnPrintInvoice) {
            if (logicalName != "" || address != "") {
                try {
                    memoPrint();
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Make connection first!", Toast.LENGTH_LONG).show();
            }
        }

    }


    public static BixolonPrinter getPrinterInstance()
    {
        return bxlPrinter;
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
        BasketAdapter adapter = new BasketAdapter(OrderCheckoutActivity.this, busketItemList,false);
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

    public void memoPrint() {
        getPrinterInstance().beginTransactionPrint();

        String memo_data="                    -- e-Fordo --                    \n" +
                "User: SYSTEM, Cr. No: 001\n" +
                "Date: 6/24/2021 12:00:00 AM\n" +
                "Oredr No: 2021102716633\n" +
                "Total Number Of Product: 1\n" +
                "Total Number Of Product Quantity : 1.0\n" +
                "------------------------------------------------\n" +
                "Sl  Product          Price       Qty     Amount\n" +
                "                                          (Tk.)\n" +
                "1. Parachute Coconut oil 95ml\n" +
                "   Code:1000013      75.00      1.00      75.00\n" +
                "------------------------------------------------\n" +
                "Total Amount                              75.00\n" +

                "------------------------------------------------\n" +
                "            Copyright Proxima Soft        \n\n\n";
        getPrinterInstance().printText(memo_data, BixolonPrinter.ALIGNMENT_LEFT, 0, 0);
        getPrinterInstance().endTransactionPrint();
    }
}