package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

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
import com.example.e_fordoapp.Model.Order;
import com.example.e_fordoapp.Model.Product;
import com.example.e_fordoapp.PrinterControl.BixolonPrinter;
import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Utility.Utility;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class OrderCheckoutActivity extends AppCompatActivity implements View.OnClickListener{
    Utility utility;
    private TextView tvSummaryTitle,tvOrderAmount;
    private RecyclerView recycleView;
    private Button btnGoToHome,btnPrintInvoice;
    List<Product> busketItemList = new ArrayList<>();
    String orderNumber;

//    private int portType = 0;
//    private String logicalName = "SPP-R310";
//   // private String address = "74:F0:7D:A7:C0:A9";
//    private String address = "74:F0:7D:EF:86:A7";

    //todo printer -------
    private static BixolonPrinter bxlPrinter = null;

    private static Fragment currentFragment;
    private static int currentPosition = 0;

    //todo _______________


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_checkout);

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

        final int ANDROID_NOUGAT = 24;
        if(Build.VERSION.SDK_INT >= ANDROID_NOUGAT)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        bxlPrinter = new BixolonPrinter(getApplicationContext());

        Thread.setDefaultUncaughtExceptionHandler(new AppUncaughtExceptionHandler());

        startConnectionActivity();
    }
    public void startConnectionActivity()
    {
        Intent intent = new Intent(getApplicationContext(), BlutoothConnectionctivity.class);
        startActivity(intent);
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        getPrinterInstance().printerClose();
    }
    public static BixolonPrinter getPrinterInstance()
    {
        return bxlPrinter;
    }

    @Override
    public void onClick(View view) {
        if(view == btnGoToHome) {
            Intent intent = new Intent(OrderCheckoutActivity.this, BlutoothConnectionctivity.class);
            startActivity(intent);
            finish();
        }

        else if (view == btnPrintInvoice) {
            // transactionPrint();
//            try {
//                memoPrint();
//            }
//            catch (Exception ex)
//            {
//                Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
//            }
        }
    }

 //todo printer section*****************




    public class AppUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread thread, final Throwable ex) {
            ex.printStackTrace();

            Process.killProcess(Process.myPid());
            System.exit(10);
        }
    }
    //todo ************************

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

//    public void transactionPrint() {
//        getPrinterInstance().beginTransactionPrint();
//        getPrinterInstance().printText("\n" + "--e-Fordo --", BixolonPrinter.ALIGNMENT_CENTER, 0, 0);
//        getPrinterInstance().printText("\n\n"+"Queue #123456789012", BixolonPrinter.ALIGNMENT_LEFT, 0, 0);
//        getPrinterInstance().printText("\n"+"Date:07/09/2021", BixolonPrinter.ALIGNMENT_LEFT, 0, 0);
//        getPrinterInstance().printText("\n"+"Time:3:28 PM", BixolonPrinter.ALIGNMENT_LEFT, 0, 0);
//        //  getPrinterInstance().printBarcode("123456789012", getPrinterInstance().printText("2222"), 3, 120, 2, getPrinterInstance().BARCODE_HRI_BELOW);
//        getPrinterInstance().printText("\n"+"", BixolonPrinter.ALIGNMENT_LEFT, 0, 0);
//        getPrinterInstance().endTransactionPrint();
//    }
    public void memoPrint() {
        getPrinterInstance().beginTransactionPrint();
        String memo_data="                    e-Fordo                    \n" +
                "User: Neera, Cr. No: 001\n" +
                "Date: 6/24/2021 12:00:00 AM\n" +
                "Receipt No: NS001042100425\n" +
                "Total Number Of Product: 1\n" +
                "Total Number Of Product Quantity : 1.0\n" +
                "------------------------------------------------\n" +
                "Sl  Product          Price       Qty     Amount\n" +
                "                                          (Tk.)\n" +
                "1. oil\n" +
                "   1000001     80.00      1.00      80.00\n" +
                "------------------------------------------------\n" +
                "Sub Total                                 80.00\n" +
                "(-)Less Discount                           0.00\n" +
                "(+)Add Vat                                 0.00\n" +
                "------------------------------------------------\n" +
                "Net Payable                               80.00\n" +
                "------------------------------------------------\n" +
                "            Copyright Proxima Soft        \n";
        getPrinterInstance().printText(memo_data, BixolonPrinter.ALIGNMENT_LEFT, 0, 0);
        getPrinterInstance().endTransactionPrint();
    }
}