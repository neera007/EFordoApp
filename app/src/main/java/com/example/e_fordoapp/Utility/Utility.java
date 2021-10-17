package com.example.e_fordoapp.Utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.e_fordoapp.Model.UserInfo;
import com.example.e_fordoapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Utility {
    Context context;
    SharedPreferences sharedPreferences;
    ProgressDialog progress;

    public Utility(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        progress = new ProgressDialog(context);
    }

    public void message(String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(R.string.app_name)
                .setIcon(R.drawable.ic_message_title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {

                    }
                }).show();
    }


    // Todo User Info --------------------------------



    public void showLoading(){
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }

    public void hideLoading(){
        progress.dismiss();
    }

    public void hideKeyboard(@NonNull Activity activity) {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void setDeviceID(String DeviceID)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        // Todo add data to Shared preferences
        String json = gson.toJson(DeviceID);
        editor.putString("deviceID", json);
        editor.commit();
    }

    public String getDeviceID()
    {
        String deviceID="";
        Gson gson = new Gson();
        String json = sharedPreferences.getString("deviceID", null);
        if (json == null)
            deviceID="";
        else {
            Type type = new TypeToken<String>() {
            }.getType();
            deviceID = gson.fromJson(json, type);
        }
        return deviceID;
    }

    public String setSpace(int ch)
    {
        String sp = "";
        for (int i = 0; i < ch; i++)
            sp = sp + " ";
        return sp;
    }

    public String currencyFormat( Double InputString)
    {
        return String.format("%.2f", Double.valueOf(InputString));
    }

    public String formatString( String InputString,int StringLength, String Character)
    {
        StringBuilder stringBuilder = new StringBuilder(InputString);
        while (stringBuilder.length() < StringLength) {
            stringBuilder.insert(0, Character);
        }
        return stringBuilder.toString();
    }




}

