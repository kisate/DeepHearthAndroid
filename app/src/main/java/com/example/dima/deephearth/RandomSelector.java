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

    public RandomSelector(LinkedList<Pair<T, Integer>> list) {
        this.list = list;
        Collections.sort(list, new Comparator<Pair<T, Integer>>() {
            @Override
            public int compare(Pair<T, Integer> o1, Pair<T, Integer> o2) {
                return o1.second - o2.second;
            }
        });
        for (Pair<T, Integer> p :
                list) {
            totalSum+= p.second;
        }
    }

    public T getRandomElement(){
        int index = (int)(Math.random()*totalSum);
        int sum = 0, i = 0;
        while (sum < index) {
            sum+= list.get(i++).second;
        }

        return list.get(Math.max(0, i-1)).first;
    }
}
