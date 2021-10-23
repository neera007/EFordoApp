package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.e_fordoapp.ApiConfig.ApiConfig;
import com.example.e_fordoapp.Model.UserInfo;
import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Service.LoginService;
import com.example.e_fordoapp.Utility.Utility;

import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvUserId,tvPassword;
    private LinearLayout logoLayout;
    private Button btnLogin,btnSettings;
    private LoginService loginService;
    private CheckBox chkRememberMe;

    public static final String PREFS_NAME = "MyPrefsFile";
    private static final String PREF_USERNAME = "username";
    private static final String PREF_PASSWORD = "password";

    String userID,password;
    Utility utility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        utility = new Utility(this);
        tvUserId = findViewById(R.id.tvUserId);
        tvPassword = findViewById(R.id.tvPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSettings = findViewById(R.id.btnSettings);
        chkRememberMe = findViewById(R.id.chkRememberMe);

        //todo ************ OnclickListener ***********
        btnLogin.setOnClickListener(this);
        btnSettings.setOnClickListener(this);

        //todo load data from shared preference
        UserInfo userInfo=utility.getUserInfo();
        if (userInfo!=null)
        {
            if (userInfo.getRememberMe()!=null && userInfo.getRememberMe()) {
                tvUserId.setText(userInfo.getSoftUser());
                if (userInfo.getPasswordWoEncrypt()!=null)
                    tvPassword.setText(userInfo.getPasswordWoEncrypt());
                chkRememberMe.setChecked(userInfo.getRememberMe());
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin ) {
            //todo validation check
            if(tvUserId.getText().toString().length() == 0){
                tvUserId.setError("User ID Required!");
                return;
            }
            if(tvPassword.getText().toString().length() == 0){
                tvPassword.setError("Password Required!");
                return;
            }
            userID = tvUserId.getText().toString();
            password = utility.md5(tvPassword.getText().toString());
            getUserInfo(userID,password);
        }
        else if (view == btnSettings ) {
              startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
        }
    }

    private void getUserInfo(final String userId, final String password) {
        utility.showLoading();
        loginService = ApiConfig.getApiClient().create(LoginService.class);
        Call call = loginService.getLoginUser(userId,password);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.isSuccessful()) {
                    utility.hideLoading();
                    if (response.body() != null) {
                        UserInfo userInfo = response.body();
                        if (userInfo.getValidated()) {

                            //if user is validated then store data in shared preference
                            userInfo.setPassword(password);
                            userInfo.setPasswordWoEncrypt(tvPassword.getText().toString());
                            userInfo.setRememberMe(chkRememberMe.isChecked());
                            utility.setUserInfo(userInfo);

                            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                            finish();
                        }else{
                            utility.message("Invalid User or Password!!!");
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                utility.hideLoading();
                Toast.makeText(LoginActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}