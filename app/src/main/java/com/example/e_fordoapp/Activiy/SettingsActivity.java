package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Service.LoginService;
import com.example.e_fordoapp.Utility.Utility;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnBack,btnCheck,btnSave;
    Utility utility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btnBack = findViewById(R.id.btnBack);
        btnCheck = findViewById(R.id.btnBack);
        btnSave = findViewById(R.id.btnBack);

        //todo ************ OnclickListener ***********
        btnBack.setOnClickListener((View.OnClickListener) this);
        btnCheck.setOnClickListener((View.OnClickListener) this);
        btnSave.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {
        if(view==btnBack)
        {
            startActivity(new Intent(SettingsActivity.this, DashboardActivity.class));
        }
        if(view==btnCheck)
        {
            //startActivity(new Intent(SettingsActivity.this, DashboardActivity.class));
        }
        if(view==btnSave)
        {
            //startActivity(new Intent(SettingsActivity.this, DashboardActivity.class));
        }
    }


}