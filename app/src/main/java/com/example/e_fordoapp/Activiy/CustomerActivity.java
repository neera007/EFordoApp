package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_fordoapp.Adapter.ProductCategoryAdapter;
import com.example.e_fordoapp.ApiConfig.ApiConfig;
import com.example.e_fordoapp.Model.Customer;
import com.example.e_fordoapp.Model.ProductCategory;
import com.example.e_fordoapp.Model.UserInfo;
import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Service.CustomerService;
import com.example.e_fordoapp.Service.LoginService;
import com.example.e_fordoapp.Utility.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnBack,btnReset,btnNext,btnUserSearchByPIN;
    private EditText editTvUserSearchByPIN,editTvUserPIN,editTvUserName,editTvUserInfo;
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

        editTvUserSearchByPIN = findViewById(R.id.editTvUserSearchByPIN);
        editTvUserPIN = findViewById(R.id.editTvUserPIN);
        editTvUserName = findViewById(R.id.editTvUserName);
        editTvUserInfo = findViewById(R.id.editTvUserInfo); //textarea

        //todo disable editing
        editTvUserPIN.setFocusable(false);
        editTvUserName.setFocusable(false);
        editTvUserInfo.setFocusable(false);

        //todo ************ OnclickListener ***********
        btnUserSearchByPIN.setOnClickListener((View.OnClickListener) this);
        btnBack.setOnClickListener((View.OnClickListener) this);
        btnReset.setOnClickListener((View.OnClickListener) this);
        btnNext.setOnClickListener((View.OnClickListener) this);

        editTvUserPIN.requestFocus();
        loadCustomerFromSession();
    }

    private void loadCustomerFromSession() {
        Customer customer= new Customer();
        customer=utility.getCustomer();
        if (customer!=null)
        {
            editTvUserPIN.setText(customer.getAccountNumber());
            editTvUserName.setText(customer.getAccountName());
            editTvUserInfo.setText(customer.getDescription());
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnBack ) {
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
        }
        else if (view == btnReset ) {
            editTvUserSearchByPIN.setText("");
            editTvUserPIN.setText("");
            editTvUserName.setText("");
            editTvUserInfo.setText("");
            editTvUserPIN.requestFocus();
        }
        else if (view == btnNext ) {
            startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
        }
        else if (view == btnUserSearchByPIN ) {
            //validation check
            if(editTvUserSearchByPIN.getText().toString().length() == 0){
                utility.message("Please enter PIN");
                return;
            }
            loadCustomerInfo(editTvUserSearchByPIN.getText().toString());
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
                    editTvUserPIN.setText(customer.getAccountNumber());
                    editTvUserName.setText(customer.getAccountName());
                    editTvUserInfo.setText(customer.getDescription());
                    utility.setCustomer(customer);
                    utility.hideKeyboard(CustomerActivity.this);
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                utility.hideLoading();
                Toast.makeText(CustomerActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}