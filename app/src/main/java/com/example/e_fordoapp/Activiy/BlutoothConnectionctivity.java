package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bxl.config.editor.BXLConfigLoader;
import com.bxl.config.util.BXLBluetoothLE;
import com.bxl.config.util.BXLNetwork;
import com.example.e_fordoapp.Model.BluetoothConn;
import com.example.e_fordoapp.Model.Customer;
import com.example.e_fordoapp.PrinterControl.BixolonPrinter;
import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Utility.Utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jpos.JposException;

public class BlutoothConnectionctivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnTouchListener, View.OnClickListener {
    Utility utility;
    private final int REQUEST_PERMISSION = 0;
    private final String DEVICE_ADDRESS_START = " (";
    private final String DEVICE_ADDRESS_END = ")";

    private final ArrayList<CharSequence> bondedDevices = new ArrayList<>();
    private ArrayAdapter<CharSequence> arrayAdapter;

    private int portType = 0;//BXLConfigLoader.DEVICE_BUS_BLUETOOTH; Select Bluetooth
    private String logicalName = "";
    private String address = "";
    private ListView listView;
    private Button btnMakeConnection,btnBack;
    private static BixolonPrinter bxlPrinter = null;
   // BluetoothConn bluetoothConn =new BluetoothConn();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blutooth_connectionctivity);
        utility = new Utility(this);
        btnMakeConnection = findViewById(R.id.btnMakeConnection);
        btnBack = findViewById(R.id.btnBack);
        btnMakeConnection.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        //todo list of paired devices
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, bondedDevices);
        listView = findViewById(R.id.listViewPairedDevices);
        listView.setAdapter(arrayAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(this);
        listView.setOnTouchListener(this);

        commonCall();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        commonCall();
    }

    private void commonCall() {
        //todo Allow bluetooth
        Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableIntent, 101);
        //todo pair device
        setPairedDevices();
    }

    private void setPairedDevices() {
        bondedDevices.clear();
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> bondedDeviceSet = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : bondedDeviceSet) {
            bondedDevices.add(device.getName() + DEVICE_ADDRESS_START + device.getAddress() + DEVICE_ADDRESS_END);
        }
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP)
            listView.requestDisallowInterceptTouchEvent(false);
        else listView.requestDisallowInterceptTouchEvent(true);
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String device = ((TextView) view).getText().toString();
        address = device.substring(device.indexOf(DEVICE_ADDRESS_START) + DEVICE_ADDRESS_START.length(), device.indexOf(DEVICE_ADDRESS_END));
        logicalName = device.substring(0, device.indexOf(" "));
        Toast.makeText(BlutoothConnectionctivity.this, address + "  "+ logicalName, Toast.LENGTH_SHORT).show();
        //todo set paired value to utility
        BluetoothConn bluetoothConn = new BluetoothConn();
        bluetoothConn.setLogicalName(logicalName);
        bluetoothConn.setAddress(address);
        utility.setBluetoothConn(bluetoothConn);

        //todo start previous activity with set up utility value
        startActivity(new Intent(BlutoothConnectionctivity.this, OrderCheckoutActivity.class));
    }

    @Override
    public void onClick(View view) {
        if(view == btnBack)
        {
            startActivity(new Intent(BlutoothConnectionctivity.this, OrderCheckoutActivity.class));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(getApplicationContext(), "permission denied", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
