package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Service.LoginService;
import com.example.e_fordoapp.Utility.Utility;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView user_id , password,tvActiviyName;
    private LinearLayout logoLayout;
    private Button btnLogin,btnSettings;
    private CheckBox chkRememberMe;

   // private LoginService loginService;

    Utility utility;
    Animation bottom_anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //todo <<<<< Animation >>>
      //  bottom_anim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
//        logoLayout = findViewById(R.id.logo_layout);
//        tvActiviyName = findViewById(R.id.tvActiviyName);
//        logoLayout.setAnimation(bottom_anim);
//        tvActiviyName.setAnimation(bottom_anim);


        user_id = findViewById(R.id.txt_user_id);
        password = findViewById(R.id.txt_password);
        btnLogin = findViewById(R.id.btnLogin);
        btnSettings = findViewById(R.id.btnSettings);




        //todo ************ OnclickListener ***********
        btnLogin.setOnClickListener((View.OnClickListener) this);
        btnSettings.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin ) {
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
        }
        if (view == btnSettings ) {
              startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
        }
        if(view == chkRememberMe){

        }
    }
}