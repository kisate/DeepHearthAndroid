package com.example.dima.deephearth;

import android.util.Log;
import android.util.Pair;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Dima on 09.05.2017.
 */

public class RandomSelector<T>{
    private LinkedList<Pair<T, Integer>> list;
    private int totalSum = 0;
    Random random;
    public RandomSelector(LinkedList<Pair<T, Integer>> list) {
        this.list = list;
        random = new Random();
        for (Pair<T, Integer> p :
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
