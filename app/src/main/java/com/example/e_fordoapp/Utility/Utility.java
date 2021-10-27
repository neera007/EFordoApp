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

import com.example.e_fordoapp.Model.Customer;
import com.example.e_fordoapp.Model.CustomerAutoComplete;
import com.example.e_fordoapp.Model.Product;
import com.example.e_fordoapp.Model.Setting;
import com.example.e_fordoapp.Model.UserInfo;
import com.example.e_fordoapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Utility {
    Context context;
    SharedPreferences sharedPreferences;
    ProgressDialog progress;
    public static List<CustomerAutoComplete> allCustomerList= new ArrayList<>();

    public Utility() {
        sharedPreferences = context.getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
    }

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

    public void setUserInfo(UserInfo userInfo)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        // Todo add data to Shared preferences
        String json = gson.toJson(userInfo);
        editor.putString("userInfo", json);
        editor.commit();
    }

    public UserInfo getUserInfo() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("userInfo", null);
        Type type = new TypeToken<UserInfo>(){}.getType();
        UserInfo userInfo=gson.fromJson(json, type);
        return userInfo;
    }

    public String getUserID()
    {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("userInfo", null);
        if (json == null)
            return "";
        Type type = new TypeToken<UserInfo>(){}.getType();
        UserInfo userInfo=gson.fromJson(json, type);
        return userInfo.getSoftUser();
    }

    public String getPassword()
    {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("userInfo", null);
        if (json == null)
            return "";
        Type type = new TypeToken<UserInfo>(){}.getType();
        UserInfo userInfo=gson.fromJson(json, type);
        return userInfo.getPassword();
    }

    public String setSpace(int ch)
    {
        String sp = "";
        for (int i = 0; i < ch; i++)
            sp = sp + " ";
        return sp;
    }

    public void setSetting(Setting settingInfo)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        // Todo add data to Shared preferences
        String json = gson.toJson(settingInfo);
        editor.putString("settingInfo", json);
        editor.commit();
    }

    public Setting getSetting() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("settingInfo", null);
        Type type = new TypeToken<Setting>(){}.getType();
        Setting settingInfo=gson.fromJson(json, type);
        return settingInfo;
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

    public String md5(String in) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(in.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 0x0f, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        return "";
    }

    /*public void updateBusketProduct(List<Product> busketProducts) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        clearBusket();
        // Todo add data to Shared preferences
        String json = gson.toJson(busketProducts);
        editor.putString("busketProducts", json);
        editor.commit();
    }*/

    public void clearBusket() {
        boolean isUpdate = false;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        // Todo get current busket data
        ArrayList<Product> busketProducts = new ArrayList<>();

        // Todo add data to Shared preferences
        String json = gson.toJson(busketProducts);
        editor.putString("busketProducts", json);
        editor.commit();

    }

    public ArrayList<Product> getBusketProduct() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("busketProducts", null);
        if (json == null)
            return new ArrayList<Product>();
        Type type = new TypeToken<ArrayList<Product>>() {
        }.getType();

        ArrayList<Product> stockItem=new ArrayList<Product>();
        ArrayList<Product> tempStockItem=gson.fromJson(json, type);
        for(int i=0;i<tempStockItem.size();i++)
            if(tempStockItem.get(i).getItemQty()>0)
                stockItem.add(tempStockItem.get(i));

        return stockItem;
    }

    public void setBusketProduct(Product product) {
        boolean isUpdate = false;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        // todo get current busket data
        ArrayList<Product> busketProducts = getBusketProduct();

        // todo check this product already exist in the basket or not if exists then qty will be increase
        for (int i = 0; i < busketProducts.size(); i++) {
            if (busketProducts.get(i).getItemCode().equals(product.getItemCode())) {
                //busketProducts.get(i).setItemQty(busketProducts.get(i).getItemQty() + 1);
                busketProducts.get(i).setItemQty(product.getItemQty());

                // Todo add data to Shared preferences
                String json = gson.toJson(busketProducts);
                editor.putString("busketProducts", json);
                editor.commit();

                isUpdate = true;
                break;
            }
        }

        // Todo if not exists then add this product in the busket
        if (!isUpdate) {
            //product.setItemQty(1);
            busketProducts.add(product);

            // Todo add data to Shared preferences
            String json = gson.toJson(busketProducts);
            editor.putString("busketProducts", json);
            editor.commit();
        }
    }

    public Double getBusketAmount() {
        Double amount=0.00,qty=0.00,salesPrice=0.00;

        Gson gson = new Gson();
        String json = sharedPreferences.getString("busketProducts", null);
        if (json == null)
            amount=0.00;
        Type type = new TypeToken<ArrayList<Product>>() {
        }.getType();

        ArrayList<Product> tempStockItem=gson.fromJson(json, type);
        for(int i=0;i<tempStockItem.size();i++) {
            qty=Double.valueOf(tempStockItem.get(i).getItemQty());
            salesPrice=Double.valueOf(tempStockItem.get(i).getPrice());
            amount=amount+(Double.valueOf(salesPrice*qty));
        }
        return amount;
    }

    public void setCustomer(Customer customer) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        // Todo add data to Shared preferences
        String json = gson.toJson(customer);
        editor.putString("customer", json);
        editor.commit();
    }

    public Customer getCustomer() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("customer", null);
        Type type = new TypeToken<Customer>(){}.getType();
        Customer customer=gson.fromJson(json, type);
        return customer;
    }

    public void clearCustomer() {
        boolean isUpdate = false;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        // Todo get current busket data
        Customer customer = new Customer();

        // Todo add data to Shared preferences
        String json = gson.toJson(customer);
        editor.putString("customer", json);
        editor.commit();

    }

}

