package com.example.dima.deephearth.FromIdea;

import android.util.Log;

import java.util.Random;

/**
 * Created by Dima on 28.03.2017.
 */
public class Probability {
    public float success, all;
    public Probability(int success, int all) {
        this.success = success;
        this.all = all;
    }

    public boolean check() {
        Log.d("Debug", "" + (success/all));
        return success/all > Math.random();
    }
}
