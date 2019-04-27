package com.example.sorensjone.test;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
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

import static java.lang.System.exit;
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

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);

        buttonScan = findViewById(R.id.scanBtn);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                TextView tv1 = (TextView) findViewById(R.id.wifiOutput);
                determine_visibility();
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
        Toast.makeText(this, "Good to go", Toast.LENGTH_LONG).show();
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
            draw_routers(1, Floor1);
        } else if (floor == 2){
            draw_routers(2, Floor2);
        } else if (floor == 3){
            draw_routers(3, Floor3);
        } else if (floor == 4) {
            draw_routers(4, Floor4);
        }
    }

    public void draw_routers(int floor_number, ArrayList<Router> floor){
        //Only draw the routers that it picks up, different colour for different floor routers
        CanvasView test = new CanvasView(this, floor_number, floor);
        setContentView(test);
    }

    //Thanks to this: http://www.coderzheaven.com/2016/12/17/introduction-to-android-canvas-simple-example/
    private class CanvasView extends View {
        private ArrayList<Router> routers;
        private int floor;
        public CanvasView(Context context, int floor_number, ArrayList<Router> floor) {
            super(context);
            this.routers = floor;
            this.floor = floor_number;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int radius = 15, offsetX = 0, offsetY = 0;
            String floor_string;
            Paint paint = new Paint();

            // make the entire canvas white
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);

            if (floor == 1){
                //https://stackoverflow.com/questions/21082184/canvas-drawbitmap-is-not-drawing-anything
                Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.cotton1);
                Rect source = new Rect(-20, 0, 950, 1435);
                canvas.drawBitmap(bMap, null, source, paint);
                floor_string = "You are on the first floor, congratulations";
                offsetX = 135;
                offsetY = 55;
            } else if (floor == 2){
                Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.cotton2);
                Rect source = new Rect(0, 0, 900, 1400);
                canvas.drawBitmap(bMap, null, source, paint);
                floor_string = "You are on the second floor, congratulations";
                offsetX = 65;
                offsetY = 30;
            } else if (floor == 3){
                Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.cotton3);
                Rect source = new Rect(0, 0, 930, 1410);
                canvas.drawBitmap(bMap, null, source, paint);
                floor_string = "You are on the third floor, congratulations";
                offsetX = 130;
                offsetY = 40;
            } else if (floor == 4){
                Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.cotton4);
                Rect source = new Rect(-30, 0, 520, 1390);
                canvas.drawBitmap(bMap, null, source, paint);
                floor_string = "You are on the fourth floor, congratulations";
                offsetX = 160;
                offsetY = 30;
            } else {
                floor_string = "Unable to find floor, please check you are in the correct dimension and try again";
            }

            paint.setAntiAlias(true);
            paint.setColor(Color.BLUE);

            //WARNING: 15 pixels per meter
            for (Router x: routers){
                for(WifiSignal y: signals){
                    if(x.BSSID.equals(y.BSSID)){
                        paint.setColor(Color.CYAN);
                        break;
                    } else {
                        paint.setColor(Color.RED);
                    }
                }
                canvas.drawCircle(offsetX + (x.y * 15), offsetY + (x.x * 15), radius, paint);
            }

            for (Router a: routers){
                if (signals.get(0).BSSID.equals(a.BSSID)){
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(8);
                    canvas.drawCircle(offsetX + (a.y * 15), offsetY + (a.x * 15), (signals.get(0).distanceInt * 15), paint);
                }

            }

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.CYAN);
            paint.setTextSize(50);
            canvas.drawText(floor_string, 70, 1470, paint);

            /*These are for calibration
            canvas.drawCircle(28, 1025, radius, paint);
            canvas.drawCircle(28, 1325, radius, paint);
            canvas.drawCircle(328, 1325, radius, paint);*/

            if (Build.VERSION.SDK_INT != Build.VERSION_CODES.M)
                canvas.restore();
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
        Floor1.clear();
        Floor2.clear();
        Floor3.clear();
        Floor4.clear();
        Floor1.add(new Router(1, 38, 1, "CO159", "70:70:8b:d3:5e:60"));
        Floor1.add(new Router(1, 38, 1, "CO159", "70:70:8b:d3:5e:61"));

        Floor1.add(new Router(7, 39, 1, "CO151", "70:70:8b:be:01:af"));
        Floor1.add(new Router(7, 39, 1, "CO151", "70:70:8b:be:01:a0"));
        Floor1.add(new Router(7, 39, 1, "CO151", "70:70:8b:be:01:a1"));
        Floor1.add(new Router(7, 39, 1, "CO151", "70:70:8b:be:01:a6"));

        Floor1.add(new Router(22, 40, 1, "CO143", "70:70:8b:d3:5b:6e"));
        Floor1.add(new Router(22, 40, 1, "CO143", "70:70:8b:d3:5b:6f"));
        Floor1.add(new Router(22, 40, 1, "CO143", "70:70:8b:d3:5b:60"));
        Floor1.add(new Router(22, 40, 1, "CO143", "70:70:8b:d3:5b:61"));

        Floor1.add(new Router(30, 38, 1, "CO141", "70:70:8b:ce:29:40"));
        Floor1.add(new Router(30, 38, 1, "CO141", "70:70:8b:ce:29:41"));
        Floor1.add(new Router(30, 38, 1, "CO141", "70:70:8b:ce:29:46"));

        Floor1.add(new Router(44, 38, 1, "CO121", "00:2c:c8:cc:30:80"));
        Floor1.add(new Router(44, 38, 1, "CO121", "00:2c:c8:cc:30:81"));
        Floor1.add(new Router(44, 38, 1, "CO121", "00:2c:c8:cc:30:86"));

        Floor1.add(new Router(67, 38, 1, "CO110", "70:70:8b:be:0c:89"));
        Floor1.add(new Router(67, 38, 1, "CO110", "70:70:8b:be:0c:8e"));
        Floor1.add(new Router(67, 38, 1, "CO110", "70:70:8b:be:0c:8f"));
        Floor1.add(new Router(67, 38, 1, "CO110", "70:70:8b:be:0c:80"));

        Floor1.add(new Router(80, 10, 1, "CO105", "b0:8b:cf:27:7b:c0"));
        Floor1.add(new Router(80, 10, 1, "CO105", "b0:8b:cf:27:7b:co"));
        Floor1.add(new Router(80, 10, 1, "CO105", "b0:8b:cf:27:7b:c1"));
        Floor1.add(new Router(80, 10, 1, "CO105", "b0:8b:cf:27:7b:ce"));//5G
        Floor1.add(new Router(80, 10, 1, "CO105", "b0:8b:cf:27:7b:cf"));

        Floor1.add(new Router(15, 23, 1, "Arthurs1", "74:88:bb:c6:b8:ae"));
        Floor1.add(new Router(15, 23, 1, "Arthurs1", "74:88:bb:c6:b8:af"));
        Floor1.add(new Router(15, 23, 1, "Arthurs1", "74:88:bb:c6:b8:a9"));

        //Unsure about this
        Floor1.add(new Router(50, 36, 1, "CO122", "70:6d:15:09:c3:6e"));
        Floor1.add(new Router(50, 36, 1, "CO122", "70:6d:15:09:c3:69"));
        Floor1.add(new Router(50, 36, 1, "CO122", "70:6d:15:09:c3:60"));
        Floor1.add(new Router(50, 36, 1, "CO122", "70:6d:15:09:c3:66"));

        Floor1.add(new Router(16, 26, 1, "Arthurs2", "b0:8b:cf:35:31:00"));
        Floor1.add(new Router(16, 26, 1, "Arthurs2", "b0:8b:cf:35:31:01"));

        Floor1.add(new Router(9, 24, 1, "Arthurs3", "b0:8b:cf:0a:36:0e"));
        Floor1.add(new Router(9, 24, 1, "Arthurs3", "b0:8b:cf:0a:36:0f"));

        Floor1.add(new Router(52, 32, 1, "CO122", "54:92:74:d2:34:70"));
        Floor1.add(new Router(52, 32, 1, "CO122", "54:92:74:d2:34:71"));

        Floor1.add(new Router(52, 25, 1, "COLT122", "e8:65:49:40:0c:10"));
        Floor1.add(new Router(52, 25, 1, "COLT122", "e8:65:49:40:0c:11"));

        Floor1.add(new Router(47, 6, 1, "CO126", "bc:26:c7:40:c0:00"));
        Floor1.add(new Router(47, 6, 1, "CO126", "bc:26:c7:40:c0:01"));


        Floor1.add(new Router(37, 6, 1, "CO132", "b0:8b:cf:35:2f:c0"));
        Floor1.add(new Router(37, 6, 1, "CO132", "b0:8b:cf:35:2f:c1")); //Not 100% sure
        Floor1.add(new Router(37, 6, 1, "CO132", "b0:8b:cf:35:2f:ce"));
        Floor1.add(new Router(37, 6, 1, "CO132", "b0:8b:cf:35:2f:cf"));
        Floor1.add(new Router(37, 6, 1, "CO132", "b0:8b:cf:35:2f:c6"));

        Floor1.add(new Router(-5, 12, 1, "CO154", "00:92:ee:d3:80:ae"));
        Floor1.add(new Router(-5, 12, 1, "CO154", "00:92:ee:d3:80:af"));

        //Create a bunch of routers, add them to their respective floor arrays
        Floor2.add(new Router(6, 7, 2, "CO258", "70:6d:15:3B:91:80"));  //MIGHT BE 5b not 100%
        Floor2.add(new Router(6, 7, 2, "CO258", "70:6d:15:3B:91:81"));

        Floor2.add(new Router(28, 25, 2, "CO243", "70:6d:15:28:83:4e"));
        Floor2.add(new Router(28, 25, 2, "CO243", "70:6d:15:28:83:4f"));

        Floor2.add(new Router(13, 43, 2, "CO246", "70:6d:15:40:cd:20"));
        Floor2.add(new Router(13, 43, 2, "CO246", "70:6d:15:40:cd:21"));

        Floor2.add(new Router(21, 37, 2, "CO242", "70:6d:15:40:ca:a0"));
        Floor2.add(new Router(21, 37, 2, "CO242", "70:6d:15:40:ca:a1"));

        Floor2.add(new Router(44, 30, 2, "CO219", "70:6d:15:40:a3:8e"));
        Floor2.add(new Router(44, 30, 2, "CO219", "70:6d:15:40:a3:8f"));
        Floor2.add(new Router(44, 30, 2, "CO219", "70:6d:15:40:a3:81"));

        Floor2.add(new Router(58, 30, 2, "CO215", "70:6d:15:40:65:c0"));
        Floor2.add(new Router(58, 30, 2, "CO215", "70:6d:15:40:65:c1"));

        Floor2.add(new Router(86, 10, 2, "CO200", "70:6d:15:40:4d:00"));
        Floor2.add(new Router(86, 10, 2, "CO200", "70:6d:15:40:4d:01"));

        Floor2.add(new Router(47, 6, 2, "CO225", "70:b3:17:d5:37:e0"));
        Floor2.add(new Router(47, 6, 2, "CO225", "70:b3:17:d5:37:e1"));
        Floor2.add(new Router(47, 6, 2, "CO225", "70:b3:17:d5:37:e6")); //Not 100% sure

        Floor2.add(new Router(43, 10, 2, "CO228", "70:b3:17:d5:34:40"));
        Floor2.add(new Router(43, 10, 2, "CO228", "70:b3:17:d5:34:41"));
        Floor2.add(new Router(43, 10, 2, "CO228", "70:b3:17:d5:34:46"));

        Floor2.add(new Router(30, 7, 2, "CO232", "70:6d:15:40:56:0e"));
        Floor2.add(new Router(30, 7, 2, "CO232", "70:6d:15:40:56:0f"));

        Floor2.add(new Router(52, 11, 2, "CO221", "00:d7:8f:f3:95:80"));
        Floor2.add(new Router(52, 11, 2, "CO221", "00:d7:8f:f3:95:81"));
        Floor2.add(new Router(52, 11, 2, "CO221", "00:d7:8f:f3:95:86"));
        Floor2.add(new Router(52, 11, 2, "CO221", "00:d7:8f:f3:95:8e"));
        Floor2.add(new Router(52, 11, 2, "CO221", "00:d7:8f:f3:95:8f"));

        Floor3.add(new Router(10, 8, 3, "CO337", "70:6d:15:36:b6:2e"));
        Floor3.add(new Router(10, 8, 3, "CO337", "70:6d:15:36:b6:2f"));
        Floor3.add(new Router(10, 8, 3, "CO337", "70:6d:15:36:b6:20"));
        Floor3.add(new Router(10, 8, 3, "CO337", "70:6d:15:36:b6:22"));

        Floor3.add(new Router(2, 10, 3, "CO365", "bc:26:c7:94:91:40"));
        Floor3.add(new Router(2, 10, 3, "CO365", "bc:26:c7:94:91:41"));
        Floor3.add(new Router(2, 10, 3, "CO365", "bc:26:c7:94:91:46"));

        Floor3.add(new Router(29, 26, 3, "CO329", "70:6d:15:16:6c:20"));
        Floor3.add(new Router(29, 26, 3, "CO329", "70:6d:15:16:6c:21"));

        Floor3.add(new Router(11, 27, 3, "CO352", "70:6d:15:40:35:c0"));
        Floor3.add(new Router(11, 27, 3, "CO352", "70:6d:15:40:35:c1"));
        Floor3.add(new Router(11, 27, 3, "CO352", "70:6d:15:40:35:ce"));//5G
        Floor3.add(new Router(11, 27, 3, "CO352", "70:6d:15:40:35:cf"));
        Floor3.add(new Router(11, 27, 3, "CO352", "70:6d:15:40:35:c6"));

        Floor3.add(new Router(27, 39, 3, "CO334", "70:6d:15:48:15:2e"));
        Floor3.add(new Router(27, 39, 3, "CO334", "70:6d:15:48:15:2f"));
        Floor3.add(new Router(27, 39, 3, "CO334", "70:6d:15:48:15:20"));

        Floor3.add(new Router(42, 5, 3, "CO319", "70:6d:15:3b:a2:60"));
        Floor3.add(new Router(42, 5, 3, "CO319", "70:6d:15:3b:a2:61"));
        Floor3.add(new Router(42, 5, 3, "CO319", "70:6d:15:3b:a2:6e"));//5G
        Floor3.add(new Router(42, 5, 3, "CO319", "70:6d:15:3b:a2:6f"));

        Floor3.add(new Router(42, 5, 3, "CO319", "70:6d:15:3b:a2:66"));//Not 100% on this

        Floor3.add(new Router(58, 9, 3, "CO310", "e8:65:49:10:00:de"));
        Floor3.add(new Router(58, 9, 3, "CO310", "e8:65:49:10:00:df"));
        Floor3.add(new Router(58, 9, 3, "CO310", "e8:65:49:10:00:d0"));
        Floor3.add(new Router(58, 9, 3, "CO310", "e8:65:49:10:00:d1"));
        Floor3.add(new Router(58, 9, 3, "CO310", "e8:65:49:10:00:d9"));

        Floor3.add(new Router(80, 11, 3, "CO304", "70:6d:15:05:be:40"));
        Floor3.add(new Router(80, 11, 3, "CO304", "70:6d:15:05:be:41"));
        Floor3.add(new Router(80, 11, 3, "CO304", "70:6d:15:05:be:46"));

        Floor4.add(new Router(76, 8, 4, "CO407", "70:6d:15:40:c9:40"));
        Floor4.add(new Router(76, 8, 4, "CO407", "70:6d:15:40:c9:41"));
        Floor4.add(new Router(76, 8, 4, "CO407", "70:6d:15:40:c9:46"));
        Floor4.add(new Router(76, 8, 4, "CO407", "70:6d:15:40:c9:49"));

        Floor4.add(new Router(52, 14, 4, "CO418", "70:6d:15:35:42:00")); //2.462G
        Floor4.add(new Router(52, 14, 4, "CO418", "70:6d:15:35:42:01"));

        Floor4.add(new Router(30, 15, 4, "CO429", "70:6d:15:35:32:e1"));
        Floor4.add(new Router(30, 15, 4, "CO429", "70:6d:15:35:32:e0"));//2.412G
        Floor4.add(new Router(30, 15, 4, "CO429", "70:6d:15:35:32:ee"));//5.745G
        Floor4.add(new Router(30, 15, 4, "CO429", "70:6d:15:35:32:ef"));

        Floor4.add(new Router(11, 13, 4, "CO435", "70:6d:15:40:cd:60"));//2.412G
        Floor4.add(new Router(11, 13, 4, "CO435", "70:6d:15:40:cd:61"));
        Floor4.add(new Router(11, 13, 4, "CO435", "70:6d:15:40:cd:66"));

    }



}
