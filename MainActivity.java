package com.example.sorensjone.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;import android.app.Application;
import android.content.Context;
import java.util.ArrayList;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private WifiManager wifiManager;
    private ListView listView;
    private Button buttonScan;
    private int size = 0;
    public static List<ScanResult> results;
    private ArrayList<String> arrayList = new ArrayList<>();
    public static ArrayList<WifiSignal> WifiOutput = new ArrayList<>();

    //Routers by floor, used to ignore other floors signals
    public static ArrayList<Router> Floor1 = new ArrayList<>();
    public static ArrayList<Router> Floor2 = new ArrayList<>();
    public static ArrayList<Router> Floor3 = new ArrayList<>();
    public static ArrayList<Router> Floor4 = new ArrayList<>();

    private ArrayAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("OUTPUT", "HENLO");
        buttonScan = findViewById(R.id.scanBtn);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                TextView tv1 = (TextView) findViewById(R.id.wifiOutput);
                tv1.setText("henlo");
                scanWifi();
            }
        });

        listView = findViewById(R.id.wifiList);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(this, "WiFi is disabled ... We need to enable it", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
            if (wifiManager.isWifiEnabled()) {
                Toast.makeText(this, "WiFi enabled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "WiFi still disabled", Toast.LENGTH_LONG).show();
            }

        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        scanWifi();
    }

    public void scanWifi() {
        arrayList.clear();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        Toast.makeText(this, "Scanning WiFi ...", Toast.LENGTH_SHORT).show();
        println("Starting scan");
        wifiManager.startScan();
        println("Scan completed");
    }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            results = wifiManager.getScanResults();
            unregisterReceiver(this);

            setContentView(R.layout.activity_main);
            TextView tv1 = (TextView) findViewById(R.id.wifiOutput);

            tv1.setText("lemme see them signals >:) " + results.size());

            for (ScanResult scanResult : results) {
                //Create Wifi Object, add to it's ordered list
                WifiSignal test = new WifiSignal(scanResult.SSID, scanResult.BSSID, scanResult.level);
                WifiOutput.add(test);

                arrayList.add(scanResult.SSID + " - " + scanResult.capabilities);
                adapter.notifyDataSetChanged();
            }

            String topThree = "";

            int x = 0;
            for (ScanResult temp : results) {
                if (x >= 3) {
                    break;
                }
                if (temp.level > -75) {
                    topThree += ("Name:     " + temp.SSID + "\nMAC:       " + temp.BSSID + "\nStrength: " + temp.level + "dB\n\n");
                    x++;
                }
            }
            tv1.setText(topThree);
            //ListView test = (TextView)findViewById(R.id.wifiList);

            //ArrayAdapter
            //NotifyUpdate
        }

        ;
    };

    /*This creates an instance of activity_display_message*/
    public void sendMessage(View view) {
        //Get the table
        //table = (TableLayout_find)ViewById(R.id.myTable);
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        String message = "";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


    public void initiate_routers(){
        //Create a bunch of routers, add them to their respective floor arrays
        Floor2.add(new Router(6, 5, 2, "CO258", "70:6d:15:3B:91:80"));  //MIGHT BE 5b not 100%
        Floor2.add(new Router(6, 5, 2, "CO258", "70:6d:15:3B:91:81"));

        Floor2.add(new Router(28, 25, 2, "CO243", "70:6d:15:28:83:4e"));
        Floor2.add(new Router(28, 25, 2, "CO243", "70:6d:15:28:83:4f"));

        Floor2.add(new Router(14, 43, 2, "CO246", "70:6d:15:40:cd:20"));
        Floor2.add(new Router(14, 43, 2, "CO246", "70:6d:15:40:cd:21"));

        Floor2.add(new Router(23, 37, 2, "CO242", "70:6d:15:40:ca:a0"));
        Floor2.add(new Router(23, 37, 2, "CO242", "70:6d:15:40:ca:a1"));

        Floor2.add(new Router(47, 30, 2, "CO219", "70:6d:15:40:a3:8e"));
        Floor2.add(new Router(47, 30, 2, "CO219", "70:6d:15:40:a3:8f"));

        Floor2.add(new Router(63, 30, 2, "CO215", "70:6d:15:40:65:c0"));
        Floor2.add(new Router(63, 30, 2, "CO215", "70:6d:15:40:65:c1"));

        Floor2.add(new Router(95, 10, 2, "CO200", "70:6d:15:40:4d:00"));
        Floor2.add(new Router(95, 10, 2, "CO200", "70:6d:15:40:4d:01"));

        Floor2.add(new Router(30, 7, 2, "CO225", "70:b3:17:d5:37:e0"));
        Floor2.add(new Router(30, 7, 2, "CO225", "70:b3:17:d5:37:e1"));

        Floor2.add(new Router(46, 10, 2, "CO228", "70:b3:17:d5:34:40"));
        Floor2.add(new Router(46, 10, 2, "CO228", "70:b3:17:d5:34:46"));

        Floor2.add(new Router(32, 5, 2, "CO232", "70:6d:15:40:56:0e"));
        Floor2.add(new Router(32, 5, 2, "CO232", "70:6d:15:40:56:0f"));
    }



}
