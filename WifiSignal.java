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
    public int level, frequency;
    public String distance;

    public WifiSignal(String name, String mac, int strength, int frequency) {
        this.SSID = name;
        this.BSSID = mac;
        this.level = strength;
        this.frequency = frequency;

        double exp = (27.55 - (20 * Math.log10(frequency)) + Math.abs(strength)) / 20.0;
        exp = Math.pow(10.0, exp);
        int test = (int) exp;
        this.distance = Integer.toString(test) + "m";
    }

}
