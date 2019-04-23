package com.example.sorensjone.test;

import android.content.Context;
import android.net.wifi.WifiManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static android.support.v4.content.ContextCompat.getSystemService;

public class WifiSignal{
    public String SSID;
    public String BSSID;
    public int level;

    public WifiSignal(String name, String mac, int strength) {
        SSID = name;
        BSSID = mac;
        level = strength;
    }

}
