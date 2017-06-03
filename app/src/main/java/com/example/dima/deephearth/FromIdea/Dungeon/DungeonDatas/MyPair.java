package com.example.dima.deephearth.FromIdea.Dungeon.DungeonDatas;

import android.util.Pair;

import java.io.Serializable;

/**
 * Created by Dima on 03.06.2017.
 */

public class MyPair<T1, T2> implements Serializable{
    public T1 first;
    public T2 second;
    public MyPair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
}
