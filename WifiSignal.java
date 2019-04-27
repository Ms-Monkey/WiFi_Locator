package com.example.sorensjone.test;

import android.content.Context;
import android.net.wifi.WifiManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static android.support.v4.content.ContextCompat.getSystemService;

public class WifiSignal{
    public String SSID, BSSID;
    public int level, frequency, floor, distanceInt, x, y;
    public String distance;
    Router router;

    public WifiSignal(String name, String mac, int strength, int frequency, int floor, int x, int y, Router r) {
        this.SSID = name;
        this.BSSID = mac;
        this.level = strength;
        this.frequency = frequency;
        this.floor = floor;
        this.x = x;
        this.y = y;
        this.router = router;

        //https://stackoverflow.com/questions/11217674/how-to-calculate-distance-from-wifi-router-using-signal-strength
        double exp = (27.55 - (20 * Math.log10(frequency)) + Math.abs(strength)) / 20.0;
        exp = Math.pow(10.0, exp);
        distanceInt = (int) exp;
        this.distance = Integer.toString(distanceInt) + "m";
    }

}
