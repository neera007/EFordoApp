package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.e_fordoapp.Model.Product;
import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Utility.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity  implements View.OnClickListener {

    private CardView cardVieNewReq ,cardViePreviousReq,cardVieSettings,cardVieLogout;
    private ImageButton imgBtnReview;
    private TextView tvUserId,tvLoginTime,tvReviewPushNotification;
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
        imgBtnReview.setOnClickListener((View.OnClickListener) this);
        cardVieNewReq.setOnClickListener((View.OnClickListener) this);
        cardViePreviousReq.setOnClickListener((View.OnClickListener) this);
        cardVieSettings.setOnClickListener((View.OnClickListener) this);
        cardVieLogout.setOnClickListener((View.OnClickListener) this);

        //show user id & login time
        //todo load default data
        tvUserId.setText("Login By : " + utility.getUserID());
        String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
        tvLoginTime.setText("Login Time: "+currentTime);
        basketList=utility.getBusketProduct();
        if (basketList.size()==0)
            tvReviewPushNotification.setText("0");
        else
            tvReviewPushNotification.setText(String.valueOf(basketList.size()));
    }

    @Override
    public void onClick(View view) {
        if (view == cardVieNewReq ) {
            startActivity(new Intent(getApplicationContext(), CustomerActivity.class));
        }
        else if (view == cardViePreviousReq ) {
            startActivity(new Intent(getApplicationContext(), RequisitionListActivity.class));
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
            startActivity(new Intent(getApplicationContext(),ReviewActivity.class));
        }
    }
}