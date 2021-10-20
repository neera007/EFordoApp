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

import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Utility.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity  implements View.OnClickListener {

    private CardView cardVieNewReq ,cardViePreviousReq,cardVieSettings,cardVieLogout;
    private ImageButton logOutB;
    private TextView tvUserId,tvLoginTime;
    Utility utility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        utility = new Utility(this);
        tvUserId=findViewById(R.id.tvUserId);
        tvLoginTime=findViewById(R.id.tvLoginTime);
        cardVieNewReq =findViewById(R.id.cardVieNewReq);
        cardViePreviousReq =findViewById(R.id.cardViePreviousReq);
        cardVieSettings =findViewById(R.id.cardVieSettings);
        cardVieLogout =findViewById(R.id.cardVieLogout);
        logOutB=findViewById(R.id.logOutB);
        //todo ************ OnclickListener ***********

        cardVieNewReq.setOnClickListener((View.OnClickListener) this);
        cardViePreviousReq.setOnClickListener((View.OnClickListener) this);
        cardVieSettings.setOnClickListener((View.OnClickListener) this);
        cardVieLogout.setOnClickListener((View.OnClickListener) this);

        tvUserId.setText(utility.getUserID());
        String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
        tvLoginTime.setText("Login Time: "+currentTime);

        //BadgeView badge = new BadgeView(this, logOutB);
        /*badge.setText("1");
        badge.show();*/

    }

    @Override
    public void onClick(View view) {
        if (view == cardVieNewReq ) {
            startActivity(new Intent(getApplicationContext(), CustomerActivity.class));
        }
        if (view == cardViePreviousReq ) {
          //  startActivity(new Intent(getApplicationContext(), CustomerActivity.class));
            utility.message("under process !");
        }
        if (view == cardVieSettings ) {
            startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
        }
        if (view == cardVieLogout ) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}