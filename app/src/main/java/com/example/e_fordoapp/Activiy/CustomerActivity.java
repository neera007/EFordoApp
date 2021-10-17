package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.e_fordoapp.R;

public class CustomerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnBack,btnReset,btnNext;
    private EditText editTvUserSearchByPIN,editTvUserPIN,editTvUserName,editTvUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

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
        btnBack.setOnClickListener((View.OnClickListener) this);
        btnReset.setOnClickListener((View.OnClickListener) this);
        btnNext.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view) {
        if (view == btnBack ) {
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
        }
        if (view == btnReset ) {
            editTvUserSearchByPIN.setText("");
            editTvUserPIN.setText("");
            editTvUserName.setText("");
            editTvUserInfo.setText("");
        }
        if (view == btnNext ) {
            startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
        }

    }
}