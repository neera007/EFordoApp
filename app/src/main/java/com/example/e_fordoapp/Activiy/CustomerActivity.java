package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_fordoapp.Adapter.ProductCategoryAdapter;
import com.example.e_fordoapp.ApiConfig.ApiConfig;
import com.example.e_fordoapp.Model.Customer;
import com.example.e_fordoapp.Model.CustomerAutoComplete;
import com.example.e_fordoapp.Model.Product;
import com.example.e_fordoapp.Model.ProductCategory;
import com.example.e_fordoapp.Model.UserInfo;
import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Service.CustomerService;
import com.example.e_fordoapp.Service.LoginService;
import com.example.e_fordoapp.Service.ProductCategoryService;
import com.example.e_fordoapp.Utility.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnBack,btnReset,btnNext,btnUserSearchByPIN;
    private EditText editTvUserPIN,editTvUserName,editTvUserInfo;
    private TextView tvReviewPushNotification,tvCartAmount;
    private ImageButton imgBtnReview;
    private AutoCompleteTextView autoCompleteTextView;
    private CustomerService customerService;
    Utility utility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        utility = new Utility(this);
        btnUserSearchByPIN=findViewById(R.id.btnUserSearchByPIN);
        btnBack = findViewById(R.id.btnBack);
        btnReset = findViewById(R.id.btnReset);
        btnNext = findViewById(R.id.btnNext);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        tvReviewPushNotification=findViewById(R.id.tvReviewPushNotification);
        tvCartAmount=findViewById(R.id.tvCartAmount);
        imgBtnReview=findViewById(R.id.imgBtnReview);

        editTvUserPIN = findViewById(R.id.editTvUserPIN);
        editTvUserName = findViewById(R.id.editTvUserName);
        editTvUserInfo = findViewById(R.id.editTvUserInfo); //textarea

        //todo disable editing
        editTvUserPIN.setFocusable(false);
        editTvUserName.setFocusable(false);
        editTvUserInfo.setFocusable(false);

        //todo ************ OnclickListener ***********
        btnUserSearchByPIN.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        imgBtnReview.setOnClickListener(this);

        // todo clear control
        tvCartAmount.setText("৳ 0");
        tvReviewPushNotification.setText("0");

        // todo load cart information
        List<Product> basketList= new ArrayList<>();
        basketList=utility.getBusketProduct();
        if (basketList.size()==0)
            tvReviewPushNotification.setText("0");
        else {
            tvReviewPushNotification.setText(String.valueOf(basketList.size()));
            tvCartAmount.setText("৳ " +String.valueOf(utility.getBusketAmount()));
        }

        editTvUserPIN.requestFocus();
        loadCustomerAutoCompleteList();
    }

    @Override
    public void onClick(View view) {
        if (view == btnBack ) {
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
        }
        else if (view == btnReset ) {
            autoCompleteTextView.setText("");
            editTvUserPIN.setText("");
            editTvUserName.setText("");
            editTvUserInfo.setText("");
            editTvUserPIN.requestFocus();
        }
        else if (view == btnNext ) {
            // todo check validation
            if (editTvUserName.getText().length()<2)
            {
                utility.message("Customer selection required.");
                return;
            }

            Customer customer=utility.getCustomer();
            if (Integer.valueOf(customer.getLimit())<1)
            {
                utility.message("Credit limit is over");
                return;
            }

            if (Integer.valueOf(customer.getInvCount())<1)
            {
                utility.message("Invoice limit is over");
                return;
            }

            List<Product> productItemList= new ArrayList<>();
            productItemList=utility.getBusketProduct();
            if (productItemList.size()>0)
            {
                String message="Some products are in the basket. \nDo you want to clear basket?";
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE: //Yes button clicked
                                utility.clearBusket();
                                startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name)
                        .setIcon(R.drawable.ic_message_title)
                        .setMessage(message).setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
            else
                startActivity(new Intent(getApplicationContext(), CategoryActivity.class));

        }
        else if (view == btnUserSearchByPIN ) {
            // todo validation check
            if(autoCompleteTextView.getText().toString().length() == 0){
                utility.message("Please enter PIN");
                return;
            }
            loadCustomerInfo(autoCompleteTextView.getText().toString());
        }
        else if (view == imgBtnReview ) {
            startActivity(new Intent(getApplicationContext(),BasketActivity.class));
        }
    }

    private void loadCustomerInfo(final String accountNumber) {
        utility.showLoading();
        customerService = ApiConfig.getApiClient().create(CustomerService.class);
        Call call = customerService.getCustomerByKeyword(utility.getUserID(),utility.getPassword(),accountNumber);
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful()) {
                    utility.hideLoading();
                    Customer customer = response.body();
                    if (customer.getAccountName()==null) {
                        utility.message("Customer not found");
                        editTvUserPIN.setText("");
                        editTvUserName.setText("");
                        editTvUserInfo.setText("");
                        return;
                    }
                    //set data
                    String invoiceLimit="";
                    /*if (customer.getInvCount().equals("0")==false)
                    {
                        invoiceLimit="Invoice Limit :"+customer.getInvCount();
                    }*/
                    invoiceLimit="Invoice Limit :"+customer.getInvCount();
                    editTvUserPIN.setText(customer.getAccountNumber());
                    editTvUserName.setText(customer.getAccountNumber()+"-"+customer.getAccountName());
                    editTvUserInfo.setText("Credit Limit: "+customer.getLimit()+"\n"
                                            +invoiceLimit+"\n"
                                            +"Division: "+customer.getDivision()+"\n"
                                            +"Department: "+customer.getDepartment()+"\n"
                                            +"Sub Department: "+customer.getSubDepartment()+"\n"
                                            +"Designation: "+customer.getDesignation()+"\n"
                                            +customer.getDescription());
                    utility.setCustomer(customer);
                    utility.hideKeyboard(CustomerActivity.this);
                    autoCompleteTextView.setText("");
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                utility.hideLoading();
                Toast.makeText(CustomerActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCustomerAutoCompleteList(){
            ArrayAdapter<CustomerAutoComplete> adapter = new ArrayAdapter<CustomerAutoComplete>(CustomerActivity.this, android.R.layout.simple_spinner_dropdown_item, Utility.allCustomerList);  // simple_dropdown_item_1line
            //autoCompleteTextView.setThreshold(4);
            autoCompleteTextView.setAdapter(adapter);
    }
}