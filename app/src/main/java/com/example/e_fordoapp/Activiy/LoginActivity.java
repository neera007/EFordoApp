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
    private TextView tvUserId,tvpassword;
    private LinearLayout logoLayout;
    private Button btnLogin,btnSettings;
    private LoginService loginService;
    private CheckBox chkRememberMe;
    public static final String PREFS_NAME = "MyPrefsFile";
    private static final String PREF_USERNAME = "username";
    private static final String PREF_PASSWORD = "password";
    String UserID,Password ;
    Utility utility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        utility = new Utility(this);
        tvUserId = findViewById(R.id.tvUserId);
        tvpassword = findViewById(R.id.tvpassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSettings = findViewById(R.id.btnSettings);
        chkRememberMe = findViewById(R.id.chkRememberMe);

        //todo Shared Preferences
        SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        String username = pref.getString(PREF_USERNAME, null);
        String password = pref.getString(PREF_PASSWORD, null);


        //todo ************ OnclickListener ***********
        btnLogin.setOnClickListener((View.OnClickListener) this);
        btnSettings.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin ) {
            //todo validation check
            if(tvUserId.getText().toString().length() == 0){
                tvUserId.setError("User ID Required!");
                return;
            }
            if(tvpassword.getText().toString().length() == 0){
                tvpassword.setError("Password Required!");
                return;
            }
            UserID = tvUserId.getText().toString();
            //Password = tvpassword.getText().toString();
            Password = utility.md5(tvpassword.getText().toString());
            getUserInfo(UserID,Password);
        }
        if (view == btnSettings ) {
              startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
        }

    }

    private void getUserInfo(final String user_id, final String password) {
        utility.showLoading();
        loginService = ApiConfig.getApiClient().create(LoginService.class);
        Call call = loginService.getLoginUser(user_id,password);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.isSuccessful()) {
                    utility.hideLoading();
                    if (response.body() != null) {
                        UserInfo userInfo = response.body();
                        if (userInfo.getValidated()) {
                            userInfo.setPassword(password);
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