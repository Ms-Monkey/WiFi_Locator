package com.example.sorensjone.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        ArrayList<WifiSignal> signals = MainActivity.signals;

        if(signals.size() > 0) {
            WifiSignal one = signals.get(0);
            TextView textView1 = findViewById(R.id.row1Name);
            textView1.setText(one.SSID);
            TextView textView2 = findViewById(R.id.row1MAC);
            textView2.setText(one.BSSID + "/ Floor:" + one.floor);
            TextView textView3 = findViewById(R.id.row1Strength);
            textView3.setText(one.distance);
        }

        if(signals.size() > 1) {
            WifiSignal two = signals.get(1);
            TextView textView4 = findViewById(R.id.row2Name);
            textView4.setText(two.SSID);
            TextView textView5 = findViewById(R.id.row2MAC);
            textView5.setText(two.BSSID + "/ Floor:" + two.floor);
            TextView textView6 = findViewById(R.id.row2Strength);
            textView6.setText(two.distance);
        }

        if(signals.size() > 2) {
            WifiSignal three = signals.get(2);
            TextView textView7 = findViewById(R.id.row3Name);
            textView7.setText(three.SSID);
            TextView textView8 = findViewById(R.id.row3MAC);
            textView8.setText(three.BSSID + "/ Floor:" + three.floor);
            TextView textView9 = findViewById(R.id.row3Strength);
            textView9.setText(three.distance);
        }

        if(signals.size() > 3) {
            WifiSignal four = signals.get(3);
            TextView textView10 = findViewById(R.id.row4Name);
            textView10.setText(four.SSID);
            TextView textView11 = findViewById(R.id.row4MAC);
            textView11.setText(four.BSSID + "/ Floor:" + four.floor);
            TextView textView12 = findViewById(R.id.row4Strength);
            textView12.setText(four.distance);
        }

        if(signals.size() > 4) {
            WifiSignal five = signals.get(4);
            TextView textView13 = findViewById(R.id.row5Name);
            textView13.setText(five.SSID);
            TextView textView14 = findViewById(R.id.row5MAC);
            textView14.setText(five.BSSID + "/ Floor:" + five.floor);
            TextView textView15 = findViewById(R.id.row5Strength);
            textView15.setText(five.distance);
        }

        if(signals.size() > 5) {
            WifiSignal six = signals.get(5);
            TextView textView16 = findViewById(R.id.row6Name);
            textView16.setText(six.SSID);
            TextView textView17 = findViewById(R.id.row6MAC);
            textView17.setText(six.BSSID + "/ Floor:" + six.floor);
            TextView textView18 = findViewById(R.id.row6Strength);
            textView18.setText(six.distance);
        }

        if(signals.size() > 6) {
            WifiSignal seven = signals.get(6);
            TextView textView19 = findViewById(R.id.row7Name);
            textView19.setText(seven.SSID);
            TextView textView20 = findViewById(R.id.row7MAC);
            textView20.setText(seven.BSSID + "/ Floor:" + seven.floor);
            TextView textView21 = findViewById(R.id.row7Strength);
            textView21.setText(seven.distance);
        }

        if(signals.size() > 7) {
            WifiSignal eight = signals.get(7);
            TextView textView22 = findViewById(R.id.row8Name);
            textView22.setText(eight.SSID);
            TextView textView23 = findViewById(R.id.row8MAC);
            textView23.setText(eight.BSSID + "/ Floor:" + eight.floor);
            TextView textView24 = findViewById(R.id.row8Strength);
            textView24.setText(eight.distance);
        }

        if(signals.size() > 8) {
            WifiSignal nine = signals.get(8);
            TextView textView25 = findViewById(R.id.row9Name);
            textView25.setText(nine.SSID);
            TextView textView26 = findViewById(R.id.row9MAC);
            textView26.setText(nine.BSSID + "/ Floor:" + nine.floor);
            TextView textView27 = findViewById(R.id.row9Strength);
            textView27.setText(nine.distance);
        }

        if(signals.size() > 9) {
            WifiSignal ten = signals.get(9);
            TextView textView28 = findViewById(R.id.row10Name);
            textView28.setText(ten.SSID);
            TextView textView29 = findViewById(R.id.row10MAC);
            textView29.setText(ten.BSSID + "/ Floor:" + ten.floor);
            TextView textView30 = findViewById(R.id.row10Strength);
            textView30.setText(ten.distance);
        }

    }

}
