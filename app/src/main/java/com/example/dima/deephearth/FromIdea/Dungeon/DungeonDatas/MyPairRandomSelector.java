package com.example.dima.deephearth.FromIdea.Dungeon.DungeonDatas;

import android.util.Log;
import android.util.Pair;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Dima on 03.06.2017.
 */

public class MyPairRandomSelector<T> {
    private LinkedList<MyPair<T, Integer>> list;
    private int totalSum = 0;
    Random random;
    public MyPairRandomSelector(LinkedList<MyPair<T, Integer>> list) {
        this.list = list;
        random = new Random();
        for (MyPair<T, Integer> p :
                list) {
            totalSum+= p.second;
        }
    }

    public T getRandomElement(){
        int index = random.nextInt(totalSum + 1);
        int sum = 0, i = 0;
        while (sum < index) {
            sum+= list.get(i++).second;
        }
        Log.d("Debug", "" + (i-1));
        return list.get(Math.max(0, i-1)).first;
    }
}
