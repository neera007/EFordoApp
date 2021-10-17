package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.e_fordoapp.R;

public class DashboardActivity extends AppCompatActivity  implements View.OnClickListener {
    private TextView tvNewReq;
    private ImageButton imgBtnNewReq;
    private LinearLayout layoutNewReq;
   // private Button btnLogin,btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tvNewReq = findViewById(R.id.tvNewReq);
        imgBtnNewReq = findViewById(R.id.imgBtnNewReq);
        layoutNewReq = findViewById(R.id.layoutNewReq);


        //todo ************ OnclickListener ***********
        tvNewReq.setOnClickListener((View.OnClickListener) this);
        imgBtnNewReq.setOnClickListener((View.OnClickListener) this);
        layoutNewReq.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {
        if (view == tvNewReq || view == imgBtnNewReq || view == layoutNewReq  ) {
            startActivity(new Intent(getApplicationContext(), CustomerActivity.class));
        }
    }
}