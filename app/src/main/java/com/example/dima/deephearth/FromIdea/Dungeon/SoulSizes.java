package com.example.dima.deephearth.FromIdea.Dungeon;

/**
 * Created by Dima on 14.05.2017.
 */

public enum SoulSizes {
    Weak(10, 15), Common(50, 90), Strong(100, 150), Great(300, 500), Hero(1000, 2000), Legendary(5000, 10000);
    int min, max, value;

    SoulSizes(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getValue() {
        value = (int)((max-min)*Math.random()) + min;
        return value;
    }
}
