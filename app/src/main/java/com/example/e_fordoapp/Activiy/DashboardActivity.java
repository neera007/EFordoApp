package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_fordoapp.ApiConfig.ApiConfig;
import com.example.e_fordoapp.Model.Customer;
import com.example.e_fordoapp.Model.CustomerAutoComplete;
import com.example.e_fordoapp.Model.Product;
import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Service.CustomerService;
import com.example.e_fordoapp.Utility.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity  implements View.OnClickListener {

    private CardView cardVieNewReq ,cardViePreviousReq,cardVieSettings,cardVieLogout;
    private ImageButton imgBtnReview;
    private TextView tvUserId,tvLoginTime,tvReviewPushNotification;
    private CustomerService customerService;
    List<Product> basketList= new ArrayList<>();
    Utility utility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        utility = new Utility(this);

        tvUserId=findViewById(R.id.tvUserId);
        tvLoginTime=findViewById(R.id.tvLoginTime);
        tvReviewPushNotification=findViewById(R.id.tvReviewPushNotification);
        imgBtnReview=findViewById(R.id.imgBtnReview);
        cardVieNewReq =findViewById(R.id.cardVieNewReq);
        cardViePreviousReq =findViewById(R.id.cardViePreviousReq);
        cardVieSettings =findViewById(R.id.cardVieSettings);
        cardVieLogout =findViewById(R.id.cardVieLogout);


        //todo ************ OnclickListener ***********
        imgBtnReview.setOnClickListener(this);
        cardVieNewReq.setOnClickListener(this);
        cardViePreviousReq.setOnClickListener(this);
        cardVieSettings.setOnClickListener(this);
        cardVieLogout.setOnClickListener(this);

        // todo show user id & login time
        basketList=utility.getBusketProduct();
        tvUserId.setText("Login By : " + utility.getUserID());
        String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
        tvLoginTime.setText("Login Time: "+currentTime);

        if (basketList.size()==0)
            tvReviewPushNotification.setText("0");
        else
            tvReviewPushNotification.setText(String.valueOf(basketList.size()));

        // todo all customer list
        loadCustomerList(utility.getUserID(),utility.getPassword());
    }

    @Override
    public void onClick(View view) {
        if (view == cardVieNewReq ) {
            startActivity(new Intent(getApplicationContext(), CustomerActivity.class));
        }
        else if (view == cardViePreviousReq ) {
            startActivity(new Intent(getApplicationContext(), OrderListActivity.class));
        }
        else if (view == cardVieSettings ) {
            startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
        }
        else if (view == cardVieLogout ) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        else if (view == imgBtnReview ) {
            startActivity(new Intent(getApplicationContext(),BasketActivity.class));
        }
    }

    private void loadCustomerList(final String user_id, final String password){
        utility.showLoading();
        customerService = ApiConfig.getApiClient().create(CustomerService.class);
        Call<List<CustomerAutoComplete>> call = customerService.getAllCustomer(user_id,password);
        call.enqueue(new Callback<List<CustomerAutoComplete>>() {
            @Override
            public void onResponse(Call<List<CustomerAutoComplete>> call, Response<List<CustomerAutoComplete>> response) {
                utility.hideLoading();
                //todo initializing the stockItemList
                if (response.body()==null){
                    return;
                }
                Utility.allCustomerList = new ArrayList<>(response.body());
            }

            @Override
            public void onFailure(Call<List<CustomerAutoComplete>> call, Throwable t) {
                utility.hideLoading();
                Toast.makeText(DashboardActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}