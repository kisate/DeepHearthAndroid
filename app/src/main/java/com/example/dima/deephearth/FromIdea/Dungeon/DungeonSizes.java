package com.example.dima.deephearth.FromIdea.Dungeon;

import android.graphics.Color;

/**
 * Created by Dima on 30.05.2017.
 */

public enum DungeonSizes {
    Tiny(Color.GRAY, 10), Small(Color.GRAY, 30), Normal(Color.WHITE, 50), Big(Color.YELLOW, 70), Large(Color.RED, 90), Huge(Color.MAGENTA, 120);

    public int color;
    int size;

    DungeonSizes(int color, int size) {
        this.color = color;
        this.size = size;
    }

    public int getSize() {
        return (int)(size*(Math.random() + 0.8));
    }
}
