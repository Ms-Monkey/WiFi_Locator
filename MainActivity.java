package com.example.sorensjone.test;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    public static ArrayList<WifiSignal> signals = new ArrayList<>();

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

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);

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
        initiate_routers();
        scanWifi();
    }


    public void determine_visibility(){
        /*This gets the floor of the strongest signal
        * It displays that floor as the image on screen
        * If the strongest has no floor, it loops through
        * all signals until it finds the next strongest with
        * a floor*/

        ImageView imageOne = findViewById(R.id.imageView1);
        ImageView imageTwo = findViewById(R.id.imageView2);
        ImageView imageThree = findViewById(R.id.imageView3);
        ImageView imageFour = findViewById(R.id.imageView4);
        int floor = 0;
        for (WifiSignal x: signals){
            if (x.floor != 0){
                floor = x.floor;
                break;
            }
        }

        if(floor == 1){
            imageOne.setVisibility(View.VISIBLE);
            imageTwo.setVisibility(View.INVISIBLE);
            imageThree.setVisibility(View.INVISIBLE);
            imageFour.setVisibility(View.INVISIBLE);
        } else if (floor == 2){
            imageOne.setVisibility(View.INVISIBLE);
            imageTwo.setVisibility(View.VISIBLE);
            imageThree.setVisibility(View.INVISIBLE);
            imageFour.setVisibility(View.INVISIBLE);
        } else if (floor == 3){
            imageOne.setVisibility(View.INVISIBLE);
            imageTwo.setVisibility(View.INVISIBLE);
            imageThree.setVisibility(View.VISIBLE);
            imageFour.setVisibility(View.INVISIBLE);
        } else if (floor == 4) {
            imageOne.setVisibility(View.INVISIBLE);
            imageTwo.setVisibility(View.INVISIBLE);
            imageThree.setVisibility(View.INVISIBLE);
            imageFour.setVisibility(View.VISIBLE);
        }
    }


    public void order_signals(){
        signals.clear();
        WifiSignal highest;
        for (WifiSignal x: WifiOutput){
            highest = x;
            if (signals.contains(x)){
                continue;
            }

            for (WifiSignal y: WifiOutput){
                //If the value is greater than y's and it isn't in signals already
                if (highest.level < y.level && !signals.contains(y)){
                    highest = y;
                } else {
                    continue;
                }
            }
            signals.add(highest);
        }
    }


    public void scanWifi() {
        arrayList.clear();
        WifiOutput.clear();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        Toast.makeText(this, "Scanning WiFi ...", Toast.LENGTH_SHORT).show();
        wifiManager.startScan();
    }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            results = wifiManager.getScanResults();
            unregisterReceiver(this);

            TextView tv1 = (TextView) findViewById(R.id.wifiOutput);

            tv1.setText("lemme see them signals >:)");

            for (ScanResult scanResult : results) {
                //Create Wifi Object, add to it's ordered list
                int floor = contains_MAC(scanResult.BSSID);
                WifiSignal test = new WifiSignal(scanResult.SSID, scanResult.BSSID, scanResult.level, scanResult.frequency, floor);
                WifiOutput.add(test);

                arrayList.add(scanResult.SSID + " - " + scanResult.capabilities);
                //adapter.notifyDataSetChanged();
            }
            order_signals();
            tv1.setText("signals" + signals.size());
            determine_visibility();
        }
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


    public int contains_MAC(String MAC){
        for (Router w: Floor1){
            if(w.BSSID.equals(MAC)){ return 1; }
        }
        for (Router x : Floor2){
            if(x.BSSID.equals(MAC)){ return 2; }
        }
        for (Router y: Floor3){
            if(y.BSSID.equals(MAC)){ return 3; }
        }
        for (Router z: Floor4){
            if(z.BSSID.equals(MAC)){ return 4; }
        }

        return 0;
    }


    public void initiate_routers(){
        Floor1.add(new Router(1, 42, 1, "CO159", "70:70:8b:d3:5e:60"));
        Floor1.add(new Router(1, 42, 1, "CO159", "70:70:8b:d3:5e:61"));

        Floor1.add(new Router(7, 42, 1, "CO151", "70:70:8b:be:01:a1"));
        Floor1.add(new Router(7, 42, 1, "CO151", "70:70:8b:be:01:a6"));

        Floor1.add(new Router(22, 40, 1, "CO143", "70:70:8b:d3:5b:6e"));
        Floor1.add(new Router(22, 40, 1, "CO143", "70:70:8b:d3:5b:6f"));

        Floor1.add(new Router(32, 37, 1, "CO141", "70:70:8b:ce:29:40"));
        Floor1.add(new Router(32, 37, 1, "CO141", "70:70:8b:ce:29:41"));

        Floor1.add(new Router(44, 37, 1, "CO121", "00:2c:c8:cc:30:80"));
        Floor1.add(new Router(44, 37, 1, "CO121", "00:2c:c8:cc:30:81"));
        Floor1.add(new Router(44, 37, 1, "CO121", "00:2c:c8:cc:30:86"));

        Floor1.add(new Router(67, 37, 1, "CO110", "70:70:8b:be:0c:8e"));
        Floor1.add(new Router(67, 37, 1, "CO110", "70:70:8b:be:0c:8f"));

        Floor1.add(new Router(80, 10, 1, "CO105", "b0:8b:cf:27:7b:c0"));
        Floor1.add(new Router(80, 10, 1, "CO105", "b0:8b:cf:27:7b:c1"));
        Floor1.add(new Router(80, 10, 1, "CO105", "b0:8b:cf:27:7b:ce"));//5G
        Floor1.add(new Router(80, 10, 1, "CO105", "b0:8b:cf:27:7b:cf"));

        Floor1.add(new Router(52, 32, 1, "CO122", "54:92:74:d2:34:70"));
        Floor1.add(new Router(52, 32, 1, "CO122", "54:92:74:d2:34:71"));

        Floor1.add(new Router(52, 25, 1, "COLT122", "e8:65:49:40:0c:10"));
        Floor1.add(new Router(52, 25, 1, "COLT122", "e8:65:49:40:0c:11"));

        Floor1.add(new Router(30, 5, 1, "CO126", "bc:26:c7:40:c0:00"));
        Floor1.add(new Router(30, 5, 1, "CO126", "bc:26:c7:40:c0:01"));

        Floor1.add(new Router(20, 5, 1, "CO132", "b0:8b:cf:35:2f:c1")); //Not 100% sure
        Floor1.add(new Router(20, 5, 1, "CO132", "b0:8b:cf:35:2f:ce"));
        Floor1.add(new Router(20, 5, 1, "CO132", "b0:8b:cf:35:2f:cf"));

        Floor1.add(new Router(-5, 12, 1, "CO154", "00:92:ee:d3:80:ae"));
        Floor1.add(new Router(-5, 12, 1, "CO154", "00:92:ee:d3:80:af"));

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
        Floor2.add(new Router(30, 7, 2, "CO225", "70:b3:17:d5:37:e6")); //Not 100% sure

        Floor2.add(new Router(46, 10, 2, "CO228", "70:b3:17:d5:34:40"));
        Floor2.add(new Router(46, 10, 2, "CO228", "70:b3:17:d5:34:41"));
        Floor2.add(new Router(46, 10, 2, "CO228", "70:b3:17:d5:34:46"));

        Floor2.add(new Router(32, 5, 2, "CO232", "70:6d:15:40:56:0e"));
        Floor2.add(new Router(32, 5, 2, "CO232", "70:6d:15:40:56:0f"));

        Floor2.add(new Router(57, 11, 2, "CO221", "00:d7:8f:f3:95:80"));
        Floor2.add(new Router(57, 11, 2, "CO221", "00:d7:8f:f3:95:86"));
        Floor2.add(new Router(57, 11, 2, "CO221", "00:d7:8f:f3:95:8f"));

        Floor3.add(new Router(5, 4, 3, "CO337", "70:6d:15:36:b6:2e"));
        Floor3.add(new Router(5, 4, 3, "CO337", "70:6d:15:36:b6:2f"));

        Floor3.add(new Router(2, 6, 3, "CO365", "bc:26:c7:94:91:40"));
        Floor3.add(new Router(2, 6, 3, "CO365", "bc:26:c7:94:91:41"));
        Floor3.add(new Router(2, 6, 3, "CO365", "bc:26:c7:94:91:46"));

        Floor3.add(new Router(32, 37, 3, "CO329", "70:6d:15:16:6c:20"));
        Floor3.add(new Router(32, 37, 3, "CO329", "70:6d:15:16:6c:21"));

        Floor3.add(new Router(10, 37, 3, "CO352", "70:6d:15:40:35:c0"));
        Floor3.add(new Router(10, 37, 3, "CO352", "70:6d:15:40:35:c1"));
        Floor3.add(new Router(10, 37, 3, "CO352", "70:6d:15:40:35:ce"));//5G
        Floor3.add(new Router(10, 37, 3, "CO352", "70:6d:15:40:35:cf"));

        Floor3.add(new Router(32, 42, 3, "CO334", "70:6d:15:48:15:2e"));
        Floor3.add(new Router(32, 42, 3, "CO334", "70:6d:15:48:15:2f"));

        Floor3.add(new Router(43, 5, 3, "CO319", "70:6d:15:3b:a2:60"));
        Floor3.add(new Router(43, 5, 3, "CO319", "70:6d:15:3b:a2:61"));
        Floor3.add(new Router(43, 5, 3, "CO319", "70:6d:15:3b:a2:6e"));//5G
        Floor3.add(new Router(43, 5, 3, "CO319", "70:6d:15:3b:a2:6f"));


        Floor3.add(new Router(43, 5, 3, "CO319", "70:6d:15:3b:a2:66"));//Not 100% on this

        Floor3.add(new Router(62, 10, 3, "CO310", "e8:65:49:10:00:df"));
        Floor3.add(new Router(62, 10, 3, "CO310", "e8:65:49:10:00:d9"));

        Floor3.add(new Router(80, 11, 3, "CO304", "70:6d:15:05:be:40"));
        Floor3.add(new Router(80, 11, 3, "CO304", "70:6d:15:05:be:41"));
        Floor3.add(new Router(80, 11, 3, "CO304", "70:6d:15:05:be:46"));

        Floor4.add(new Router(80, 6, 4, "CO407", "70:6d:15:40:c9:40"));
        Floor4.add(new Router(80, 6, 4, "CO407", "70:6d:15:40:c9:41"));
        Floor4.add(new Router(80, 6, 4, "CO407", "70:6d:15:40:c9:46"));
        Floor4.add(new Router(80, 6, 4, "CO407", "70:6d:15:40:c9:49"));

        Floor4.add(new Router(55, 12, 4, "CO418", "70:6d:15:35:42:00")); //2.462G
        Floor4.add(new Router(55, 12, 4, "CO418", "70:6d:15:35:42:01"));

        Floor4.add(new Router(30, 12, 4, "CO429", "70:6d:15:53:32:e0"));//2.412G
        Floor4.add(new Router(30, 12, 4, "CO429", "70:6d:15:53:32:ee"));//5.745G
        Floor4.add(new Router(30, 12, 4, "CO429", "70:6d:15:53:32:ef"));

        Floor4.add(new Router(12, 12, 4, "CO435", "70:6d:15:40:cd:60"));//2.412G
        Floor4.add(new Router(12, 12, 4, "CO435", "70:6d:15:40:cd:61"));
        Floor4.add(new Router(12, 12, 4, "CO435", "70:6d:15:40:cd:66"));

    }



}
