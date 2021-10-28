package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.e_fordoapp.Model.Setting;
import com.example.e_fordoapp.Model.UserInfo;
import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Service.LoginService;
import com.example.e_fordoapp.Utility.Utility;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnBack,btnSave;
    private TextView tvBaseIP;
    Utility utility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        utility = new Utility(this);

        btnBack = findViewById(R.id.btnBack);
        //btnCheck = findViewById(R.id.btnCheck);
        btnSave = findViewById(R.id.btnSave);
        tvBaseIP=findViewById(R.id.tvBaseIP);

        //todo ************ OnclickListener ***********
        btnBack.setOnClickListener(this);
       // btnCheck.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        //todo load data from shared preference
        loadDefaultSettings();
    }

    private void loadDefaultSettings() {
        Setting setting=utility.getSetting();
        tvBaseIP.setText("");
        if (setting!=null)
        {
            if (setting.getBaseIP()!=null) {
                tvBaseIP.setText(setting.getBaseIP());
            }
        }
    }
    @Override
    public void onClick(View view) {
        if(view==btnBack)
        {
            super.onBackPressed();
            //startActivity(new Intent(SettingsActivity.this, DashboardActivity.class));
        }
//        if(view==btnCheck)
//        {
//            utility.message("Under construction");
//            //startActivity(new Intent(SettingsActivity.this, DashboardActivity.class));
//        }
        if(view==btnSave)
        {
            if(tvBaseIP.getText().length()<7)
            {
                utility.message("Please enter API IP address.");
                return;
            }
            Setting settingInfo= new Setting();
            settingInfo.setBaseIP(tvBaseIP.getText().toString());
            utility.setSetting(settingInfo);
            utility.message("Connection setup is successful");
        }
    }


}