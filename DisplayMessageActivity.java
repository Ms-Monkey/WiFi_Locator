package com.example.sorensjone.test;

import android.content.Intent;
import android.net.wifi.ScanResult;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        ArrayList<WifiSignal> unorderedSignals = MainActivity.WifiOutput;
        ArrayList<WifiSignal> signals = new ArrayList<WifiSignal>();

        // Capture the layout's TextView and set the string as its text

        /*for(ScanResult temp: test) {
            TextView textView = findViewById(R.id.row);
            textView.setText(temp.SSID);
        }*/
        //for(int x = 0; x < myTable.getChildCount(); x++){
        /*TableLayout table = (TableLayout) findViewById(R.id.myTable);
        TextView textView = findViewById(R.id.row3MAC);
        View row = table.getChildAt(0);
        textView.setText("" + row);*/
        //}


        WifiSignal highest;
        for (WifiSignal x: unorderedSignals){
            highest = x;
            if (signals.contains(x)){
                continue;
            }

            for (WifiSignal y: unorderedSignals){
                //If the value is greater than y's and it isn't in signals already
                if (highest.level < y.level && !signals.contains(y)){
                    highest = y;
                } else {
                    continue;
                }
            }
            signals.add(highest);
        }

        WifiSignal one = signals.get(0);
        TextView textView1 = findViewById(R.id.row1Name);
        textView1.setText(one.SSID);
        TextView textView2 = findViewById(R.id.row1MAC);
        textView2.setText(one.BSSID);
        TextView textView3 = findViewById(R.id.row1Strength);
        textView3.setText(one.distance);

        WifiSignal two = signals.get(1);
        TextView textView4 = findViewById(R.id.row2Name);
        textView4.setText(two.SSID);
        TextView textView5 = findViewById(R.id.row2MAC);
        textView5.setText(two.BSSID);
        TextView textView6 = findViewById(R.id.row2Strength);
        textView6.setText(two.distance);

        WifiSignal three = signals.get(2);
        TextView textView7 = findViewById(R.id.row3Name);
        textView7.setText(three.SSID);
        TextView textView8 = findViewById(R.id.row3MAC);
        textView8.setText(three.BSSID);
        TextView textView9 = findViewById(R.id.row3Strength);
        textView9.setText(three.distance);

        WifiSignal four = signals.get(3);
        TextView textView10 = findViewById(R.id.row4Name);
        textView10.setText(four.SSID);
        TextView textView11 = findViewById(R.id.row4MAC);
        textView11.setText(four.BSSID);
        TextView textView12 = findViewById(R.id.row4Strength);
        textView12.setText(four.distance);

        WifiSignal five = signals.get(4);
        TextView textView13 = findViewById(R.id.row5Name);
        textView13.setText(five.SSID);
        TextView textView14 = findViewById(R.id.row5MAC);
        textView14.setText(five.BSSID);
        TextView textView15 = findViewById(R.id.row5Strength);
        textView15.setText(five.distance);

        /*WifiSignal six = signals.get(5);
        TextView textView16 = findViewById(R.id.row6Name);
        textView16.setText(six.SSID);
        TextView textView17 = findViewById(R.id.row6MAC);
        textView17.setText("" + six.BSSID + " / " + six.distance);
        TextView textView18 = findViewById(R.id.row6Strength);
        textView18.setText("" + six.level);

        WifiSignal seven = signals.get(6);
        TextView textView19 = findViewById(R.id.row7Name);
        textView19.setText(seven.SSID);
        TextView textView20 = findViewById(R.id.row7MAC);
        textView20.setText("" + seven.BSSID + " / " + seven.frequency);
        TextView textView21 = findViewById(R.id.row7Strength);
        textView21.setText("" + seven.level);

        WifiSignal eight = signals.get(7);
        TextView textView22 = findViewById(R.id.row8Name);
        textView22.setText(eight.SSID);
        TextView textView23 = findViewById(R.id.row8MAC);
        textView23.setText("" + eight.BSSID + " / " + eight.frequency);
        TextView textView24 = findViewById(R.id.row8Strength);
        textView24.setText("" + eight.level);

        WifiSignal nine = signals.get(8);
        TextView textView25 = findViewById(R.id.row9Name);
        textView25.setText(nine.SSID);
        TextView textView26 = findViewById(R.id.row9MAC);
        textView26.setText("" + nine.BSSID + " / " + nine.frequency);
        TextView textView27 = findViewById(R.id.row9Strength);
        textView27.setText("" + nine.level);

        WifiSignal ten = signals.get(9);
        TextView textView28 = findViewById(R.id.row10Name);
        textView28.setText(ten.SSID);
        TextView textView29 = findViewById(R.id.row10MAC);
        textView29.setText("" + ten.BSSID + " / " + ten.frequency);
        TextView textView30 = findViewById(R.id.row10Strength);
        textView30.setText("" + ten.level);*/



        //print length of scan results
        //For each through the table rows
        //Set each column in said row with applicable map vals

    }

}
