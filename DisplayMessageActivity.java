package com.example.sorensjone.test;

import android.content.Intent;
import android.net.wifi.ScanResult;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        List<ScanResult> test = MainActivity.results;

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

        ScanResult one = test.get(0);
        TextView textView1 = findViewById(R.id.row1Name);
        textView1.setText(one.SSID);
        TextView textView2 = findViewById(R.id.row1MAC);
        textView2.setText("" + one.BSSID);
        TextView textView3 = findViewById(R.id.row1Strength);
        textView3.setText("" + one.level);

        ScanResult two = test.get(1);
        TextView textView4 = findViewById(R.id.row2Name);
        textView4.setText(two.SSID);
        TextView textView5 = findViewById(R.id.row2MAC);
        textView5.setText("" + two.BSSID);
        TextView textView6 = findViewById(R.id.row2Strength);
        textView6.setText("" + two.level);

        ScanResult three = test.get(2);
        TextView textView7 = findViewById(R.id.row3Name);
        textView7.setText(three.SSID);
        TextView textView8 = findViewById(R.id.row3MAC);
        textView8.setText("" + three.BSSID);
        TextView textView9 = findViewById(R.id.row3Strength);
        textView9.setText("" + three.level);

        ScanResult four = test.get(3);
        TextView textView10 = findViewById(R.id.row4Name);
        textView10.setText(four.SSID);
        TextView textView11 = findViewById(R.id.row4MAC);
        textView11.setText("" + four.BSSID);
        TextView textView12 = findViewById(R.id.row4Strength);
        textView12.setText("" + four.level);

        ScanResult five = test.get(4);
        TextView textView13 = findViewById(R.id.row5Name);
        textView13.setText(five.SSID);
        TextView textView14 = findViewById(R.id.row5MAC);
        textView14.setText("" + five.BSSID);
        TextView textView15 = findViewById(R.id.row5Strength);
        textView15.setText("" + five.level);

        ScanResult six = test.get(5);
        TextView textView16 = findViewById(R.id.row6Name);
        textView16.setText(six.SSID);
        TextView textView17 = findViewById(R.id.row6MAC);
        textView17.setText("" + six.BSSID);
        TextView textView18 = findViewById(R.id.row6Strength);
        textView18.setText("" + six.level);

        ScanResult seven = test.get(6);
        TextView textView19 = findViewById(R.id.row7Name);
        textView19.setText(seven.SSID);
        TextView textView20 = findViewById(R.id.row7MAC);
        textView20.setText("" + seven.BSSID);
        TextView textView21 = findViewById(R.id.row7Strength);
        textView21.setText("" + seven.level);

        ScanResult eight = test.get(7);
        TextView textView22 = findViewById(R.id.row8Name);
        textView22.setText(eight.SSID);
        TextView textView23 = findViewById(R.id.row8MAC);
        textView23.setText("" + eight.BSSID);
        TextView textView24 = findViewById(R.id.row8Strength);
        textView24.setText("" + eight.level);

        ScanResult nine = test.get(8);
        TextView textView25 = findViewById(R.id.row9Name);
        textView25.setText(nine.SSID);
        TextView textView26 = findViewById(R.id.row9MAC);
        textView26.setText("" + nine.BSSID);
        TextView textView27 = findViewById(R.id.row9Strength);
        textView27.setText("" + nine.level);

        ScanResult ten = test.get(9);
        TextView textView28 = findViewById(R.id.row10Name);
        textView28.setText(ten.SSID);
        TextView textView29 = findViewById(R.id.row10MAC);
        textView29.setText("" + ten.BSSID);
        TextView textView30 = findViewById(R.id.row10Strength);
        textView30.setText("" + ten.level);



        //print length of scan results
        //For each through the table rows
        //Set each column in said row with applicable map vals

    }

}
